package com.gpdi.common.util;



import java.util.ArrayList;
import java.util.List;

/**
 * 系统相关常量
 * 
 * @author Dong
 * 
 */
public class Constants {
	/******** 地市版首页使用的静态常量，省版暂时无用 ********/
	// 定义功能树父节点Id为0(必须与数据库相对应)
	public static int TreeRootId = 0;

	// 主页默认模块
	public static int defaultTabbar = 30000;

	// 定义页面总宽度
	public static int SCGREEN_WIDTH = 970;

	// 定义每一格功能模板的宽度,单位:像素
	public static int MODULE_CELL_WIDTH = 83;

	// 定义grid每页显示多少条记录
	public static int PAGESIZE = 20;

	// 首页jfreechart报表图片标题属性名称
	public static String[] JFC_PIC_TITLE = { "pic1Title", "pic2Title",
			"pic3Title", "pic4Title" };
	
	/** *******SysStatusUtil Start********************* */
	public static final String SPC_STATION_STATIONTYPE = "SPC_STATION_STATIONTYPE";
	public static final String SPC_MACROOM_ROOMTYPE = "SPC_MACROOM_ROOMTYPE";
	public static final String SPC_MACROOM_ROOMPROPERTY = "2PROPERTYTYPE";
	/** *******SysStatusUtil End********************* */
	
	/******** 地市版首页使用的静态常量，END ********/
	
	/** *******日志 Start********************* */
	/**
	 * 用户登陆后用于在session中存放登陆流水号的名称
	 */
	public static final String LOG_LOGIN_USER_ID = "loginUserId";
	/** *******日志 End********************* */


	// 传输网管系统同步接口标识常量
	public static String SYNID;

	//传输网管系统同步接口常量
	public static List<String> TNMS_FILES = new ArrayList<String>();
	
	//本应用IP
	public static String localIP;
	//本应用端口
	public static String localPort;
	
	//标准地址模块单点登录
	public static final String VALIDATE_LOGIN = "VALIDATE_LOGIN";
	
	/** 接口配置相关 对应于infore_workflow用户的sys_var 表中varName字段，都需要是大写 **/
	//后缀
	public static final String SUFFIX_ADDR = "_ADDR";//接口地址后缀
	public static final String SUFFIX_SUC_RESP = "_SUC_RESP";//接口成功调用返回结果后缀
	public static final String SUFFIX_WORKING = "_WORKING";//接口是否开启后缀
	public static final String SUFFIX_AUTO_RESENT = "_AUTO_RESENT";//接口是否自动重发后缀
	public static final String WORKING = "T";//接口开
	public static final String NO_WORKING = "F";//接口关
	public static final String AUTO_RESENT = "T";//自动重发开
	public static final String NO_AUTO_RESENT = "F";//自动重发关
	//传输网管光缆割接影响分析接口
	public static final String POMSFORTNMSSERVICE = "POMSFORTNMSSERVICE";//光缆割接任务接口 网管调用推送影响结果
	public static final String POMSFORPIPELINESERVICE = "POMSFORPIPELINESERVICE";//光缆割接接口 管线调用发送采集任务
	
	public static final String PUSHMARKSWORKINFOSERVICE = "PUSHMARKSWORKINFOSERVICE"; //光缆割接接口 光缆段影响网元告警标工服务  名称
	public static final String PUSHMARKSWORKINFOSERVICE_ADDR = "PUSHMARKSWORKINFOSERVICE_ADDR";//光缆割接接口 光缆段影响网元告警标工服务 地址
	public static final String PUSHMARKSWORKINFOSERVICE_AUTO = "PUSHMARKSWORKINFOSERVICE_WORKING";//光缆割接接口 光缆段影响网元告警标工服务 开关
	
	public static final String OPTICALXIRMSSERVICE = "OPTICALXIRMSSERVICE";//接口类型名
	public static final String OPTICALXIRMSSERVICE_ADDR = "OPTICALXIRMSSERVICE_ADDR";//接口地址
	public static final String OPTICALXIRMSSERVICE_SUC_RESP = "OPTICALXIRMSSERVICE_SUC_RESP";//接口成功调用返回结果
	public static final String OPTICALXIRMSSERVICE_AUTO_RESENT = "OPTICALXIRMSSERVICE_AUTO_RESENT";//接口自动重发是否开启 false:关、true：开
	//综合资管业务开通接口
	public static final String GENERALPOMSSERVICEQUERYSERVICE = "GENERALPOMSSERVICEQUERYSERVICE";//业务开通管线接口发布服务名
	public static final String POMSADAPTOR = "POMSADAPTOR";//接口类型名
	public static final String POMSADAPTOR_ADDR = "POMSADAPTOR_ADDR";//接口地址
	public static final String POMSADAPTOR_SUC_RESP = "POMSADAPTOR_SUC_RESP";//接口成功调用返回结果
	public static final String POMSADAPTOR_AUTO_RESENT = "POMSADAPTOR_AUTO_RESENT";//接口自动重发是否开启 false:关、true：开
	//EOMS用户信息同步接口
	public static final String EOMSUSERMGRSERVICE = "EOMSUSERMGRSERVICE";//EOMS系统用户查询服务接口发布服务名
	public static final String INFORESUSERMGRSERVICE = "INFORESUSERMGRSERVICE";//管线系统用户信息同步服务接口发布服务名
	public static final String USERMGRIF = "USERMGRIF";//接口类型名
	public static final String USERMGRIF_ADDR = "USERMGRIF_ADDR";//接口地址
	public static final String USERMGRIF_SUC_RESP = "USERMGRIF_SUC_RESP";//接口成功调用返回结果
	public static final String USERMGRIF_AUTO_RESENT = "USERMGRIF_AUTO_RESENT";//接口自动重发是否开启 false:关、true：开
	//EOMS用户远程登录校验接口
	public static final String REMOTINGLOGINSERVICE = "REMOTINGLOGINSERVICE";//接口发布服务名
	public static final String SSOSERVICE = "SSOSERVICE";//接口类型名
	public static final String SSOSERVICE_ADDR = "SSOSERVICE_ADDR";//接口地址
	
