/**
* Team Zhinengxianfeng Hebei Normal University
* FileName: Demo.java
* �����ࡣ
*
* @author Deng Yang
    * @Date    2018/4/17
* @version 1.00
*/

package com.janborn.www;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.janborn.www.Nlp.aipNoConnectionException;
import com.janborn.www.Weather.DailyContent;
import com.janborn.www.Weather.ErrorMessageException;


public class Demo {

	/*�ص�*/
	private String location=null;
	/*����*/
	//��ʱȱʡ
	private String language=null;
	/*���µ�λ*/
	//��ʱȱʡ
	private String unit=null;
	/*��������������ѯ��ʱ��*/
	private boolean dailyLife[] = new boolean[4];
	//��ʱȱʡ
	/*���ڴ洢����ָ�����������*/
	private boolean lifeSuggestion[]=new boolean[6];
	/*���ڴ洢������ϸ��Ϣ���������*/
	private boolean lifeDetil[]=new boolean[4];
	
	/*���ڼ�¼������Ϣ*/
	public static String userText=null;
	public static String answer;
	
	/*��¼�Ƿ��Ѿ����꣬�Ծ���������*/
	public boolean wheatherRain = false;
	
	/*�������ǣ����ڸ��ƻش�*/
	public boolean netanswer = false;
	
	/*�û�����*/
	User user=null;
	
	
	/**
	 * �����������ڳ�ʼ���û�
	 * 
	 * @param none
	 * @return none
	 * @throws IOException 
	 */
	public Demo() {
		// TODO Auto-generated constructor stub
		initUser();
	}
	
	/**
	 * �û���ʼ��ģ��
	 * 
	 * @param none
	 * @return none
	 * @throws IOException 
	 */
	public void initUser() {
		try {
			user= new User();
		} catch (IOException e) {
			WriteTimer.println("��־��û�ҵ��û��ļ���");
		}
	}
	
	/**
	 * Ӧ�ó�ʼ��ģ��</br>
	 * 1.���к�</br>
	 * 2.ѯ������</br>
	 * 3.�ж����������Ƿ���������֤�����������ٷ��ء�
	 * 
	 * @param User user �û���Ϣ
	 * @return boolean
	 * @throws IOException 
	 */
	public boolean initMain(User user) {
		//���к���ѯ������
		greeting(user);
		//�������״��
		WriteTimer.println("�Եȣ��Ұ�����һ�����簡����");
		netanswer = false;
		if(checkNet())
			return true;
		else return false;
	}
	
	/**
	 * �������
	 * <ul>
	 * <li>��������磬����true</li>
	 * <li>����ѯ���û��Ƿ�Ҫ������ѯ�������������ô��������������ʱ�Զ����ѣ�����ֱ���˳�����</li>
	 * 
	 * @param none
	 * @return boolean
	 * @throws IOException 
	 */
	public boolean checkNet() {
		/*�Ƿ��һ�μ��*/
		boolean f = true;
		/*�Ƿ����������ϵ�*/
		boolean reCon = false;
		
		while(!isConnect("https://www.seniverse.com/") 
				&& !isConnect("https://login.bce.baidu.com/")) {
			if(f==true) {
				f=false;
				WriteTimer.println("����������������������ˣ�Ҳ����Ӧ�ø��ҿ���ɡ���");
				WriteTimer.println("����������ģ��Ҹû�ȥ����������Ҫ��ʱ���ٽ��ҡ�");
				
				MainWindows.createButton("�á�", true, 1);
				MainWindows.createButton("�����ˣ��������㹤���ˡ�", false, 2);
				
				waitForIndex();
				
				//��չ��û������ʱ�Ĳ���
				if(answer.equals("1")) {
					WriteTimer.println("�ţ��������������ʱ���һ��������ġ�");
					WriteTimer.println("һ���������");
					reCon = true;
					continue;
					//��֮������յ����²�ѯ�ĺ������ټ�����磬�ڴ�֮ǰ�ȴ�
				}
				else System.exit(0);
			}
			
		}
		if (isConnect("https://www.seniverse.com/") 
				&& isConnect("https://login.bce.baidu.com/")
				&& !netanswer) {
			WriteTimer.println("�ܺã�����״��ͨ��");
			if(reCon) WriteTimer.println("�٣����࣡��·���������ˣ��㻹����");
			netanswer=true;
		}
		return true;
	}
	
	/**
	 * �����������
	 * <p>ʹ��IO������ҳ���ӡ�����ܴ�˵��������������������true������׳��쳣������������ʧ�ܣ�����false</p>
	 * 
	 * @param String net Ҫ������ʵ���ַ
	 * @return boolean
	 */
	public boolean isConnect(String net){  
		boolean connect=false;
		URL url = null;  
        try {  
            url = new URL(net);  
            try {  
                InputStream in = url.openStream();  
                in.close();  
                connect=true;
            } catch (IOException e) {  
            	connect=false;
            }  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        }  
        return connect;
    }
	
