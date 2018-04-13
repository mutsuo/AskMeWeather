/**
* Team Zhinengxianfeng Hebei Normal University
* FileName: GetURL.java
* 从URL获取JSONP字符串。需要一个String类型的参数URL来实例化对象。
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
	/** 目标URL */
	private String i_url;
	
	/**
	* 构造器
	*
	* @param url
	* @return none
	* @throws 异常类型.错误代码 注明从此类方法中抛出异常的说明
	*/
	public GetURL(String url) {
		// TODO Auto-generated constructor stub
		i_url=url;
	}
	
	/**
	* 从url获取String类型的JSONP字符串
	*
	* @param none
	* @return String
	* @throws 异常类型.错误代码 注明从此类方法中抛出异常的说明
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
