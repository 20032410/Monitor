package com.monitor.service;

import java.util.List;
import com.monitor.dao.MonitorDataDao;
import com.monitor.domain.MonitorInfo;
import com.monitor.factory.DaoFactory;
import com.monitor.util.XMlParserUtils;


public class MonitorService {
	private MonitorDataDao dao = DaoFactory.getFactory().getMonitorDao();
	
	/**
	* ���ݳ���id��ȡ�ó����µļ����Ϣ����ת��Ϊxml�ַ�������
	* @return ���ݳ���id�������µļ����Ϣ��xml�ַ���
	*/
	public String getLastMonitorData(Integer car_id){	
		MonitorInfo monitorData = dao.getLastMonitorData(car_id);
		return XMlParserUtils.XMLOneMonitorDataParserXtoS(monitorData);
		
	}

	
	/**
	 * ���ݳ���id��ѯ�������еļ����Ϣ����ת��Ϊxml�ַ���
	 * @param car_id
	 * @return
	 */
	public String getAllMonitorData(Integer car_id){
		
		List<MonitorInfo> monitorDataList = dao.getAllMonitorDataByCar(car_id);

		return XMlParserUtils.XMLMonitorDataParserXtoS(monitorDataList);
	}
	
}
