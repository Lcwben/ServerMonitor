package com.gpdi.gx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.gpdi.common.util.Constants;

public class CheckServiceMonitor {
	
	private static Logger logger = Logger.getLogger(CheckServiceMonitor.class);
	
	public static List<ClientInfo> gxServiceInfoList = null;
	public static ClientInfo messageServiceInfo = null;
	public static CheckInfo checkInfo = null;
	public static Map<String,List<Boolean>> tempFlagMap = new HashMap<String,List<Boolean>>();
	
	private static CheckServiceMonitor checkServiceMonitor;
	private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(6, 10, 5*60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
	public static synchronized CheckServiceMonitor getInstance(){
		if (checkServiceMonitor == null) {    
			checkServiceMonitor = new CheckServiceMonitor();  
        }    
       return checkServiceMonitor; 
	}
	
	public void excute(){
		
		//读取开关配置文件
	    String checkServiceMonitorSwitch = null;
	    InputStream is = null;
		try {
			Properties pps = new Properties();
			String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			is = new FileInputStream(path + "/com/gpdi/gx/runningSwitch.properties");
			pps.load(is);
			checkServiceMonitorSwitch = pps.getProperty("CheckServiceMonitorSwitch");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(is!=null) {is.close();}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(!"true".equals(checkServiceMonitorSwitch)) { //若开关参数为true，则监控功能运行
			logger.warn("runningSwitch.properties 管线接口监控功能开关为非启动状态，管线接口监控功能不启动。");
			return;
		}
		
		if(messageServiceInfo==null){
			messageServiceInfo = getMessageServiceInfo();
		}
		if(gxServiceInfoList==null){
			gxServiceInfoList=getGxServiceInfo();
		}
		if(checkInfo==null){
			checkInfo = getCheckInfo();
		}
		
		for(int i = 0 ;i<gxServiceInfoList.size();i++){
    		// task to run goes here 
    		final ClientInfo clientInfo = gxServiceInfoList.get(i);
    		if(tempFlagMap.get(clientInfo.getEndPoint())==null){
    			tempFlagMap.put(clientInfo.getEndPoint(), new ArrayList<Boolean>());
    		}
    		threadPool.execute(new Thread(){
    			public void run(){
    				GxWebClient gxWebClient = new GxWebClient(clientInfo, messageServiceInfo,checkInfo);
    				gxWebClient.check(tempFlagMap.get(clientInfo.getEndPoint()));
    				
    			}
    		});
    		
    	}
		
	}
	
	public List<ClientInfo> getGxServiceInfo(){
		 Properties pps = new Properties();
		 InputStream resourceAsStream = null;
		 try {
			resourceAsStream = GxWebClient.class.getResourceAsStream("webServiceInfo.properties");
			pps.load(resourceAsStream);
			String endPoints = pps.getProperty("InforesServiceEndPoint");
			String xmlInfo = pps.getProperty("xml");
			String recoverCount = pps.getProperty("recoverCount");
			List<ClientInfo> infoList = new ArrayList<ClientInfo>();
			if(endPoints!=null && endPoints.length()>0){
				String[] split = endPoints.split(",");
				for(String str : split){
					ClientInfo clientInfo = new ClientInfo();
					clientInfo.setEndPoint(str);
					clientInfo.setXmlInfo(xmlInfo);
					clientInfo.setHasSendMsg(false);
					clientInfo.setRecoverCountReset(Integer.parseInt(recoverCount));
					infoList.add(clientInfo);
				}
			}
			return infoList;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(resourceAsStream!=null) {resourceAsStream.close();}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		 return null;
	}
	
	
	/**
	 * 获取短信网关配置文件内容的方法
	 * 
	 * @return
	 */
	public ClientInfo getMessageServiceInfo(){
		Properties pps = new Properties();
		InputStream resourceAsStream = null;
		try {
			resourceAsStream = GxWebClient.class.getResourceAsStream("messageServiceInfo.properties");
			pps.load(resourceAsStream);
			ClientInfo clientInfo = new ClientInfo();
			clientInfo.setEndPoint(pps.getProperty("messageServiceEndPoint"));
			clientInfo.setXmlInfo(pps.getProperty("xml"));
			clientInfo.setPhoneNums(pps.getProperty("phoneNums"));
			return clientInfo;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(resourceAsStream!=null) {resourceAsStream.close();}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
		
		
	}
	
	public CheckInfo getCheckInfo(){
		Properties pps = new Properties();
		InputStream resourceAsStream = GxWebClient.class.getResourceAsStream("webServiceInfo.properties");
		try {
			pps.load(resourceAsStream);
			CheckInfo checkInfo = new CheckInfo();
			checkInfo.setTimedSend(Integer.parseInt(pps.getProperty("timedSend")));
			checkInfo.setErrorCount(Integer.parseInt(pps.getProperty("errorCount")));
			checkInfo.setCheckedCount(Integer.parseInt(pps.getProperty("checkedCount")));
			return checkInfo;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(resourceAsStream!=null) {resourceAsStream.close();}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
		
		
	}
	
}
