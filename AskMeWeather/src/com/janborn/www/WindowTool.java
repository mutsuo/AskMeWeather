/**
* Team Zhinengxianfeng Hebei Normal University
* FileName: WindowToll.java
* ���ڹ����ࡣ������´�����Ԫ�����ࡣ
*
* @author Deng Yang
    * @Date    2018/5/23
* @version 1.00
*/
package com.janborn.www;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.janborn.www.Weather.ErrorMessageException;

public class WindowTool {

	public static JTextArea tf=null;
	/*�����*/
	public static JTextPane p=null;
	/*�����*/
	public static JPanel dp=null;
	/*��ť���*/
	public static JPanel bp=null;
	/*�������*/
	public static JPanel inp=null;
	/*������*/
	public static JScrollBar jsb=null;
	/*���ڿ��*/
	public static JFrame frame=null;
	
	public WindowTool() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * ���ô��ڹ�����
	 * 
	 * @param JTextArea tf �������
	 * @param JTextPane p1 �����
	 * @param JPanel p2 �����
	 * @param JPanel p4 �����
	 * @param JPanel p3 ��ť���
	 * @param JFrame f ���
	 * @param JScrollBar jsb ������
	 * */
	public WindowTool(JTextArea ttf,JTextPane p1,JPanel p2,JPanel p4,JPanel p3,JFrame f,JScrollBar tjsb) {
		tf=ttf;
		p=p1;
		frame=f;
		bp=p3;
		inp=p4;
		dp=p2;
		jsb=tjsb;
	}
	

}
