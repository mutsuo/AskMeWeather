/**
* Team Zhinengxianfeng Hebei Normal University
* FileName: Nlp.java
* JSONObject���jsonObject�����ʽ�ķ�����ͨ���̳и���GetNlp������jsonObject����ĸ�ʽ���õ���Ч�ĵ���λ����Ϣ��
*
* @author Fang Yuzhen
    * @Date    2018/4/17
* @version 1.00
*/

package com.janborn.www;

import java.util.ArrayList;

public class Nlp extends GetNlp {
	/* text������յ����ı� */
	private String text;
	/* i_item�������������������Ա�ڲ���Items��������Ϣ */
	private ArrayList<Items> i_item = new ArrayList<>();

	/**
	 * Team Zhinengxianfeng Hebei Normal University FileName: Nlp.java
	 * ��Ա�ڲ���Items���õ�����һ�����飬����jsonObject������Ĳ�����Ч��Ϣ��ʹ��key��value��ֵһһ��Ӧ��
	 *
	 * @author Fang Yuzhen
	 * @Date 2018/4/17
	 * @version 1.00
	 */
	public class Items {
		/* item�Ǵʻ���ַ��� */
		private String item;
		/* ne������ʵ�����ͣ�����ʵ��ʶ���㷨ʹ�á����Ա�ע�㷨�У�����Ϊ�մ� */
		private String ne;
		/* pos�Ǵ��ԣ����Ա�ע�㷨ʹ�á�����ʵ��ʶ���㷨�У�����Ϊ�մ� */
		private String pos;
		/* byte_offset��text�е��ֽڼ�offset��ʹ��GBK���룩 */
		private int byte_offset;
		/* uri����ָ��֪ʶ���URI��ֻ������ʵ����Ч�����ڷ�����ʵ������Ӳ���֪ʶ�������ʵ�壬����Ϊ�մ� */
		private String uri;
		/* formal�Ǵʻ�ı�׼������Ҫ���ʱ�䡢���ֵ�λ��û�й�һ�����ģ�����Ϊ�մ� */
		private String formal;

		/**
		 * �вι��캯����ͨ���õ�һ��items���飬�������±��keyֵ����ȥ���õ���Ӧ��valueֵ
		 *
		 * @param һ������������i��ʾ�����±�
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
		 * ��ôʻ��ַ���item
		 *
		 * @param �޲���
		 * @return ����һ���ַ������͵�itemֵ
		 */
		public String getItem() {
			return item;
		}

		/**
		 * �������ʵ������ne
		 *
		 * @param �޲���
		 * @return ����һ���ַ������͵�neֵ
		 */
		public String getNe() {
			return ne;
		}

		/**
		 * ��ô���pos
		 *
		 * @param �޲���
		 * @return ����һ���ַ������͵�posֵ
		 */
		public String getPos() {
			return pos;
		}

		/**
		 * �����text�е��ֽڼ�offset��byte_offset
		 *
		 * @param �޲���
		 * @return ����һ���������͵�byte_offsetֵ
		 */
		public int getByte_offset() {
			return byte_offset;
		}

		/**
		 * �����ָ��֪ʶ���URI
		 *
		 * @param �޲���
		 * @return ����һ���ַ������͵�uriֵ
		 */
		public String getUri() {
			return uri;
		}

		/**
		 * ��ôʻ�ı�׼�����formal
		 *
		 * @param �޲���
		 * @return ����һ���ַ������͵�formalֵ
		 */
		public String getFormal() {
			return formal;
		}

	}

	/**
	 * ����ı�������text
	 *
	 * @param �޲���
	 * @return ����һ���ַ������͵�textֵ
	 */
	public String getText() {
		return text;
	}

	/**
	 * ��text��ֵ�������ı�������text
	 *
	 * @param һ������������t���մ��������ַ����ݣ������ڳ�Աtext��ֵ
	 * @return �޷���ֵ
	 */
	public void setText(String t) {
		this.text = t;
	}

	/**
	 * ��ȡi_item�±�Ϊi��ֵ
	 *
	 * @param һ������������i���մ��������±꣬ͨ���±꣬�ҵ���ǰ�±��ֵ
	 * @return ����һ��Items���͵ĵ�ǰ�±�i��ֵ
	 */
	public Items getI_ite(int i) {
		return i_item.get(i);
	}

	/**
	 * ��ȡ����λ�õ���Ϣ��ne="LOC"
	 *
	 * @param �޲���
	 * @return ����һ���ַ������͵ĵ���λ�õ�ֵ
	 */
	public String getLocaction() {
		// �ַ���s�����õĵ���λ�õ���Ϣ����ֵΪ�մ�
		String s = "";
		// ͨ������i_item���飬�жϵ�ǰ�������Ƿ��ǵ���λ�ã�����ǣ��򱣴���s���󷵻�s
		for (int i = 0; i < getLength(); i++) {
			if (i_item.get(i).getNe().equals("LOC")) {
				s = s + i_item.get(i).getItem();
			}
		}
		return s;
	}

	/**
	 * ��i_item���Ԫ�أ�ͨ����������items���������뵽i_item��ĩβλ��
	 *
	 * @param һ������������items���մ�������Items���ݣ��������ڳ�Աi_itemĩβλ��
	 * @return �޷���ֵ
	 */
	public void setI_item(Items items) {
		this.i_item.add(items); // �����޸�
	}

	/**
	 * ��ȡi_item����ĳ���
	 *
	 * @param �޲���
	 * @return �����������͵ĳ��ȵ�ֵ
	 */
	public int getLength() {
		// TODO Auto-generated method stub
		return i_item.size();
	}

	/**
	 * �޲ι��캯����
	 *
	 * @param �޲���
	 */
	public Nlp() {
		super();
	}

	/**
	 * �вι��캯���������ı�����text�Ͱ�ͨ������õ�GetJSON��ʽΪi_item���鸳ֵ
	 *
	 * @param һ������:����t���մ��������ַ������ݣ�������ֵ��text
	 */
	public Nlp(String t) {
		super(t);
		// TODO Auto-generated constructor stub
		text = t;
		for (int i = 0; i < GetJSON().getJSONArray("items").length(); i++) {
			i_item.add(new Items(i));
		}
	}

}
