package com.monitor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.monitor.domain.CarInfo;
import com.monitor.util.JDBCUtils;

public class MySqlCarDao implements CarDao{

	@Override
	public List<CarInfo> getAllCarInfo() {
		String sql = "select * from car";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null; 
		
		//ArrayList���ֳ���ȫ����Ч�ʱ�Vector��
		List<CarInfo> carList = new ArrayList<CarInfo>();
		
		try {
			//��ȡ���ݿ�����������
			conn = JDBCUtils.getConn();
			
			//��ȡ����������
			ps = conn.prepareStatement(sql);
									
			//ִ��sql�������
			rs = ps.executeQuery();
			
			//���ҵ���car��Ϣ����ArrayList<Car>��������
			if(rs.next()){				
				do {
					CarInfo car = new CarInfo();
					
					car.setCar_id(rs.getInt("car_id"));
					car.setDriver_name(rs.getString("driver_name"));
					car.setNumber(rs.getString("number"));
					
					carList.add(car);
					car = null;	
				}while (rs.next());
				
				return carList;
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
