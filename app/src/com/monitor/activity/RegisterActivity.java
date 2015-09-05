package com.monitor.activity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import com.monitor.R;
import com.monitor.domain.UserInfo;
import com.monitor.evenbus.info.RegisterResult;
import com.monitor.exception.UserMsgException;
import com.monitor.util.HttpUtil;
import com.monitor.util.StatusUtil;
import de.greenrobot.event.EventBus;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	public static final int REGISTER_SUCCESS = 2;
	//�ؼ�����
	private EditText username;
	private EditText nickname;
	private EditText password;
	private EditText password2;
	private RadioGroup gender;
	private EditText phone;
	private EditText email;
	private Button comfirm;
	private Button cannel;
	
	
	private String responseStr;
	
	private OnClickListener comfirmListenner = new OnClickListener(){
		public void onClick(View arg0) {		
			//�����û���
			UserInfo user = new UserInfo();
			user.setUsername(username.getText().toString());
			user.setNickname(nickname.getText().toString());
			user.setPassword(password.getText().toString());
			user.setPassword2(password2.getText().toString());
			user.setPhone(phone.getText().toString());
			user.setEmail(email.getText().toString());
			
			switch (gender.getCheckedRadioButtonId()) {
			case R.id.register_gender_m:
				user.setGender("M");
				break;
			case R.id.register_gender_f:
				user.setGender("F");
				break;
			default:
				break;
			}
			
			//�û�����Ϸ��Լ��
			try {
				user.checkValue();
				//ע��
				Register(user);
			} catch (UserMsgException e) {
				// TODO Auto-generated catch block
			
				checkUserMsgException(e);
			}								
		}				
	};
	
	private OnClickListener cannelListenner = new OnClickListener(){

		@Override
		public void onClick(View arg0) {			
			setResult(REGISTER_SUCCESS,null);
			finish();		
		}
		
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		//��ÿؼ�
		username = (EditText) findViewById(R.id.ET_rgst_name);
		nickname = (EditText) findViewById(R.id.ET_rgst_nickname);
		password = (EditText) findViewById(R.id.ET_rgst_password);
		password2 = (EditText) findViewById(R.id.ET_rgst_password2);
		gender = (RadioGroup) findViewById(R.id.register_gender_group);
		phone = (EditText) findViewById(R.id.ET_rgst_phone);
		email = (EditText) findViewById(R.id.ET_rgst_email);
		comfirm = (Button) findViewById(R.id.btn_comfirm);
		cannel = (Button) findViewById(R.id.btn_cancel);
		
		//Ϊ�ؼ����ü�����
		comfirm.setOnClickListener(comfirmListenner);
		cannel.setOnClickListener(cannelListenner );
		
		//ע�������Ϣ
		EventBus.getDefault().register(this);
	}
	
	/**
	 * ��� �û����ݼ��鷵�صĴ�����Ϣ����������Ӧ��Ӧ��
	 * @param e
	 */
	private void checkUserMsgException(UserMsgException e) {
		if("�û�������Ϊ��".equals(e.getMessage())){
			username.requestFocus();
			Toast.makeText(getApplicationContext(), "�û�������Ϊ��",
					Toast.LENGTH_SHORT).show();
		}else if("�ǳƲ���Ϊ��".equals(e.getMessage())){
			nickname.requestFocus();
			Toast.makeText(getApplicationContext(), "�ǳƲ���Ϊ��",
					Toast.LENGTH_SHORT).show();
		}else if("���벻��Ϊ��".equals(e.getMessage())){
			password.requestFocus();
			Toast.makeText(getApplicationContext(), "���벻��Ϊ��",
					Toast.LENGTH_SHORT).show();
		}else if("ȷ�����벻��Ϊ��".equals(e.getMessage())){
			password2.requestFocus();
			Toast.makeText(getApplicationContext(), "ȷ�����벻��Ϊ��",
					Toast.LENGTH_SHORT).show();
		}else if("�ֻ����벻��Ϊ��".equals(e.getMessage())){
			phone.requestFocus();
			Toast.makeText(getApplicationContext(), "�ֻ����벻��Ϊ��",
					Toast.LENGTH_SHORT).show();
		}else if("���䲻��Ϊ��".equals(e.getMessage())){
			email.requestFocus();
			Toast.makeText(getApplicationContext(), "���䲻��Ϊ��",
					Toast.LENGTH_SHORT).show();
		}else if("�������벻һ��".equals(e.getMessage())){
			password.setText("");
			password2.setText("");
			password.requestFocus();
			Toast.makeText(getApplicationContext(), "�������벻һ��",
					Toast.LENGTH_SHORT).show();
		}else if("�ֻ����볤�Ȳ���".equals(e.getMessage())){
			phone.requestFocus();
			phone.setText("");
			Toast.makeText(getApplicationContext(), "�ֻ����볤�Ȳ���",
					Toast.LENGTH_SHORT).show();
		}else if("�����ʽ����ȷ".equals(e.getMessage())){
			email.requestFocus();
			email.setText("");
			Toast.makeText(getApplicationContext(), "�����ʽ����ȷ",
					Toast.LENGTH_SHORT).show();
		}
	}
	
	/**
	 * ��½����
	 * @param username
	 * @param password
	 */
	private void Register(UserInfo user) {

		RegisterRunnable registerRunnable = new RegisterRunnable(user);
		
		new Thread(registerRunnable).start();

	}
	
	
	/**
	 * ע������ʾ
	 * 
	 * @param even
	 */
	public void onEventMainThread(RegisterResult even) {

		if (null == responseStr) {
			Toast.makeText(getApplicationContext(), "�������", Toast.LENGTH_SHORT)
					.show();
		} else {
			switch (Integer.valueOf(responseStr)) {
			case StatusUtil.REGISTER_SUCCESS:
				// ���ص�½����
				Toast.makeText(getApplicationContext(), "ע��ɹ�",
						Toast.LENGTH_SHORT).show();
				setResult(REGISTER_SUCCESS, null);
				finish();
				break;

			case StatusUtil.REGISTER_NAME_ERROR:
				username.requestFocus();
				Toast.makeText(getApplicationContext(), "�û����Ѵ���",
						Toast.LENGTH_SHORT).show();
				break;

			}
		}
	}
	

	
				
	/**
	 * ��½�߳�	
	 */
		class RegisterRunnable implements Runnable {

			private UserInfo user;

			public RegisterRunnable(UserInfo user) {
				this.setUser(user);
			}

			@Override
			public void run() {

				String path = "http://192.168.91.1:8080/MonitorWeb/Servlet/RegistServlet";
				Map<String, String> params = new HashMap<String, String>();

				// ����ʵ������
				params.put("username", user.getUsername());
				params.put("nickname", user.getNickname());
				params.put("password", user.getPassword());
				params.put("password2", user.getPassword2());
				params.put("gender", user.getGender());
				params.put("phone", user.getPhone());
				params.put("email", user.getEmail());

				try {
					// ע��
					responseStr = HttpUtil.sendHttpClientPostRequest(path,params, "UTF-8");

					EventBus.getDefault().post(new RegisterResult());

				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			public void setUser(UserInfo user) {
				this.user = user;
			}
		}
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}
}
