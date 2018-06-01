/**
* Team Zhinengxianfeng Hebei Normal University
* FileName: Communicate.java
* 用户和程序进行交互的类。获取用户的输入信息或选项信息，将其记录并显示在聊天面板上
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
	 * 添加机器人语句</br>
	 * 将机器人要说的话添加到聊天界面中
	 * 
	 * @param String w 机器人说的话
	 * @return none
	 */
	public static void addRobot(String w) {
		SimpleDateFormat t = new SimpleDateFormat("HH:mm:ss");
		
		 SimpleAttributeSet set = new SimpleAttributeSet();
		 StyleConstants.setForeground(set, new Color(102,0,0));//设置文字颜色
		 StyleConstants.setFontSize(set, 18);//设置字体大小
		 Document doc = WindowTool.p.getStyledDocument();
		 try
		 {
		  doc.insertString(doc.getLength(), "\n"+"[ROBOT]\t"+t.format(new Date())
			+"\n    "+w, set);//插入文字
		 }
		 catch (BadLocationException e)
		 {
		 }
		
		updateScrollBar();
		WindowTool.frame.setVisible(true);
	}

	/**
	 * 添加用户选项</br>
	 * 将用户说的选择作为句子添加到聊天界面中
	 * 
	 * @param String w 用户的选择
	 * @return none
	 */
	public static void addUser(String w) {
		SimpleDateFormat t = new SimpleDateFormat("HH:mm:ss");
		
		SimpleAttributeSet set = new SimpleAttributeSet();
		 StyleConstants.setForeground(set, new Color(0,0,0));//设置文字颜色
		 StyleConstants.setFontSize(set, 18);//设置字体大小
		 Document doc = WindowTool.p.getStyledDocument();
		 try
		 {
		  doc.insertString(doc.getLength(), "\n"+"[ME]\t"+t.format(new Date())
			+"\n    "+w, set);//插入文字
		 }
		 catch (BadLocationException e)
		 {
		 }
		
		WindowTool.tf.setText(null);
		updateScrollBar();
		WindowTool.frame.setVisible(true);
	}
	
	
	/**
	 * 添加用户语句</br>
	 * 将用户输入的话添加到聊天界面中
	 * 
	 * @param String w 用户说的话
	 * @return none
	 */
	public static void addUser() {
		SimpleDateFormat t = new SimpleDateFormat("HH:mm:ss");
		
		SimpleAttributeSet set = new SimpleAttributeSet();
		 StyleConstants.setForeground(set, new Color(0,0,0));//设置文字颜色
		 StyleConstants.setFontSize(set, 18);//设置字体大小
		 Document doc = WindowTool.p.getStyledDocument();
		 try
		 {
		  doc.insertString(doc.getLength(), "\n"+"[ME]\t"+t.format(new Date())
			+"\n    "+WindowTool.tf.getText(), set);//插入文字
		 }
		 catch (BadLocationException e)
		 {
		 }
		
		WindowTool.tf.setText(null);
		updateScrollBar();
		WindowTool.frame.setVisible(true);
	}
	
	/**
	 * 将滚动条滑到最底端</br>
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
