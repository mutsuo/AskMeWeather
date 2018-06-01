/**
* Team Zhinengxianfeng Hebei Normal University
* FileName: Handler.java
* 用于监听键盘和鼠标事件的ActionListener,KeyListener接口实现类
*
* @author Deng Yang
    * @Date    2018/5/23
* @version 1.00
*/

package com.janborn.www;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Handler implements ActionListener, KeyListener, ComponentListener {
	/*用于访问窗口工具类的接口*/
	WindowTool tool=null;
	
	/**
	 * 构造器
	 */
	public Handler() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 构造器</br>
	 * 带参的构造器，请使用这个
	 */
	public Handler(WindowTool t) {
		// TODO Auto-generated constructor stub
		this.tool=t;
	}
	

	/**
	 * 将相关信息添加到聊天窗口
	 */
	public void addWords() {
		Communicate.addUser();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 监听键盘按下事件。</br>
	 * - 检查是否同时按下“ALT+ENTER”，若按下组合键，则表明用户输入完毕，将用户输入信息添加到聊天窗口。</br>
	 * - 若按下“ENTER”键，则给出提示。
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_ENTER
				&&e.isAltDown()) {
			Communicate.scanLine();
			addWords();
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER
				&& !e.isAltDown() && !e.isControlDown()) {
			WriteTimer.println("回车键是用来换行的，如果想发送的话，麻烦按“ALT+ENTER”啦。");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 监听鼠标点击事件。</br>
	 * 当鼠标按下发送键，则读取用户输入信息，添加到聊天窗口中。
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Communicate.scanLine();
		addWords();
		e.getActionCommand();
	}
	

	/**
	 * 监听窗口尺寸变化事件。</br>
	 * 若窗口尺寸变化，则重新绘制输入框以适应窗口。
	 */
	@Override
	public void componentResized(ComponentEvent e){
		WindowTool.frame.setVisible(true);
	    WindowTool.tf.setSize((int)(WindowTool.frame.getWidth()*0.75), WindowTool.tf.getHeight());
	    WindowTool.tf.repaint();
	    WindowTool.frame.setVisible(true);
	}

	/**
	 * 获取信息。
	 */
	public String getWords(String w) {
		return w;
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

}
