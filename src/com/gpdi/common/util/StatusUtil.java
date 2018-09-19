package com.gpdi.common.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StatusUtil {

	/**
	 * 更新新监控记录表(RMGD.BAS_WECHATALERT)方法
	 * 
	 * @param normalFlag	指监控目标是否正常的指标变量(true代表正常，false为异常)
	 * @param alertDetail	监控的具体对象
	 */
	public static void updateRecord(boolean normalFlag, String alertDetail) {
		//查询表RMXC.BAS_WECHATALERT对应的记录
		String sql = "";
		if(normalFlag==false) {
			sql = "update RMXC.BAS_WECHATALERT set ALERTVALUE = ?, ALERTSTATUS = ?, ALERTDATE = sysdate where ALERTDETAIL = ? ";	
		} else {
			sql = "update RMXC.BAS_WECHATALERT set ALERTVALUE = ?, ALERTSTATUS = ? where ALERTDETAIL = ? ";			
		}
		
		Connection conn = null;
    	PreparedStatement pStatement = null;
    	try {
    		conn = DBUtil.getDataSourceConn();
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, normalFlag==true?0:1);
			pStatement.setInt(2, normalFlag==true?0:1);
			pStatement.setString(3, alertDetail);			
			int count = pStatement.executeUpdate();
    	} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, pStatement, conn);
		}
		
	}
	
	
	/**
	 * 更新新监控记录表(RMGD.BAS_WECHATALERT)方法(用于Tomcat状态监控)
	 * 
	 * @param normalFlag	指监控目标是否正常的指标变量(true代表正常，false为异常)
	 * @param alertDetail	监控的具体对象
	 * @param alertType		Tomcat监控的具体类型(线程状态，内存使用状态)
	 */
	public static void updateRecord(boolean normalFlag, String alertDetail, String alertType) {
		//查询表RMXC.BAS_WECHATALERT对应的记录
		String sql = "";
		if(normalFlag==false) {
			sql = "update RMXC.BAS_WECHATALERT set ALERTVALUE = ?, ALERTSTATUS = ?, ALERTDATE = sysdate where ALERTTYPE = ? and ALERTDETAIL = ? ";
//			sql = "update RMGD.BAS_WECHATALERT set ALERTVALUE = ?, ALERTSTATUS = ?, ALERTDATE = sysdate where ALERTTYPE = ? and ALERTDETAIL = ? ";	
		} else {
			sql = "update RMXC.BAS_WECHATALERT set ALERTVALUE = ?, ALERTSTATUS = ? where ALERTTYPE = ? and ALERTDETAIL = ? ";
//			sql = "update RMGD.BAS_WECHATALERT set ALERTVALUE = ?, ALERTSTATUS = ? where ALERTTYPE = ? and ALERTDETAIL = ? ";
		}
		
		Connection conn = null;
    	PreparedStatement pStatement = null;
    	try {
    		conn = DBUtil.getDataSourceConn();
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, normalFlag==true?0:1);
			pStatement.setInt(2, normalFlag==true?0:1);
			pStatement.setString(3, alertType);	
			pStatement.setString(4, alertDetail);			
			int count = pStatement.executeUpdate();
    	} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, pStatement, conn);
		}
		
	}
}
