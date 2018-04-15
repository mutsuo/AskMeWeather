/**
* Team Zhinengxianfeng Hebei Normal University
* FileName: Weather.java
* 返回需要的天气数据
*
* @author ??
    * @Date    ????-??-??
* @version 1.00
*/
package com.janborn.www;

import java.util.List;

import org.json.JSONObject;

public class Weather extends GetWeather {
	private JSONObject weather;
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
		private Location location;
		private String text;
		private String code;
		private String temperature;
		private String last_update;

		public Now() {
			text = "";
			code = "";
			temperature = "";
		}

		public Location getLocation() {
			return location;
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

		public void setLocation(Location location) {
			this.location = location;
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
		private Location location;
		private List<DailyContent> daily;
		private String last_update;
		public Daily(){
		}
		public Location getLocation() {
			return location;
		}
		public List<DailyContent> getDaily() {
			return daily;
		}
		public String getLast_update() {
			return last_update;
		}
		public void setLocation(Location location) {
			this.location = location;
		}
		public void setDaily(List<DailyContent> daily) {
			this.daily = daily;
		}
		public void setLast_update(String last_update) {
			this.last_update = last_update;
		}
		
	}

	public Weather(String location, String language, String unit) {
		super(location, language, unit);
		// TODO Auto-generated constructor stub
	}

	public Weather(String location, String language, String unit, String start, String end) {
		super(location, language, unit, start, end);
		// TODO Auto-generated constructor stub
	}

	public Weather(String location, String language) {
		super(location, language);
		// TODO Auto-generated constructor stub
	}

}
