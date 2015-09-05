package com.monitor.web;

import java.io.IOException;




import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.monitor.domain.UserInfo;
import com.monitor.exception.UserMsgException;
import com.monitor.service.UserService;


public class RegistServlet extends HttpServlet{


	private static final long serialVersionUID = 1L;
	public final static  int REGISTER_NAME_ERROR = 3;
	public final static  int REGISTER_SUCCESS = 4;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//���÷������ı���Ϊutf-8
		req.setCharacterEncoding("utf-8");
		
		//����������ı���Ϊutf-8
		resp.setContentType("text/html;charset=utf-8");

		UserService service = new UserService();
		UserInfo user = new UserInfo();
		
		try {
			//��װ�û�����
			BeanUtils.populate(user, req.getParameterMap());
			
			//ע���û�
			service.registerUser(user);
			
			//������Ϣ---ע��ɹ�	
			resp.getOutputStream().write(String.valueOf(REGISTER_SUCCESS).getBytes("UTF-8"));
			
		}catch(UserMsgException e){
			if("�û����Ѵ���".equals(e.getMessage())){	
				resp.getOutputStream().write(String.valueOf(REGISTER_NAME_ERROR).getBytes("UTF-8"));
			}	
		}catch (Exception e) {
			e.printStackTrace();
		}
			
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		doGet(req, resp);
	}

}
