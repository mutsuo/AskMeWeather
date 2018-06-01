/**
* Team Zhinengxianfeng Hebei Normal University
* FileName: Handler.java
* ���ڼ������̺�����¼���ActionListener,KeyListener�ӿ�ʵ����
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
	/*���ڷ��ʴ��ڹ�����Ľӿ�*/
	WindowTool tool=null;
	
	/**
	 * ������
	 */
	public Handler() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * ������</br>
	 * ���εĹ���������ʹ�����
	 */
	public Handler(WindowTool t) {
		// TODO Auto-generated constructor stub
		this.tool=t;
	}
	

	/**
	 * �������Ϣ��ӵ����촰��
	 */
	public void addWords() {
		Communicate.addUser();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * �������̰����¼���</br>
	 * - ����Ƿ�ͬʱ���¡�ALT+ENTER������������ϼ���������û�������ϣ����û�������Ϣ��ӵ����촰�ڡ�</br>
	 * - �����¡�ENTER�������������ʾ��
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
			WriteTimer.println("�س������������еģ�����뷢�͵Ļ����鷳����ALT+ENTER������");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * ����������¼���</br>
	 * ����갴�·��ͼ������ȡ�û�������Ϣ����ӵ����촰���С�
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Communicate.scanLine();
		addWords();
		e.getActionCommand();
	}
	

	/**
	 * �������ڳߴ�仯�¼���</br>
	 * �����ڳߴ�仯�������»������������Ӧ���ڡ�
	 */
	@Override
	public void componentResized(ComponentEvent e){
		WindowTool.frame.setVisible(true);
	    WindowTool.tf.setSize((int)(WindowTool.frame.getWidth()*0.75), WindowTool.tf.getHeight());
	    WindowTool.tf.repaint();
	    WindowTool.frame.setVisible(true);
	}

	/**
	 * ��ȡ��Ϣ��
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