	//EOMS光缆割接公告新版(页面集成)
	public static final String OPTCABLECUT_INTEGRATED_URL = "OPTCABLECUT_INTEGRATED_URL";//集成的URL地址
	public static final String INTEGRATED_URL_DEBUG = "INTEGRATED_URL_DEBUG";//集成的URL是否开启debug模式
	public static String NEW_ANNOUNCEMENT_ENABLE = "system.config.newAnnouncement.enable";//新版光缆割接公告开关  false:关、true：开
	public static String SSL_STORE_URL = "javax.net.ssl.store.url";//光缆割接sso单点证书信任库url地址
	public static String SSL_STORE_PASSWORD = "javax.net.ssl.store.password";//光缆割接sso单点证书信任库密码
	public static String OPTCABLECUT_SSO_USERNAME = "eoms.optcablecut.sso.username";//光缆割接sso单点用户名
	public static String OPTCABLECUT_SSO_PASSWORD = "eoms.optcablecut.sso.password";//光缆割接sso单点密码
	//EOMS账号中心集成
	public static String ACCOUNTCENTER_SSO_USERNAME = "accountcenter.sso.username";//账号中心sso单点用户名
	public static String ACCOUNTCENTER_SSO_PASSWORD = "accountcenter.sso.password";//账号中心sso单点密码
	
	//EOMS光缆割接公告推送接口
	public static final String OUTERADAPTER = "OUTERADAPTER";//接口类型名
	public static final String OUTERADAPTER_ADDR = "OUTERADAPTER_ADDR";//接口地址
	public static final String OUTERADAPTER_SUC_RESP = "OUTERADAPTER_SUC_RESP";//接口成功调用返回结果
	public static final String OUTERADAPTER_AUTO_RESENT = "OUTERADAPTER_AUTO_RESENT";//接口自动重发是否开启 false:关、true：开
	//空间资源同步接口
	public static final String SYNCLOCATIONSERVICE = "SYNCLOCATIONSERVICE";//接口发布服务名
	//综资根据网元查询基站接口地址
	public static final String RESATRSATIONWSDL = "RESSHARE2EOMSSERVICE.WSURL";
	//EOMS短信发送推送接口
	public static final String INNERADAPTER = "INNERADAPTER";//接口类型名
	public static final String INNERADAPTER_ADDR = "INNERADAPTER_ADDR";//接口地址
	public static final String INNERADAPTER_SUC_RESP = "INNERADAPTER_SUC_RESP";//接口成功调用返回结果
	public static final String INNERADAPTER_AUTO_RESENT = "INNERADAPTER_AUTO_RESENT";//接口自动重发是否开启 false:关、true：开
	//IDC影响范围下属支持人员同步.//IDC影响范围同步(EOMS >POMS)
	public static final String POMS4EOMSADAPTER_FLAT_A ="A";//新增
	public static final String POMS4EOMSADAPTER_FLAT_U ="U";//新增
	public static final String POMS4EOMSADAPTER_FLAT_D ="D";//新增
	//接入点快速查询接口
	public static final String IRMS_QUERYTRANSDEVICEINFO2="IRMS_QUERYTRANSDEVICEINFO2";//接口开关
	
	
	
	//惠州派送短信接口
	//public static final String NOTIFY = "NOTIFY";//接口类型名
	//public static final String NOTIFY_ADDRE = "NOTIFY_ADDRE";//接品地址
	//public static final String NOTITY_SUC_RESP = "NOTITY_SUC_RESP";//接口成功调用返回结果
	//public static final String NOTITY_AUTO_RESENT = "NOTITY_AUTO_RESENT";//接口自动重发是否开启 false 关 true 开
	
	//工单系统下发华为接口
	public static final String IODN_ORDERSEND = "IODN_ORDERSEND";//接口类型名
	public static final String IODN_ORDERSEND_ADDR = "IODN_ORDERSEND_ADDR";//接品地址
	public static final String IODN_ORDERSEND_SUC_RESP = "IODN_ORDERSEND_SUC_RESP";//接口成功调用返回结果
	public static final String IODN_ORDERSEND_WORKING = "IODN_ORDERSEND_WORKING";//工单系统下发华为接口服务开关
	public static final String IODN_ORDERSEND_AUTO_RESENT = "IODN_ORDERSEND_AUTO_RESENT";//工单系统下发华为接口自动重发开关
    public static final String IODN_SENDROUTEWOORKLIST_ADDR="SENDROUTEWORKLIST";//接口服开关

	//家客预覆盖服开光路配置接口
	public static final String RM2SGSERVICE= "RM2SGSERVICE";//接口类型名
	public static final String RM2SGSERVICE_ADDR= "RM2SGSERVICE_ADDR";//接口地址
	public static final String RM2SGSERVICE_SUC_RESP= "RM2SGSERVICE_SUC_RESP";//接口成功调用返回结果
	public static final String RM2SGSERVICE_WORKING= "RM2SGSERVICE_WORKING";//接口服务开关
	public static final String RM2SGSERVICE_AUTO_RESENT= "RM2SGSERVICE_AUTO_RESENT";//接口自动重发开关
	//EOMS与管线系统城域网网元接口  