	/**
	 * ���û����к�
	 * <ol>
	 * <li>��������һ��ʱ���ʺ�</li>
	 * <li>�ʺá����ҽ���</li>
	 * <li>�Ǽ��û���Ϣ</li>
	 * </ol>
	 * <p>�ռ��û���¼��Ϣ�����Ŀ���Ǽ�¼�û���ip��ַ��������ʱ���ϵ���������Ŀǰ�Ѿ������ˡ�</p>
	 * <p>���⣬�û���Ϣ�����л������<i>userData</i>�û�Ŀ¼��</p>
	 * 
	 * @param user �û�����
	 * @return none
	 * @throws IOException 
	 */
	public void greeting(User user) {
		//1.ʱ���ʺ���
		//��ȡʱ�����ж����������ϡ����绹������
		Calendar now = Calendar.getInstance();
		int time=now.get(Calendar.HOUR_OF_DAY);
		//�峿��05��01-06��59 
		//���ϣ�07��01-08��59 
		//���磺09��00-12��00 
		//���磺12��01-13��59 
		//���磺14��00-17��59 
		//����18��00-18��59 
		//���ϣ�19��00-23��59 
		//�賿��24��00-05��00
		if(time>=5 && time<7)
			WriteTimer.println("�������峿������ȫ��һ�졣");
		if(time>=7 && time<9)
			WriteTimer.println("���Ϻã�");
		if(time>=9 && time<12)
			WriteTimer.println("����ã�");
		if(time>=12 && time<14)
			WriteTimer.println("����ã�");
		if(time>=14 && time<18)
			WriteTimer.println("����ã�");
		if(time>=18 && time<19)
			WriteTimer.println("�����ǰ���ʱ�֣��������Ȱɣ�");
		if(time>=19 && time<=21)
			WriteTimer.println("���Ϻã�");
		if(time>21 && time<=23 || time==0)
			WriteTimer.println("�ֵ���˵����ʱ�䣡");
		if(time>0 && time<5)
			WriteTimer.println("ʱ�仹�磬����̯��һ���飬ϸƷһ������Դ��ճ��ɣ�");
		
		//2.���к�
		WriteTimer.println("���ѽ�����ǻ�����С�ǣ������ƶ˹۲�����");
		WriteTimer.println("Ŷ�����������Ұ�����");
		WriteTimer.println("�Ǳ��Ǹ��ں�����С��һ��������ɣ�");
		//3.ѯ������
		//�ж��Ƿ����¿ͻ�
		//���������¼��
		if(user.getUserFile().length()!=0) {
			WriteTimer.println("���ǻ����ǵ�һ�����졭��");
			WriteTimer.println("�Ҽǵ����"+user.getName()+"����");
			
			//�ռ��û��ش�
			//������ť
			MainWindows.createButton("�ǵ�", true, 1);
			MainWindows.createButton("�Ҳ����������", false, 2);
			//�˴����Ը�Ϊ�û�����
			waitForIndex();
			
			if(answer.equals("1"))
				WriteTimer.println("�ٺ٣����ѽ�����࣡");
			else {
				WriteTimer.println("����ְ������ҵ�оƬ��������");
				WriteTimer.println("�����°����ָ�����һ����");
				WriteTimer.println("�ȵȣ��������������ֵĻ�����ȥ�ļ�¼�����Ҿ������������Ŷ��");
				WriteTimer.println("�ҿɲ����³���������ġ�");
				
				MainWindows.createButton("��", true, 1);
				MainWindows.createButton("�ٿ���һ��", false, 2);
				waitForIndex();
				
				System.out.println(answer);
				if(answer.equals("1")) {
					if(user.deleteData()) {
						WriteTimer.println("��־����ȥ��¼��ɾ����");
						initUser();
						WriteTimer.println("�ã���������԰������ָ������ˡ�");
						userText=null;
						while(userText==null || userText.length()==0) {
							waitForLine();
							if(userText==null || userText.length()==0)
								WriteTimer.println("�㲻��ʲô�������룡�������У�");
						}
						user.setName(userText);
						WriteTimer.println("Ŷ!"+user.getName()+"���ѽ��");
						//���л�
						if(user.serializeUser(user))
							WriteTimer.println("������ֺܺüǣ����Ѿ���ס��Ŷ��");
						else
							WriteTimer.println("��¼������оƬ�������û���¼��ʧ��");
					}
					else
						WriteTimer.println("��־��ɾ��ʧ�ܡ�");
				}	
			}
		}
		else {
			WriteTimer.println("���ʲô��������֪����һ�������޲��ء���");
			WriteTimer.println("��������������룺������������ֱ�Ӱ����ָ����ң��ҿɲ�֪�������������ֵķ���");
			
			userText=null;
			waitForLine();
			while(userText==null || userText.length()==0) {
				WriteTimer.println("������ֲ���Ϊ�գ�");
				waitForLine();
			}
			user.setName(userText);
			
			
			//4.������Ӧ
			WriteTimer.println("Ŷ!"+user.getName()+"���ѽ��");
			//���л�
			if(user.serializeUser(user))
				WriteTimer.println("������ֺܺüǣ����Ѿ���ס��Ŷ��");
			else
				WriteTimer.println("��¼������оƬ�������û���¼��ʧ��");
		}
	}
	
	/**
	 * �ȴ������û�������Ϣ
	 * <p>���ڶ�����������û���������Ϣ��ֱ�������û���������Ϣ���ߴ����¼��Ż��˳��÷�����</p>
	 * 
	 * @param none
	 * @return none
	 */
	public void waitForLine() {
		WindowTool.tf.requestFocus(true);
		userText=null;
		while(userText==null) {System.out.println(userText);continue;}
	}
	
	/**
	 * �ȴ������û���ť��Ϣ
	 * <p>���ڶ�ȡ���µİ�ť��Ϣ��ֱ�������û��İ�ť��Ϣ�Ż��˳��÷�����</p>
	 * 
	 * @param none
	 * @return none
	 */
	public void waitForIndex() {
		answer=null;
		while(answer==null) {System.out.println(answer);continue;}
	}
	
