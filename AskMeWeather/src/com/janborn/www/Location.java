package com.janborn.www;

public class Location {

	public Location() {
		// TODO Auto-generated constructor stub
	}
	/*����λ��*/
	private String location;
	/*Nlp�Ķ���������ȡ����λ��*/
	private Nlp nlp;
	
	/**
	 * ��ôʻ��ַ���location
	 *
	 * @param �޲���
	 * @return ����һ���ַ������͵�locationֵ
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * ��������λ�ã���ֵ���ַ���location
	 *
	 * @param �޲���
	 * @return ����һ���ַ������͵�locationֵ
	 */
	public String analize() {
		if (!nlp.getLocaction().equals("")) {
			location=nlp.getLocaction();
		}
		return location;
	}

	/**
	 * �вι��캯���������ı�����text�Ͱ�ͨ������õ�GetJSON��ʽΪi_item���鸳ֵ
	 *
	 * @param һ������:����nlp��Nlp�Ķ���,��ֵ������nlp
	 */
	public Location(Nlp nlp) {
		super();
		this.location = "ip";
		this.nlp = nlp;
	}
	
}