	public static final String EOMSOPERATIONSERVICE = "EOMSOPERATIONSERVICE";//接口发布服务名
	public static final String PROCESS_PPTSERVICE = "PROCESS_PPTSERVICE";//接口类型名
	public static final String RMTOEOMSUSERNAME = "RMTOEOMSUSERNAME";//连接用户名
	public static final String RMTOEOMSPASSWORD = "RMTOEOMSPASSWORD";//连接密码
	public static final String PROCESS_PPTSERVICE_ADDR = "PROCESS_PPTSERVICE_ADDR";//连接地址
	public static final String PROCESS_PPTSERVICE_SUC_RESP = "PROCESS_PPTSERVICE_SUC_RESP";//调用成功表示
	public static final String PROCESS_PPTSERVICE_WORKING = "PROCESS_PPTSERVICE_WORKING";//服务开关
	public static final String PROCESS_PPTSERVICE_AUTO_RESENT = "PROCESS_PPTSERVICE_AUTO_RESENT";//自动重发开发
	
	//Restar与管线系统城域网元生命周期接口
	public static final String RESTAROPERATIONSERVICE = "RESTAROPERATIONSERVICE";//接口发布服务名
	public static final String POMSADAPTORSERVICE = "POMSADAPTORSERVICE";//接口类型名
	public static final String POMSADAPTORSERVICE_ADDR = "POMSADAPTORSERVICE_ADDR";//连接地址
	public static final String POMSADAPTORSERVICE_SUC_RESP = "POMSADAPTORSERVICE_SUC_RESP";//调用成功表示
	public static final String POMSADAPTORSERVICE_WORKING = "POMSADAPTORSERVICE_WORKING";//服务开关
	public static final String POMSADAPTORSERVICE_AUTO_RESENT = "POMSADAPTORSERVICE_AUTO_RESENT";//自动重发开关
	
	//WLAN
	public static final String WLANADAPTORSERVICE = "WLANADAPTORSERVICE";//WLAN接口类型名
	
	//光缆割接回退接口类型
	public static final String OPTCUT_ROLLBACK ="CutSpliceRollback";
	
	//测试接口
	public static final String TESTWEBSERVICE = "TESTWEBSERVICE";//接口类型名
	public static final String TESTWEBSERVICE_ADDR = "TESTWEBSERVICE_ADDR";//接口地址
	public static final String TESTWEBSERVICE_SUC_RESP = "TESTWEBSERVICE_SUC_RESP";//接口成功调用返回结果
	public static final String TESTWEBSERVICE_WORKING = "TESTWEBSERVICE_WORKING";//接口是否开启 false:关、true：开
	public static final String TESTWEBSERVICE_AUTO_RESENT = "TESTWEBSERVICE_AUTO_RESENT";//接口自动重发是否开启 false:关、true：开
	/**FTP相关**/
	//管线系统FTP
	public static final String FTP_POMS_IP = "FTP_POMS_IP";//管线FTP服务器IP地址和端口号
	public static final String FTP_POMS_USER = "FTP_POMS_USER";//管线FTP服务器账户
	public static final String FTP_POMS_PASS = "FTP_POMS_PASS";//管线FTP服务器密码
	public static final String FTP_POMS_CSWG = "FTP_POMS_CSWG";//管线FTP服务器传输网管传输数据文件夹
	public static final String FTP_POMS_WGYYXN = "FTP_POMS_WGYYXN";//管线FTP服务器卓越门户网管应用性能数据文件夹
	public static final String WGYYXN_REMOTEFOLDER = "WGYYXN_REMOTEFOLDER";//网管应用效能接口的FTP文件目录
	public static final String WGYYXN_UPLOADFILES = "WGYYXN_UPLOADFILES";//网管应用效能接口上传文件的模板路径
	
	//华为上报资源数据接口
	public static final String FTP_IODN_IP="FTP_IODN_IP";//华为上报资源数据接口IP地址
	public static final String FTP_IODN_PORT="FTP_IODN_PORT";//华为上报资源数据接口端口号
	public static final String FTP_IODN_USERNAME="FTP_IODN_USERNAME";//华为上报资源数据接口账户
	public static final String FTP_IODN_PASSWORD="FTP_IODN_PASSWORD";//华为上报资源数据接口密码
	public static final String FTP_IODN_FOLDER="FTP_IODN_FOLDER";//华为上报资源数据接口FTP本地文件目录
	public static final String FTP_IODN_REMOTEFILE="FTP_IODN_REMOTEFILE";//华为上报资源数据接口FTP远程文件目录
	public static final String DB_IODN_USERNAME="DB_IODN_USERNAME";//华为上报资源数据接口数据库账户
	public static final String DB_IODN_PASSWORD="DB_IODN_PASSWORD";//华为上报资源数据接口数据库密码
	public static final String DB_IODN_INSTANCE="DB_IODN_INSTANCE";//华为上报资源数据接口数据库实例名
	public static final String FTP_IODN_FILES_NAMES="FTP_IODN_FILES_NAMES";//华为上报资源数据接口文件名
	
