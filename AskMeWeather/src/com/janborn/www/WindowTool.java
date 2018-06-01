/**
* Team Zhinengxianfeng Hebei Normal University
* FileName: WindowToll.java
* 窗口工具类。方便更新窗口内元件的类。
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
	/*上面板*/
	public static JTextPane p=null;
	/*下面板*/
	public static JPanel dp=null;
	/*按钮面板*/
	public static JPanel bp=null;
	/*输入面板*/
	public static JPanel inp=null;
	/*滚动条*/
	public static JScrollBar jsb=null;
	/*窗口框架*/
	public static JFrame frame=null;
	
	public WindowTool() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 设置窗口工具类
	 * 
	 * @param JTextArea tf 聊天面板
	 * @param JTextPane p1 上面板
	 * @param JPanel p2 下面板
	 * @param JPanel p4 出入框
	 * @param JPanel p3 按钮面板
	 * @param JFrame f 框架
	 * @param JScrollBar jsb 滚动条
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
