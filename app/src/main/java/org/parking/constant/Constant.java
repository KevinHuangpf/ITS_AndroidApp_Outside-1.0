package org.parking.constant;

public class Constant {
	//Btn的标识
	public static final int BTN_FLAG_MESSAGE = 01;
	public static final int BTN_FLAG_CONTACTS = 02;
	public static final int BTN_FLAG_NEWS = 4;
	public static final int BTN_FLAG_SETTING = 0x01 << 3;
	
	//Fragment的标识
	public static final String FRAGMENT_FLAG_MESSAGE = "我要停车";
	public static final String FRAGMENT_FLAG_CONTACTS = "车位概况";
	public static final String FRAGMENT_FLAG_NEWS = "我的服务";
	public static final String FRAGMENT_FLAG_SETTING = "设置中心";
	public static final String FRAGMENT_FLAG_SIMPLE = "缺省设置";

}
