package com.gpdi.gx;

public class CheckInfo {
	

	public int timedSend;//发送间隔（秒）
	public int errorCount;//检验的错误次数
	public int checkedCount;//检验总数
	
	public int getTimedSend() {
		return timedSend;
	}
	public void setTimedSend(int timedSend) {
		this.timedSend = timedSend;
	}
	public int getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}
	public int getCheckedCount() {
		return checkedCount;
	}
	public void setCheckedCount(int checkedCount) {
		this.checkedCount = checkedCount;
	}
	
}
