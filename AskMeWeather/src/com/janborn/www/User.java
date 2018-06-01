/**
* Team Zhinengxianfeng Hebei Normal University
* FileName: User.java
* 记录用户信息
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
	/*用户名*/
	private String name=null;
	/*用户IP*/
	private String ip=null;
	/*序列化文件保存路径*/
	private final String root = "userData/user.txt";
	/*序列化文件*/
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

	//判断该用户是否存在
	public boolean userIsExists() throws IOException {
        if(name=="")
        	return false;
        else
        	return true;
	}
	
	//反序列化
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
	
	//序列化
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
	
	//构造函数
	public User() throws IOException {
		// TODO Auto-generated constructor stub
		//反序列化用户文件
		if(!userFile.exists()) {
			userFile.createNewFile();
			name="";
			setIp();
		}
		else {
			User u = deserializeUser();
			//如果文件不是空的
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
			address = InetAddress.getLocalHost();//获取的是本地的IP地址 
			ip = address.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