	//管线数据同步接口
	public static final String PIPELINEDATASYNCSERVER = "PIPELINEDATASYNCSERVER";//接口类型名
	public static final String PIPELINE_DATA_SYNC_WORKING = "PIPELINE_DATA_SYNC_WORKING";//接口是否开启 false:关、true：开
	public static final String FTP_PIPELINE_DATA_SYNC_IP = "FTP_PIPELINE_DATA_SYNC_IP";//FTP IP与端口
	public static final String FTP_PIPELINE_DATA_SYNC_USER = "FTP_PIPELINE_DATA_SYNC_USER";//FTP 用户名
	public static final String FTP_PIPELINE_DATA_SYNC_PASS = "FTP_PIPELINE_DATA_SYNC_PASS";//FTP 密码
	public static final String PIPELINE_DATA_SYNC_REMOTEFOLDER = "PIPELINE_DATA_SYNC_REMOTEFOLDER";//传输管线接口FTP文件目录
	public static final String PIPELINE_DATA_SYNC_UPLOADFILES = "PIPELINE_DATA_SYNC_UPLOADFILES";//传输管线接口上传文件的模板路径
	public static final String PIPELINE_DATA_SYNC_DELETETEMPFILE = "PIPELINE_DATA_SYNC_DELETETEMPFILE";//传输管线接口删除临时文件路径
	public static final String PIPELINE_DATA_SYNC_DBUSERNAME= "PIPELINE_DATA_SYNC_DBUSERNAME";//查询表所在数据库用户名
	public static final String PIPELINE_DATA_SYNC_DBPASSWORD = "PIPELINE_DATA_SYNC_DBPASSWORD";//数据库密码
	public static final String PIPELINE_DATA_SYNC_DBHOST1 = "PIPELINE_DATA_SYNC_DBHOST1";//连接数据库IP 1
	public static final String PIPELINE_DATA_SYNC_DBHOST2 = "PIPELINE_DATA_SYNC_DBHOST2";//连接数据库IP 2
	public static final String PIPELINE_DATA_SYNC_SERVICE_NAME = "PIPELINE_DATA_SYNC_SERVICE_NAME";//连接数据库服务名
	public static final String PIPELINE_DATA_SYNC_PAGENUM = "PIPELINE_DATA_SYNC_PAGENUM";//一个文件的最多数据量

	public static final String DLCX_REMOTEFOLDER = "DLCX_REMOTEFOLDER";//电路查询接口的FTP文件目录
	public static final String DLCX_LOCALFOLDER = "DLCX_LOCALFOLDER";//电路查询接口的本地文件目录

	//EOMS系统FTP
	public static final String FTP_EOMS_IP = "FTP_EOMS_IP";//EOMS FTP服务器IP端口
	public static final String FTP_EOMS_USER = "FTP_EOMS_USER";//EOMS FTP服务器账户
	public static final String FTP_EOMS_PASS = "FTP_EOMS_PASS";//EOMS FTP服务器密码
	
	//自动重发SOAP消息服务
	public static final String SOAPMSGSERVER = "SOAPMSGSERVER";//接口类型名
	public static final String SOAPMSGSERVER_WORKING = "SOAPMSGSERVER_WORKING";//接口是否开启 false:关、true：开
	public static final String LOG_WEBCLIENT = "LOG_WEBCLIENT";//调用外系统接口日志记录开关；
	public static final String LOG_WEBSERVICE = "LOG_WEBSERVICE";//被外系统调用接口日志记录开关；
	
	//自动同步传输数据服务
	public static final String UPDATETNMSDATA = "UPDATETNMSDATA";//接口类型名
	public static final String UPDATETNMSDATA_WORKING = "UPDATETNMSDATA_WORKING";//接口是否开启 false:关、true：开
	
	//自动获取影响电路服务
	public static final String TRAPHINFOQUERY = "TRAPHINFOQUERY";//接口类型名
	public static final String TRAPHINFOQUERY_WORKING = "TRAPHINFOQUERY_WORKING";//接口是否开启 false:关、true：开
	
	//PON数据服务
	public static final String UPDATEPONDATA = "UPDATEPONDATA";//接口类型名
	public static final String UPDATEPONDATA_WORKING = "UPDATEPONDATA_WORKING";//接口是否开启 false:关、true：开
	
	//佛山鑫干线数据核查回传接口
	public static final String FSXGXVDT = "FSXGX_VDT";//接口类型名
	public static final String FSXGX_VDT_WORKING = "FSXGX_VDT_WORKING";//接口是否开启 false:关、true：开
	
	//验收流程推送代维公司验收人日接口
	public static final String YSTIMESENDSERVICE = "YSTIMESENDSERVICE";//接口类型名
	public static final String YSTIMESENDSERVICE_ADDR = "YSTIMESENDSERVICE_ADDR";//接口地址
	public static final String YSTIMESENDSERVICE_SUC_RESP = "YSTIMESENDSERVICE_SUC_RESP";//接口成功调用返回结果
	public static final String YSTIMESENDSERVICE_WORKING = "YSTIMESENDSERVICE_WORKING";//接口是否开启 false:关、true：开
	public static final String YSTIMESENDSERVICE_AUTO_RESENT = "YSTIMESENDSERVICE_AUTO_RESENT";//接口自动重发是否开启 false:关、true：开
	
	//自动同步网管应用效能数据服务
	public static final String POMSUPLOADSERVER = "POMSUPLOADSERVER";//接口类型名
	public static final String POMSUPLOADSERVER_WORKING = "POMSUPLOADSERVER_WORKING";//接口是否开启 false:关、true：开
	
	//管线与综合监控系统接口
	public static final String MONITORUPLIADSERVER = "MONITORUPLIADSERVER";//接口类型名
	public static final String MONITORUPLIADSERVER_WORKING = "MONITORUPLIADSERVER_WORKING";//接口是否开启 false:关、true：开
	public static final String FTP_MONITOR_IP = "FTP_MONITOR_IP";//FTP IP与端口
	public static final String FTP_MONITOR_USER = "FTP_MONITOR_USER";//FTP 用户名
	public static final String FTP_MONITOR_PASS = "FTP_MONITOR_PASS";//FTP 密码
	public static final String MONITOR_REMOTEFOLDER = "MONITOR_REMOTEFOLDER";//传输管线接口FTP文件目录
	public static final String MONITOR_UPLOADFILES = "MONITOR_UPLOADFILES";//传输管线接口上传文件的模板路劲
	
