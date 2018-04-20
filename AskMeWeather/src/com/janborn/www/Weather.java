/**
* Team Zhinengxianfeng Hebei Normal University
* FileName: Weather.java
* 返回需要的天气数据
*
* @author Liu Hengren
    * @Date    2018-04-19
* @version 1.00
*/
package com.janborn.www;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;



public class Weather extends GetWeather {
	private JSONObject weather;
	public Location location2=new Location();
	public LifeSuggestion lifeSuggestion=new LifeSuggestion();
	public Daily  daily=new Daily();
	public Now now=new Now();
	public class Location {
		private String id;
		private String name;
		private String country;
		private String timezone;
		private String timezone_offset;
		public Location() {
			id = "";
			name = "";
			country = "";
			timezone = "";
			timezone_offset = "";
		}


		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getCountry() {
			return country;
		}

		public String getTimezone() {
			return timezone;
		}

		public String getTimezone_offset() {
			return timezone_offset;
		}


		public void setId(String id) {
			this.id = id;
		}


		public void setName(String name) {
			this.name = name;
		}


		public void setCountry(String country) {
			this.country = country;
		}


		public void setTimezone(String timezone) {
			this.timezone = timezone;
		}


		public void setTimezone_offset(String timezone_offset) {
			this.timezone_offset = timezone_offset;
		}
		
		
	}

	public class Now {
		private String text;
		private String code;
		private String temperature;
		private String last_update;

		public Now() {
			text = "";
			code = "";
			temperature = "";
		}


		public String getText() {
			return text;
		}

		public String getCode() {
			return code;
		}

		public String getTemperature() {
			return temperature;
		}

		public String getLast_update() {
			return last_update;
		}


		public void setText(String text) {
			this.text = text;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public void setTemperature(String temperature) {
			this.temperature = temperature;
		}

		public void setLast_update(String last_update) {
			this.last_update = last_update;
		}
		
	}

	public class DailyContent {
		private String date;
		private String text_day;
		private String code_day;
		private String text_night;
		private String code_night;
		private String high;
		private String low;
		private String precip;
		private String wind_direction;
		private String wind_direction_degree;
		private String wind_speed;
		private String wind_scale;
		public DailyContent() {
			date = "";
			text_day = "";
			code_day = "";
			text_night = "";
			code_night = "";
			high = "";
			low = "";
			precip = "";
			wind_direction = "";
			wind_direction_degree = "";
			wind_speed = "";
			wind_scale = "";
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getText_day() {
			return text_day;
		}

		public void setText_day(String text_day) {
			this.text_day = text_day;
		}

		public String getCode_day() {
			return code_day;
		}

		public void setCode_day(String code_day) {
			this.code_day = code_day;
		}

		public String getText_night() {
			return text_night;
		}

		public void setText_night(String text_night) {
			this.text_night = text_night;
		}

		public String getCode_night() {
			return code_night;
		}

		public void setCode_night(String code_night) {
			this.code_night = code_night;
		}

		public String getHigh() {
			return high;
		}

		public void setHigh(String high) {
			this.high = high;
		}

		public String getLow() {
			return low;
		}

		public void setLow(String low) {
			this.low = low;
		}

		public String getPrecip() {
			return precip;
		}

		public void setPrecip(String precip) {
			this.precip = precip;
		}

		public String getWind_direction() {
			return wind_direction;
		}

		public void setWind_direction(String wind_direction) {
			this.wind_direction = wind_direction;
		}

		public String getWind_direction_degree() {
			return wind_direction_degree;
		}

		public void setWind_direction_degree(String wind_direction_degree) {
			this.wind_direction_degree = wind_direction_degree;
		}

		public String getWind_speed() {
			return wind_speed;
		}

		public void setWind_speed(String wind_speed) {
			this.wind_speed = wind_speed;
		}

		public String getWind_scale() {
			return wind_scale;
		}

		public void setWind_scale(String wind_scale) {
			this.wind_scale = wind_scale;
		}
		
	}

	public class Daily {
		private List<DailyContent> daily=new ArrayList<>();
		private String last_update;
		public Daily(){
		}
		public List<DailyContent> getDaily() {
			return daily;
		}
		public String getLast_update() {
			return last_update;
		}
		public void setDaily(List<DailyContent> daily) {
			this.daily = daily;
		}
		public void setLast_update(String last_update) {
			this.last_update = last_update;
		}
		
	}
	public  class LifeSuggestion{
		private String dressing;
		private String flu;
		private String sport;
		private String travel;
		private String uv;
		private String car_washing;
		private String last_update;
		public String getCar_washing() {
			return car_washing;
		}
		public void setCar_washing(String car_washing) {
			this.car_washing = car_washing;
		}
		public String getDressing() {
			return dressing;
		}
		public void setDressing(String dressing) {
			this.dressing = dressing;
		}
		public String getLast_update() {
			return last_update;
		}
		public void setLast_update(String last_update) {
			this.last_update = last_update;
		}
		public String getFlu() {
			return flu;
		}
		public void setFlu(String flu) {
			this.flu = flu;
		}
		public String getSport() {
			return sport;
		}
		public void setSport(String sport) {
			this.sport = sport;
		}
		public String getTravel() {
			return travel;
		}
		public void setTravel(String travel) {
			this.travel = travel;
		}
		public String getUv() {
			return uv;
		}
		public void setUv(String uv) {
			this.uv = uv;
		}
		
	}

