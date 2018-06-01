/**
* Team Zhinengxianfeng Hebei Normal University
* FileName: User.java
* ��¼�û���Ϣ
*
* @author Deng Yang
    * @Date    2018-05-04
* @version 0.00
*/

package com.janborn.www;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class User implements Serializable{
	/*�û���*/
	private String name=null;
	/*�û�IP*/
	private String ip=null;
	/*���л��ļ�����·��*/
	private final String root = "userData/user.txt";
	/*���л��ļ�*/
	private final File userFile = new File(root);
	

	public File getUserFile() {
		return userFile;
	}
	
	public String getRoot() {
		return root;
	}

	@Override
	public String toString() {
		System.out.println( "User [name=" + name + ", ip=" + ip + "]");
		return "User [name=" + name + ", ip=" + ip + "]";
	}

	//�жϸ��û��Ƿ����
	public boolean userIsExists() throws IOException {
        if(name=="")
        	return false;
        else
        	return true;
	}
	
	//�����л�
	public User deserializeUser() throws IOException{
		User u = new User(1) ;
		ObjectInputStream ois;
		if(userFile.exists()) {
			try {
				ois = new ObjectInputStream(new FileInputStream(userFile));
				u = (User) ois.readObject();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				u.name="";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				u.name="";
			}  
	        catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				u.name="";
			} 
		}
		
//		u.toString();
        return u;
	}
	
	//���л�
	public boolean serializeUser(User u) {
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(userFile));
			oos.writeObject(u);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		} 
        catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
//		u.toString();
        return true;
	}
	
	//���캯��
	public User() throws IOException {
		// TODO Auto-generated constructor stub
		//�����л��û��ļ�
		if(!userFile.exists()) {
			userFile.createNewFile();
			name="";
			setIp();
		}
		else {
			User u = deserializeUser();
			//����ļ����ǿյ�
			if(u.name!="") {
				name=u.name;
				ip=u.ip;
			}
			else {
				name="";
				setIp();	
			}
		}
	}
	public User(int i) {
		ip="";
		name="";
	}
	public boolean deleteData() {
		if(userFile.delete())
			return true;
		else return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp() {
		InetAddress address;
		try {
			address = InetAddress.getLocalHost();//��ȡ���Ǳ��ص�IP��ַ 
			ip = address.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
