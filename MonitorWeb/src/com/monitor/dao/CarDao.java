package com.monitor.dao;

import java.util.List;

import com.monitor.domain.CarInfo;


public interface CarDao {
	
	/**
	 * �������ݿ������еĳ�����Ϣ
	 * @return ������Ϣ��List����
	 */
	public List<CarInfo> getAllCarInfo();
}
