package com.gpdi.common.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import com.gpdi.gx.ClientInfo;
import com.gpdi.gx.GxWebClient;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtil {
   
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();  
	
    private static String URL = "jdbc:oracle:thin:@192.168.255.57:1521:rmgd2";  
    //system为登陆oracle数据库的用户名     
    private static String USER = "rmgd";    
    //manager为用户名system的密码     
    private static String PASSWORD = "Gxgpdi_2017";
    public static Connection CONN;
    
    public static List<Map> getAllDBInfo() {
    	Properties pps = new Properties();
    	List<Map> dbUrlList = new Vector<Map>();
    	InputStream resourceAsStream = null;
		try {
			resourceAsStream = GxWebClient.class.getResourceAsStream("DBStatusInfo.properties");
			pps.load(resourceAsStream);
			String urls = pps.getProperty("url");
			String users = pps.getProperty("user");
			String passwords = pps.getProperty("password");
			if(urls!=null && urls.length()>0){
				String[] urlArr = urls.split(",");
				String[] userArr = users.split(",");
				String[] passwordArr = passwords.split(",");
				for(int i = 0; i<urlArr.length; i++){
					Map infoMap = new HashMap<String, String>();
					infoMap.put("url", urlArr[i]);
					infoMap.put("user", userArr[i]);
					infoMap.put("password", passwordArr[i]);
					dbUrlList.add(infoMap);
				}
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(resourceAsStream!=null) {resourceAsStream.close();}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return dbUrlList;
    }
    
    /**
     * 生成链接
     * 
     */
    public static Connection getConnection(){    
        try {    
            //初始化驱动包     
            Class.forName("oracle.jdbc.driver.OracleDriver");    
            //根据数据库连接字符，名称，密码给conn赋值     
            CONN = DriverManager.getConnection(URL, USER, PASSWORD);
                
        } catch (Exception e) {       
            e.printStackTrace();    
        } 
        return CONN;
    }
    
    /**
     * 获得所有数据库的链接对象
     * 
     * @return
     * @throws ClassNotFoundException 
     * @throws SQLException 
     */
    public static List<Map> getConnectionForAllNode() throws SQLException {
    	List<Map> connList = new ArrayList<Map>();
    	List<Map> dbUrlList = getAllDBInfo();
    	for(int i=0; i<dbUrlList.size(); i++) {
    		Map infoMap = dbUrlList.get(i);
    		try {
    			
    			Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection connection = DriverManager.getConnection((String)infoMap.get("url"), 
						(String)infoMap.get("user"), (String)infoMap.get("password"));
				Map<String, Connection> connMap = new HashMap<String, Connection>();
				connMap.put((String)infoMap.get("url"), connection);
				connList.add(connMap);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				throw e;
			}  
    	}
		return connList;
    	
    }
    
    /**
     * 获得指定数据库链接url的链接对象
     * 
     * @param connectUrl
     * @return
     * @throws SQLException
     */
    public static Connection getConnectionByUrl(String connectUrl) throws SQLException {
    	List<Map> dbUrlList = getAllDBInfo();
    	for(int i=0; i<dbUrlList.size(); i++) {
    		Map infoMap = dbUrlList.get(i);
    		if(connectUrl.equals(infoMap.get("url"))) {
    			try {
        			
        			Class.forName("oracle.jdbc.driver.OracleDriver");
    				Connection connection = DriverManager.getConnection((String)infoMap.get("url"), 
    						(String)infoMap.get("user"), (String)infoMap.get("password"));
    				return connection;
    				
    			} catch (ClassNotFoundException e) {
    				e.printStackTrace();
    			} catch (SQLException e) {
    				throw e;
    			}  
        	
    		}
    	}
    	
    	return null;
    }
    
    
    /**
     * 关闭ResultSet，Statement与Connection对象
     * 
     * @param rs
     * @param st
     * @param conn
     */
	    public static void close(ResultSet rs, Statement st, Connection conn) {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	    
 
     /**
     * 获得C3P0链接池连接
     *   
     * @return
     */
    public static Connection getDataSourceConn() {  
        try {
        	
            return dataSource.getConnection();  
        }catch (SQLException e) {  
            throw new RuntimeException(e);  
        }  
    }   


    public static String getDataSourceName() {
    	return dataSource.getDataSourceName();
    }
    
    public static Map<String, String> getDbPropertiesInfo() {
    	Map<String, String> propertyMap = new HashMap<String, String>();
    	Properties pps = new Properties();
    	InputStream resourceAsStream = GxWebClient.class.getResourceAsStream("DBStatusInfo.properties");
		try {
			pps.load(resourceAsStream);
			propertyMap.put("url", pps.getProperty("url"));
			propertyMap.put("user", pps.getProperty("user"));
			propertyMap.put("password", pps.getProperty("password"));
			propertyMap.put("timedSend", pps.getProperty("timedSend"));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(resourceAsStream!=null) {resourceAsStream.close();}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return propertyMap;
    	
    }

}
