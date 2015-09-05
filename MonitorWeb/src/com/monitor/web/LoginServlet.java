package com.monitor.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.monitor.domain.UserInfo;
import com.monitor.service.UserService;



public class LoginServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	public final static  int LOGIN_NAME_OR_PSW_ERROR = 1;
	public final static  int LOGIN_SUCCESS = 2;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		UserService service = new UserService();
		
		//���÷������ı���Ϊutf-8
		req.setCharacterEncoding("UTF-8");
		
		//����������ı���Ϊutf-8
		resp.setContentType("text/html;charset=UTF-8");
				
		//��ȡ�ͻ����ύ���û���������
		String username = req.getParameter("username"); //�����û�����
		String password = req.getParameter("password");
			
		//����Service�еķ�������û�������
		UserInfo user = service.isUser(username, password);
		
		if(null != user){//���򷵻�3
			resp.getOutputStream().write(String.valueOf(LOGIN_SUCCESS).getBytes("UTF-8"));
		}
		else{//���򷵻�2
			resp.getOutputStream().write(String.valueOf(LOGIN_NAME_OR_PSW_ERROR).getBytes("UTF-8"));
		}
				
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		doGet(req, resp);
	}
}
