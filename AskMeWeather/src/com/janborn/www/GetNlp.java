/**
* Team Zhinengxianfeng Hebei Normal University
* FileName: GetNlp.java
* �ʷ����������յ����ı���ͨ���ʷ�����������ת��ΪJSONObject��ʽ
*
* @author Fang Yuzhen
    * @Date    2018/4/17
* @version 1.00
*/
package com.janborn.www;

import org.json.JSONObject;

import com.baidu.aip.nlp.AipNlp;

public class GetNlp {
	/* ����APPID/AK/SK */
	public static final String APP_ID = "11069214";
	public static final String API_KEY = "G1SCbCcrXpwiut6lR1fK92xT";
	public static final String SECRET_KEY = "8IETLzLCZa0hr4weHBmAeuk1GGR20bz4";

	/* text������յ����ı� */
	private String text;
	/* ���ı�text������ת��JSONObject����,��jsonObject���� */
	private JSONObject jsonObject;

	/**
	 * ��text��ֵ�������ı�������text
	 *
	 * @param һ������������text���մ��������ַ����ݣ������ڳ�Աtext��ֵ
	 * @return �޷���ֵ
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * ��jsonObject��ֵ����text�ı�ת��ΪJSONObject������jsonObject������
	 *
	 * @param �޲�����ֱ��������˽�г�Աtext�ı�ת��ΪJSONObject������jsonObject������
	 * @return �޷���ֵ
	 */
	public void toJSON() {
		// ��ʼ��һ��AipNlp
		AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);
		// ���ýӿ�
		jsonObject = client.lexer(text, null);
	}

	/**
	 * �õ�jsonObject�Ķ���jsonObject
	 *
	 * @param �޲�����
	 * @return ����JSONObject���͵Ķ���jsonObject
	 */
	public JSONObject GetJSON() {
		return jsonObject;
	}

	/**
	 * �вι��캯���������ı�����text�Ͱ��ı�����textת��ΪJSONObject������jsonObject������
	 *
	 * @param һ������:����t���մ��������ַ������ݣ�������ֵ��text
	 */
	public GetNlp(String t) {
		text = t;
		// ��ʼ��һ��AipNlp
		AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);
		// ���ýӿ�
		jsonObject = client.lexer(text, null);
	}

	/**
	 * �޲ι��캯����
	 *
	 * @param �޲���
	 */
	public GetNlp() {

	}

}
