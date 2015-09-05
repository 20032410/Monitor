package com.monitor.service;

import com.monitor.dao.LocationDao;
import com.monitor.domain.LocationInfo;
import com.monitor.factory.DaoFactory;
import com.monitor.util.XMlParserUtils;

public class LocationService {
private LocationDao dao = DaoFactory.getFactory().getLocationDao();
	
	public String getLastLocationInfo(int car_id){
		
		//��ȡ���µĵ���λ����Ϣ
		LocationInfo locationInfo = dao.getLocationLastInfo(car_id);
			
		//���ص���λ����Ϣ��xml�ַ���
		return XMlParserUtils.XMLLocationDataParserXtoS(locationInfo);
	}
}
