package com.monitor.dao;

//��sql�Ľӿڰ������ӿ���ֲ��
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.monitor.domain.UserInfo;
import com.monitor.util.JDBCUtils;


public class MySqlUserDao implements  UserDao{

	@Override
	public UserInfo findUserByUserName(String username) {
		String sql = "select * from users where username=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			//��ȡ���ݿ�����������
			conn = JDBCUtils.getConn();
			
			//��ȡ����������
			ps = conn.prepareStatement(sql);
			
			//д��sql���name��Ϣ
			ps.setString(1,  username);
			
			//ִ��sql�������
			rs = ps.executeQuery();
			
			if(rs.next()){
				UserInfo user = new UserInfo();
				
				user.setUser_id(rs.getInt("user_id"));
				user.setusername(rs.getString("username"));
				user.setNickname(rs.getString("nickname"));
				user.setPassword(rs.getString("password"));
				user.setGender(rs.getString("gender")); 
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				
				return user;
			}else{
				return null;
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			JDBCUtils.close(rs, ps, conn);
		}
		
	}

	@Override
	public void addUser(UserInfo user) {
		String sql = "insert into users values(null,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			//��ȡ���ݿ�����������
			conn = JDBCUtils.getConn();
			
			//��ȡ����������
			ps = conn.prepareStatement(sql);
			
			//д��sql���values��Ϣ
			ps.setString(1, user.getusername());
			ps.setString(2, user.getNickname());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getGender()+"");
			ps.setString(5, user.getPhone());
			ps.setString(6, user.getEmail());
			
			//ִ��sql�������
			ps.executeUpdate();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			JDBCUtils.close(rs, ps, conn);
		}
		
	}

	@Override
	public UserInfo findUserByUNandPSW(String username, String password) {
		
		String sql = "select * from users where username=? and password=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			//��ȡ���ݿ�����������
			conn = JDBCUtils.getConn();
			
			//��ȡ����������
			ps = conn.prepareStatement(sql);
			
			//д��sql���name��Ϣ
			ps.setString(1,  username);
			ps.setString(2, password);
			
			//ִ��sql�������
			rs = ps.executeQuery();
			
			if(rs.next()){
				UserInfo user = new UserInfo();
				
				user.setUser_id(rs.getInt("user_id"));
				user.setusername(rs.getString("username"));
				user.setNickname(rs.getString("nickname"));
				user.setPassword(rs.getString("password"));
				user.setGender(rs.getString("gender")); 
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				
				return user;
			}else{
				return null;
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			JDBCUtils.close(rs, ps, conn);
		}
	}

}
