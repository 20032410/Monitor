package com.monitor.util;


import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
	private static Properties prop = null;
	private JDBCUtils() {
	}
	static{
		try{
			prop = new Properties();  //�½�һ����ȡ�����ļ���
			
			//������������������ļ�config.properties
			String filepath = JDBCUtils.class.getClassLoader().getResource("config.properties").getPath();
			FileReader file = new FileReader(filepath);
			prop.load(file);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * ��ȡ����
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @return ���ݿ�����������
	 */
	public static Connection getConn() throws ClassNotFoundException, SQLException{
		
		// 1.ע�����ݿ�����
		Class.forName(prop.getProperty("driver"));
		// 2.��ȡ���Ӷ���
		return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
	}
	
	/**
	 * �ر�����
	 * @param rs ��Դ
	 * @param stat ����������
	 * @param conn ����������
	 */
	public static void close(ResultSet rs, Statement stat,Connection conn){
		//�رռ�¼��
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				rs = null;
			}
		}
		
		//�ر�����
		if(stat!=null){
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				stat = null;
			}
		}
		
		//�ر����Ӷ���
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				conn = null;
			}
		}
	
	}
}
