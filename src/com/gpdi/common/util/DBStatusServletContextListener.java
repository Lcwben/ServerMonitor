package com.gpdi.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MalformedObjectNameException;
import javax.management.ReflectionException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.gpdi.gx.CheckInfo;
import com.gpdi.gx.DBStatusMonitor;
import com.gpdi.gx.GxWebClient;

/**
 * 状态监控监听器
 * 
 * @author liangchengwei
 *
 */
public class DBStatusServletContextListener implements ServletContextListener {

	private static Logger logger = Logger.getLogger(DBStatusServletContextListener.class);
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		final DBStatusMonitor monitor = DBStatusMonitor.getInstance();
		
		Runnable runnable = new Runnable() {  
			public void run() {  

				monitor.checkDBConnectStatus();
				
            }  
        };
        
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		Map<String, String> propertyMap = DBUtil.getDbPropertiesInfo();
		
		// 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
		service.scheduleAtFixedRate(runnable, 10, Integer.parseInt(propertyMap.get("timedSend")), TimeUnit.SECONDS);			


	}

}
