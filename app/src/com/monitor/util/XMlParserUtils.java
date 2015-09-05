package com.monitor.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

import com.monitor.domain.CarInfo;
import com.monitor.domain.LocationInfo;
import com.monitor.domain.MonitorInfo;

public class XMlParserUtils {

	/**
	 * �Ѵ������ȡ����car ��Ϣ��xml��ת����List<Car>
	 * @param inStream ������xml��
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static List<CarInfo> XMLCarParserStoCarList(String xmlString) throws XmlPullParserException, IOException{
		
		CarInfo car = null;
		List<CarInfo> carList = new ArrayList<CarInfo>();
		
		XmlPullParser parser = Xml.newPullParser();
		
		
		//��xmlStringת��ByteArrayInputStream��
		ByteArrayInputStream inStream = new ByteArrayInputStream(xmlString.getBytes());
			
		parser.setInput(inStream, "UTF-8");
	
		int event = parser.getEventType();
		
		//��������car��
		while (event != XmlPullParser.END_DOCUMENT){
			switch(event){
			case XmlPullParser.START_TAG:
				if("car".equals(parser.getName())){
					car = new CarInfo();
					car.setCar_id(Integer.parseInt(parser.getAttributeValue(0)));
				}else if ("driver_name".equals(parser.getName())){
					car.setDriver_name(parser.nextText());
				}else if ("number".equals(parser.getName())){
					car.setNumber(parser.nextText());
				}
				break;
			case XmlPullParser.END_TAG:
				if("car".equals(parser.getName())){
					carList.add(car);
					car = null;
				}
				break;
			default:
				break;
			}
			event = parser.next();
		}
		
					
		return carList;    
	}
	
	/**
	 * �Ѵ������ȡ����monitorData��Ϣ��xml��ת����List<MonitorInfo>
	 * @param xmlString
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static List<MonitorInfo> XMLMDataParserStoMonitorDataList(String xmlString) throws XmlPullParserException, IOException{
		
		MonitorInfo monitorData = null;
		List<MonitorInfo> dataList = new ArrayList<MonitorInfo>();
		
		XmlPullParser parser = Xml.newPullParser();
		
		
		//��xmlStringת��ByteArrayInputStream��
		ByteArrayInputStream inStream = new ByteArrayInputStream(xmlString.getBytes());
		
		
		parser.setInput(inStream, "UTF-8");
		int event = parser.getEventType();
		
		//��������MonitorData��
		while (event != XmlPullParser.END_DOCUMENT){
			switch(event){
			case XmlPullParser.START_TAG:
				if("monitorData".equals(parser.getName())){
					monitorData = new MonitorInfo();
					monitorData.setMd_id(Integer.parseInt(parser.getAttributeValue(0)));
				}else if ("temperature".equals(parser.getName())){
					monitorData.setTemperature(Float.parseFloat(parser.nextText()));
				}else if ("humidity".equals(parser.getName())){
					monitorData.setHumidity(Float.parseFloat(parser.nextText()));
				}else if ("carbon_dioxide".equals(parser.getName())){
					monitorData.setCarbon_dioxide(Double.parseDouble(parser.nextText()));
				}else if ("oxygen".equals(parser.getName())){
					monitorData.setOxygen(Double.parseDouble(parser.nextText()));
				}
				else if ("nitrogen".equals(parser.getName())){
					monitorData.setNitrogen(Double.parseDouble(parser.nextText()));
				}
				else if ("ethylene".equals(parser.getName())){
					monitorData.setEthylene(Double.parseDouble(parser.nextText()));
				}
				else if ("ammonia".equals(parser.getName())){
					monitorData.setAmmonia(Double.parseDouble(parser.nextText()));
				}
				else if ("sulfur_dioxide".equals(parser.getName())){
					monitorData.setSulfur_dioxide(Double.parseDouble(parser.nextText()));
				}
				else if ("car_id".equals(parser.getName())){
					monitorData.setCar_id(Integer.parseInt(parser.nextText()));
				}
				else if ("time".equals(parser.getName())){
					monitorData.setTime(Timestamp.valueOf(parser.nextText()));
				}
				break;
			case XmlPullParser.END_TAG:
				if("monitorData".equals(parser.getName())){
					dataList.add(monitorData);
					monitorData = null;
				}
				break;
			default:
				break;
			}
			event = parser.next();
		}
						
		return dataList;    
	}
	
	/**
	 * �Ѵ������ȡ����LoctionInfo��Ϣ��xml��ת����LocationInfo
	 * @param xmlString
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static LocationInfo XMLLctInfoParserStoLctInfo(String xmlString)
			throws XmlPullParserException, IOException {

		LocationInfo locationInfo = null;

		XmlPullParser parser = Xml.newPullParser();

		// ��xmlStringת��ByteArrayInputStream��
		ByteArrayInputStream inStream = new ByteArrayInputStream(
				xmlString.getBytes());

		parser.setInput(inStream, "UTF-8");
		int event = parser.getEventType();

		// ��������LocationInfo��
		while (event != XmlPullParser.END_DOCUMENT) {
			switch (event) {
			case XmlPullParser.START_TAG:
				if ("location".equals(parser.getName())) {
					locationInfo = new LocationInfo();
					locationInfo.setLocation_id(Integer.parseInt(parser.getAttributeValue(0)));
				} else if ("car_id".equals(parser.getName())) {
					locationInfo.setCar_id(Integer.valueOf(parser.nextText()));
				} else if ("latitude".equals(parser.getName())) {
					locationInfo.setLatitude(Double.parseDouble(parser.nextText()));
				} else if ("longitude".equals(parser.getName())) {
					locationInfo.setLongitude(Double.parseDouble(parser.nextText()));
				} 
				break;
			case XmlPullParser.END_TAG:
				break;
			default:
				break;
			}
			event = parser.next();
		}

		return locationInfo;
	}
}
