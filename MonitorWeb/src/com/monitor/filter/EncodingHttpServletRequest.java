package com.monitor.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodingHttpServletRequest extends HttpServletRequestWrapper {

	private HttpServletRequest request;
	public EncodingHttpServletRequest(HttpServletRequest request) {
		super(request);	
		this.request = request;
	}
	
	/**
	 * ����getParameter,����ֵ�����Ѿ����ַ���ת��Ϊutf-8�ı���
	 */
	@Override
	public String getParameter(String name) {
		
		String value = request.getParameter(name);
		if(null != value){
			try {
				value = new String(value.getBytes("ISO08859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {				
				e.printStackTrace();
				throw new RuntimeException("ת��ʧ��");
			}
		}
		
		return value;
	}
	
}
