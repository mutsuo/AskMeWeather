/**
* Team Zhinengxianfeng Hebei Normal University
* FileName: Main.java
* 用于绘制主窗口的类
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
	/*获取分辨率*/
	private final static Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
	private final static int width = (int)screensize.getWidth();
	private final static int height = (int)screensize.getHeight(); 

	
	public MainWindows() {
		// TODO Auto-generated constructor stub
	}

	//f：是否还要添加按钮 index:按钮编号
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
		//设置窗口信息
		//1.窗口名
		frame.setTitle("AskMeWeather");
		//窗口风格
		frame.getRootPane().setWindowDecorationStyle(JRootPane.FILE_CHOOSER_DIALOG);
		//2.关闭方式
		frame.addWindowListener(new WindowAdapter() {
            public void windowClosing (WindowEvent e) {
                User u = new User(1);          
                //如果序列化文件存在但为空，删除
                if(u.getUserFile().exists() && u.getUserFile().length()==0) {
                	u.getUserFile().delete();
                }
                System.exit(0);
            }
	    });
		//添加监听事件
		frame.addComponentListener(new Handler());
		//隐藏设置按钮
		UIManager.put("RootPane.setupButtonVisible", false);
		
        //3.初始位置、窗口大小
		int winwidth = (int)(0.52*width);
		int winheight = (int)(0.92*height);
		frame.setBounds((width-winwidth)/2, (height-winheight)/2, winwidth, winheight);
		//设置窗口大小最小值
		Dimension dimen = new Dimension(700,700);
		frame.setMinimumSize(dimen);

        
        //定义容器
        Container c= frame.getContentPane();
        //设置布局：边界布局
        c.setLayout(new BorderLayout());
        
        //设置上面板
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
        
        //2.下面板
        JPanel p2 = new JPanel();
        //3.按钮面板
        JPanel p3 = new JPanel();
        //4.输入面板
        JPanel p4 = new JPanel();
        
        //定义下面板零件
        //输入框
        JTextArea tf = new JTextArea();
        tf.setSize((int)(frame.getWidth()*0.75),(int) (0.093*height));
        tf.setFont(new Font("",1,16));
        
        //自动换行
        tf.setLineWrap(true);
        //按钮
        JButton button = new JButton("发送");
        button.setSize((int) (0.093*height), (int) (0.093*height));
        button.setFont(new Font("",1,16));

        //设置下面板
        p2.setSize((int)(0.42*width),(int) (0.093*height));
        //设置下面板布局
        p2.setLayout(new BoxLayout(p2,BoxLayout.Y_AXIS));
        
        //设置按钮面板布局
        p3.setLayout(new BoxLayout(p3,BoxLayout.X_AXIS));
        
        //加入零件
        p4.add(tf);
        p4.add(button);
        p2.add(p3);
        p2.add(p4);
        
        
        //添加监听器
        WindowTool t = new WindowTool(tf,p1,p2,p4,p3,frame,jsp.getVerticalScrollBar());
        Handler h=new Handler(t);
        //1.发送按钮监听器
        button.addActionListener(h);
        //2.键盘监听器（输入框）
        tf.addKeyListener(h);
       
        //将上下面板加入容器
        c.add(p2,"South");
        //将滚动条加入容器
        c.add(jsp,"Center");
       
        frame.setVisible(true);
	}
}
