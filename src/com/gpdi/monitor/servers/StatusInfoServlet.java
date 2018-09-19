package com.gpdi.monitor.servers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.gpdi.common.util.DBUtil;

public class StatusInfoServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) { 
		Connection conn = DBUtil.getDataSourceConn();
		String sql = "select ALERTID,ALERTTYPE,ALERTDETAIL,ALERTVALUE,VALUE,ALERTSTATUS,ALERTDATE,REMARK,WEIXINSTATUS,WEIXINDATE from RMGD.BAS_WECHATALERT ";
		PreparedStatement pst = null;
		ResultSet rs = null;
		PrintWriter out = null;
		
		JSONArray jsonArr = new JSONArray();
		
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			JSONObject jsonObj = new JSONObject();
			while(rs.next()) {
				jsonObj.put("alertId", rs.getLong(1));
				jsonObj.put("alertType", rs.getString(2));
				jsonObj.put("alertDetail", rs.getString(3));
				jsonObj.put("alertValue", rs.getString(4));
				jsonObj.put("value", rs.getString(5));
				jsonObj.put("alertStatus", rs.getLong(6));
				jsonObj.put("alertDate", rs.getDate(7));
				jsonObj.put("remark", rs.getString(8));
				jsonObj.put("weixinStatus", rs.getLong(7));
				jsonObj.put("weixinDate", rs.getDate(10));
				jsonArr.add(jsonObj);
			}
			System.out.println(jsonArr.toString());
			
			response.setContentType("application/json;charset=utf-8");
	        try {
	        	out = response.getWriter();
	        	out.println(jsonArr);
	        }
	        catch (IOException ex1) {
	            ex1.printStackTrace();
	        }
		        
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pst, conn);
			if(out!=null) {
				out.close();
			}
		}
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("statusInfo.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
