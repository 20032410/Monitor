package com.monitor.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBOpenHelper extends SQLiteOpenHelper {

	public DBOpenHelper(Context context) {
		
		super(context, "monitorsysytem.db", null, 1); //�������ݿ�
	
	}

	@Override 
	public void onCreate(SQLiteDatabase db) {
		//���ɳ�����Ϣ���ݱ�
		db.execSQL("CREATE TABLE car("
				+ "_id integer primary key autoincrement,"
				+ "driver_name varchar(20) NOT NULL,"
				+ "number varchar(12) NOT NULL)");

	}

	@Override //�汾�ű��޸�ʱ����
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