	//用于获取即时天气
	public Weather(String location, String language, String unit) {
		super(location, language, unit);
		Now now = this.new Now();
		weather=new JSONObject(this.getJsonString());
		JSONArray jsonWeatherArr=weather.getJSONArray("results");
		setLocation();
		JSONObject jsonNow=jsonWeatherArr.getJSONObject(0).getJSONObject("now");
		JSONObject jsonlast_update=jsonWeatherArr.getJSONObject(0);
		this.now.setCode(jsonNow.getString("code"));
		this.now.setText(jsonNow.getString("text"));
		this.now.setTemperature(jsonNow.getString("temperature"));
		this.now.setLast_update(jsonlast_update.getString("last_update"));
		// TODO Auto-generated constructor stub
	}
	//用于获取逐日天气（3天）
	public Weather(String location, String language, String unit, String start, String end) {
		super(location, language, unit, start, end);
		weather=new JSONObject(this.getJsonString());
		JSONArray jsonWeatherArr=weather.getJSONArray("results");
		setLocation();
		JSONObject jsonlast_update=jsonWeatherArr.getJSONObject(0);
		this.daily.setLast_update(jsonlast_update.getString("last_update"));
		JSONArray jsonDailyArr=jsonWeatherArr.getJSONObject(0).getJSONArray("daily");
		//System.out.println(jsonDailyArr.length());		//测试用代码
		for (int i=0;i<jsonDailyArr.length();i++)
		{
			DailyContent dailyContent = this.new DailyContent();
			dailyContent.setDate(jsonDailyArr.getJSONObject(i).getString("date"));
			dailyContent.setText_day(jsonDailyArr.getJSONObject(i).getString("text_day"));
			dailyContent.setCode_day(jsonDailyArr.getJSONObject(i).getString("code_day"));
			dailyContent.setText_night(jsonDailyArr.getJSONObject(i).getString("text_night"));
			dailyContent.setCode_night(jsonDailyArr.getJSONObject(i).getString("code_night"));
			dailyContent.setHigh(jsonDailyArr.getJSONObject(i).getString("high"));
			dailyContent.setLow(jsonDailyArr.getJSONObject(i).getString("low"));
			dailyContent.setPrecip(jsonDailyArr.getJSONObject(i).getString("precip"));
			dailyContent.setWind_direction(jsonDailyArr.getJSONObject(i).getString("weind_direction"));
			dailyContent.setWind_direction_degree(jsonDailyArr.getJSONObject(i).getString("wind_direction_degree"));
			dailyContent.setWind_speed(jsonDailyArr.getJSONObject(i).getString("wind_speed"));
			dailyContent.setWind_scale(jsonDailyArr.getJSONObject(i).getString("wind_scale"));
			this.daily.daily.add(dailyContent);
			
		}
		
		// TODO Auto-generated constructor stub
	}
	//用于获取生活信息
	public Weather(String location, String language) {
		super(location, language);
		weather=new JSONObject(this.getJsonString());
		JSONArray jsonWeatherArr=weather.getJSONArray("results");
		setLocation();
		JSONObject jsonSuggestion=jsonWeatherArr.getJSONObject(0).getJSONObject("suggestion");
		JSONObject jsonCar_washing=jsonSuggestion.getJSONObject("car_washing");
		JSONObject jsonDressing=jsonSuggestion.getJSONObject("dressing");
		JSONObject jsonFlu=jsonSuggestion.getJSONObject("flu");
		JSONObject jsonSport=jsonSuggestion.getJSONObject("sprot");
		JSONObject jsonTravel=jsonSuggestion.getJSONObject("travel");
		JSONObject jsonUv=jsonSuggestion.getJSONObject("uv");
		this.lifeSuggestion.setDressing(jsonDressing.getString("brief"));
		this.lifeSuggestion.setFlu(jsonFlu.getString("brief"));
		this.lifeSuggestion.setSport(jsonSport.getString("brief"));
		this.lifeSuggestion.setTravel(jsonTravel.getString("brief"));
		this.lifeSuggestion.setUv(jsonUv.getString("brief"));
		this.lifeSuggestion.setCar_washing(jsonCar_washing.getString("brief"));
		// TODO Auto-generated constructor stub
	}
	//用于设置Location
	public void setLocation() {
		JSONArray jsonWeatherArr=weather.getJSONArray("results");
		JSONObject jsonlocation=jsonWeatherArr.getJSONObject(0).getJSONObject("location");
		this.location2.setCountry(jsonlocation.getString("country"));
		this.location2.setId(jsonlocation.getString("id"));
		this.location2.setName(jsonlocation.getString("name"));
		this.location2.setTimezone(jsonlocation.getString("timezone"));
		this.location2.setTimezone_offset(jsonlocation.getString("timezone_offset"));
	}

}
