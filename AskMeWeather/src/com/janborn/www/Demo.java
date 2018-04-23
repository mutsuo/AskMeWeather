package com.janborn.www;

public class Demo {

	public Demo() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		Nlp gt=new Nlp("我在石家庄");
		System.out.println("loc"+gt.locaction());
		// System.out.println(gt.GetJSON().getJSONArray("items").length());
		// System.out.println(gt.GetJSON().getJSONArray("items").getJSONObject(3).getString("item"));
		// System.out.println(gt.GetJSON().toString(2));
	}
}