	public static final String STATISTICS_UPLOADFILES = "STATISTICS_UPLOADFILES";//SYS_STATISTICS_LOG上传文件的模板路劲
	public static final String STATISTICS_REMOTEFOLDER = "STATISTICS_REMOTEFOLDER";//上传到ftp的路径statistics_hour_remotefolder
	public static final String STATISTICS_HOUR_REMOTEFOLDER = "STATISTICS_HOUR_REMOTEFOLDER";//上传到ftp的路径
	public static final String FTP_STATISTICS_USER = "FTP_STATISTICS_USER";//SYS_STATISTICS_LOG FTP用户名
	public static final String FTP_STATISTICS_PASS = "FTP_STATISTICS_PASS";//SYS_STATISTICS_LOG FTP密码
	public static final String FTP_STATISTICS_IP = "FTP_STATISTICS_IP"; //SYS_STATISTICS_LOG IP
	public static final String STATISTICS_MONTH_UPLOADFILES = "STATISTICS_MONTH_UPLOADFILES"; //系统访问量上传路径
	public static final String STATISTICSUPLIADSERVER = "STATISTICSUPLIADSERVER"; //接口服务开关配置
	
	
	
	public static final String MONITOR_DELETETEMPFILE = "MONITOR_DELETETEMPFILE";//传输管线接口删除临时文件路劲
	public static final String SMSSERVER = "SMSSERVER";//接口类型名
	
	//自动重发SOAP消息服务
    public static final String PDAOFFLINESERVER = "PDAOFFLINESERVER";//接口类型名
	
	public static final String SMSSERVER_WORKING = "SMSSERVER_WORKING";//接口是否开启 false:关、true：开
	
	//流程短信接口 
	public static final String MSGSERVER = "MSGSERVER";//接口类型名
	public static final String MSGSERVER_WORKING = "MSGSERVER_WORKING";//接口是否开启 false:关 true ：开
	
	//工单系统下发光路工单给U2000 ODN网管系统接口
	//public static final String ORDERSENDSERVICE = "ORDERSENDSERVICE";
	
	public static final String ORDER_SEND_RESULT = "1";
	/**
	 * 施工反馈
	 */
	public static final String NODE_CON_COMBACK = "施工反馈";
	
	//自动重发SOAP消息服务
	public static final String EMAILSERVER = "EMAILSERVER";//接口类型名
	public static final String EMAILSERVER_WORKING = "EMAILSERVER_WORKING";//接口是否开启 false:关、true：开
	
	//--
	public static final String GXZYGK_PROCEDURE = "GXZYGK_PROCEDURE";//数据库用户名
	
	//传输数据同步接口FTP
	public static final String FTP_TNMS_IP="FTP_TNMS_IP";
	public static final String FTP_TNMS_PORT="FTP_TNMS_PORT";
	public static final String FTP_TNMS_USERNAME="FTP_TNMS_USERNAME";
	public static final String FTP_TNMS_PASSWORD="FTP_TNMS_PASSWORD";
	public static final String FTP_TNMS_FOLDER="FTP_TNMS_FOLDER";
	public static final String FTP_TNMS_REMOTEFILE="FTP_TNMS_REMOTEFILE";
	public static final String DB_TNMS_USERNAME="DB_TNMS_USERNAME";
	public static final String DB_TNMS_PASSWORD="DB_TNMS_PASSWORD";
	public static final String DB_TNMS_INSTANCE="DB_TNMS_INSTANCE";
	public static final String FTP_TNMS_FILES_NAMES="FTP_TNMS_FILES_NAMES";
	
	//--光缆割接公告推送接口中FTP
	public static final String EOMSUSERNAME="EOMSUSERNAME";//EOMS接口地址的用户名
	public static final String EOMSPASSWORD="EOMSPASSWORD";//EOMS接口地址的口令
	public static final String EMOSFTPHOST = "EMOSFTPHOST";//EOMS FTP服务器IP端口
	public static final String EMOSFTPPORT = "EMOSFTPPORT";//EOMS FTP服务器端口号
	public static final String EMOSFTPUSERNAME = "EMOSFTPUSERNAME";//EOMS FTP服务器帐号
	public static final String EMOSFTPPASSWORD = "EMOSFTPPASSWORD";//EOMS FTP服务器密码
	public static final String EMOSFTPREMOTEFOLDER = "EMOSFTPREMOTEFOLDER";//EOMS FTP服远程地址
	
	//传输数据同步接口FTP
	public static final String FTP_PON_IP="FTP_PON_IP";
	public static final String FTP_PON_PORT="FTP_PON_PORT";
	public static final String FTP_PON_USERNAME="FTP_PON_USERNAME";
	public static final String FTP_PON_PASSWORD="FTP_PON_PASSWORD";
	public static final String FTP_PON_FOLDER="FTP_PON_FOLDER";
	public static final String FTP_PON_INNERFOLDER="FTP_PON_INNERFOLDER";
	public static final String FTP_PON_REMOTEFILE="FTP_PON_REMOTEFILE";
	public static final String DB_PON_USERNAME="DB_PON_USERNAME";
	public static final String DB_PON_PASSWORD="DB_PON_PASSWORD";
	public static final String DB_PON_INSTANCE="DB_PON_INSTANCE";
	public static final String FTP_PONS_FILES_NAMES="FTP_PONS_FILES_NAMES";
	