	/**
	 * �ж�Ŀ��λ���Ƿ�֧�ֲ�ѯ
	 * <p>���ڼ��Ŀ��λ���Ƿ�֧�ֲ�ѯ��Ŀ��λ��ͨ���������롣</p>
	 * <p>ͨ���ڹٷ��ṩ�ĵص�Excel���м���ƥ�䣬���ж�Ŀ��ص��Ƿ�֧�֡�</p>
	 * 
	 * @param String location Ŀ��ص�
	 * @return int (1,2,-1)
	 * <ul>
	 * 	<li>����ֵ<i>1</i>��ʾ�õص�֧�֣���ԭʼ�ִ��ԡ�ʡ���ֽ�β</li>
	 * 	<li>����ֵ<i>2</i>��ʾ�õص�֧�֣�������ԭʼ�ִ�������ˡ�ʡ���֡�</li>
	 * 	<li>����ֵ<i>-1</i>��ʾ�õص㲻֧��</li>
	 * </ul>
	 */
	public int judgeLocation(String location)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		String path = System.getProperty("user.dir")+"/resrc/ChineseCities.xlsx";
		File file = new File(path);
		InputStream inputStream = new FileInputStream(file);
		Workbook workbook = null;
		workbook = WorkbookFactory.create(inputStream);
		inputStream.close();
		// ���������
		Sheet sheet = workbook.getSheetAt(0);
		// ������
		int rowLength = sheet.getLastRowNum() + 1;
		// ���������
		Row row = sheet.getRow(0);
		// ������
		int colLength = row.getLastCellNum();
		// �õ�ָ���ĵ�Ԫ��
		Cell cell = row.getCell(0);
		for (int i = 0; i < colLength; i++) {
			//��Ҫ��һ�У���Ч��
			for (int j = 1; j < rowLength; j++) {
				row = sheet.getRow(j);
				cell = row.getCell(i);
				// �õ���Ԫ�������
				String s = cell.getStringCellValue();
				if (s=="") {
					continue;
				}
				if (location.indexOf(s) != -1) {
					if(i==colLength-1) {
						//���ص�ת��Ϊʡ+ʡ��
						// ���������
						Sheet sheet1 = workbook.getSheetAt(1);
						// ������
						int rowLength1 = sheet1.getLastRowNum() + 1;
						for (int k = 1; k < rowLength1; k++) {
							Row row1 = sheet1.getRow(k);
							Cell cell1 = row1.getCell(0);
							String loc=cell1.getStringCellValue();
							if (loc.equals(s)) {
								if(this.location.charAt(this.location.length()-1)=='ʡ')
									this.location=this.location.substring(0,this.location.length()-1)
													+row1.getCell(1).getStringCellValue();
								else
									this.location=this.location+row1.getCell(1).getStringCellValue();
								return 2;
							}
						}
					}
					return 1;
				}
			}
		}
		return -1;
	}

	
	/**
	 * ������ȡ��ѯ������ʵ��������
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 * @throws EncryptedDocumentException 
	 * */
	public void analyzeNowWeather() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		
		//1.ѯ�ʵص�
		WriteTimer.println("���ˣ���Ӧ�ð�����ĵ�������");
		WriteTimer.println("��������������룺������ʯ��ׯ������������ֱ�����롰ʯ��ׯ����֮��֮��ġ�");
		
		while(true) {
			//2.�����û�����
			userText=null;
			while(userText==null || userText.length()==0) {
				waitForLine();
				if(userText==null || userText.length()==0)
					WriteTimer.println("�㲻��ʲô�������룡�������У�");
			}
			//3.ʵ����Nlp���󣬷����ص�
			Nlp locationNlp=null;
			location = null;
			netanswer = false;
			while(!(locationNlp!=null && location!=null)) {
				try {
					/*��ʱ�����̣߳����ȴ�ʱ�������������ʾ*/
					WaitMessageThread.startThread();
					locationNlp = new Nlp(userText);			
					//4.���õص�
					location = locationNlp.getLocaction();
					WaitMessageThread.stopThread();
				} catch (aipNoConnectionException e) {
					WaitMessageThread.stopThread();
					checkNet();
				}
			}
			
			//��չ�������Ӵ����ж�һ���Ƿ��������ص㡣
			//5.ͨ����ѯExcel����ж��Ƿ���֧�ֵĵص�
			int f = judgeLocation(location);
			if(f==1 || f==2) {
				//���֧��
				if(f==1) {
					WriteTimer.println(location+"������֪����һ����������ĵط���");
					WriteTimer.println("����ţ�����͸�����"+location+"�����������");
					break;
				}
				else if(f==2) {
					WriteTimer.println("�������尡��Ҫ����ֱ�Ӱ����ʡ��"+location+"�İɣ�");
					//��ѡ�ť
					MainWindows.createButton("��ʡ���", true, 1);
					MainWindows.createButton("�����ط���", false, 2);
					
					waitForIndex();
					if(answer.equals("1"))
						WriteTimer.println("����ţ�����͸����ʡ��"+location+"�����������");
					else {
						WriteTimer.println("����ʲô�ط���");
						continue;
					}
				}
				break;
			}
			else {
				WriteTimer.println("��־���ص㲻֧�֡�");
				WriteTimer.println("�ޡ������˷ǳ���Ǹ�����⡣");
				WriteTimer.println("Ҫ��Ҫ��һ���ط���");
				
				//��ѡ�ť
				MainWindows.createButton("��һ��", true, 1);
				MainWindows.createButton("���˰�", false, 2);
				
				waitForIndex();
				
				if(answer.equals("1")) {
					WriteTimer.println("Ҫ����ʲô�ط���");
					continue;
				}
				else break;
			}
		}
		
		
	}
	
	/**
	 * ������ȡ��ѯ����������������
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 * @throws EncryptedDocumentException 
	 * */
	public void analyzeDailyWeather() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {	
		//1.ѯ�ʵص�
		WriteTimer.println("���ˣ��㻹û������Ҫ��ѯ��λ�ã�");
		WriteTimer.println("��������������룺������ʯ��ׯ������������ֱ�����롰ʯ��ׯ����֮��֮��ġ�");

		while(true) {
			//2.�����û�����
			userText=null;
			while(userText==null || userText.length()==0) {
				waitForLine();
				if(userText==null || userText.length()==0)
					WriteTimer.println("�㲻��ʲô�������룡�������У�");
			}
			
			//3.ʵ����Nlp���󣬷����ص�
			Nlp locationNlp=null;
			netanswer = false;
			while(!(locationNlp!=null && location!=null)) {
				try {
					/*��ʱ�����̣߳����ʱ�������������ʾ*/
					WaitMessageThread.startThread();
					locationNlp = new Nlp(userText);				
					//4.���õص�
					location = locationNlp.getLocaction();
					WaitMessageThread.stopThread();
				} catch (aipNoConnectionException e) {
					// TODO Auto-generated catch block
	//				e.printStackTrace();
					WaitMessageThread.stopThread();
					checkNet();
				}
			}
			
			//�����Ƿ���֧�ֵĵص�
			int f = judgeLocation(location);
			if(f==1 || f==2) {
				//���֧��
				if(f==1) {
					WriteTimer.println(location+"������֪����һ����������ĵط���");
					WriteTimer.println("����ţ�����͸�����"+location+"�����������");
					break;
				}
				else if(f==2) {
					WriteTimer.println("�������尡��Ҫ����ֱ�Ӱ����ʡ��"+location+"�İɣ�");
					//��ѡ�ť
					MainWindows.createButton("��ʡ���", true, 1);
					MainWindows.createButton("�����ط���", false, 2);
					
					waitForIndex();
					if(answer.equals("1")) {
						WriteTimer.println("����ţ�����͸����ʡ��"+location+"�����������");
						break;
					}
					else {
						WriteTimer.println("����ʲô�ط���");
						continue;
					}
				}
			}
			else {
				WriteTimer.println("��־���ص㲻֧�֡�");
				WriteTimer.println("�ޡ������˷ǳ���Ǹ�����⡣");
				WriteTimer.println("Ҫ��Ҫ��һ���ط���");
				
				//��ѡ�ť
				MainWindows.createButton("��һ��", true, 1);
				MainWindows.createButton("���˰�", false, 2);
				
				waitForIndex();
				
				if(answer.equals("1")) {
					WriteTimer.println("Ҫ����ʲô�ط���");
					continue;
				}
				else return;
			}
		}
		
		//5.ѯ��Ҫ��ѯ������
		WriteTimer.println("�ٺٺ١�");
		WriteTimer.println("���˸����㣬ƾ���ҵ�������ֻ�ܰ�����������������");
		WriteTimer.println("���죬���죬���죬ѡһ��ɣ�");
		
		//6.��������
		int times = 0;
		while(true) {
			times++;
			userText=null;
			while(userText==null || userText.length()==0) {
				waitForLine();
				if(userText==null || userText.length()==0)
					WriteTimer.println("�㲻��ʲô�������룡�������У�");
			}
			
			//7.����
			Nlp day=null;
			netanswer = false;
			while(day==null) {
				try {
					/*��ʱ�����̣߳���ִ��ʱ�������������ʾ*/
					WaitMessageThread.startThread();
					day = new Nlp(userText);
					WaitMessageThread.stopThread();
				} catch (aipNoConnectionException e) {
					// TODO Auto-generated catch block
	//				e.printStackTrace();
					WaitMessageThread.stopThread();
					checkNet();
				}
			}
			
			for(int i=0;i<day.getLength();i++)
			{
				switch (day.getI_ite(i).getItem()) {
				case "����":
					dailyLife[0]=true;
					break;
				case "����":
					dailyLife[1]=true;
					break;
				case "����":
					dailyLife[2]=true;	
					break;
				default:
					break;
				}
			}
			int i=0;
			while(i<3){
				if(dailyLife[i])
					break;
				i++;
			}
			if(i==3) {
				if(times>2) {
					WriteTimer.println("�ҡ���");
					WriteTimer.println("�������ò��ã�ֻ�ܴ��Ҹղ�˵���Ǽ�������ѡһ����");
				}
				else if(times>5){
					WriteTimer.println("�ҡ����Ҷ��������");
					WriteTimer.println("����˵һ�飬ֻ�ܴ��Ҹղ�˵���Ǽ�������ѡһ����");
				}
				else if(times>8) {
					WriteTimer.println("����Ĳ��ǹ���Ҫ�����ģ�����һ����ʿ��");
					WriteTimer.println("����������������ҵĻ����Դ��͸��㣡");
					WriteTimer.println("����˵һ�飬ֻ�ܴ��Ҹղ�˵��ѡ������ѡһ����");
				}
				else if(times>9) {
					WriteTimer.println("���졢���졢���졭��");
					WriteTimer.println("��֪�����ж������= =��");
					WriteTimer.println("��Ī�����ڵ�Ϸ�ҡ�");
				}
				else
					WriteTimer.println("��Ǹ�����������ҵ���˼��ֻ�ܴӽ��졢���졢������ѡһ�죡");
			}
			else break;
		}
		
	}
	
	/**
	 * ������ȡ��ѯ����������ָ����
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 * @throws EncryptedDocumentException 
	 * */
	public void analyzeLifeSuggestion() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		//1.ѯ�ʵص�
		WriteTimer.println("���ˣ��㻹û������Ҫ��ѯ��λ�ã�");
		WriteTimer.println("��������������룺������ʯ��ׯ������������ֱ�����롰ʯ��ׯ����֮��֮��ġ�");
		
		while(true) {
			//2.�����û�����
			userText=null;
			while(userText==null || userText.length()==0) {
				waitForLine();
				if(userText==null || userText.length()==0)
					WriteTimer.println("�㲻��ʲô�������룡�������У�");
			}
			
			//3.ʵ����Nlp���󣬷����ص�
			Nlp locationNlp=null;
			location = null;
			netanswer = false;
			while(!(locationNlp!=null && location!=null)) {
				try {
					/*ִ��ʱ������̣߳���ִ��ʱ�������������ʾ*/
					WaitMessageThread.startThread();
					locationNlp = new Nlp(userText);				
					//4.���õص�
					location = locationNlp.getLocaction();
					WaitMessageThread.stopThread();
				} catch (aipNoConnectionException e) {
					WaitMessageThread.stopThread();
					checkNet();
				}
			}
			
			//�ж��Ƿ�֧��
			int f = judgeLocation(location);
			if(f==1 || f==2) {
				//���֧��
				if(f==1) {
					WriteTimer.println(location+"������֪����һ����������ĵط���");
					WriteTimer.println("����ţ�����͸�����"+location+"�����������");
					break;
				}
				else if(f==2) {
					WriteTimer.println("�������尡��Ҫ����ֱ�Ӱ����ʡ��"+location+"�İɣ�");
					//��ѡ�ť
					MainWindows.createButton("��ʡ���", true, 1);
					MainWindows.createButton("�����ط���", false, 2);
					
					waitForIndex();
					if(answer.equals("1")) {
						WriteTimer.println("����ţ�����͸����ʡ��"+location+"�����������");
						break;
					}
					else {
						WriteTimer.println("����ʲô�ط���");
						continue;
					}
				}
			}
			else {
				WriteTimer.println("��־���ص㲻֧�֡�");
				WriteTimer.println("�ޡ������˷ǳ���Ǹ�����⡣");
				WriteTimer.println("Ҫ��Ҫ��һ���ط���");
				
				//��ѡ�ť
				MainWindows.createButton("��һ��", true, 1);
				MainWindows.createButton("���˰�", false, 2);
				
				waitForIndex();
				
				if(answer.equals("1")) {
					WriteTimer.println("Ҫ����ʲô�ط���");
					continue;
				}
				else return;
			}
		}
		
		Arrays.fill(lifeSuggestion, false);

		//��ʾ
		WriteTimer.println("����֪��ʲô��Ϣ��?");
		WriteTimer.println("�������ܰ���ĺ�����,ֻ�ܸ��������漸��Ŷ");
		WriteTimer.println("[����ָ��] [ϴ��] [�᲻���ð] [�˶�] [����] [������ǿ��]");
		
		int times=0;
		while(true) {
			times++;
			userText=null;
			while(userText==null || userText.length()==0) {
				waitForLine();
				if(userText==null || userText.length()==0)
					WriteTimer.println("�㲻��ʲô�������룡�������У�");
			}
			
			Nlp life=null;
			netanswer = false;
			while(life==null) {
				try {
					WaitMessageThread.startThread();
					life = new Nlp(userText);
					WaitMessageThread.stopThread();
				} catch (aipNoConnectionException e) {
					WaitMessageThread.stopThread();
					checkNet();
				}
			}
			
			for(int i=0;i<life.getLength();i++)
			{
				switch (life.getI_ite(i).getItem()) {
				case "����":
					lifeSuggestion[0]=true;
					break;
				case "�·�":
					lifeSuggestion[0]=true;
					break;
				case "���·�":
					lifeSuggestion[0]=true;
					break;
				case "��":
					lifeSuggestion[0]=true;
					break;
				case "ϴ��":
					lifeSuggestion[1]=true;
					break;
				case "��ð":
					lifeSuggestion[2]=true;	
					break;
				case "�˶�":
					lifeSuggestion[3]=true;
					break;
				case "����":
					lifeSuggestion[4]=true;
					break;
				case "����":
					lifeSuggestion[4]=true;
					break;
				case "��":
					lifeSuggestion[4]=true;
					break;
				case "������":
					lifeSuggestion[5]=true;
					break;
				case "ɹ":
					lifeSuggestion[5]=true;
					break;
				default:
					break;
				}
			}
			int i=0;
			while(i<6){
				if(lifeSuggestion[i])
					break;
				i++;
			}
			if(i==6) {
				if(times>2) {
					WriteTimer.println("�ҡ���");
					WriteTimer.println("�������ò��ã�ֻ�ܴ��Ҹղ�˵��ѡ������ѡһ����");
				}
				else if(times>5){
					WriteTimer.println("�ҡ����Ҷ��������");
					WriteTimer.println("����˵һ�飬ֻ�ܴ��Ҹղ�˵��ѡ������ѡһ����");
				}
				else if(times>8) {
					WriteTimer.println("����Ĳ��ǹ���Ҫ�����ģ�����һ����ʿ��");
					WriteTimer.println("����������������ҵĻ����Դ��͸��㣡");
					WriteTimer.println("����˵һ�飬ֻ�ܴ��Ҹղ�˵��ѡ������ѡһ����");
				}
				else
					WriteTimer.println("��Ǹ�����������ҵ���˼��ֻ�ܴ��Ҹղ�˵��ѡ������ѡһ����");
			}
			else break;
		}
	}
	
	/**
	 * ������ȡ��ѯ�������û����飩
	 * 
	 * <p>�����������û���Ҫ���ҵ���Ŀ</p>
	 * */
	public void analyzeLifeDetil() {
		Arrays.fill(lifeDetil, false);
		//��ʾ
		WriteTimer.println("�������ܰ���ĺ�����,ֻ��ֻ���������漸���������٣�");
		WriteTimer.println("[�²�]"+"[���ϵ�����]"+"[������]");
		WriteTimer.println("����˵����һ����֪��ȫ������Ϣ��");
		
		int times=0;
		while(true) {
			times++;
			//�û�����������Ϣ
			userText=null;
			while(userText==null || userText.length()==0) {
				waitForLine();
				if(userText==null || userText.length()==0)
					WriteTimer.println("�㲻��ʲô�������룡�������У�");
			}
			
			Nlp detil=null;
			netanswer = false;
			while(detil==null) {
				try {
					WaitMessageThread.startThread();
					detil = new Nlp(userText);
					WaitMessageThread.stopThread();
				} catch (aipNoConnectionException e) {
					WaitMessageThread.stopThread();
					checkNet();
				}
			}
			
			for(int i=0;i<detil.getLength();i++)
			{
				switch (detil.getI_ite(i).getItem()) {
				case "ȫ��":
					lifeDetil[0]=true;
					break;
				case "�²�" :
					lifeDetil[1]=true;
					break;
				case "��" :
					lifeDetil[1]=true;
					break;
				case"����":
					lifeDetil[3]=true;
					break;
				case"������":
					lifeDetil[3]=true;
					break;
				case"����":
					lifeDetil[3]=true;
					break;
				case"��":
					lifeDetil[3]=true;
					break;
				case "����":
					lifeDetil[2]=true;
					break;
				case "����":
					lifeDetil[2]=true;
					break;
				case "��":
					lifeDetil[2]=true;
					break;
				case "ҹ��":
					lifeDetil[2]=true;
					break;
				}
			}
			int i=0;
			while(i<4){
				if(lifeDetil[i])
					break;
				i++;
			}
			if(i==4) {
				if(times>2) {
					WriteTimer.println("�ҡ���");
					WriteTimer.println("�������ò��ã�ֻ�ܴ��Ҹղ�˵��ѡ������ѡһ����");
				}
				else if(times>5){
					WriteTimer.println("�ҡ����Ҷ��������");
					WriteTimer.println("����˵һ�飬ֻ�ܴ��Ҹղ�˵��ѡ������ѡһ����");
				}
				else if(times>8) {
					WriteTimer.println("����Ĳ��ǹ���Ҫ�����ģ�����һ����ʿ��");
					WriteTimer.println("����������������ҵĻ����Դ��͸��㣡");
					WriteTimer.println("����˵һ�飬ֻ�ܴ��Ҹղ�˵��ѡ������ѡһ����");
				}
				else
					WriteTimer.println("��Ǹ�����������ҵ���˼��ֻ�ܴ��Ҹղ�˵��ѡ������ѡһ����");
			}
			else break;
		}
	}
	
	/**
	 * �ṩ������ϸ������Ϣ��
	 * 
	 * <p>�����û���Ҫ���ҵ���Ŀ</p>
	 * */
	public void offerDetilLife(String start,String end) throws  ErrorMessageException
	{
		analyzeLifeDetil();
		try {
			Weather askDetil=null;
			netanswer = false;
			while(askDetil==null) {
				try {
					WaitMessageThread.startThread();
					askDetil = new Weather(location, start, end, language, unit);
					WaitMessageThread.stopThread();
				} catch (IOException e) {
					// TODO Auto-generated catch block
	//				e.printStackTrace();
					WaitMessageThread.stopThread();
					checkNet();
				}
			}
			
			askDetil.daily.getToday();
			DailyContent dailyAsk = askDetil.daily.getDailyAsk();
			
			int high=Integer.parseInt(dailyAsk.getHigh());
			int low=Integer.parseInt(dailyAsk.getLow());
			int hl=high-low;
			String day = null;
			if(start.equals("0")) day="����";
			else if(start.equals("1")) day="����";
			else if(start.equals("2")) day="����";
			
			if(lifeDetil[0]) {
				//�ſ�
				WriteTimer.println(day+"����"+dailyAsk.getText_night()+","
						+dailyAsk.getLow()+"��"+dailyAsk.getHigh()+"���϶ȡ�");
				
				//�²�
				WriteTimer.println("��ҹ�²�"+hl+"���϶ȡ�");
				if(hl>=10) WriteTimer.println("��ҹ�²��е�󣬼ǵ������·�����");
				//������
				if(dailyAsk.getPrecip()!=null && !dailyAsk.getPrecip().isEmpty()) {
					if(!wheatherRain) {
						WriteTimer.println("���⣬"+day+"��"+dailyAsk.getPrecip()+"%�Ŀ��ܻή��");
						if(dailyAsk.getPrecip().length()==2 && dailyAsk.getPrecip().charAt(0)>='6')
							WriteTimer.println("�ǵô�����ɡ�����ҿɲ��������ȥ�ģ�");
					}
				}
				else {
					WriteTimer.println("�����ʡ����Բ�����û�鵽��");
					WriteTimer.println("��Ų�������ɣ��ٺ١�");
				}
			}
			else {
				if(lifeDetil[1]) {
					//�ſ�
					WriteTimer.println(day+dailyAsk.getLow()+"��"+dailyAsk.getHigh()+"���϶ȡ�");
					//�²�
					WriteTimer.println("��ҹ�²�"+hl+"���϶ȡ�");
					if(hl>=10) WriteTimer.println("��ҹ�²��е�󣬼ǵ������·�����");
				}
				else if(lifeDetil[2]) {
					WriteTimer.println(day+"������"+dailyAsk.getText_night()+"�죡");
				}
				else if(lifeDetil[3]) {
					if(dailyAsk.getPrecip()!=null && !dailyAsk.getPrecip().isEmpty()) {
						if(!wheatherRain) {
							WriteTimer.println("���⣬"+day+"��"+dailyAsk.getPrecip()+"%�Ŀ��ܻή��");
							if(dailyAsk.getPrecip().length()==2 && dailyAsk.getPrecip().charAt(0)>='6')
								WriteTimer.println("�ǵô�����ɡ�����ҿɲ��������ȥ�ģ�");
						}
					}
					else {
						WriteTimer.println("�����ʡ����Բ�����û�鵽��");
						WriteTimer.println("��Ų�������ɣ��ٺ١�");
					}
				}
				
			}
			
			wheatherRain=false;
			
		}
		catch(ErrorMessageException e) {
			excReac(Weather.errorManager.getStatus_code());
		}
	}
	
	/**
	 * �ṩ����ʵ��������
	 * @throws InterruptedException 
	 * @throws InvalidFormatException 
	 * @throws EncryptedDocumentException 
	 * */
	public void offerNowWeather() throws InterruptedException, EncryptedDocumentException, InvalidFormatException {
		
		try {
			//1.������ز���
			netanswer = false;
			try {
				analyzeNowWeather();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
//				e1.printStackTrace();
				checkNet();
			}
			//2.ʵ����������ѯ����
			Weather askWheather=null;
			netanswer = false;
			while(askWheather==null) {
				try {
					WaitMessageThread.startThread();
					askWheather = new Weather(location,language,unit);
					WaitMessageThread.stopThread();
					
				} catch (IOException e) {
					WaitMessageThread.stopThread();
					checkNet();
				}
			}
			//3.��������
			WriteTimer.println("�һ���������ѯ��ϣ�");
			WriteTimer.println("����"+location+askWheather.now.getText()+"�����ڲ��"
					+askWheather.now.getTemperature()+"�ȵ����Ӱ�");
			if(askWheather.now.getText().indexOf('��')!=-1)
				wheatherRain = true;
			//4.�²۲��䴦��������£�����һЩ���ģ�
			//���
			//ѯ���Ƿ���Ҫ��ѯ�����Ϣ�����Ҫ������������������ȥ�顰���족����Ϣ
			WriteTimer.println("��ô��?�һ��ܸ���������ϢŶ��");
			WriteTimer.println("����˵����ĸ���֮��ġ�");
			//��ť���
			MainWindows.createButton("�ð�", true, 1);
			MainWindows.createButton("û��Ȥ", false, 2);
			waitForIndex();
			if(answer.equals("1"))
				//1.��
				offerDetilLife("0","1");
			else
				//2.��
				WriteTimer.println("�Ǻðɡ�");
			
		}
		catch(ErrorMessageException e) {
			excReac(Weather.errorManager.getStatus_code());
		}
	}
	
	/**
	 * ��Ӧ�����������������Ĳ�ѯ�����Ӧ���û�
	 * 
	 * @param Weather askWeather ������Ϣ��ѯ���
	 * @param int day Ҫ��ѯ������
	 * @throws ErrorMessageException
	 * 
	 * */
	public void lifeDailyReaction(Weather askWeather,int day) throws ErrorMessageException {
		switch(day) {
		case 0:
			askWeather.daily.getToday();
			break;
		case 1:
			askWeather.daily.getTomorrow();
			break;
		case 2:
			askWeather.daily.getTheDayAfterTomo();
			break;
		}
		if(askWeather.daily.getDailyAsk()!=null) {
			WriteTimer.println("�һ���������ѯ��ϣ�");
			switch(day) {
			case 0:
				WriteTimer.println("����");
				break;
			case 1:
				WriteTimer.println("����");
				break;
			case 2:
				WriteTimer.println("����");
				break;
			}
			WriteTimer.println("����"+askWeather.daily.getDailyAsk().getText_day()+"��"
								+"���"+askWeather.daily.getDailyAsk().getText_night()+"��"
								+askWeather.daily.getDailyAsk().getLow()+"��"+askWeather.daily.getDailyAsk().getHigh()+"��");
		}
		//����������Ӵ�	
		//���
		WriteTimer.println("��ô��������֪�����������������");
		MainWindows.createButton("�ð���", true, 1);
		MainWindows.createButton("���˰ɡ�", false, 2);
		waitForIndex();
		if(answer.equals("1"))
			switch(day) {
			case 0:
				offerDetilLife("0","1");
				break;
			case 1:
				offerDetilLife("1","1");
				break;
			case 2:
				offerDetilLife("2","1");
				break;
			}
		else
		//2.��
			WriteTimer.println("�Ǻðɡ�");
		//ѯ�ʻ���Ҫ��ѯʲô��Ϣ�������ظ�
	}
	
	/**
	 * �ṩ��������������
	 * @throws ErrorMessageException 
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws InvalidFormatException 
	 * @throws EncryptedDocumentException 
	 * */
	public void offerDailyWeather() throws ErrorMessageException, InterruptedException, EncryptedDocumentException, InvalidFormatException {
		try {
			//1.������ز���
			netanswer = false;
			try {
				analyzeDailyWeather();
			} catch (IOException e1) {
				checkNet();
			}
			
			//2.ʵ����������ѯ����
			String day=null;
			int i;
			for(i=0;i<4;i++) {
				if(dailyLife[i]) {
					day=day+i;
					break;
				}
			}
			Weather askWeather=null;
			netanswer = false;
			while(askWeather==null) {
				try {
					WaitMessageThread.startThread();
					askWeather = new Weather(location,"0",day,language,unit);
					WaitMessageThread.stopThread();
					//3.��������
				} catch (IOException e) {
					WaitMessageThread.stopThread();
					checkNet();
				}
			}
			lifeDailyReaction(askWeather,i);
			
		}
		catch(ErrorMessageException e) {
			excReac(Weather.errorManager.getStatus_code());
		}
	}
	
	/**
	 * ���ڴ�����֪�����������ķ���
	 * 
	 * @param String e �������
	 * */
	public void excReac(String e) {
		switch(e) {
			case "AP010006":
				WriteTimer.println("�Բ�����û�в�ѯ����ص��Ȩ�ޡ�");
				break;
			case "AP010010":
				WriteTimer.println("�Բ�������ص��ƺ������ڡ�");
				break;
			case "AP010011":
				WriteTimer.println("�Һ�����ʧ�˷��򡭡����Ҳ���������У�");
				break;
			case "AP010014":
				WriteTimer.println("���Сʱ�Ĳ�ѯ�����Ѿ������ˣ��ҵ�Ъ�����");
				break;
			default:
				WriteTimer.println("��־��ϵͳ�ڲ������˲�����״�Ĵ���");
				break;
		}
	}
	
	/**
	 * ��Ӧ������������ָ���Ĳ�ѯ����ظ����û�
	 * @param Weather askLifeSuggestion ������ѯ��Ϣ
	 * 
	 * */
	public void lifeSuggestionReaction(Weather askLifeSuggestion) {
		for(int i=0;i<=5;i++) {
			if(lifeSuggestion[i]) {
				switch (i) {
				case 0:
					switch (askLifeSuggestion.lifeSuggestion.getDressing()) {
					case "����":
						WriteTimer.println("��������"+askLifeSuggestion.lifeSuggestion.getDressing()
							+",���鴩����֯�������Ķ��¡���ȹ������ȹ���̿㡣");
						break;
					case "����":
						WriteTimer.println("��������"+askLifeSuggestion.lifeSuggestion.getDressing()
							+",���鴩�������ϵĳ���������ȹ����T����");
						break;
					case "����":
						WriteTimer.println("��������"+askLifeSuggestion.lifeSuggestion.getDressing()
							+",���鴩���¡������¡�Ƥ�пˡ��������ס���ñ�����ס����޷���Ƥ����");					
						break;
					case "����":
						WriteTimer.println("��������"+askLifeSuggestion.lifeSuggestion.getDressing()
							+",���鴩ë�¡����¡�ë��װ��������װ");
						break;
					case "����":
						WriteTimer.println("��������"+askLifeSuggestion.lifeSuggestion.getDressing()
							+",���鴩�����������ϵĶ���װ��T��������ţ�����㡢���з���ְҵ��װ��");
						break;
					case "������":
						WriteTimer.println("��������"+askLifeSuggestion.lifeSuggestion.getDressing()
							+",���鴩��װ�����¡����¡�����װ���п�������װ����ë�¡�");
						break;
					case "��":
						WriteTimer.println("��������"+askLifeSuggestion.lifeSuggestion.getDressing()
							+",���鴩�����������ϵĶ���װ��T��������ţ�����㡢���з���ְҵ��װ��");
						break;
					case "��":
						WriteTimer.println("��������"+askLifeSuggestion.lifeSuggestion.getDressing()
							+",���鴩���¡����¡��д��¡����ס�ë�¡�ë��װ����װ����������");
						break;
					default:
						WriteTimer.println("�š�����ûʲô�����ԵĽ��顣");
						break;
					}
					break;
				case 1:
					WriteTimer.println("����"+askLifeSuggestion.lifeSuggestion.getCar_washing()+"ϴ����");
					break;
				case 2:
					WriteTimer.println("����"+askLifeSuggestion.lifeSuggestion.getFlu()+"��ð��");
					break;
				case 3:
					WriteTimer.println("����"+askLifeSuggestion.lifeSuggestion.getSport()+"�˶���");
					break;
				case 4:
					WriteTimer.println("����"+askLifeSuggestion.lifeSuggestion.getTravel()+"���С�");
					break;
				case 5:
					WriteTimer.println("����������"+askLifeSuggestion.lifeSuggestion.getUv()+"��");
					break;
				default:
					break;
				}
			}
		}
	}

	
	/**
	 * �ṩ��������ָ����
	 * @throws ErrorMessageException 
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws InvalidFormatException 
	 * @throws EncryptedDocumentException 
	 * */
	public void offerLifeSuggestion() throws  ErrorMessageException, InterruptedException, EncryptedDocumentException, InvalidFormatException {
		//1.�Է���������Ϣ�������
		netanswer = false;
		try {
			analyzeLifeSuggestion();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
//			e1.printStackTrace();
			checkNet();
		}
		//2.��ȡ����������Ϣ
		Weather askLifeSuggestion=null;
		netanswer = false;
		while(askLifeSuggestion==null) {
			try {
				WaitMessageThread.startThread();
				askLifeSuggestion = new Weather(location, language);
				WaitMessageThread.stopThread();
			} catch (IOException e) {
				// TODO Auto-generated catch block
	//			e.printStackTrace();
				WaitMessageThread.stopThread();
				checkNet();
			}
		}
		//3.��������
		lifeSuggestionReaction(askLifeSuggestion);
		
		
	}
	
	/**
	 * ������
	 * ��������֧�Ĵ���
	 * 
	 * @throws ErrorMessageException 
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws InvalidFormatException 
	 * @throws EncryptedDocumentException 
	 * */
	public void server() throws ErrorMessageException, InterruptedException, EncryptedDocumentException, InvalidFormatException {
		boolean whetherFirstTime = true;
		
		while(true) {
			//����ǵ�һ��ʹ��
			if(whetherFirstTime) {
				//1.�׳�����
				WriteTimer.println("��ô���������кι���أ�");
				//2.��ʾ��ť
				MainWindows.createButton("���Ҳ�һ�½��������:)", true, 1);
				MainWindows.createButton("����֪�����������:)", true, 2);
				MainWindows.createButton("�ҽ��졭��", false, 3);
				waitForIndex();
				whetherFirstTime=false;
			}
			//����
			else {
				//1.�׳�����
				WriteTimer.println("����ʲô��֪������");
				//2.��ʾ��ť
				MainWindows.createButton("���Ҳ�һ�½��������:)", true, 1);
				MainWindows.createButton("����֪�����������:)", true, 2);
				MainWindows.createButton("�ҽ��졭��", true, 3);
				MainWindows.createButton("�ݰݣ�", false, 4);
				//3.����ѡ��
				waitForIndex();
			}
			//4.������Ӧģ��
			System.out.println(answer);
			if(answer.equals("1")) {
				WriteTimer.println("�ޣ��õģ��������ˣ�");
				offerNowWeather();
			}
			else if(answer.equals("2")) {
				WriteTimer.println("�ޣ��õģ��������ˣ�");
				offerDailyWeather();
			}
			else if(answer.equals("3")) {
				WriteTimer.println("�ޣ��õģ��������ˣ�");
				offerLifeSuggestion();
			}
			else if(answer.equals("4")) {
				WriteTimer.println("��ô�ټ�����");
				System.exit(0);
			}
		}
		
	}
	
	/**
	 * ��������
	 * 
	 * @throws IOException
	 * @throws ErrorMessageException
	 * @throws InterruptedException
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 * */
	public static void main(String[] args) throws ErrorMessageException, InterruptedException, EncryptedDocumentException, InvalidFormatException{
		Demo demo=new Demo();
		
		try {
			//������뷨�л����µİ�������
			System.setProperty("sun.java2d.noddraw", "true");
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
			
		}
       javax.swing.SwingUtilities.invokeLater(new Runnable() {
           public void run() {
               MainWindows.paintGUI();
           }
       });
		
		if(demo.initMain(demo.user)) {
			//��ʼ���ɹ�����������
			demo.server();
		}
	}
}
