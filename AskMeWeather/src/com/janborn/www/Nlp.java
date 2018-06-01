/**
* Team Zhinengxianfeng Hebei Normal University
* FileName: Nlp.java
* JSONObject类的jsonObject对象格式的分析：通过继承父类GetNlp，分析jsonObject对象的格式，得到有效的地理位置信息。
*
* @author Fang Yuzhen
    * @Date    2018/4/17
* @version 1.00
*/

package com.janborn.www;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.ErrorManager;

import org.json.JSONObject;

import com.janborn.www.Nlp.aipNoConnectionException;
import com.janborn.www.Weather.EerrorMessage;

public class Nlp extends GetNlp {
	/* text保存接收到的文本 */
	private String text;
	/* i_item是数组名，用来保存成员内部类Items的所有信息 */
	private List<Items> i_item = new ArrayList<>();

	/**
	 * Team Zhinengxianfeng Hebei Normal University FileName: Nlp.java
	 * 成员内部类Items：得到的是一个数组，保存jsonObject对象里的部分有效信息。使得key与value的值一一对应。
	 *
	 * @author Fang Yuzhen
	 * @Date 2018/4/17
	 * @version 1.00
	 */
	public class Items {
		/* item是词汇的字符串 */
		private String item;
		/* ne是命名实体类型，命名实体识别算法使用。词性标注算法中，此项为空串 */
		private String ne;
		/* pos是词性，词性标注算法使用。命名实体识别算法中，此项为空串 */
		private String pos;
		/* byte_offset在text中的字节级offset（使用GBK编码） */
		private int byte_offset;
		/* uri是链指到知识库的URI，只对命名实体有效。对于非命名实体和链接不到知识库的命名实体，此项为空串 */
		private String uri;
		/* formal是词汇的标准化表达，主要针对时间、数字单位，没有归一化表达的，此项为空串 */
		private String formal;

		/**
		 * 有参构造函数：通过得到一个items数组，把数组下标和key值传进去，得到对应的value值
		 *
		 * @param 一个参数：参数i表示数组下标
		 */
		public Items(int i) {
			this.item = Nlp.this.GetJSON().getJSONArray("items").getJSONObject(i).getString("item");
			this.ne = Nlp.this.GetJSON().getJSONArray("items").getJSONObject(i).getString("ne");
			this.pos = Nlp.this.GetJSON().getJSONArray("items").getJSONObject(i).getString("pos");
			this.byte_offset = Nlp.this.GetJSON().getJSONArray("items").getJSONObject(i).getInt("byte_offset");
			this.uri = Nlp.this.GetJSON().getJSONArray("items").getJSONObject(i).getString("uri");
			this.formal = Nlp.this.GetJSON().getJSONArray("items").getJSONObject(i).getString("formal");
		}

		/**
		 * 获得词汇字符串item
		 *
		 * @param 无参数
		 * @return 返回一个字符串类型的item值
		 */
		public String getItem() {
			return item;
		}

		/**
		 * 获得命名实体类型ne
		 *
		 * @param 无参数
		 * @return 返回一个字符串类型的ne值
		 */
		public String getNe() {
			return ne;
		}

		/**
		 * 获得词性pos
		 *
		 * @param 无参数
		 * @return 返回一个字符串类型的pos值
		 */
		public String getPos() {
			return pos;
		}

		/**
		 * 获得在text中的字节级offset的byte_offset
		 *
		 * @param 无参数
		 * @return 返回一个整数类型的byte_offset值
		 */
		public int getByte_offset() {
			return byte_offset;
		}

		/**
		 * 获得链指到知识库的URI
		 *
		 * @param 无参数
		 * @return 返回一个字符串类型的uri值
		 */
		public String getUri() {
			return uri;
		}

		/**
		 * 获得词汇的标准化表达formal
		 *
		 * @param 无参数
		 * @return 返回一个字符串类型的formal值
		 */
		public String getFormal() {
			return formal;
		}

	}

	/**
	 * 获得文本的内容text
	 *
	 * @param 无参数
	 * @return 返回一个字符串类型的text值
	 */
	public String getText() {
		return text;
	}

	/**
	 * 给text赋值：设置文本的内容text
	 *
	 * @param 一个参数：参数t接收传进来的字符内容，给类内成员text赋值
	 * @return 无返回值
	 */
	public void setText(String t) {
		this.text = t;
	}

	/**
	 * 获取i_item下标为i的值
	 *
	 * @param 一个参数：参数i接收传进来的下标，通过下标，找到当前下标的值
	 * @return 返回一个Items类型的当前下标i的值
	 */
	public Items getI_ite(int i) {
		return i_item.get(i);
	}

	/**
	 * 获取地理位置的信息：ne="LOC"
	 *
	 * @param 无参数
	 * @return 返回一个字符串类型的地理位置的值
	 */
	public String getLocaction() {
		// 字符串s保存获得的地理位置的信息，初值为空串
		String s = "";
		// 通过遍历i_item数组，判断当前的内容是否是地理位置，如果是，则保存在s里，最后返回s
		for (int i = 0; i < getLength(); i++) {
			if (i_item.get(i).getNe().equals("LOC")) {
				s = s + i_item.get(i).getItem();
			}
		}
		return s;
	}

	/**
	 * 给i_item添加元素：通过传进来的items，把它插入到i_item的末尾位置
	 *
	 * @param 一个参数：参数items接收传进来的Items内容，插入类内成员i_item末尾位置
	 * @return 无返回值
	 */
	public void setI_item(Items items) {
		this.i_item.add(items); // 必须修改
	}

	/**
	 * 获取i_item数组的长度
	 *
	 * @param 无参数
	 * @return 返回整数类型的长度的值
	 */
	public int getLength() {
		// TODO Auto-generated method stub
		return i_item.size();
	}

	/**
	 * 无参构造函数：
	 *
	 * @param 无参数
	 */
	public Nlp() {
		super();
	}

	/**
	 * 有参构造函数：设置文本内容text和把通过父类得到GetJSON格式为i_item数组赋值
	 *
	 * @param 一个参数:参数t接收传进来的字符串内容，把它赋值给text
	 * @throws aipNoConnectionException 
	 */
	public Nlp(String t) throws aipNoConnectionException {
		super(t);
		// TODO Auto-generated constructor stub
		if(super.GetJSON()==null) throw new aipNoConnectionException();
		else {
			text = t;
			JSONObject jsonObject2 = Nlp.this.GetJSON();
			Iterator<String> keys = jsonObject2.keys();
			String key =keys.next().toString();
			for(;keys.hasNext();)
				key=keys.next();
			if(key.equals("items")) {
				for (int i = 0; i < GetJSON().getJSONArray("items").length(); i++) {
					i_item.add(new Items(i));
				}
			}
			else {
				throw new aipNoConnectionException();
			}
		}
	}
	
	public class aipNoConnectionException extends Exception
	{
	  public aipNoConnectionException()
	  {

	  }
	  public aipNoConnectionException(String s)
	  {
	        super(s);
	  }
	}

}
