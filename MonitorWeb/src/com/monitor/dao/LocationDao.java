package com.monitor.dao;

import com.monitor.domain.LocationInfo;

public interface LocationDao {
	
	/**
	 * ���ݳ���id��ȡ������λ����Ϣ
	 * @param car_id
	 * @return LocationInfo����
	 */
	public LocationInfo getLocationLastInfo(int car_id);
}
