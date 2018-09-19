package com.gpdi.gx;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gpdi.common.util.SoapUtil;

public class CheckServiceServlet extends HttpServlet {
	
	private int width = 65 ; //设置图片宽度
	private int height = 22 ; //设置图片高度

	public void doGet(HttpServletRequest request , HttpServletResponse response){
		this.doPost(request, response) ;
	} ;
	
	public void doPost(HttpServletRequest request , HttpServletResponse response){
//		GxWebClient gxWebClient = new GxWebClient();
//		List<ClientInfo> gxServiceInfo = gxWebClient.getGxServiceInfo();
//		gxWebClient.check(gxServiceInfo.get(0));
		
		
		
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	} ;
}
