package com.monitor.service;

import com.monitor.dao.UserDao;
import com.monitor.domain.UserInfo;
import com.monitor.exception.UserMsgException;
import com.monitor.factory.DaoFactory;

public class UserService {

	private UserDao dao = DaoFactory.getFactory().getUserDao();
	
	/**
	 * ���û�����ӵ����ݿ���
	 * @param user Ҫע����û�
	 * @throws UserMsgException
	 */
	public void registerUser(UserInfo user) throws UserMsgException{
		
		//1.����û����Ƿ��Ѿ�����
		if(null != dao.findUserByUserName(user.getusername())){
			throw new UserMsgException("�û����Ѵ���");
		}
		
		//2.ע���û�
		dao.addUser(user);
	}
	
	/**
	 * �����û����������ж��û��Ƿ������ݿ���
	 * @param username �û���
	 * @param password �û�����
	 * @return ���򷵻��û������࣬���𷵻�NULL
	 */
	public UserInfo isUser(String username, String password){
		
		return dao.findUserByUNandPSW(username, password);
	}	
}
