package com.monitor.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.xmlpull.v1.XmlPullParserException;

import com.monitor.R;
import com.monitor.adapter.CarAutoCompleteAdaper;
import com.monitor.adapter.MLFragmentAdapter;
import com.monitor.dao.CarDao;
import com.monitor.dao.SqliteCarDao;
import com.monitor.domain.CarInfo;
import com.monitor.evenbus.info.GetLastLocationInfoEven;
import com.monitor.evenbus.info.GetLastMonitorInfoEven;
import com.monitor.evenbus.info.UpdateCarInfo;
import com.monitor.util.HttpUtil;
import com.monitor.util.XMlParserUtils;

import de.greenrobot.event.EventBus;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;


public class InfoActivity extends FragmentActivity {
	
	//�ؼ�����
	private ViewPager pager;     
	private List<android.support.v4.app.Fragment> fragList;
	private List<String> titleList;  //����
	private PagerTabStrip tab;       //�����л�
	CarAutoCompleteAdaper autoAdapter;
	
	private Button bntCarConfirm;    //ȷ�ϳ�����ѯ��ť
	private AutoCompleteTextView autoCptText;
	List<CarInfo> carList = null;
	
	Thread getCarInfo;
	
	private OnClickListener carConfirmLst = new OnClickListener(){
		@Override
		public void onClick(View arg0) {
			
			//��ȡ������Ϣ
			String inputTxt = autoCptText.getText().toString();
			
			//�ָ�������Ϣ
			String[] carInfo = inputTxt.trim().split(":");
			
			CarDao dao = new SqliteCarDao(getApplicationContext());
			
			//����˾�����������ݿ��еĳ�����Ϣ
			CarInfo car = dao.findByDriverNameAndNumber(carInfo[0], carInfo[1]);
			if(null != car){
				//���ݳ�����ID��Ϣ��MonitorInfoFragment���������ϵش����ϻ�ȡ���µļ����Ϣ
				EventBus.getDefault().post(new GetLastMonitorInfoEven(car.getCar_id()));
				
				//���ݳ�����ID��Ϣ��LocationInfoFragment3���������ϵش����ϻ�ȡ���µĵ���λ����Ϣ
				EventBus.getDefault().post(new GetLastLocationInfoEven(car.getCar_id()));
			}
					
		}
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_info);
		
		//��ȡ�ؼ�
		tab = (PagerTabStrip) findViewById(R.id.tab);
		pager = (ViewPager) findViewById(R.id.pager);
		autoCptText = (AutoCompleteTextView) findViewById(R.id.car_auto_text);
		bntCarConfirm = (Button) findViewById(R.id.car_info_comfirm);
			
		//fragment��������
		titleList = new ArrayList<String>();
		titleList.add("�����Ϣ");
		titleList.add("����λ����Ϣ");
		
		//��������		
		tab.setDrawFullUnderline(false);
		tab.setBackgroundColor(Color.WHITE);
		tab.setTabIndicatorColor(Color.BLUE);
		
		//���fragment	
		fragList = new ArrayList<android.support.v4.app.Fragment>();
		fragList.add(new MonitorInfoFragment());
		fragList.add(new LocationInfoFragment3());
		
		//��ȡ���ƽ̨�����еĳ�����Ϣ
		GetAllCarInfo();
			
		//����autoCptText������
		autoAdapter = new CarAutoCompleteAdaper(this, null, 1);
		autoCptText.setAdapter(autoAdapter);
		
		//����pager��������
		MLFragmentAdapter adapter = new MLFragmentAdapter(getSupportFragmentManager(), fragList, titleList);	
		pager.setAdapter(adapter);
		
		//����ȷ�ϰ��������¼�����
		bntCarConfirm.setOnClickListener(carConfirmLst);
		
		//ע�������Ϣ
		EventBus.getDefault().register(this);
	}


	
	private void GetAllCarInfo() {

		GetAllCarInfoRunnable getAllCarInfoRunnable = new GetAllCarInfoRunnable();
		getCarInfo = new Thread(getAllCarInfoRunnable);
		getCarInfo.start();
		try {
			getCarInfo.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	class GetAllCarInfoRunnable implements Runnable{
				
		public GetAllCarInfoRunnable(){
			
		}
		
		@Override
		public void run() {
		String path = "http://192.168.91.1:8080/MonitorWeb/Servlet/CarServlet";
		
			
			try {
				//��ȡ���еĳ�����Ϣs
				String responseStr = HttpUtil.sendHttpClientPostRequest(path, null, "UTF-8");
				
				//�����泵����Ϣ��XML�ַ������н���
				carList = XMlParserUtils.XMLCarParserStoCarList(responseStr);
				
				//�ѳ�����Ϣ���뱾�����ݿ�
				CarDao dao = new SqliteCarDao(getApplicationContext());
				
				for (CarInfo tmp : carList){
					dao.save(tmp);
				}
				
				//����������
				EventBus.getDefault().post(new UpdateCarInfo());
					
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
		}

			
	}
		
	//���³�����Ϣ������
	public void onEventMainThread(UpdateCarInfo even) {
		autoCptText.setAdapter(autoAdapter);
		autoAdapter.notifyDataSetChanged();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}
}
