package com.gpdi.gx;



import java.net.URL;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gpdi.common.util.Constants;
import com.gpdi.common.util.SoapUtil;
import com.gpdi.gx.adapter.InnerAdapterImpl;
import com.gpdi.gx.adapter.InnerAdapter_Service;
import com.gpdi.gx.adapter.InnerAdapter_ServiceLocator;


public class SmsService {

	private static InnerAdapterImpl impl = null;
	
	// 取得EOMS短信服务实例
	public static InnerAdapterImpl getInnerAdapterImpl(ClientInfo messageServiceInfo) throws ServiceException {
		if(impl==null){
			URL endpoint = SoapUtil.getEndpointAddr(messageServiceInfo.getEndPoint());
			InnerAdapter_Service innerAdapter = new InnerAdapter_ServiceLocator();
			
			if (endpoint == null) {
				impl = innerAdapter.getInnerAdapter();
			} else {
				impl = innerAdapter.getInnerAdapter(endpoint);
			}
		}
		
		return impl;
	}
	
	/**
	 * 特殊字符转换
	 * @param str
	 * @return
	 */
	public static String repXmlChar(String str){ 
        if(null==str||"".equals(str)){
        	return ""; 
        }else{
	        str = str.replaceAll("&","&amp;"); 
	        str = str.replaceAll(">", "&gt;"); 
	        str = str.replaceAll("<", "&lt;"); 
	        str = str.replaceAll("\"", "&quot;"); 
        }
        return str; 
    } 
}
