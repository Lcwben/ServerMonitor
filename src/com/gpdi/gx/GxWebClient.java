package com.gpdi.gx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.gpdi.common.util.DBUtil;
import com.gpdi.common.util.SoapUtil;
import com.gpdi.common.util.StatusUtil;
import com.gpdi.gx.adapter.InnerAdapterImpl;

public class GxWebClient {
	
	private static Logger log = Logger.getLogger(GxWebClient.class);

	private ClientInfo clientInfo;
	private ClientInfo messageServiceInfo;
	private CheckInfo checkInfo;
	public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public GxWebClient(ClientInfo clientInfo,ClientInfo messageServiceInfo,CheckInfo checkInfo){
		this.clientInfo = clientInfo;
		this.messageServiceInfo = messageServiceInfo;
		this.checkInfo = checkInfo;
	}
	public GxWebClient(ClientInfo messageServiceInfo){
		this.clientInfo = null;
		this.messageServiceInfo = messageServiceInfo;
		this.checkInfo = null;
	}
	
	public void check(List<Boolean> flagList){
//		System.out.println("========check:"+clientInfo.getEndPoint());
		log.warn("调用的服务："+clientInfo.getEndPoint());
		send2InforesService(flagList);
		sendMobilMessage(flagList);
		
	}
	
	
	public void send2InforesService(List<Boolean> flagList){
		boolean flag = true;
		try {
			String sentSOAPMsg = SoapUtil.sentSOAPMsg(clientInfo.getXmlInfo(), clientInfo.getEndPoint());
//			System.out.println(sentSOAPMsg);
			log.warn("SOAPMsg信息："+sentSOAPMsg);
			flag = checkSendSuccess(sentSOAPMsg);
			addSucceedFlag(flag,flagList);
		} catch (Exception e) {
			e.printStackTrace();
			Throwable cause = e.getCause();
			System.out.println(e.getLocalizedMessage());
			log.error("soap连接错误:"+e.getLocalizedMessage());
			flag = false;
			addSucceedFlag(flag,flagList);
		} finally {
			//记录监控信息表监控记录表RMGD.BAS_WECHATALERT
			StatusUtil.updateRecord(flag, clientInfo.getEndPoint());
		}
	}
	
	
	public synchronized boolean isSendMessage(List<Boolean> flagList){
		int num = 0;
		boolean isSend = false;
		for(boolean flag : flagList){
			if(!flag){
				num++;
			}
		}
		log.warn("服务调用检验的错误次数"+num);
		if(num>=checkInfo.getErrorCount()){
			
			//判断表是否已发送短信标志的值与发短信后的恢复周期值
			//（恢复周期值>0则已发短信，且暂停短信发送周期未走完）
			if(this.clientInfo.getHasSendMsg()==true && this.clientInfo.getRecoverCount()>0) {
				
				isSend = false;
				this.clientInfo.setRecoverCount(this.clientInfo.getRecoverCount()-1);					

				log.warn("暂停短信发送周期还剩: "+this.clientInfo.getRecoverCount());
			} else {
				//设置是否已发送短信标志为true
				this.clientInfo.setHasSendMsg(true);
				this.clientInfo.setRecoverCount(this.clientInfo.getRecoverCountReset());
				isSend = true;
				
			}
//			return true;
		}
		return isSend;
	}
	
	
	/**
	 * 发送短信接口的方法
	 * 
	 * @param flagList
	 * @return
	 */
	public String sendMobilMessage(List<Boolean> flagList){
		
		if(!isSendMessage(flagList)){
			return null;
		}
		
		String sentSOAPMsg = null;
		String phoneNums = messageServiceInfo.getPhoneNums();
		String[] phoneArr = phoneNums.split(",");
		for (int i = 0; i < phoneArr.length; i++) {
			
			try {
				String xmlInfo = messageServiceInfo.getXmlInfo();
				xmlInfo = xmlInfo.replace("#0", phoneArr[i]);	
				xmlInfo = xmlInfo.replace("#1", "one");
				String flag = System.currentTimeMillis()+"";
				flag = flag.substring(flag.length()-8);
				xmlInfo = xmlInfo.replace("#2", flag);
				xmlInfo = xmlInfo.replace("#3", dateFormat.format(new Date(System.currentTimeMillis()))+"  调用：["+clientInfo.getEndPoint()+"]异常！");
				log.warn("服务调用异常信息:"+xmlInfo);
				log.warn("开始调用短信服务进行通知.... ");
				InnerAdapterImpl innerAdapterImpl = SmsService.getInnerAdapterImpl(messageServiceInfo);
				sentSOAPMsg = innerAdapterImpl.process("SMS_SEND_MESSAGE_REMEDY", "POMS", xmlInfo);
				log.warn("短信服务调用结束:"+sentSOAPMsg);
			
			} catch (Exception e) {
				log.error("短信服务发送异常:"+e.getMessage());
				e.printStackTrace();
			}
			
		}
		return sentSOAPMsg;
		
	}
	
	/**
	 * 发送短信接口的方法
	 * 
	 * @param errorMsg
	 * @return
	 */
	public String sendMobilMessage(String errorMsg){
		
		String sentSOAPMsg = null;
		String phoneNums = messageServiceInfo.getPhoneNums();
		String[] phoneArr = phoneNums.split(",");
		for (int i = 0; i < phoneArr.length; i++) {
			
			try {
				String xmlInfo = messageServiceInfo.getXmlInfo();
				xmlInfo = xmlInfo.replace("#0", phoneArr[i]);	
				xmlInfo = xmlInfo.replace("#1", "one");
				String flag = System.currentTimeMillis()+"";
				flag = flag.substring(flag.length()-8);
				xmlInfo = xmlInfo.replace("#2", flag);
				xmlInfo = xmlInfo.replace("#3", dateFormat.format(new Date(System.currentTimeMillis()))+"  调用：["+errorMsg+"]异常！");
				log.warn("服务调用异常信息:"+xmlInfo);
				log.warn("开始调用短信服务进行通知.... ");
				InnerAdapterImpl innerAdapterImpl = SmsService.getInnerAdapterImpl(messageServiceInfo);
				sentSOAPMsg = innerAdapterImpl.process("SMS_SEND_MESSAGE_REMEDY", "POMS", xmlInfo);
				log.warn("短信服务调用结束:"+sentSOAPMsg);
			
			} catch (Exception e) {
				log.error("短信服务发送异常:"+e.getMessage());
				e.printStackTrace();
			}
			
		}
		return sentSOAPMsg;
		
	}
	
	public boolean checkSendSuccess(String sentSOAPMsg){
		return sentSOAPMsg.indexOf("response")!=-1;
	}
	
	
	public synchronized void addSucceedFlag(boolean flag,List<Boolean> flagList){
		if(flagList.size()==checkInfo.getCheckedCount()){
			flagList.remove(0);
		}
		flagList.add(flag);
//		System.out.println("加入次数："+flagList.size());
		log.warn("调用的次数"+flagList.size());
		
	}
	
	
	
}
