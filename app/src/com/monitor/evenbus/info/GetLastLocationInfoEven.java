package com.monitor.evenbus.info;

/**
 * ��ȡ���µļ����Ϣ�¼�������infoActivity���͸�MonitorInfoFragment3���������carId
 * ���ϵشӷ������л�ȡ���µĵ���λ����Ϣ
 * @author jian
 *
 */
public class GetLastLocationInfoEven {

	private int carId;
	
	public GetLastLocationInfoEven(int carId){
		setCarId(carId);
	}
	
	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

}
