package com.gpdi.gx;

import java.io.IOException;
import java.lang.management.MemoryUsage;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.CompositeType;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.log4j.Logger;

import com.gpdi.common.util.StatusUtil;
import com.gpdi.gx.adapter.InnerAdapterImpl;

public class CheckTomcatClient {

	private static Logger logger = Logger.getLogger(CheckTomcatClient.class);
	
	private TomcatInfo tomcatInfo;
	private ClientInfo messageServiceInfo;
	private CheckInfo checkInfo;
	
	private String reqProcessorName = null;
	private Integer reqErrCount = 0;
	
	public static DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public CheckTomcatClient(TomcatInfo tomcatInfo,ClientInfo messageServiceInfo,CheckInfo checkInfo){
		this.tomcatInfo = tomcatInfo;
		this.messageServiceInfo = messageServiceInfo;
		this.checkInfo = checkInfo;
	}
	
	public void check(List<Boolean> flagList){

		try {
			boolean successFlag = checkTomcatStatus(flagList);
			addSucceedFlag(successFlag,flagList);
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		} catch (InstanceNotFoundException e) {
			e.printStackTrace();
		} catch (ReflectionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sendMobilMessage(flagList);
	}
	
	
	public synchronized void addSucceedFlag(boolean flag,List<Boolean> flagList){
		if(flagList.size()==checkInfo.getCheckedCount()){
			flagList.remove(0);
		}
		flagList.add(flag);
//		System.out.println("加入次数："+flagList.size());
		logger.warn("调用的次数"+flagList.size());
		
	}
	
	/**
	 * 监控tomcat状况
	 * 
	 * @throws IOException
	 * @throws MalformedObjectNameException
	 * @throws AttributeNotFoundException
	 * @throws InstanceNotFoundException
	 * @throws MBeanException
	 * @throws ReflectionException
	 */
	public boolean checkTomcatStatus(List<Boolean> flagList) throws IOException, MalformedObjectNameException,  
	InstanceNotFoundException, ReflectionException{
		
		//tomcat状态是否正常的指标变量，也作是否发短信的指标，true为正常（不发短信），false为异常（发短信）
		boolean successFlag = true;
		//用于更新监控记录表(RMGD.BAS_WECHATALERT)的指标变量
		boolean normalFlag = true;
		
		
		JMXServiceURL url = null;
		JMXConnector connector = null;
		MBeanServerConnection connection = null;
		try {
			try {
				url = new JMXServiceURL(tomcatInfo.getJmxServiceUrl());
				connector = JMXConnectorFactory.connect(url);  
				connection = connector.getMBeanServerConnection();
				
				
			} catch (MalformedURLException e1) {
				logger.error("JMXServiceUrl格式错误："+url.toString()+" ，请检查tomcatStatusInfo.properties。 "+e1.getMessage(), e1);
				return successFlag;
			} catch (Exception e2) {
				logger.error("使用JMXServiceUrl链接错误："+url.toString()+" ，请检查tomcatStatusInfo.properties或被监控Tomcat的JMX配置。 "+e2.getMessage(), e2);
				return successFlag;
			} 
			
			// list ObjectNames  
	//        Set<ObjectName> names = connection.queryNames(new ObjectName("Catalina:*"), null); 
	//		Set<ObjectName> names = connection.queryNames(new ObjectName("java.lang:*"), null); 
	//        logger.info("++++++++++++++++++++++++++++++++++++++++++++++++++");  
	//        for (ObjectName name : names) {
	//        	logger.info("ObjectName : " + name); 
	//        }  
	//        logger.info("++++++++++++++++++++++++++++++++++++++++++++++++++");
			
			logger.info("JMXServiceUrl为："+tomcatInfo.getTomcatAddr()+"   监控的Tomcat地址和端口为："+tomcatInfo.getTomcatAddr());
			String tomcatAddr = tomcatInfo.getTomcatAddr();
			String servicePort = tomcatAddr.substring(tomcatAddr.indexOf(":")+1);
			
	        /*
	         * RequestInfo： 请求的统计信息 
	         */
	        //获取请求处理对象名称，一般一个tomcat只会有一个，但也有多端口部署的情况（未对一Tomcat多端口做处理）
	/*        logger.warn(":: Request Information ↓↓↓↓↓↓↓↓↓↓↓↓");
	        Set<ObjectName> requestProcessorNames = connection.queryNames(new ObjectName("Catalina:type=GlobalRequestProcessor,name=http-"+servicePort), null) ;
			
	        for(ObjectName on : requestProcessorNames){
	        	
	        	if("Catalina:type=GlobalRequestProcessor,name=jk-8009".equals(on.toString())) { //排除此类端口监控
	        		continue;
	        	}
	        	
	        	logger.warn("======》》 Request Processor Name : " + on);
	        	logger.info("======== Request Information ========================================");
	        	try {
					logger.info("bytesReceived\t: "+connection.getAttribute(on, "bytesReceived"));
		        	logger.info("bytesSent\t: "+connection.getAttribute(on, "bytesSent")); 
		        	logger.warn("errorCount\t: "+connection.getAttribute(on, "errorCount")); 
		        	logger.warn("maxTime\t\t: "+connection.getAttribute(on, "maxTime")); 
		        	//System.out.println(connection.getAttribute(on, "modelerType")); 
					logger.warn("processingTime\t: "+connection.getAttribute(on, "processingTime"));
		        	logger.info("requestCount\t: "+connection.getAttribute(on, "requestCount")); 
		        	this.reqProcessorName = on.toString();
		        	this.reqErrCount = Integer.parseInt(connection.getAttribute(on, "errorCount").toString());
		        	//TODO 这里的errorCount怎么定？
	        	} catch (AttributeNotFoundException e) {
					logger.error(e.getMessage(), e);
				} catch (MBeanException e) {
					successFlag = setSendingMsgflag();
					normalFlag = false;
				} 
	        }	*/
	        	
	        /*
	         * ThreadInfo: tomcat 中线程池信息 
	         */
	        //获取线程情况，第个端口一个，对应Connector
	        logger.warn(":: Tomcat Thread Information ↓↓↓↓↓↓↓↓↓↓↓↓");
	        Set<ObjectName> threadInfoNames = connection.queryNames(new ObjectName("Catalina:type=ThreadPool,name=http-"+servicePort), null);
	        
	        for(ObjectName on : threadInfoNames){
	        	
	        	if("Catalina:type=ThreadPool,name=jk-8009".equals(on.toString())) { //排除此种端口监控
	        		continue;
	        	}
	        	
	        	logger.warn("=====>> Thread Information Object Name : " + on);
	        	logger.warn("======= Thread Information =================================");
	        	try {
	        		int maxThread = (Integer)connection.getAttribute(on, "maxThreads");
	        		int currentBusyThreads = (Integer)connection.getAttribute(on, "currentThreadsBusy");
					logger.warn("maxThreads\t\t:" + maxThread);
					logger.warn("currentThreadCount\t:" + connection.getAttribute(on, "currentThreadCount"));
					logger.warn("currentThreadsBusy\t:" + currentBusyThreads);
					//计算繁忙线程占tomcat总线程比率
					NumberFormat numberFormat = NumberFormat.getInstance();
					numberFormat.setMaximumFractionDigits(2);
					double busyRate = Double.parseDouble(numberFormat.format((float) currentBusyThreads / (float) maxThread * 100));
					logger.warn("busyRate\t:" + busyRate);
					//统计得的线程繁忙率大于设定的线程繁忙率阈值，则发送短信
					if(TomcatInfo.THREAD_BUSYRATE!=null && busyRate > TomcatInfo.THREAD_BUSYRATE) {
						logger.error("Tomcat线程占用率："+busyRate+"大于预设定的繁忙率阈值:"+TomcatInfo.THREAD_BUSYRATE+"。");
						successFlag = setSendingMsgflag();
						normalFlag = false;
					}
					
	        	} catch (AttributeNotFoundException e) {
					logger.error(e.getMessage(), e);
				} catch (MBeanException e) {
					successFlag = setSendingMsgflag();
					normalFlag = false;
				}
	        }
	        
	        /*
	         * MemoryPool PS Old Gen : JVM中内存池老生代信息 
	         */
	        logger.warn(":: JVM MemoryPool:Old Gen Information ↓↓↓↓↓↓↓↓↓↓↓↓");
	        Set<ObjectName> memoryPoolNames = connection.queryNames(new ObjectName("java.lang:type=MemoryPool,name=PS Old Gen"), null);
	        for(ObjectName on : memoryPoolNames){
	        	logger.warn("=====>> JVM MemoryPool:Old Gen Object Name : " + on);
	        	logger.warn("======= JVM MemoryPool:Old Gen Information =================================");
	        	try {
	        		CompositeDataSupport usage = (CompositeDataSupport) connection.getAttribute(on, "Usage");
	        		MemoryUsage memoryUsage = MemoryUsage.from(usage);
	       		    
	        		long committed = (Long)memoryUsage.getCommitted();
	        		long used = (Long)memoryUsage.getUsed();
	        		logger.warn("MemoryPool:Old Gen Usage committed\t\t: "+committed);
	        		logger.warn("MemoryPool:Old Gen Usage init\t\t: "+used);
	        		logger.warn("MemoryPool:Old Gen Usage max\t\t\t: "+memoryUsage.getMax());
	        		logger.warn("MemoryPool:Old Gen Usage used\t\t: "+memoryUsage.getUsed());
	        		
	        		//计算使用的内存占JVM总内存比率
					NumberFormat numberFormat = NumberFormat.getInstance();
					numberFormat.setMaximumFractionDigits(2);
					numberFormat.setMinimumFractionDigits(2);
					double busyRate = Double.parseDouble( numberFormat.format(new BigDecimal(used / committed).multiply(new BigDecimal(100))));
					logger.warn("MemoryPool:Old Gen Usage busyRate\t\t: "+busyRate);
					//统计得的内存使用率大于设定的阈值，则发送短信
					if(TomcatInfo.MEM_BUSYRATE!=null && busyRate > TomcatInfo.MEM_BUSYRATE) {
						logger.error("Tomcat内存占用率："+busyRate+"大于预设定的占用率阈值:"+TomcatInfo.MEM_BUSYRATE+"。");
						successFlag = setSendingMsgflag();
						normalFlag = false;
						
					}
	        		
				} catch (AttributeNotFoundException e) {
					logger.error(e.getMessage(), e);
				} catch (MBeanException e) {
					successFlag = setSendingMsgflag();
					normalFlag = false;
				}
	        }
	        String[] alertTypeArr = tomcatInfo.getAlertTypeArr();
	        for(String alertType : alertTypeArr) {
	        	//记录监控信息表监控记录表RMGD.BAS_WECHATALERT
	    		StatusUtil.updateRecord(normalFlag, tomcatInfo.getTomcatAddr(), alertType);
	        }
	        
			
	        return successFlag;
		} finally {
			if (connector!=null) {connector.close();}
		}
	}

	/**
	 * 设置发短信指标变量方法
	 * 
	 * @return
	 */
	private boolean setSendingMsgflag() {
		boolean successFlag;
		//若错误已发短信接口，且处于发短信后的回复周期内，则不发短信，恢复周期-1
		if(this.tomcatInfo.getHasSendMsg()==true && this.tomcatInfo.getRecoverCount()!=null && this.tomcatInfo.getRecoverCount()>0) {
			successFlag = true;
			logger.warn("暂停短信发送周期还剩: "+this.tomcatInfo.getRecoverCount());
			this.tomcatInfo.setRecoverCount(this.tomcatInfo.getRecoverCount()-1);
			
		} else {//设置发送短信接口的标志变量，重置恢复周期为配置的恢复周期最大值
			successFlag = false;
			this.tomcatInfo.setHasSendMsg(true);
			this.tomcatInfo.setRecoverCount(TomcatInfo.RECOVERCOUNTRESET);
		}
		return successFlag;
	}
	
	

	public synchronized boolean isSendMessage(List<Boolean> flagList){
		int num = 0;
		boolean isSend = false;
		for(boolean flag : flagList){
			if(!flag){
				num++;
			}
		}
		logger.warn("服务调用检验的错误次数"+num);
		if(num>=this.checkInfo.getErrorCount()){
			
			//判断表是否已发送短信标志的值与发短信后的恢复周期值
			//（恢复周期值>0则已发短信，且暂停短信发送周期未走完）
			if(this.tomcatInfo.getHasSendMsg()==true && this.tomcatInfo.getRecoverCount()>0) {
				
				isSend = false;
				this.tomcatInfo.setRecoverCount(this.tomcatInfo.getRecoverCount()-1);					

				logger.warn("暂停短信发送周期还剩: "+this.tomcatInfo.getRecoverCount());
			} else {
				//设置是否已发送短信标志为true
				this.tomcatInfo.setHasSendMsg(true);
				this.tomcatInfo.setRecoverCount(TomcatInfo.RECOVERCOUNTRESET);
				isSend = true;
			}
			
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
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < phoneArr.length; i++) {
			
			try {
				String xmlInfo = messageServiceInfo.getXmlInfo();
				xmlInfo = xmlInfo.replace("#0", phoneArr[i]);	
				xmlInfo = xmlInfo.replace("#1", "one");
				String flag = System.currentTimeMillis()+"";
				flag = flag.substring(flag.length()-8);
				xmlInfo = xmlInfo.replace("#2", flag);				
				xmlInfo = xmlInfo.replace("#3", dateFormat.format(new Date(System.currentTimeMillis()))+"调用：["+tomcatInfo.getTomcatAddr()+"]异常！");
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