	//数据核查回传接口FTP
	public static final String FTP_VDT_IP="FTP_VDT_IP";
	public static final String FTP_VDT_PORT="FTP_VDT_PORT";
	public static final String FTP_VDT_USERNAME="FTP_VDT_USERNAME";
	public static final String FTP_VDT_PASSWORD="FTP_VDT_PASSWORD";
	public static final String FTP_VDT_FOLDER="FTP_VDT_FOLDER";
	public static final String FTP_VDT_INNERFOLDER="FTP_VDT_INNERFOLDER";
	public static final String FTP_VDT_REMOTEFILE="FTP_VDT_REMOTEFILE";
	public static final String DB_VDT_USERNAME="DB_VDT_USERNAME";
	public static final String DB_VDT_PASSWORD="DB_VDT_PASSWORD";
	public static final String DB_VDT_INSTANCE="DB_VDT_INSTANCE";
	public static final String FTP_VDT_FILES_NAMES="FTP_VDT_FILES_NAMES";
	
	//统一网管平台邮件推送接口
	public static final String EMNOTIFY = "EMNOTIFY";//接口类型名
	
	public static final String WGYYXN_DELETETEMPFILE = "WGYYXN_DELETETEMPFILE";//删除临时文件路径
	
	//光功率采集任务定时请求服务
	public static final String POWERSERVICE = "POWERSERVICE";
	public static final String POWERS_PER_TIME = "POWERS_PER_TIME";//每次请求采集的光功率数量，默认500条
	/** 结束接口配置相关 **/
	
	//PDA升级包上传地址
	public static final String PDA_UPGRADE_ADDRESS = "PDA_UPGRADE_ADDRESS";
	public static final String PDA_UPGRADE_URL = "PDA_UPGRADE_URL";
	//邮件短信状态状态,0：未发送，1：已发送，2发送失败
	public static final String MAIL_STATUS_NOTSEND = "0"; 
	public static final String MAIL_STATUS_SUCESS = "1";
	public static final String MAIL_STATUS_FAILURE = "2";
	
	//邮件短信发送方式 0邮件，1短信
	public static final Long SEND_EMAIL_FLAG=0L;
	public static final Long SEND_SHORTMESSAGE_FLAG=1L;
	
	//邮件短信的状态
	public static final Long SEND_SUCESS = 1L;
	public static final Long SEND_FAIL = 2L;
	
	
	/***********EMOS发布信息***************/
	public static final Long SEND_EMOSSTATUS_NOTSEND=0L; //未发布
	public static final Long SEND_EMOSSTATUS_SUCESS=1L;//发布成功
	public static final Long SEND_EMOSSTATUS_REVOKE = 2L;//已撤销
	public static final Long SEND_EMOSSTATUS_FAILURE =3L;//发送失败
	public static final Long SEND_RELATESEND = 4L;//延迟发送
	public static final Long SEND_VERIFY_FAILURE = 5L;//校验失败(发布人,操作人校验)
	
	//FTP上传参数配置
	public static final String FTP_IP = "FTP_WORKSHEET_IP";
	public static final String FTP_PORT = "FTP_WORKSHEET_PORT";
	public static final String FTP_USER = "FTP_WORKSHEET_USERNAME";
	public static final String FTP_PASS = "FTP_WORKSHEET_PASSWORD";
	
	//传输管线接口
	public static final String OPTICROUTESERVICE = "OPTICROUTESERVICE";//接口发布服务名
	
	//光缆割接影响电路附件标识
	public static final Long FTP_GLGJ_FILE = -1L;
	
	
	/**
	 * 光缆割接是否要采集光功率
	 */
	public static final String POWER_WORKING = "OPTPOWER_WORKING";
	/**
	 * 光缆割接是否调用网管采集光功率
	 */
	public static final String OPTCABLE_COLLECTION_WORKING = "OPTCABLE_COLLECTION_WORKING";
	/**
	 * 检查数据导出接口--6050
	 */
	public static final String EXPORTCHECKDATA = "EXPORTCHECKDATA";
	
	public static final String EXPORTCHECKDATA_URL = "EXPORTCHECKDATA_URL";
	
	/***4A同步用户接口***/
	public static final String SYNC4AUSERSERVICE = "SYNC4AUSERSERVICE";
	
	/**
	 * 工单系统下发华为接口
	 */
	public static final String ORDER_SEND_STATUS = "0"; //表示成功
	
	public static final String ORDER_SEND_STATUS_OTHER = "1"; //表示失败
	public static final String ORDER_TASK_STATUS_SUCESS="0";//0 任务已完成
	
	/**
	 * 等待审核
	 */
	public static final String WAITING_FOR_AUDIT = "等待审核";
	
	/**发送与传输段关联子工单**/
	public static final String CORRELATION_SON_SIGNLE="发送与传输段关联子工单";
	
    //----------------时间后缀-----------------------
	/**
	 * 开始时间后缀
	 */
	public static final String START_TIME_SUFFIX = " 00:00:00";
	
	/**
	 * 结束时间后缀
	 */
	public static final String END_TIME_SUFFIX = " 23:59:59";
	
	/**
	 * 工单废弃的常量表示
	 */
	public static final String WORKSHEET_SCRAP = "-5";
	
	/**
	 * 选择状态(0:未,1:已)
	 */
	public static final String RES_ID = "1";
	
	/**
	 * 附件上传列表中的文件大小常量
	 */
	public static final Double BYTE_SIZE = 1024d;
	
