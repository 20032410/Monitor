package com.monitor.dao;

import java.util.List;

import com.monitor.domain.MonitorInfo;

public interface MonitorDataDao {
	
	/**
	 * ����car_id��ȡ�ó����еļ����Ϣ
	 * @return
	 */
	public List<MonitorInfo> getAllMonitorDataByCar(Integer car_id);
	
	/**
	 * ����car_id��ȡ�ó����µļ����Ϣ
	 * @param car_id
	 * @return
	 */
	public MonitorInfo getLastMonitorData(Integer car_id);
}
