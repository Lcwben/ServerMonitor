package com.gpdi.common.util;

import java.util.List;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.gpdi.gx.CheckInfo;
import com.gpdi.gx.CheckServiceMonitor;
import com.gpdi.gx.ClientInfo;
import com.gpdi.gx.GxWebClient;

public class MyServletContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		  
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		final CheckServiceMonitor checkServiceMonitor = CheckServiceMonitor.getInstance();
		Runnable runnable = new Runnable() {  
			
			
            public void run() {  
            	checkServiceMonitor.excute();
            	
            }  
        };
		ScheduledExecutorService service = Executors  
                .newSingleThreadScheduledExecutor();  
		CheckInfo checkInfo = checkServiceMonitor.getCheckInfo();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间  
        service.scheduleAtFixedRate(runnable, 10, checkInfo.getTimedSend(), TimeUnit.SECONDS);
		
	}

}
