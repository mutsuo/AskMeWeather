/**
* Team Zhinengxianfeng Hebei Normal University
* FileName: GetURL.java
* ��URL��ȡJSONP�ַ�������Ҫһ��String���͵Ĳ���URL��ʵ��������
*
* @author Deng Yang
    * @Date    2018-04-13
* @version 1.00
*/

package com.janborn.www;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SignatureException;
import java.util.Iterator;
import java.util.List;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetURL {
	/** Ŀ��URL */
	private String i_url;
	
	/**
	* ������
	*
	* @param url
	* @return none
	* @throws �쳣����.������� ע���Ӵ��෽�����׳��쳣��˵��
	*/
	public GetURL(String url) {
		// TODO Auto-generated constructor stub
		i_url=url;
	}
	
	/**
	* ��url��ȡString���͵�JSONP�ַ���
	*
	* @param none
	* @return String
	* @throws �쳣����.������� ע���Ӵ��෽�����׳��쳣��˵��
	*/

	public String getUrl() throws IOException {
		URL myurl = new URL(i_url);
		InputStream in = myurl.openStream();
	    BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
	    StringBuffer bs = new StringBuffer();
	    String JsonStr = null;
	    while((JsonStr=buffer.readLine())!=null){
	        bs.append(JsonStr);
	    }
	    return JsonStr;
	}
}
