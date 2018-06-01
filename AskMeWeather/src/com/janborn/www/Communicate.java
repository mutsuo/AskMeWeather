/**
* Team Zhinengxianfeng Hebei Normal University
* FileName: Communicate.java
* �û��ͳ�����н������ࡣ��ȡ�û���������Ϣ��ѡ����Ϣ�������¼����ʾ�����������
*
* @author Deng Yang
    * @Date    2018/4/17
* @version 1.00
*/
package com.janborn.www;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class Communicate {

	
	public Communicate() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * ��ӻ��������</br>
	 * ��������Ҫ˵�Ļ���ӵ����������
	 * 
	 * @param String w ������˵�Ļ�
	 * @return none
	 */
	public static void addRobot(String w) {
		SimpleDateFormat t = new SimpleDateFormat("HH:mm:ss");
		
		 SimpleAttributeSet set = new SimpleAttributeSet();
		 StyleConstants.setForeground(set, new Color(102,0,0));//����������ɫ
		 StyleConstants.setFontSize(set, 18);//���������С
		 Document doc = WindowTool.p.getStyledDocument();
		 try
		 {
		  doc.insertString(doc.getLength(), "\n"+"[ROBOT]\t"+t.format(new Date())
			+"\n    "+w, set);//��������
		 }
		 catch (BadLocationException e)
		 {
		 }
		
		updateScrollBar();
		WindowTool.frame.setVisible(true);
	}

	/**
	 * ����û�ѡ��</br>
	 * ���û�˵��ѡ����Ϊ������ӵ����������
	 * 
	 * @param String w �û���ѡ��
	 * @return none
	 */
	public static void addUser(String w) {
		SimpleDateFormat t = new SimpleDateFormat("HH:mm:ss");
		
		SimpleAttributeSet set = new SimpleAttributeSet();
		 StyleConstants.setForeground(set, new Color(0,0,0));//����������ɫ
		 StyleConstants.setFontSize(set, 18);//���������С
		 Document doc = WindowTool.p.getStyledDocument();
		 try
		 {
		  doc.insertString(doc.getLength(), "\n"+"[ME]\t"+t.format(new Date())
			+"\n    "+w, set);//��������
		 }
		 catch (BadLocationException e)
		 {
		 }
		
		WindowTool.tf.setText(null);
		updateScrollBar();
		WindowTool.frame.setVisible(true);
	}
	
	
	/**
	 * ����û����</br>
	 * ���û�����Ļ���ӵ����������
	 * 
	 * @param String w �û�˵�Ļ�
	 * @return none
	 */
	public static void addUser() {
		SimpleDateFormat t = new SimpleDateFormat("HH:mm:ss");
		
		SimpleAttributeSet set = new SimpleAttributeSet();
		 StyleConstants.setForeground(set, new Color(0,0,0));//����������ɫ
		 StyleConstants.setFontSize(set, 18);//���������С
		 Document doc = WindowTool.p.getStyledDocument();
		 try
		 {
		  doc.insertString(doc.getLength(), "\n"+"[ME]\t"+t.format(new Date())
			+"\n    "+WindowTool.tf.getText(), set);//��������
		 }
		 catch (BadLocationException e)
		 {
		 }
		
		WindowTool.tf.setText(null);
		updateScrollBar();
		WindowTool.frame.setVisible(true);
	}
	
	/**
	 * ��������������׶�</br>
	 */
	public static void updateScrollBar() {
		WindowTool.frame.setVisible(true);
		if (WindowTool.jsb != null) {
			WindowTool.jsb.setValue(WindowTool.jsb.getMaximum());
			WindowTool.jsb.repaint();
		}
	}
	public static void scanLine() {
		Demo.userText=WindowTool.tf.getText();
	}

}
