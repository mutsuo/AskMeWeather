package com.janborn.www;

public class Location {

	public Location() {
		// TODO Auto-generated constructor stub
	}
	/*地理位置*/
	private String location;
	/*Nlp的对象，用来获取地理位置*/
	private Nlp nlp;
	
	/**
	 * 获得词汇字符串location
	 *
	 * @param 无参数
	 * @return 返回一个字符串类型的location值
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * 分析地理位置，赋值给字符串location
	 *
	 * @param 无参数
	 * @return 返回一个字符串类型的location值
	 */
	public String analize() {
		if (!nlp.getLocaction().equals("")) {
			location=nlp.getLocaction();
		}
		return location;
	}

	/**
	 * 有参构造函数：设置文本内容text和把通过父类得到GetJSON格式为i_item数组赋值
	 *
	 * @param 一个参数:参数nlp是Nlp的对象,赋值给属性nlp
	 */
	public Location(Nlp nlp) {
		super();
		this.location = "ip";
		this.nlp = nlp;
	}
	
}