	/**
	 * PDA资源入库失败常量表示
	 */
	public static final String FAILURE_RESOURCE_WAREHOUSING= "0";
	
	/**
	 * pda资源入库成功常量标识(1);
	 */
	public static final String SUCCESS_RESOURCE_WAREHOUSING = "1";

	/**
	 * PDA 网维审核 提交归档时 对核查资源进来校验的常量
	 */
	public static final String STORAGE_FAILURE = "入库失败";
	/***
	 * PDA资源入库前的确定通过
	 */
	public static final String SOURCE_THROUGH="2";
	
	
	/**
	 * PDA资源未选常量表示
	 */
	public static final String RES_STATUS_00 = "0";
	/**
	 * PDA资源已选常量表示
	 */
	public static final String RES_STATUS_01 = "1";
	/**
	 * PDA资源挂载常量表示
	 */
	public static final String RES_STATUS_02 = "2";
	
	/**
	 * PDA资源未核查常量表示0
	 */
	public static final String RES_VERIFY_STATUS_0="0";
	/**
	 * PDA资源已核查常量表示1
	 */
	public static final String RES_VERIFY_STATUS_1="1";
	
	/**
	 * PDA资源退回常量表示 对应数据库PDA_MAP_RESOURCE 表中的  res_verify_status 字段
	 */
	public static final String RES_VERIFY_STATUS_BACK="2";
	
	
	/**
	 * (光缆割接)(光路调度流程)(工程管理流程)到达本环节的工单时间字段显示常量
	 */
	public static final String GO_TO_SEGMENT_TIME = "true";
    
	/**
	 * PDA任务派发退回状态 (0)
	 */
	public static final String PDA_TASK_STATE_I = "0";
	/**
	 * PDA任务派发未签收状态 (1)
	 */
	public static final String PDA_TASK_STATE_II = "1";
	/**
	 * PDA离线任务
	 */
	public static final String PDA_TASK_OFFLINE_YES = "1";
	
	/**l
	 * PDA非离线任务
	 */
	public static final String PDA_TASK_OFFLINE_NO = "0";
	
	/**
	 * PDA离线任务已打包
	 */
	public static final Long PDA_TASK_OFFLINE_PACK_YES = 1L;
	
	/**
	 * PDA离线任务未打包
	 */
	public static final Long PDA_TASK_OFFLINE_PACK_NO = 0L;
	
	/**
	 * PDA离线任务已下载
	 */
	public static final Long PDA_TASK_OFFLINE_DOWN_YES = 1L;
	
	/**
	 * PDA离线任务未下载
	 */
	public static final Long PDA_TASK_OFFLINE_DOWN_NO = 0L;
	
	/**
	 * PDA任务派已签收状态 (2)
	 */
	public static final String PDA_TASK_STATE_III = "2";
	/**
	 * PDA任务派发已提交状态 (3)
	 */
	public static final String PDA_TASK_STATE_V = "3";
	/**
	 * PDA任务派发关闭状态 (4)
	 */
	public static final String PDA_TASK_STATE_VI = "4";
	
	/**
	 * PDA 为派发的状态
	 */
	public static final String PDA_MAP_RESOURCE_STATUS_DISTRIBUTE = "-2";
	/**
	 * PDA 审核资源状态对应临时常量(做第一次资源不通过标识-1)
	 */
	public static final String PDA_MAP_SOURCE_STATUS_FLAG = "-1";
	/**
	 * PDA 审核资源状态对应的常量(不通过0)
	 */
	public static final String PDA_MAP_SOURCE_STATUS_I = "0";
	/**
	 * PDA 审核资源状态对应的常量(通过1)
	 */
	public static final String PDA_MAP_SOURCE_STATUS_II = "1"; 
	/**
	 * PDA 审核资源状态对应的常量(再次通过2)
	 */
	public static final String PDA_MAP_SOURCE_STATUS_III = "2";
	
	
	//----PDA 核查反馈  对页面核查任务的通过 不通过按钮的常量标识
	/**
	 * 核查反馈  对页面核查任务的通过 不通过按钮的常量标识(1)通过
	 */
	public static final String PDA_TASK_CHECKED_PASS_I = "1";
	/**
	 * 核查反馈  对页面核查任务的通过 不通过按钮的常量标识(2)不通过
	 */
	public static final String PDA_TASK_CHECKED_NOT_PASS_II = "2";
	
	/**
	 * pda从现场核查提交到核查反馈的状态值 常量(0)未核查
	 */
	public static final String PDA_RES_VERIFY_STATUS_ = "0";
	
	
	/**
	 * PDA 网维审核 通过的常量标识1
	 */
	public static final String PDA_PASS_STATUS_NET_MAINTAIN_VERIFY  = "1";
	
	/**
	 * PDA 网维审核 不通过的常量标识0
	 */
	public static final String PDA_NOT_PASS_STATUS_NET_MAINTAIN_VERIFY = "0";
	
	/**
	 * PDA 网椎审核 入库成功的常量标识 2
	 */
	public static final String PDA_WAREHOUSEING_SUCCESS_NET_MAINTAIN_VERIFY = "2";
	
	/**
	 * PDA 网维审核 入库失败的常量标识 3
	 */
	public static final String PDA_WAREHOUSING_FAILURE_NET_MAINTAIN_VERIFY = "3";
		
	/**
	 * pda从现场核查提交到核查反馈的状态值 常量(1)已核查
	 */
	public static final String PDA_RES_VERIFY_STATUS__ = "1";
	

