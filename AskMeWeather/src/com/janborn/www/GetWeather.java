/**
* Team Zhinengxianfeng Hebei Normal University
* FileName: Weather.java
* 根据需求查询天气数据
*
* @author ??
    * @Date    ????-??-??
* @version 1.00
*/

package com.janborn.www;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.SignatureException;
import java.util.Date;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;

public class GetWeather {
	private String TIANQI_DAILY_WEATHER_URL = "https://api.seniverse.com/v3/weather/daily.json";
    private String TIANQI_NOW_WEATHER_URL = "https://api.seniverse.com/v3/weather/now.json";
    private String TIANQI_LIFE_SUGGESTRION_URL = "https://api.seniverse.com/v3/life/suggestion.json";
    
    private String TIANQI_API_SECRET_KEY = "vxigji7i0ua4bqzw"; //
    private String TIANQI_API_USER_ID = "UFB08DAFBA"; //
    
    private String i_location;
    private String i_language;
    private String i_unit;
    private String i_start;
    private String i_end;
    
    
	public GetWeather(
			String location,
			String language,
			String unit) {
		
	}
	public GetWeather(
			String location,
		    String language,
		    String unit,
		    String start,
		    String end) {
		
	}
	public GetWeather(
			String location,
			String language) {
		
	}
	
	  /**
	  * Generate HmacSHA1 signature with given data string and key
	  * @param data
	  * @param key
	  * @return String
	  * @throws SignatureException
	  */
	 private String generateSignature(String data, String key) throws SignatureException {
	     String result;
	     try {
	         // get an hmac_sha1 key from the raw key bytes
	         SecretKeySpec signingKey = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA1");
	         // get an hmac_sha1 Mac instance and initialize with the signing key
	         Mac mac = Mac.getInstance("HmacSHA1");
	         mac.init(signingKey);
	         // compute the hmac on input data bytes
	         byte[] rawHmac = mac.doFinal(data.getBytes("UTF-8"));
	         result = new sun.misc.BASE64Encoder().encode(rawHmac);
	     }
	     catch (Exception e) {
	         throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
	     }
	     return result;
	 }
	   /**
	   * Generate the URL to get now weather
	   * @param location
	   * @param language
	   * @param unit
	   * @return String
	   */
	  public String generateGetNowWeatherURL() {String temp=new String();return temp;}
	  
	  /**
	   * Generate the URL to get diary weather
	   * @param location
	   * @param language
	   * @param unit
	   * @param start
	   * @param end
	   * @return String
	   */
    public String generateGetDiaryWeatherURL() { String temp=new String();return temp;}
    
    /**
	   * Generate the URL to get life suggestion
	   * @param location
	   * @param language
	   * @return String
	   */
	 public String getLifeSuggestionURL() {String temp=new String();return temp;}
	
}
