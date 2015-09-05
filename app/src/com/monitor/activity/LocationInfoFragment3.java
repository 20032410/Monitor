package com.monitor.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.http.client.ClientProtocolException;
import org.xmlpull.v1.XmlPullParserException;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.model.LatLng;
import com.monitor.R;
import com.monitor.domain.LocationInfo;
import com.monitor.evenbus.info.GetLastLocationInfoEven;
import com.monitor.evenbus.info.GetLastMonitorInfoEven;
import com.monitor.evenbus.info.UpdateMonitorFragmentUI;
import com.monitor.util.HttpUtil;
import com.monitor.util.StatusUtil;
import com.monitor.util.XMlParserUtils;

import de.greenrobot.event.EventBus;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LocationInfoFragment3 extends Fragment {
	
	private final int CARID_DEFAULT = -1; 
	
	private MapView mMapView = null; 
	private View view = null;
	private BaiduMap mBaiduMap = null;

	BitmapDescriptor bdA = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);
	private Marker mMarkerA;
	
	private Lock lock = new ReentrantLock(); //��
	private Condition condition = lock.newCondition();//��������
	private Integer carId = CARID_DEFAULT;
	private String responseStr = null;
	private LocationInfo locationInfo = null;
	
	
	OverlayOptions ooA;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.location_info_fragment3, container, false);
		mMapView = (MapView) view.findViewById(R.id.bmapView);
		
		//��ͨ��ͼ
		mBaiduMap = mMapView.getMap();
		
		//���õ�ͼ�����ű���
		MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(16.0f);
		
		//��ǰ��Ĳ�������BaiduMap��
		mBaiduMap.setMapStatus(msu);
		
		EventBus.getDefault().register(this);
		
		getLastLoactionInfo();
		
		return view;
	}
	
	
	void getLastLoactionInfo(){
		new Thread(new getLastLocationInfoThread()).start();
	}
	
	class getLastLocationInfoThread implements Runnable{

		@Override
		public void run() {
			

			String path = "http://192.168.91.1:8080/MonitorWeb/Servlet/LocationInfoServlet";
			Map<String, String> params = new HashMap<String, String>();
			
			while(true){
				try {
					lock.lock();
					//�ȴ�carId���ı�
									
					if (CARID_DEFAULT == carId) {
						condition.await();						
					}
					
					params.put("car_id", carId.toString());
										
					responseStr = HttpUtil.sendHttpClientPostRequest(path, params,
							"UTF-8");
					
					//����responseStr���ɼ����Ϣ����
					
					if(null != responseStr){		
						locationInfo = XMlParserUtils.XMLLctInfoParserStoLctInfo(responseStr);
						if(null != locationInfo){
							getData();
						}									
					}				
								
				} catch (InterruptedException e) {
					e.printStackTrace();
				}catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					lock.unlock();
				}
				
				
				try { //ÿ�������һ������ȥ��ȡ����
					Thread.sleep(1000*2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			
					
		}

		
	}
	
	private void getData() {
		
		//����Maker�����
		LatLng ll = new LatLng(locationInfo.getLatitude(), locationInfo.getLongitude());
		
		//����״̬
		MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);			
		mBaiduMap.animateMapStatus(update);
		
		//����markerͼ���ͼ��λ��
		ooA = new MarkerOptions().position(ll).icon(bdA).zIndex(9).draggable(true);
		
		//�ڵ�ͼ���Marker,����ʾ
		mMarkerA = (Marker) (mBaiduMap.addOverlay(ooA));
	}
				
	// ��Ϣ�����߳�
	public void onEventMainThread(GetLastLocationInfoEven even) {
		lock.lock();
			carId = even.getCarId();
			condition.signal();
		lock.unlock();
	}
	
		
	 @Override
	public void onDestroy() {  
	        super.onDestroy();  
	        //��activityִ��onDestroyʱִ��mMapView.onDestroy()��ʵ�ֵ�ͼ�������ڹ���  
	        mMapView.onDestroy();       
	        //����bitmap��Դ
	        bdA.recycle();        
	        EventBus.getDefault().unregister(this);
	        
	    }  
	 
	    @Override
		public void onResume() {  
	        super.onResume();  
	        //��activityִ��onResumeʱִ��mMapView. onResume ()��ʵ�ֵ�ͼ�������ڹ���  
	        mMapView.onResume();  
	        }  
	    @Override
		public void onPause() {  
	        super.onPause();  
	        //��activityִ��onPauseʱִ��mMapView. onPause ()��ʵ�ֵ�ͼ�������ڹ���  
	        mMapView.onPause();  
	        }      
}
