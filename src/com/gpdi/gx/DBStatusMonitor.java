package com.gpdi.gx;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.gpdi.common.util.DBUtil;
import com.gpdi.common.util.DBStatusServletContextListener;
import com.gpdi.common.util.StatusUtil;



public class DBStatusMonitor {
	
	private static Logger logger = Logger.getLogger(DBStatusMonitor.class);
	
	private static DBStatusMonitor dbStatusMonitor;
	
	//存放所有DB状态记录对象的Map
	private static Map<String, DBStatusInfo> dbStatusInfoMap = new HashMap<String, DBStatusInfo>();

	
	public static synchronized DBStatusMonitor getInstance(){
		if (dbStatusMonitor == null) {    
			dbStatusMonitor = new DBStatusMonitor();  
        }    
       return dbStatusMonitor; 
	}

	
	/**
	 * 监控数据库连接状态
	 * 
	 */
	public void checkDBConnectStatus() {
		
		//读取开关配置文件
        String dbStatusMonitorSwitch = null;
        InputStream is = null;
		try {
			Properties pps = new Properties();
			String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			is = new FileInputStream(path + "/com/gpdi/gx/runningSwitch.properties");
			pps.load(is);
			dbStatusMonitorSwitch = pps.getProperty("DBStatusMonitorSwitch");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(is!=null) {is.close();}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(!"true".equals(dbStatusMonitorSwitch)) { //若开关参数为true，则监控功能运行
			logger.warn("runningSwitch.properties数据库监控功能开关为非启动状态，数据库监控功能不启动。");
			return;
		}
		
		List<Map> infoList = DBUtil.getAllDBInfo();
		
		//利用数据库url作为key，方便统计不同的库
		if(infoList!=null && infoList.size()>0) {
			for(int i=0; i<infoList.size(); i++) {
				DBStatusInfo dbStatusInfo = dbStatusInfoMap.get(infoList.get(i).get("url").toString());
				if(dbStatusInfo == null) {
					dbStatusInfo = new DBStatusInfo();
					dbStatusInfoMap.put(infoList.get(i).get("url").toString(), dbStatusInfo);
				}
			} 
			
		} else {
			logger.error("获取数据库信息失败，请检查DBStatusInfo.properties配置。");
			return;
		}
		
		//测试链接状态
		testStatus(infoList);
    	
    	
    }


	/**
	 * 测试链接状态方法
	 * 
	 * @param infoList
	 * @param dbStatusInfo
	 * @return
	 */
	private void testStatus(List<Map> infoList) {
		for (int i=0; i<infoList.size(); i++) { //每个链接对象分别测试链接状态
			String connectionKey = infoList.get(i).get("url").toString(); //获取用作key的url字符串
			boolean normalFlag = true; //用于更新监控记录表(RMGD.BAS_WECHATALERT)的指标变量
			
	    	String sql = "select 1 from dual";
    		Connection conn = null;
	    	PreparedStatement pStatement = null;
	    	ResultSet resultSet = null;
	    	DBStatusInfo dbStatusInfo = null;
	    	try {
	    		dbStatusInfo = dbStatusInfoMap.get(connectionKey);
	    		conn = DBUtil.getConnectionByUrl(connectionKey);
	    		Long startTime = System.currentTimeMillis();
				pStatement = conn.prepareStatement(sql);
				resultSet = pStatement.executeQuery();
				while(resultSet.next()) {
					//打印各种数据库状态信息
					Long ms = System.currentTimeMillis() - startTime;
					logger.warn("数据库:"+connectionKey+" 链接成功! 用时:"+ ms +"ms。");
					dbStatusInfo.setTotalCount(dbStatusInfo.getTotalCount()+1);
					logger.warn("测试链接失败次数: "+dbStatusInfo.getErrorCount());
					logger.warn("测试链接次数: "+dbStatusInfo.getTotalCount());
					//TODO 记录数据库连接日常信息（待实现）
				}
				
			} catch (SQLException e) {
				normalFlag = false;
				
				//在规定次数中累计错误达到错误阈值则记录错误信息并发通知
				if((dbStatusInfo.getErrorCount()+1)>=DBStatusInfo.MAXERRORCOUNT) {
					CheckServiceMonitor monitor = CheckServiceMonitor.getInstance();
					ClientInfo sendMsgInfo = monitor.getMessageServiceInfo();
					
					if(dbStatusInfo.getRecoverCount()==null || dbStatusInfo.getRecoverCount()<=0) { //判断是否已发过短信并在重发恢复周期内
						String errMsg = "数据库链接："+connectionKey;
						logger.warn("为出错"+errMsg+"发送短信。");
						new GxWebClient(sendMsgInfo).sendMobilMessage(errMsg); //发送短信
						dbStatusInfo.setHasSendMsg(true);
						dbStatusInfo.setRecoverCount(DBStatusInfo.MAXRECOVERCOUNT);
						dbStatusInfo.setErrorCount(0); //异常后重置错误数值计数器						
					}
					dbStatusInfo.setRecoverCount(dbStatusInfo.getRecoverCount()-1);
				}
				
				logger.error("数据库:"+connectionKey+" 链接异常! ", e);
				logger.error("数据库:"+connectionKey+" 链接次数： " + (dbStatusInfo.getTotalCount()+1));
				logger.error("数据库:"+connectionKey+" 出错次数： " + (dbStatusInfo.getErrorCount()+1));
				if(dbStatusInfo.getRecoverCount()!=null && dbStatusInfo.getRecoverCount() > 0) {
					logger.error("数据库:"+connectionKey+" 暂停发送短信周期：" + dbStatusInfo.getRecoverCount());
				}
				//更新DB状态对象中的总数计数器及错误数计数器增1
				dbStatusInfo.setErrorCount(dbStatusInfo.getErrorCount()+1);
				dbStatusInfo.setTotalCount(dbStatusInfo.getTotalCount()+1);
				
			} finally {
				DBUtil.close(resultSet, pStatement, conn);
				String alertDetail = connectionKey.substring(connectionKey.indexOf("@")+1, connectionKey.lastIndexOf(":"));
				StatusUtil.updateRecord(normalFlag, alertDetail);
			}
	    	
		}
		
	}
	
	
	
	

}
