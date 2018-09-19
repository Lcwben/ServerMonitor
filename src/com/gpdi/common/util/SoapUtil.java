package com.gpdi.common.util;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
//import java.io.StringBufferInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.apache.axis.client.Call;


public class SoapUtil {
	
	public static Call addSoapHandle(Call call){
		String sid = UUID.randomUUID().toString();
		SoapRepHandle rep = new SoapRepHandle(sid);
		SoapReqHandle req = new SoapReqHandle(sid , call.getTargetEndpointAddress());
		call.setClientHandlers(req, rep);
			//System.out.println("已添加handle");
		return call;
	}
	//将将SOAP消息对象转换为字符串
	public static String cover2Str(SOAPMessage msg) throws SOAPException{
		ByteArrayOutputStream myByteArrayOutputString = new ByteArrayOutputStream();
		try {
			msg.writeTo(myByteArrayOutputString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String soapMessage = null;
		try {
			soapMessage = new String(myByteArrayOutputString.toByteArray(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return soapMessage;
	}
	//发送soap消息
	public static String sentSOAPMsg(String sopaMsg , String endPointAddr) throws Exception
	{
		String respon = null;
		//InputStream in = new StringBufferInputStream(sopaMsg);
		
		InputStream in = new ByteArrayInputStream(sopaMsg.getBytes("UTF-8"));
		//创建SOAP消息对象
		MessageFactory factory = MessageFactory.newInstance();
		SOAPMessage message = factory.createMessage(new MimeHeaders() , in);
		//目标地址
		URL endpoint = new URL(endPointAddr);
		//创建连接
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
			.newInstance();
		SOAPConnection connection = soapConnectionFactory
			.createConnection();
		//返回信息
		SOAPMessage response = connection.call(message, endpoint);
		//关闭连接
		connection.close();
		respon = cover2Str(response);
		
		return respon;
	}
	//判断ws调用是否成功
	public static boolean checkSuccess(String response , String endpointAddress)
	{
//		if (response != null && endpointAddress != null)
//		{
//			String returnStr = null;
//			//取得成功返回结果配置
//			String sucFlag = getSOAPVarvalue(getServiceName(endpointAddress) + Constants.SUFFIX_SUC_RESP);
//			if (sucFlag != null)
//			{
//				try {
//					returnStr = XmlUtil.getXMLLeaf2Map(response, XmlUtil.getEncoding(response))
//						.get("return");
//					//result = XmlUtil.getXMLLeaf2Map(returnStr, XmlUtil.getEncoding(returnStr)).get("rtCode");
//					if (returnStr != null)
//					{
//						return formalStr(returnStr).contains(sucFlag);
//					}	
//				} catch (DocumentException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
		return false;
	}
	//判断接口自动重发机制是否打开
	public static boolean resentable(String endpointAddress)
	{
		if (endpointAddress != null)
		{
			String serviceName = getServiceName(endpointAddress);
			return checkResentableByName(serviceName);
		}
		return false;
	}
	//读取定时调度服务开关配置 如果数据库无记录，默认为关
	public static boolean checkWorkingByName(String serviceName)
	{
		boolean flag = false;
		if (serviceName != null)
		{
			String working = getSOAPVarvalue(serviceName + Constants.SUFFIX_WORKING);
			if (working != null)
			{
				flag = Constants.WORKING.equalsIgnoreCase(working);
				//System.out.println(flag);
			}
		}
		return flag;
	}
	//读取本地WS接口开关配置 如果数据库无记录或者服务名为空，默认为开
	public static boolean checkWSWorkingByName(String serviceName)
	{
		boolean flag = true;
		if (serviceName != null)
		{
			String working = getSOAPVarvalue(serviceName + Constants.SUFFIX_WORKING);
			if (working != null)
			{
				flag = Constants.WORKING.equalsIgnoreCase(working);
				//System.out.println(flag);
			}
		}
		return flag;
	}
	public static boolean checkResentableByName(String serviceName)
	{
		boolean flag = false;
		if (serviceName != null)
		{
			String working = getSOAPVarvalue(serviceName + Constants.SUFFIX_AUTO_RESENT);
			if (working != null)
			{
				flag = Constants.AUTO_RESENT.equalsIgnoreCase(working);
			}
		}
		return flag;
	}
	//取得服务名
	private static String getServiceName(String endpointAddress)
	{
		String serviceName = "";
		if (endpointAddress != null)
		{
			//取得service名
			serviceName = endpointAddress.substring(endpointAddress.lastIndexOf("/") + 1);
		}
		return serviceName;
	}
	//处理转义字符
	private static String formalStr(String str)
	{
		if (str != null)
		{
			str = str.replace("&lt;", "<");
			str = str.replace("&gt;", ">");
		}
		return str;
	}
	//获取数据库中SOAP相关的配置信息
	public static String getSOAPVarvalue(String varname)
	{
//		SysVarService sysVarService = (SysVarService)SpringContextUtil.getBean("sysVarService");
//		return sysVarService.getVar(varname);
		return null;
	}
	public static URL getEndpointAddr(String addr)
	{
		URL endpoint = null;
		if (addr!=null && addr.length()>0){
	        try {
	            endpoint = new URL(addr);
	        }
	        catch (MalformedURLException e) {
	        	e.printStackTrace();
	        }
		}
        return endpoint;
	}
	public static boolean getSsoDebug(String varName) {
		boolean flag = false;
		if (varName != null){
			String working = getSOAPVarvalue(varName);
			if (working != null){
				flag = Constants.WORKING.equalsIgnoreCase(working);
			}
		}
		return flag;
	}
}
