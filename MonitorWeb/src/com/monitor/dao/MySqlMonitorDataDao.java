package com.monitor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.monitor.domain.MonitorInfo;
import com.monitor.util.JDBCUtils;

public class MySqlMonitorDataDao implements MonitorDataDao{

	
	public List<MonitorInfo> getAllMonitorDataByCar(Integer car_id) {
		
		String sql = "select * from monitordata where car_id=?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null; 
		
		//ArrayList���ֳ���ȫ����Ч�ʱ�Vector��
		List<MonitorInfo> MonitorDataList = new ArrayList<MonitorInfo>();
		
		try {
			//��ȡ���ݿ�����������
			conn = JDBCUtils.getConn();
			
			//��ȡ����������
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, String.valueOf(car_id));
			
			//ִ��sql�������
			rs = ps.executeQuery();

	
			//���ҵ���MonitorData��Ϣ����ArrayList<Car>��������
			if(rs.next()){				
				do {
					MonitorInfo monitorData = new MonitorInfo();
					
					monitorData.setMd_id(rs.getInt("md_id"));
					monitorData.setTemperature(rs.getFloat("temperature"));
					monitorData.setHumidity(rs.getFloat("humidity"));
					monitorData.setCarbon_dioxide(rs.getDouble("carbon_dioxide"));
					monitorData.setOxygen(rs.getDouble("oxygen"));
					monitorData.setNitrogen(rs.getDouble("nitrogen"));
					monitorData.setEthylene(rs.getDouble("ethylene"));
					monitorData.setAmmonia(rs.getDouble("ammonia"));
					monitorData.setSulfur_dioxide(rs.getDouble("sulfur_dioxide"));
					monitorData.setCar_id(rs.getInt("car_id"));
					monitorData.setTime(rs.getTimestamp("time"));
					
					MonitorDataList.add(monitorData);
					monitorData = null;	
				}while (rs.next());
				
				return MonitorDataList;
			}
			else{
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
	public MonitorInfo getLastMonitorData(Integer car_id) {
	String sql = "select * from monitordata where car_id=? order by md_id desc";
	
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null; 
		
		
		try {
			//��ȡ���ݿ�����������
			conn = JDBCUtils.getConn();
			
			//��ȡ����������
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, car_id);
			
			//ִ��sql�������
			rs = ps.executeQuery();

	
			//���ҵ���MonitorData��Ϣ����ArrayList<Car>��������
			if(rs.next()){				
			
				MonitorInfo monitorData = new MonitorInfo();
				
				monitorData.setMd_id(rs.getInt("md_id"));
				monitorData.setTemperature(rs.getFloat("temperature"));
				monitorData.setHumidity(rs.getFloat("humidity"));
				monitorData.setCarbon_dioxide(rs.getDouble("carbon_dioxide"));
				monitorData.setOxygen(rs.getDouble("oxygen"));
				monitorData.setNitrogen(rs.getDouble("nitrogen"));
				monitorData.setEthylene(rs.getDouble("ethylene"));
				monitorData.setAmmonia(rs.getDouble("ammonia"));
				monitorData.setSulfur_dioxide(rs.getDouble("sulfur_dioxide"));
				monitorData.setCar_id(rs.getInt("car_id"));
				monitorData.setTime(rs.getTimestamp("time"));			
				return monitorData;
			}
			else{
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
