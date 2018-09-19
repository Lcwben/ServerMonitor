package com.gpdi.gx;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * DB状态记录对象
 * 注：TotalCount（测试状态计数器）>=10次时，自动重新设0
 * 
 * @author liangchengwei
 *
 */
public class DBStatusInfo {

	private Integer errorCount = 0; //错误次数统计值
	private Integer totalCount = 0; //测试次数统计值
	private boolean hasSendMsg = false; //是否已发短信标志
	private Integer recoverCount; //发送短信后到下次发短信的恢复周期
	
	public static Integer MAXERRORCOUNT; //错误次数阈值
	public static Integer MAXTOTALCOUNT; //最大累计测试数周期
	public static Integer MAXRECOVERCOUNT; //恢复周期阈值
	
	
//	public static Integer getMAXRECOVERCOUNT() {
//		return MAXRECOVERCOUNT;
//	}
//	public static void setMAXRECOVERCOUNT(Integer mAXRECOVERCOUNT) {
//		MAXRECOVERCOUNT = mAXRECOVERCOUNT;
//	}
//	public static Integer getMAXERRORCOUNT() {
//		return MAXERRORCOUNT;
//	}
//	public static void setMAXERRORCOUNT(Integer mAXERRORCOUNT) {
//		MAXERRORCOUNT = mAXERRORCOUNT;
//	}
//	public static Integer getMAXTOTALCOUNT() {
//		return MAXTOTALCOUNT;
//	}
//	public static void setMAXTOTALCOUNT(Integer mAXTOTALCOUNT) {
//		MAXTOTALCOUNT = mAXTOTALCOUNT;
//	}

	static {
		Properties pps = new Properties();
		 InputStream resourceAsStream = null;
		 try {
			resourceAsStream = DBStatusInfo.class.getResourceAsStream("DBStatusInfo.properties");
			pps.load(resourceAsStream);
			MAXERRORCOUNT = Integer.parseInt((String)pps.getProperty("maxErrorCount"));
			MAXTOTALCOUNT = Integer.parseInt((String)pps.getProperty("maxTotalCount"));
			MAXRECOVERCOUNT = Integer.parseInt((String)pps.getProperty("maxRecoverCount"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resourceAsStream!=null) {resourceAsStream.close();}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public Integer getRecoverCount() {
		return recoverCount;
	}
	public void setRecoverCount(Integer recoverCount) {
		if(recoverCount > 0 && this.hasSendMsg==true) {
			this.recoverCount = recoverCount;
		} else if(hasSendMsg==true) {
			this.recoverCount = DBStatusInfo.MAXRECOVERCOUNT;
			this.hasSendMsg = false;
		} else {
			this.recoverCount = null;
		}

	}
	public boolean isHasSendMsg() {
		return hasSendMsg;
	}
	public void setHasSendMsg(boolean hasSendMsg) {
		this.hasSendMsg = hasSendMsg;
	}
	public Integer getErrorCount() {
		return errorCount;
	}
	
	public void setErrorCount(Integer errorCount) {
		if(errorCount > DBStatusInfo.MAXTOTALCOUNT) {
			this.errorCount = 0;
		} else {
			this.errorCount = errorCount;			
		}
	}
	
	public Integer getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(Integer totalCount) {
		if(totalCount > DBStatusInfo.MAXERRORCOUNT) { //测试状态计数器>=配置的最大出错值时，重新设0
			totalCount = 0;
		} else {
			this.totalCount = totalCount;		
		}
	}
	
	
}
