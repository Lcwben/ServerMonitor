package com.gpdi.base.servers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckCodeServlet extends HttpServlet {
	
	private int width = 65 ; //设置图片宽度
	private int height = 22 ; //设置图片高度

	public void doGet(HttpServletRequest request , HttpServletResponse response){
		this.doPost(request, response) ;
	} ;
	
	public void doPost(HttpServletRequest request , HttpServletResponse response){
		
		HttpSession session = request.getSession() ;
		response.setContentType("image/jpg") ;
		
		
		
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
