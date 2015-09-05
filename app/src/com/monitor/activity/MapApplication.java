package com.monitor.activity;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;

import android.app.Application;

public class MapApplication extends Application {

	public LocationClient bdLocationClient;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		//������Ŀʱ���Զ���ʼ���ٶȵ�ͼ
		SDKInitializer.initialize(getApplicationContext()); 
		
		//��ʼ���ٶȵ�ͼ������
		bdLocationClient = new LocationClient(this);
	}
}