package com.monitor.dao;

import com.monitor.domain.UserInfo;

public interface UserDao {
	/**
	 * �����û��������û�
	 * @param username �û���
	 * @return �����û����ҵ����û���Ϣbean,���û�ҵ�����null
	 */
	public UserInfo findUserByUserName(String username);
	/**
	 * ����û�
	 * @param user Ҫ��ӵ��û���Ϣbean
	 */
	public void addUser(UserInfo user);
	
	/**
	 * �����û���������Ҷ�Ӧ���û�
	 * @param username �û���
	 * @param password ����
	 * @return �ҵ����û�,����Ҳ����򷵻�null
	 */
	public UserInfo findUserByUNandPSW(String username,String password);
}
