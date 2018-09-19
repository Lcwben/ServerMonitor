package com.gpdi.gx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.log4j.Logger;

public class TomcatStatusMonitor {

	private static Logger logger = Logger.getLogger(TomcatStatusMonitor.class);
	
	public static List<TomcatInfo> tomcatInfoList = null;
	public static ClientInfo messageServiceInfo = null;
	public static CheckInfo checkInfo = null;
	public static Map<String,List<Boolean>> tempFlagMap = new HashMap<String,List<Boolean>>();
	
	private static CheckServiceMonitor checkServiceMonitor;
	private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(6, 10, 5*60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());


	private static TomcatStatusMonitor tomcatStatusMonitor;
	
	public static synchronized TomcatStatusMonitor getInstance(){
		if (tomcatStatusMonitor == null) {    
			tomcatStatusMonitor = new TomcatStatusMonitor();  
        }    
       return tomcatStatusMonitor; 
	}
	
	
	public void excute(){
		
		//读取开关配置文件
        String tomcatStatusMonitorSwitch = null;
        InputStream is = null;
		try {
			Properties pps = new Properties();
			String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			is = new FileInputStream(path + "/com/gpdi/gx/runningSwitch.properties");
			pps.load(is);
			tomcatStatusMonitorSwitch = pps.getProperty("TomcatStatusMonitorSwitch");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(is!=null) {is.close();}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(!"true".equals(tomcatStatusMonitorSwitch)) { //若开关参数为true，则监控功能运行
			logger.warn("runningSwitch.properties Tomcat状态监控功能开关为非启动状态，Tomcat状态监控功能不启动。");
			return;
		}
		
		if(messageServiceInfo==null){
			messageServiceInfo = getMessageServiceInfo();
		}
		if(tomcatInfoList==null){
			tomcatInfoList = getTomcatInfo();
		}
		if(checkInfo==null){
			checkInfo = getCheckInfo();
		}
		
		for(int i = 0 ;i<tomcatInfoList.size();i++){
    		// task to run goes here 
    		final TomcatInfo tomcatInfo = tomcatInfoList.get(i);
    		if(tempFlagMap.get(tomcatInfo.getJmxServiceUrl())==null){
    			tempFlagMap.put(tomcatInfo.getJmxServiceUrl(), new ArrayList<Boolean>());
    		}
    		threadPool.execute(new Thread(){
    			public void run(){
    				CheckTomcatClient checkTomcatClient = new CheckTomcatClient(tomcatInfo, messageServiceInfo,checkInfo);
    				checkTomcatClient.check(tempFlagMap.get(tomcatInfo.getJmxServiceUrl()));
    				
    			}
    		});
    		
    	}
	}
	

	public CheckInfo getCheckInfo(){
		Properties pps = new Properties();
		InputStream resourceAsStream = GxWebClient.class.getResourceAsStream("tomcatStatusInfo.properties");
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
	
	
	public List<TomcatInfo> getTomcatInfo(){
		 Properties pps = new Properties();
		 InputStream resourceAsStream = null;
		 try {
			resourceAsStream = GxWebClient.class.getResourceAsStream("tomcatStatusInfo.properties");
			pps.load(resourceAsStream);
			String jmxServiceURL = pps.getProperty("JMXServiceURL");
			String threadBusyRate = pps.getProperty("threadBusyRate");
			String memBusyRate = pps.getProperty("memBusyRate");
			String recoverCount = pps.getProperty("recoverCount");
			String alertTypeStr = pps.getProperty("alertType");
			List<TomcatInfo> infoList = new ArrayList<TomcatInfo>();
			if(jmxServiceURL!=null && jmxServiceURL.length()>0){
				String[] split = jmxServiceURL.split(",");
				String[] alertTypeArr = alertTypeStr.split(",");
				for(String str : split){
					String urlStr = str.substring(0, str.indexOf("("));
					String tomcatAddr = str.substring(str.indexOf("(")+1, str.indexOf(")"));
					
					TomcatInfo tomcatInfo = new TomcatInfo();
					tomcatInfo.setJmxServiceUrl(urlStr);
					tomcatInfo.setTomcatAddr(tomcatAddr);
					tomcatInfo.setHasSendMsg(false);
					tomcatInfo.setAlertTypeArr(alertTypeArr);
					TomcatInfo.THREAD_BUSYRATE = Double.parseDouble(threadBusyRate); 
					TomcatInfo.MEM_BUSYRATE = Double.parseDouble(memBusyRate);
					TomcatInfo.RECOVERCOUNTRESET = Integer.parseInt(recoverCount);
					
					infoList.add(tomcatInfo);
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
	
}
