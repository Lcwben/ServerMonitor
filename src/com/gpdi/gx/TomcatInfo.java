package com.gpdi.gx;

public class TomcatInfo {

	private String jmxServiceUrl; //tomcat开放的远程监听端口url
	private String tomcatProccessorName; //tomcat Proccessor名称
	
	public static Integer RECOVERCOUNTRESET; //重新统计状态的间隔周期模板（用于重置周期）
	public static Double THREAD_BUSYRATE; //线程繁忙率阈值
	public static Double MEM_BUSYRATE; //线程繁忙率阈值
	
	private Integer recoverCount; //发短信后，恢复到重新统计状态的间隔周期
	private Boolean hasSendMsg; //是否已发送短信标志
	
	private String tomcatAddr;

	private String[] alertTypeArr;

	
	public String[] getAlertTypeArr() {
		return alertTypeArr;
	}

	public void setAlertTypeArr(String[] alertTypeArr) {
		this.alertTypeArr = alertTypeArr;
	}

	public String getTomcatAddr() {
		return this.tomcatAddr;
	}

	public void setTomcatAddr(String tomcatAddr) {
		this.tomcatAddr = tomcatAddr;
	}

	public String getJmxServiceUrl() {
		return this.jmxServiceUrl;
	}

	public void setJmxServiceUrl(String jmxServiceUrl) {
		this.jmxServiceUrl = jmxServiceUrl;
	}

	public String getTomcatProccessorName() {
		return this.tomcatProccessorName;
	}

	public void setTomcatProccessorName(String tomcatProccessorName) {
		this.tomcatProccessorName = tomcatProccessorName;
	}

	public Integer getRecoverCount() {
		return recoverCount;
	}

	public void setRecoverCount(Integer recoverCount) {
		this.recoverCount = recoverCount;
	}

	public Boolean getHasSendMsg() {
		return hasSendMsg;
	}

	public void setHasSendMsg(Boolean hasSendMsg) {
		this.hasSendMsg = hasSendMsg;
	}
	
	

}
