/**
* Team Zhinengxianfeng Hebei Normal University
* FileName: Nlp.java
* �ʷ�����
*
* @author ??
    * @Date    ????-??-??
* @version 1.00
*/
package com.janborn.www;
import java.util.HashMap;
import org.json.JSONObject;
import com.baidu.aip.nlp.AipNlp;
public class GetNlp {
	//����APPID/AK/SK
    public static final String APP_ID = "11069214";
    public static final String API_KEY = "G1SCbCcrXpwiut6lR1fK92xT";
    public static final String SECRET_KEY = "8IETLzLCZa0hr4weHBmAeuk1GGR20bz4";
    
    private String i_text;
    
	public GetNlp(String text) {
		i_text=text;
	}
	
	public JSONObject getNlp() {
        JSONObject temp = new JSONObject();
        return temp;
	}

}