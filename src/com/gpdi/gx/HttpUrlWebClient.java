package com.gpdi.gx;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.gpdi.common.util.StatusUtil;
import com.gpdi.gx.adapter.InnerAdapterImpl;

public class HttpUrlWebClient {

	private static Logger logger = Logger.getLogger(HttpUrlWebClient.class);
	
	private HttpUrlStatusInfo httpUrlStatusInfo;
	private ClientInfo messageServiceInfo;
	private CheckInfo checkInfo;
	
	public static DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public HttpUrlWebClient(HttpUrlStatusInfo httpUrlStatusInfo, 
			ClientInfo messageServiceInfo, CheckInfo checkInfo) {
		this.httpUrlStatusInfo = httpUrlStatusInfo;
		this.messageServiceInfo = messageServiceInfo;
		this.checkInfo = checkInfo;
	}
	
	
	public void check(List<Boolean> flagList) {
//		logger.warn("监控的Http Url："+httpUrlStatusInfo.getUrl());
		accessHttpUrl(flagList);
		sendMobilMessage(flagList);
	}
	
	
	public void accessHttpUrl(List<Boolean> flagList) {
		
		CloseableHttpClient httpclient = HttpClients.createDefault();  
	       
        //创建httpGet对象
		HttpGet httpGet = new HttpGet(httpUrlStatusInfo.getUrl());
		//设置链接超时时间阈值（时间单位：ms）
		RequestConfig config = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(10000)    
        .setSocketTimeout(30000).build();
		httpGet.setConfig(config);
		
        logger.warn("进行监控的HTTP URL: " + httpGet.getURI());  
        
        CloseableHttpResponse response = null; 
        StatusLine status = null;
        HttpEntity entity = null;
        boolean normalFlag = true;
        
        try {
        	//获取response对象
			response = httpclient.execute(httpGet);
			
			//获取响应实体
			entity = response.getEntity();
			status = response.getStatusLine();
			
			//whiteListCode 判断Http URL返回状态码是否在配置白名单中的指标变量
			//true为存在于白名单中，false则为存在白名单外的状态码
			boolean whiteListCode = false; 
			for(String code : HttpUrlStatusInfo.STATUSCODEWHITELIST) {
				if(status.toString().contains(code)) {
					whiteListCode = true;
				}
			}
			
			if(whiteListCode == false) {
				//当响应状态为错误（非白名单内状态码）的时候，需要做的逻辑
				logger.error("HTTP URL: "+httpUrlStatusInfo.getUrl()+" 响应状态异常，异常响应状态为：" + status.toString());
				normalFlag = false;
				
				addSucceedFlag(normalFlag, flagList);
			} else {
				//响应状态
				logger.warn("HTTP URL: "+httpUrlStatusInfo.getUrl()+" 响应状态为：" + status.toString());		
			}
			
			if(entity != null) {
				// 打印响应内容长度    
				logger.info("HTTP URL响应内容长度: " + entity.getContentLength());  
                // 打印响应内容    
//				logger.info("HTTP URL响应内容: " + EntityUtils.toString(entity));  
              
			}
			
			addSucceedFlag(normalFlag, flagList);
			
		} catch (ConnectTimeoutException e2) {
			logger.error("HTTP URL: "+httpUrlStatusInfo.getUrl()+" 链接异常：" + e2.getMessage());
			normalFlag = false;
			addSucceedFlag(normalFlag, flagList);
			return;
		} catch (SocketTimeoutException e3) {
			logger.error("HTTP URL: "+httpUrlStatusInfo.getUrl()+" 链接异常：" + e3.getMessage());
			normalFlag = false;
			addSucceedFlag(normalFlag, flagList);
			return;
		} catch (SocketException e1) {
			logger.error("HTTP URL: "+httpUrlStatusInfo.getUrl()+" 链接异常：" + e1.getMessage());
			normalFlag = false;
			addSucceedFlag(normalFlag, flagList);
			return;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(entity != null && response.getStatusLine().getStatusCode() != 200) {
					httpGet.abort();
				}
				if(entity != null) {EntityUtils.consume(entity);}
				if(response != null) {response.close();}
				if(httpclient != null) {httpclient.close();}
			} catch (IOException e) {
				e.printStackTrace();
			}

			StatusUtil.updateRecord(normalFlag, httpUrlStatusInfo.getUrl());
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
		logger.warn("监控url服务检验的错误次数"+num);
		if(num>=this.checkInfo.getErrorCount()){
			
			//判断表是否已发送短信标志的值与发短信后的恢复周期值
			//（恢复周期值>0则已发短信，且暂停短信发送周期未走完）
			if(this.httpUrlStatusInfo.getHasSendMsg()==true && this.httpUrlStatusInfo.getRecoverCount()>0) {
				
				isSend = false;
				this.httpUrlStatusInfo.setRecoverCount(this.httpUrlStatusInfo.getRecoverCount()-1);					

				logger.warn("暂停短信发送周期还剩: "+this.httpUrlStatusInfo.getRecoverCount());
			} else {
				//设置是否已发送短信标志为true
				this.httpUrlStatusInfo.setHasSendMsg(true);
				this.httpUrlStatusInfo.setRecoverCount(HttpUrlStatusInfo.RECOVERCOUNTRESET);
				isSend = true;
			}
			
		}
		return isSend;
	}
	
	
	public synchronized void addSucceedFlag(boolean flag,List<Boolean> flagList) {
		if(flagList.size()==checkInfo.getCheckedCount()){
			flagList.remove(0);
		}
		flagList.add(flag);
		logger.warn("监控url调用的次数"+flagList.size());
		
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
				xmlInfo = xmlInfo.replace("#3", dateFormat.format(new Date(System.currentTimeMillis()))+"调用：["+httpUrlStatusInfo.getUrl()+"]异常！");
				logger.warn("服务调用异常信息:"+xmlInfo);
				logger.warn("开始调用短信服务进行通知.... ");
				InnerAdapterImpl innerAdapterImpl = SmsService.getInnerAdapterImpl(messageServiceInfo);
				sentSOAPMsg = innerAdapterImpl.process("SMS_SEND_MESSAGE_REMEDY", "POMS", xmlInfo);
				logger.warn("短信服务调用结束:"+sentSOAPMsg);
			
			} catch (Exception e) {
				logger.error("短信服务发送异常:"+e.getMessage());
				e.printStackTrace();
			}
			
		}
		return sentSOAPMsg;
		
	}
	
}
