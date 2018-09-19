package com.gpdi.common.util;

import java.io.IOException;
import java.util.Map;
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

import com.gpdi.gx.CheckInfo;
import com.gpdi.gx.TomcatStatusMonitor;

public class TomcatStatusServletContextListener implements ServletContextListener  {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		final TomcatStatusMonitor tomcatMonitor = TomcatStatusMonitor.getInstance();
		
		Runnable runnable = new Runnable() {  
			public void run() {  
//				try {
					
					tomcatMonitor.excute();
					
//				} catch (MalformedObjectNameException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (AttributeNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (InstanceNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (MBeanException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (ReflectionException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
            }  
        };
		
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        CheckInfo checkInfo = tomcatMonitor.getCheckInfo();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间  
        service.scheduleAtFixedRate(runnable, 10, checkInfo.getTimedSend(), TimeUnit.SECONDS);
	}

}
