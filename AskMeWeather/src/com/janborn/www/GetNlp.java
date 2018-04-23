/**
* Team Zhinengxianfeng Hebei Normal University
* FileName: GetNlp.java
* 词法分析：接收到的文本，通过词法分析，把它转化为JSONObject格式
*
* @author Fang Yuzhen
    * @Date    2018/4/17
* @version 1.00
*/
package com.janborn.www;

import org.json.JSONObject;

import com.baidu.aip.nlp.AipNlp;

public class GetNlp {
	/* 设置APPID/AK/SK */
	public static final String APP_ID = "11069214";
	public static final String API_KEY = "G1SCbCcrXpwiut6lR1fK92xT";
	public static final String SECRET_KEY = "8IETLzLCZa0hr4weHBmAeuk1GGR20bz4";

	/* text保存接收到的文本 */
	private String text;
	/* 将文本text的内容转成JSONObject对象,用jsonObject保存 */
	private JSONObject jsonObject;

	/**
	 * 给text赋值：设置文本的内容text
	 *
	 * @param 一个参数：参数text接收传进来的字符内容，给类内成员text赋值
	 * @return 无返回值
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * 给jsonObject赋值：把text文本转化为JSONObject对象，用jsonObject来保存
	 *
	 * @param 无参数：直接用类内私有成员text文本转化为JSONObject对象，用jsonObject来保存
	 * @return 无返回值
	 */
	public void toJSON() {
		// 初始化一个AipNlp
		AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);
		// 调用接口
		jsonObject = client.lexer(text, null);
	}

	/**
	 * 得到jsonObject的对象jsonObject
	 *
	 * @param 无参数：
	 * @return 返回JSONObject类型的对象jsonObject
	 */
	public JSONObject GetJSON() {
		return jsonObject;
	}

	/**
	 * 有参构造函数：设置文本内容text和把文本内容text转化为JSONObject对象，用jsonObject来保存
	 *
	 * @param 一个参数:参数t接收传进来的字符串内容，把它赋值给text
	 */
	public GetNlp(String t) {
		text = t;
		// 初始化一个AipNlp
		AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);
		// 调用接口
		jsonObject = client.lexer(text, null);
	}

	/**
	 * 无参构造函数：
	 *
	 * @param 无参数
	 */
	public GetNlp() {

	}

}
