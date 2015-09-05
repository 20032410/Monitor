package com.monitor.service;

import java.util.List;

import com.monitor.dao.CarDao;
import com.monitor.domain.CarInfo;
import com.monitor.factory.DaoFactory;
import com.monitor.util.XMlParserUtils;

public class CarService {
	private CarDao dao = DaoFactory.getFactory().getCarDao();
	
	/**
	 * ��ȡ���еĳ�����Ϣ����������ϢתΪxml�ַ���
	 * @return ���г�����Ϣ��xml��ʽ���ַ���
	 */
	public String getAllCarInfo(){		
		//��ȡ���еĳ�����Ϣ
		List<CarInfo> carList = dao.getAllCarInfo();			
		//���س�����Ϣ��xml�ַ���
		return XMlParserUtils.XMLCarParserXtoS(carList);
	}
}
