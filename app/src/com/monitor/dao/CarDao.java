package com.monitor.dao;

import java.util.List;

import android.database.Cursor;

import com.monitor.domain.CarInfo;


public interface CarDao {
	

	/**
	 * ��Ӽ�¼
	 * @param car
	 */
	public void save(CarInfo car);
	
	/**
	 * ����id��ɾ����¼
	 * @param id
	 */
	public void deleteById(Integer id);
	
	/**
	 * ���¼�¼
	 * @param car
	 */
	public void update(CarInfo car);
	
	/**
	 * ����id�Ų��ҳ�����Ϣ
	 * @param id
	 * @return
	 */
	public CarInfo findById(Integer id);
	
	/**
	 * ����˾�������ҳ�����Ϣ
	 * @param driverName
	 * @return
	 */
	public CarInfo findByDriverName(String driverName);
	
	/**
	 * ����˾�����ͳ���������ҳ�����Ϣ
	 * @param driverName
	 * @param number
	 * @return
	 */
	public CarInfo findByDriverNameAndNumber(String driverName, String number);
	
	/**
	 * ���ݳ��ƺŲ��ҳ�����Ϣ
	 * @param number
	 * @return
	 */
	public CarInfo findByNumber(String number);
	
		
	/**
	 * ��ȡ��������
	 * @return
	 */
	public long getCount();
	
	/**
	 * ����str����ģ������
	 * @param name
	 * @return
	 */
	public Cursor query(String str);
	
	
}
