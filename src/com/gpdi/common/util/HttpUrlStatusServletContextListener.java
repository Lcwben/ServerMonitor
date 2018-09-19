package com.gpdi.common.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.gpdi.gx.CheckFukaiInterfaceMonitor;
import com.gpdi.gx.CheckInfo;
import com.gpdi.gx.HttpUrlStatusMonitor;

public class HttpUrlStatusServletContextListener implements
		ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		final HttpUrlStatusMonitor monitor = HttpUrlStatusMonitor.getInstance();
		
		Runnable runnable = new Runnable() {  
	
            public void run() {  
            	monitor.excute();
            	
            }  
        };
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();  
		CheckInfo checkInfo = monitor.getCheckInfo();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间  
        service.scheduleAtFixedRate(runnable, 10, checkInfo.getTimedSend(), TimeUnit.SECONDS);

	}

}
