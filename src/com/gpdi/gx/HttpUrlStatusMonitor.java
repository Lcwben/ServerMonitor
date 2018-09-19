package com.gpdi.gx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class HttpUrlStatusMonitor {

	private static Logger logger = Logger.getLogger(HttpUrlStatusMonitor.class);
	
	public static List<HttpUrlStatusInfo> httpUrlStatusInfoList = null;
	public static ClientInfo messageServiceInfo = null;
	public static CheckInfo checkInfo = null;
	public static Map<String,List<Boolean>> tempFlagMap = new HashMap<String,List<Boolean>>();
	
	private static HttpUrlStatusMonitor httpUrlStatusMonitor;
	private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(6, 10, 5*60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
	
	public static synchronized HttpUrlStatusMonitor getInstance() {
		if (httpUrlStatusMonitor == null) {    
			httpUrlStatusMonitor = new HttpUrlStatusMonitor();  
        }    
       return httpUrlStatusMonitor; 
	}
	
	public void excute(){
		
		//读取开关配置文件
	    String httpUrlStatusMonitorSwitch = null;
	    InputStream is = null;
		try {
			Properties pps = new Properties();
			String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			is = new FileInputStream(path + "/com/gpdi/gx/runningSwitch.properties");
			pps.load(is);
			httpUrlStatusMonitorSwitch = pps.getProperty("HttpUrlStatusMonitorSwitch");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(is!=null) {is.close();}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(!"true".equals(httpUrlStatusMonitorSwitch)) { //若开关参数为true，则监控功能运行
			logger.warn("runningSwitch.properties HttpUrl状态监控功能开关为非启动状态，HttpUrl状态监控功能不启动。");
			return;
		}
	
		if(messageServiceInfo==null){
			messageServiceInfo = getMessageServiceInfo();
		}
		if(httpUrlStatusInfoList==null){
			httpUrlStatusInfoList = getHttpUrlStatusInfo();
		}
		if(checkInfo==null){
			checkInfo = getCheckInfo();
		}
		
		for(int i = 0 ;i<httpUrlStatusInfoList.size();i++){

    		final HttpUrlStatusInfo httpUrlStatusInfo = httpUrlStatusInfoList.get(i);
    		if(tempFlagMap.get(httpUrlStatusInfo.getUrl())==null){
    			tempFlagMap.put(httpUrlStatusInfo.getUrl(), new ArrayList<Boolean>());
    		}
    		threadPool.execute(new Thread(){
    			public void run(){
    				HttpUrlWebClient httpUrlWebClient = new HttpUrlWebClient(httpUrlStatusInfo, messageServiceInfo,checkInfo);
    				httpUrlWebClient.check(tempFlagMap.get(httpUrlStatusInfo.getUrl()));
    				
    			}
    		});
    		
    	}
		
	}
	
	
	/**
	 * 获取发服开接口发送信息方法
	 * 
	 * @return
	 */
	public List<HttpUrlStatusInfo> getHttpUrlStatusInfo() {
		Properties pps = new Properties();
		 InputStream resourceAsStream = null;
		 try {
			resourceAsStream = GxWebClient.class.getResourceAsStream("httpUrlStatusInfo.properties");
			pps.load(resourceAsStream);
			String httpUrl = pps.getProperty("url");
			String recoverCount = pps.getProperty("recoverCount");
			String whiteListStr = pps.getProperty("StatusCodeWhiteList");
			List<HttpUrlStatusInfo> infoList = new ArrayList<HttpUrlStatusInfo>();
			if(httpUrl!=null && httpUrl.length()>0) {
				
				String[] split = httpUrl.split(",");
				String[] codesStr = whiteListStr.split(",");
				
				for(String str : split) {
					HttpUrlStatusInfo httpUrlStatusInfo = new HttpUrlStatusInfo();
					httpUrlStatusInfo.setUrl(str);
					httpUrlStatusInfo.setHasSendMsg(false);
					httpUrlStatusInfo.setRecoverCount(Integer.parseInt(recoverCount));
					Set<String> whiteList = new HashSet<String>();
					for (int i = 0; i < codesStr.length; i++) {
						whiteList.add(codesStr[i]);
					}
					HttpUrlStatusInfo.STATUSCODEWHITELIST = whiteList;
					HttpUrlStatusInfo.RECOVERCOUNTRESET = Integer.parseInt(recoverCount);
					infoList.add(httpUrlStatusInfo);
				}
			}
			return infoList;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
	 * 获取短信平台接口发送信息方法
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
			e.printStackTrace();
		} catch (IOException e) {
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
	 * 获取查询周期等信息的方法
	 * 
	 * @return
	 */
	public CheckInfo getCheckInfo(){
		Properties pps = new Properties();
		InputStream resourceAsStream = GxWebClient.class.getResourceAsStream("fukaiServiceInfo.properties");
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
