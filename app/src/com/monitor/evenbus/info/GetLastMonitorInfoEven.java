package com.monitor.evenbus.info;

/**
 * ��ȡ���µļ����Ϣ�¼�������infoActivity���͸�LocationInfoFragment���������carId
 * ���ϵشӷ������л�ȡ���µļ������
 * @author jian
 *
 */
public class GetLastMonitorInfoEven {

	private int carId;
	
	public GetLastMonitorInfoEven(int carId){
		setCarId(carId);
	}
	
	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

}