	/**
	 * pda从现场核查提交到核查反馈的状态值 常量(2)回退
	 */
	public static final String PDA_RES_VERIFY_STATUS___= "2";
	/**
	 * 区域类型，1 表示为区县
	 */
	public static final Long REGION_TYPE = 1L;
	
	
	
	//--PDA 网维审核环节 回退时 对(未通过)(通过)(资源入库)的资源进行未通过的常量标识(根据资源差异表的未通过 来判断PDA_MAP_RESOURCE表中的未通过)
	/**
	 * 未通过
	 */
	public static final String PDA_TASK_IS_NOT_PASS = "未通过";
	
	/**
	 * 通过
	 */
	public static final String PDA_TASK_IS_PASS = "通过";
	
	//PDA 核查反馈和网维审核页面 工单道出 区分两环节的常量
	/**
	 * 工单导出核查反馈(1)
	 */
	public static final String PDA_WORKSHEET_EXPORT_CHECK_RETURN = "1";
	
	/**
	 * 工单导出网维审核(2)
	 */
	public static final String PDA_WORKSHEET_EXPORT_NET_EXAMINE = "2";
	/**
	 * Android 资源核查流程 区分网维审核和核查反馈(通过 不能过)功能的常量标识
	 */
	public static final String PDA_NET_MAINTAIN_VERIFY_ = "4";
	
	/**
	 * PDA专项下画区域 和PDA专项下工单画区域的常量表示
	 */
	
	public static final  String PDA_PROJECT_CONTANTS = "1";
	
	public static final String PDA_PROJECT_WORKSHEET_CONTANTS = "2";
	
	public static final String EDIT_PDA_PROJECT_WORKSHEET_CONTANTS = "edit";
	
	public static final String PDA_WORKSHEET_FIRST_SAVE = "firstSave";
	
	public static final String SHOW_IMAGE_FLOW = "showPdaProject";
	
	/**
	 * 工单是否删除的常量表示1(未删除) 
	 */
	public static final String DELETE_WORK_SHEET_CONSTANTS = "1";
	
	/**
	 * 专项状态（0：未完成，1：已完成）
	 * */
	public static final String PDA_PROJECT_STATUS = "0";
	
	
	//CABLEPIPE-7319 工程验收流程中增加'工单挂起'功能
	
	/**
	 *  工单挂起 0
	 */
	public static final Long PROJECT_MANAGEMENT_CY_WORKSHEET_PUT_UP = 0L;
	
	/**
	 * 工单激活 1
	 */
	public static final Long PROJECT_MANAGEMENT_CY_WORKSHEET_ACTIVE = 1L; 
	
	/**
	 * 非保存操作
	 */
	public static final String WORKSHEET_NOT_SAVE = "1"; 
	
	/**
	 * 推送标工信息(1表示推送(是) 0表示否)
	 */
	public static final Long CONSTANTS_PUSH_MARK_WORKS_INFO = 1L;
	
	/**
	 * 推送标工信息 状态常量标识(回退的标识) 1.正常推送 2.回退推送 3关闭推送
	 */
	public static final Long CONSTANTS_PUSH_MARK_WORKS_INFO_STATUS_BACK =2L;
	
	public static final Long CONSTANTS_PUSH_MARK_WORKS_INFO_STATS_END = 3L;
	
	/**sys_var配置上传下载infores服务器地址**/
	public static final String UPLOAD_URL = "UPLOAD_URL";
	/**下载模块**/
	public static final String UPLOADMODE = "UPLOADMODE";
	
	public static final String USER_ONLINE_WEB_DATASOURCE = "2";
	
	

	/**短信催办通知信息常量*/
	public static final String TASK_STATUS_ID = "getId";
	
	public static final String TASK_STATUS_NAME = "getName";
	
	
	/****管线发送EOMS接口测试常量   默认不推送EOMS公告*****/
	public static final Long PUSH_EOMS_TEST= 0L;
	
	/***CABLEPIPE-8555  新建工程界面关联工程 入库的常量***/
	public static final Long PROJECT_PRJSTATUS_FINISHED = 1L;
	
	/************CABLEPIPE-8555  新建工程界面关联工程 验收中的常量******************/
	public static final Long PROJECT_PRJSTATUS_ACCEPTANCE = 2L;
    
	public static final String PROJECT_NEWS_INSERT_SQL="INSERT";
	
	public static final String PROJECT_NEWS_UPDATE_SQL="UPDATE";
	
	/**4A初始化用户密码**/
	public static final String USER_PASSWORD_DEF = "12345";
	/**标识4A 接口删除的用户(常量值)**/
	public static final String IS_DETELE_FLAG = "-99";
	/**标识为4A用户/被4A改动过的数据**/
	public static final Long IS_4A = 4L;
	
	/**管线系统定时生成一、二干截图**/
	public static final String INFORESMAPPING = "INFORESMAPPING";//接口类型名
	public static final String INFORESMAPPING_WORKING = "INFORESMAPPING_WORKING";//接口是否开启 false:关 true ：开
	
	/**EOMS账号同步接口**/
	public static final String EOMSINFOSYNC="EOMSINFOSYNC";//接口类型名
    public static final String EOMSINFOSYNC_WORKING ="EOMSINFOSYNC_WORKING";//接口是否开启 false:关 true ：开
	
	/**密码有效天数**/
	public static final String PWDMODIRANGE = "PWDMODIRANGE";

	/**用户密码输入10次错误锁定*/
	public static final int PWD_ERR_LOCKED = 3;
	
	/**广州区域id*/
	public static final String GZ_REGIONID = "11087000001501";
	
	public static final Long SG2TYPE_USERID = -99999999L;


}