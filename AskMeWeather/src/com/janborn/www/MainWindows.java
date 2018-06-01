/**
* Team Zhinengxianfeng Hebei Normal University
* FileName: Main.java
* ���ڻ��������ڵ���
*
* @author Deng Yang
    * @Date    2018/5/23
* @version 1.00
*/

package com.janborn.www;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;  
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;  
import org.jb2011.lnf.beautyeye.ch8_toolbar.BEToolBarUI;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;  
import org.jb2011.lnf.beautyeye.ch8_toolbar.BEToolBarUI;  

public class MainWindows {
	/*��ȡ�ֱ���*/
	private final static Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
	private final static int width = (int)screensize.getWidth();
	private final static int height = (int)screensize.getHeight(); 

	
	public MainWindows() {
		// TODO Auto-generated constructor stub
	}

	//f���Ƿ�Ҫ��Ӱ�ť index:��ť���
	public static void createButton(String w,boolean f,int index) {
		WindowTool.dp.remove(WindowTool.inp);
		WindowTool.dp.repaint();
		WindowTool.frame.setVisible(true);

		JButton b = new JButton(w);
		b.setActionCommand(""+index);
		b.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
        b.setForeground(Color.WHITE);
        b.setFont(new Font("",1,16));

		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Demo.answer=e.getActionCommand();
				JButton tb =(JButton)e.getSource();
				Communicate.addUser(tb.getText());
				MainWindows.clearButton();
				WindowTool.dp.add(WindowTool.inp);
				WindowTool.dp.repaint();
				WindowTool.frame.setVisible(true);
			}
		});
		WindowTool.bp.add(b);
		if(!f) {
			WindowTool.frame.setVisible(true);
		}
		else {
			WindowTool.bp.add(new JLabel(" "));
			WindowTool.bp.repaint();
			WindowTool.frame.setVisible(true);
		}
	}
	
	public static void clearButton() {
		WindowTool.bp.removeAll();
		WindowTool.bp.repaint();
	}
	
	
	public static void paintGUI() {
		JFrame frame = new JFrame();
		//���ô�����Ϣ
		//1.������
		frame.setTitle("AskMeWeather");
		//���ڷ��
		frame.getRootPane().setWindowDecorationStyle(JRootPane.FILE_CHOOSER_DIALOG);
		//2.�رշ�ʽ
		frame.addWindowListener(new WindowAdapter() {
            public void windowClosing (WindowEvent e) {
                User u = new User(1);          
                //������л��ļ����ڵ�Ϊ�գ�ɾ��
                if(u.getUserFile().exists() && u.getUserFile().length()==0) {
                	u.getUserFile().delete();
                }
                System.exit(0);
            }
	    });
		//��Ӽ����¼�
		frame.addComponentListener(new Handler());
		//�������ð�ť
		UIManager.put("RootPane.setupButtonVisible", false);
		
        //3.��ʼλ�á����ڴ�С
		int winwidth = (int)(0.52*width);
		int winheight = (int)(0.92*height);
		frame.setBounds((width-winwidth)/2, (height-winheight)/2, winwidth, winheight);
		//���ô��ڴ�С��Сֵ
		Dimension dimen = new Dimension(700,700);
		frame.setMinimumSize(dimen);

        
        //��������
        Container c= frame.getContentPane();
        //���ò��֣��߽粼��
        c.setLayout(new BorderLayout());
        
        //���������
        JTextPane p1 = new JTextPane();
        p1.setSize(28*18,2);
        p1.setFont(new Font("",1,18));
        p1.setEditable(false);
        JScrollPane jsp = new JScrollPane(p1);
        jsp.setSize((int)(winwidth*0.6), (int)(winheight*0.6));
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setSize((int)(winwidth*0.9), (int)(winheight*0.9));
        jsp.setBackground(null);
        
        //2.�����
        JPanel p2 = new JPanel();
        //3.��ť���
        JPanel p3 = new JPanel();
        //4.�������
        JPanel p4 = new JPanel();
        
        //������������
        //�����
        JTextArea tf = new JTextArea();
        tf.setSize((int)(frame.getWidth()*0.75),(int) (0.093*height));
        tf.setFont(new Font("",1,16));
        
        //�Զ�����
        tf.setLineWrap(true);
        //��ť
        JButton button = new JButton("����");
        button.setSize((int) (0.093*height), (int) (0.093*height));
        button.setFont(new Font("",1,16));

        //���������
        p2.setSize((int)(0.42*width),(int) (0.093*height));
        //��������岼��
        p2.setLayout(new BoxLayout(p2,BoxLayout.Y_AXIS));
        
        //���ð�ť��岼��
        p3.setLayout(new BoxLayout(p3,BoxLayout.X_AXIS));
        
        //�������
        p4.add(tf);
        p4.add(button);
        p2.add(p3);
        p2.add(p4);
        
        
        //��Ӽ�����
        WindowTool t = new WindowTool(tf,p1,p2,p4,p3,frame,jsp.getVerticalScrollBar());
        Handler h=new Handler(t);
        //1.���Ͱ�ť������
        button.addActionListener(h);
        //2.���̼������������
        tf.addKeyListener(h);
       
        //����������������
        c.add(p2,"South");
        //����������������
        c.add(jsp,"Center");
       
        frame.setVisible(true);
	}
}
