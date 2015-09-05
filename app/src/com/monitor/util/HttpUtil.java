package com.monitor.util;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;


public class HttpUtil {
	
	
	 /* ͨ��post�ķ�ʽ��������
	 * @param path�������ַ
	 * @param params����������
	 * @param ecoding�������ʽ
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String sendHttpClientPostRequest(String path,
			Map<String, String> params, String ecoding) throws ClientProtocolException, IOException {
		
		List<NameValuePair> pairs = new ArrayList<NameValuePair>(); //����������
		
		//���������ݷ���paris���ϵ���
		if (null != params && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));		
			}	
		}
		
		//����ʵ������
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs, ecoding); 
		
		//����HttpPost����
		HttpPost httpPost = new HttpPost(path);
		
		//��������
		httpPost.setEntity(entity);
		
		//�൱�������
		DefaultHttpClient client = new DefaultHttpClient(); 
		
		//���ʷ�����
		HttpResponse response = client.execute(httpPost);
		
		//�жϷ��ʷ������Ƿ�ɹ�
		if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
			
			//��ȡ�������з��ص�����
			String responseStr = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
	
			return responseStr;
		}
		
		return null;
		
	}
	
}



