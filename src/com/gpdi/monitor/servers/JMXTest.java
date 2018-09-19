package com.gpdi.monitor.servers;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Set;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.JMX;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

//import org.apache.coyote.ProtocolHandler;

public class JMXTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ProtocolHandler a ;
		
		JMXTest test = new JMXTest() ;
		try {
			test.pringTomceStatus();
//			test.firstTry();
		} catch (MalformedObjectNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AttributeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ReflectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
	
	public void pringTomceStatus() throws IOException, MalformedObjectNameException, AttributeNotFoundException, InstanceNotFoundException, MBeanException, ReflectionException{
		JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://127.0.0.1:9999/jmxrmi");  
		JMXConnector connector = JMXConnectorFactory.connect(url);  
        MBeanServerConnection connection = connector.getMBeanServerConnection();  
        
        System.out.println(":: Request Infornation ↓↓↓↓↓↓↓↓↓↓↓↓");
        /*
         * RequestInfo： 请求的统计信息 
         */
        //获取请求处理对象名称 <p>一般一个tomcat只会有一个，但也有多端口部署的情况</p>
        Set<ObjectName> requestProcessorNames = connection.queryNames(new ObjectName("Catalina:type=GlobalRequestProcessor,*"), null);
        
        String tomcatAddress = "";
		Set<ObjectName> hosts = connection.queryNames(new ObjectName("Catalina:type=Host,host=*"), null);
		if(hosts!=null && hosts.size()>0) {
			Object[] hostname = hosts.toArray();
			String str = hostname[0].toString();
			tomcatAddress += str.substring(str.lastIndexOf("=")+1);
		}
		tomcatAddress += ":";
		Set<ObjectName> ports = connection.queryNames(new ObjectName("Catalina:type=ThreadPool,name=http-*"), null);
		if(ports!=null && ports.size()>0) {
			Object[] portname = ports.toArray();
			String str = portname[0].toString();
			tomcatAddress += str.substring(str.lastIndexOf("-")+1);			
		}
		
		System.out.println("~~~~~~~~~~~~~~~~~ tomcatAddress : "+tomcatAddress);
        
        for(ObjectName on : requestProcessorNames){
        	System.out.println("======》》 Request Processor Name : " + on);
        	System.out.println("======== Request Infornation ========================================");
        	System.out.println("bytesReceived\t: "+connection.getAttribute(on, "bytesReceived")); 
        	System.out.println("bytesSent\t: "+connection.getAttribute(on, "bytesSent")); 
        	System.out.println("errorCount\t: "+connection.getAttribute(on, "errorCount")); 
        	System.out.println("maxTime\t\t: "+connection.getAttribute(on, "maxTime")); 
        	//System.out.println(connection.getAttribute(on, "modelerType")); 
        	System.out.println("processingTime\t: "+connection.getAttribute(on, "processingTime")); 
        	System.out.println("requestCount\t: "+connection.getAttribute(on, "requestCount")); 
        }
        
        System.out.println("");
        System.out.println("");
        System.out.println(":: Tomcat Thread Infornation ↓↓↓↓↓↓↓↓↓↓↓↓");
        /*
         * ThreadInfo: tomcat 中线程池信息 
         */
        //获取线程情况，第个端口一个，对应Connector
        Set<ObjectName> threadInfoNames = connection.queryNames(new ObjectName("Catalina:type=ThreadPool,*"), null) ;
        for(ObjectName on : threadInfoNames){
        	System.out.println("=====>> Thread Infornation Object Name : " + on);
        	System.out.println("======= Thread Infornation =================================");
        	System.out.println("maxThreads\t\t:" + connection.getAttribute(on, "maxThreads"));
        	System.out.println("currentThreadCount\t:" + connection.getAttribute(on, "currentThreadCount"));
        	System.out.println("currentThreadsBusy\t:" + connection.getAttribute(on, "currentThreadsBusy"));
        }
        
	}
	
	public void firstTry() throws IOException, MalformedObjectNameException{
		JMXServiceURL url = new JMXServiceURL(  
                "service:jmx:rmi:///jndi/rmi://127.0.0.1:9999/jmxrmi");  
        JMXConnector connector = JMXConnectorFactory.connect(url);  
        MBeanServerConnection connection = connector.getMBeanServerConnection();  
//        connection.get
        //通过newPlatformMXBeanProxy获取远程MXBean的控制权
        ThreadMXBean bean = ManagementFactory.newPlatformMXBeanProxy(connection,"java.lang:type=Threading", ThreadMXBean.class);
        // list domains  
        String[] domains = connection.getDomains();  
        for (String domain : domains) {  
            System.out.println("domain : " + domain);  
        }  
  
        // list ObjectNames  
        Set<ObjectName> names = connection.queryNames(new ObjectName("Catalina:*"), null);  
        for (ObjectName name : names) {  
            System.out.println("ObjectName : " + name);  
        }  
        
        System.out.println("========================================");
        
        long[] threadIds = bean.getAllThreadIds() ;
        System.out.println("The Threads count : "+threadIds.length);
        for(int i =0 ; i < threadIds.length ; i++){
        	ThreadInfo ti = bean.getThreadInfo(threadIds[i]) ;
        	System.out.println("Thread Name :: "+ti.getThreadName() +" ;State:: "+ ti.getThreadState()) ; 
        }
        
  
        // remote operation  
        //ObjectName name = new ObjectName("test.xue.mbean:type=Hello");  
       // HelloMBean mbean = JMX.newMBeanProxy(connection, name, HelloMBean.class);  
       // mbean.sayHello();  
       // System.out.println("1 + 2 ="+mbean.add(1, 2));  
        
        ObjectName name = new ObjectName("Catalina:type=ProtocolHandler,port=8080");
//        ProtocolHandler a = JMX.newMBeanProxy(connection, name, ProtocolHandler.class) ;
//        //ProtocolHandler b = ManagementFactory.newPlatformMXBeanProxy(connection,"Catalina:type=ProtocolHandler", ProtocolHandler.class); ;
//        org.apache.tomcat.util.modeler.BaseModelMBean b = ManagementFactory.newPlatformMXBeanProxy(connection,"Catalina:type=ProtocolHandler,port=8080", org.apache.tomcat.util.modeler.BaseModelMBean.class);
        // close connection  
        connector.close();  
	}

}
