package com.monitor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.monitor.domain.LocationInfo;
import com.monitor.util.JDBCUtils;

public class MySqlLocationDao implements LocationDao {

	/**
	 * ���ݳ���id��ȡ������λ����Ϣ
	 * @param car_id
	 * @return LocationInfo����
	 */
	@Override
	public LocationInfo getLocationLastInfo(int car_id) {
		
		//Ҫִ�е�SQL�������
		String sql = "select * from locationInfo where car_id=? order by location_id desc";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			//��ȡ���ݿ�����������
			conn = JDBCUtils.getConn();
			
			//��ȡ����������
			ps = conn.prepareStatement(sql);
			
			//д��sql���name��Ϣ
			ps.setInt(1,  car_id);
			
			//ִ��sql�������
			rs = ps.executeQuery();
			
			if(rs.next()){
				LocationInfo locationInfo = new LocationInfo();
				
				//����LocationInfo����
				locationInfo.setLocation_id(Integer.valueOf(rs.getString("location_id")));
				locationInfo.setCar_id(Integer.valueOf(rs.getString("car_id")));
				locationInfo.setLatitude(rs.getDouble("latitude"));
				locationInfo.setLongitude(rs.getDouble("longitude"));
				
				return locationInfo;
			}else{
				return null;
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			//�ر�����
			JDBCUtils.close(rs, ps, conn);
		}
	}

}
