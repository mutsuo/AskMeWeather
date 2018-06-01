/**
* Team Zhinengxianfeng Hebei Normal University
* FileName: Demo.java
* 主调类。
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

	/*地点*/
	private String location=null;
	/*语言*/
	//暂时缺省
	private String language=null;
	/*气温单位*/
	//暂时缺省
	private String unit=null;
	/*用于逐日天气查询的时间*/
	private boolean dailyLife[] = new boolean[4];
	//暂时缺省
	/*用于存储生活指数需求的数组*/
	private boolean lifeSuggestion[]=new boolean[6];
	/*用于存储天气详细信息需求的数组*/
	private boolean lifeDetil[]=new boolean[4];
	
	/*用于记录输入信息*/
	public static String userText=null;
	public static String answer;
	
	/*记录是否已经下雨，以纠正降雨率*/
	public boolean wheatherRain = false;
	
	/*网络检查标记，用于改善回答*/
	public boolean netanswer = false;
	
	/*用户数据*/
	User user=null;
	
	
	/**
	 * 构造器，用于初始化用户
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
	 * 用户初始化模块
	 * 
	 * @param none
	 * @return none
	 * @throws IOException 
	 */
	public void initUser() {
		try {
			user= new User();
		} catch (IOException e) {
			WriteTimer.println("日志：没找到用户文件！");
		}
	}
	
	/**
	 * 应用初始化模块</br>
	 * 1.打招呼</br>
	 * 2.询问名字</br>
	 * 3.判断网络连接是否正常：保证网络正常后再返回。
	 * 
	 * @param User user 用户信息
	 * @return boolean
	 * @throws IOException 
	 */
	public boolean initMain(User user) {
		//打招呼、询问名字
		greeting(user);
		//检查网络状况
		WriteTimer.println("稍等，我帮你检查一下网络啊……");
		netanswer = false;
		if(checkNet())
			return true;
		else return false;
	}
	
	/**
	 * 检查网络
	 * <ul>
	 * <li>如果有网络，返回true</li>
	 * <li>否则，询问用户是否还要继续查询。如果继续，那么机器人在有网络时自动唤醒；否则直接退出程序</li>
	 * 
	 * @param none
	 * @return boolean
	 * @throws IOException 
	 */
	public boolean checkNet() {
		/*是否第一次检查*/
		boolean f = true;
		/*是否是重新连上的*/
		boolean reCon = false;
		
		while(!isConnect("https://www.seniverse.com/") 
				&& !isConnect("https://login.bce.baidu.com/")) {
			if(f==true) {
				f=false;
				WriteTimer.println("我连不上你们人类的网络了！也许你应该给我块饼干……");
				WriteTimer.println("哈哈，逗你的，我该回去工作啦，需要的时候再叫我。");
				
				MainWindows.createButton("好。", true, 1);
				MainWindows.createButton("不用了，不打扰你工作了。", false, 2);
				
				waitForIndex();
				
				//扩展：没有网络时的操作
				if(answer.equals("1")) {
					WriteTimer.println("嗯，当我连上网络的时候我会回来叫你的。");
					WriteTimer.println("一会儿见啦。");
					reCon = true;
					continue;
					//这之后如果收到重新查询的呼唤，再检查网络，在此之前等待
				}
				else System.exit(0);
			}
			
		}
		if (isConnect("https://www.seniverse.com/") 
				&& isConnect("https://login.bce.baidu.com/")
				&& !netanswer) {
			WriteTimer.println("很好！网络状联通！");
			if(reCon) WriteTimer.println("嘿！人类！网路重新连上了，你还在吗？");
			netanswer=true;
		}
		return true;
	}
	
	/**
	 * 检查网络连接
	 * <p>使用IO流打开网页连接。如果能打开说明网络连接正常，返回true；如果抛出异常，则网络连接失败，返回false</p>
	 * 
	 * @param String net 要请求访问的网址
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
	 * 和用户打招呼
	 * <ol>
	 * <li>首先送上一句时间问候</li>
	 * <li>问好、自我介绍</li>
	 * <li>登记用户信息</li>
	 * </ol>
	 * <p>收集用户登录信息的最初目的是记录用户的ip地址，但由于时间关系，这个功能目前已经弃用了。</p>
	 * <p>另外，用户信息将序列化后存在<i>userData</i>用户目录下</p>
	 * 
	 * @param user 用户对象
	 * @return none
	 * @throws IOException 
	 */
	public void greeting(User user) {
		//1.时间问候语
		//获取时间以判断现在是早上、中午还是晚上
		Calendar now = Calendar.getInstance();
		int time=now.get(Calendar.HOUR_OF_DAY);
		//清晨：05：01-06：59 
		//早上：07：01-08：59 
		//上午：09：00-12：00 
		//中午：12：01-13：59 
		//下午：14：00-17：59 
		//傍晚：18：00-18：59 
		//晚上：19：00-23：59 
		//凌晨：24：00-05：00
		if(time>=5 && time<7)
			WriteTimer.println("现在是清晨，开启全新一天。");
		if(time>=7 && time<9)
			WriteTimer.println("早上好！");
		if(time>=9 && time<12)
			WriteTimer.println("上午好！");
		if(time>=12 && time<14)
			WriteTimer.println("中午好！");
		if(time>=14 && time<18)
			WriteTimer.println("下午好！");
		if(time>=18 && time<19)
			WriteTimer.println("现在是傍晚时分，来杯咖啡吧！");
		if(time>=19 && time<=21)
			WriteTimer.println("晚上好！");
		if(time>21 && time<=23 || time==0)
			WriteTimer.println("又到了说晚安的时间！");
		if(time>0 && time<5)
			WriteTimer.println("时间还早，不妨摊开一本书，细品一杯早茶以待日出吧！");
		
		//2.打招呼
		WriteTimer.println("你好呀，我是机器人小智，正在云端观测天象。");
		WriteTimer.println("哦！让我来找找啊……");
		WriteTimer.println("那边那个黑乎乎的小点一定就是你吧！");
		//3.询问名字
		//判断是否是新客户
		//如果曾经登录过
		if(user.getUserFile().length()!=0) {
			WriteTimer.println("我们或许不是第一次聊天……");
			WriteTimer.println("我记得你叫"+user.getName()+"对吗？");
			
			//收集用户回答
			//创建按钮
			MainWindows.createButton("是的", true, 1);
			MainWindows.createButton("我不叫这个名字", false, 2);
			//此处可以改为用户输入
			waitForIndex();
			
			if(answer.equals("1"))
				WriteTimer.println("嘿嘿，你好呀，人类！");
			else {
				WriteTimer.println("好奇怪啊……我的芯片出错了吗？");
				WriteTimer.println("能重新把名字告诉我一下吗？");
				WriteTimer.println("等等，如果你想更新名字的话，过去的记录数据我就用来填饱肚子了哦。");
				WriteTimer.println("我可不会吐出来还给你的。");
				
				MainWindows.createButton("嗯", true, 1);
				MainWindows.createButton("再考虑一下", false, 2);
				waitForIndex();
				
				System.out.println(answer);
				if(answer.equals("1")) {
					if(user.deleteData()) {
						WriteTimer.println("日志：过去记录已删除。");
						initUser();
						WriteTimer.println("好，现在你可以把新名字告诉我了。");
						userText=null;
						while(userText==null || userText.length()==0) {
							waitForLine();
							if(userText==null || userText.length()==0)
								WriteTimer.println("你不能什么都不输入！这样不行！");
						}
						user.setName(userText);
						WriteTimer.println("哦!"+user.getName()+"你好呀！");
						//序列化
						if(user.serializeUser(user))
							WriteTimer.println("你的名字很好记，我已经记住了哦！");
						else
							WriteTimer.println("记录：记忆芯片出错。该用户记录丢失。");
					}
					else
						WriteTimer.println("日志：删除失败。");
				}	
			}
		}
		else {
			WriteTimer.println("你叫什么？……我知道你一定不叫罗伯特……");
			WriteTimer.println("你可以像这样输入：“张三”。请直接把名字告诉我，我可不知道你们人类名字的法则。");
			
			userText=null;
			waitForLine();
			while(userText==null || userText.length()==0) {
				WriteTimer.println("你的名字不能为空！");
				waitForLine();
			}
			user.setName(userText);
			
			
			//4.给出回应
			WriteTimer.println("哦!"+user.getName()+"你好呀！");
			//序列化
			if(user.serializeUser(user))
				WriteTimer.println("你的名字很好记，我已经记住了哦！");
			else
				WriteTimer.println("记录：记忆芯片出错。该用户记录丢失。");
		}
	}
	
	/**
	 * 等待读入用户输入信息
	 * <p>用于读入输入框中用户的输入信息。直到读到用户的输入信息或者触发事件才会退出该方法。</p>
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
	 * 等待读入用户按钮信息
	 * <p>用于读取按下的按钮信息。直到读到用户的按钮信息才会退出该方法。</p>
	 * 
	 * @param none
	 * @return none
	 */
	public void waitForIndex() {
		answer=null;
		while(answer==null) {System.out.println(answer);continue;}
	}
	
	/**
	 * 判断目标位置是否支持查询
	 * <p>用于检查目标位置是否支持查询。目标位置通过参数传入。</p>
	 * <p>通过在官方提供的地点Excel表中检索匹配，来判断目标地点是否支持。</p>
	 * 
	 * @param String location 目标地点
	 * @return int (1,2,-1)
	 * <ul>
	 * 	<li>返回值<i>1</i>表示该地点支持，且原始字串以“省”字结尾</li>
	 * 	<li>返回值<i>2</i>表示该地点支持，且已在原始字串后加上了“省”字。</li>
	 * 	<li>返回值<i>-1</i>表示该地点不支持</li>
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
		// 工作表对象
		Sheet sheet = workbook.getSheetAt(0);
		// 总行数
		int rowLength = sheet.getLastRowNum() + 1;
		// 工作表的列
		Row row = sheet.getRow(0);
		// 总列数
		int colLength = row.getLastCellNum();
		// 得到指定的单元格
		Cell cell = row.getCell(0);
		for (int i = 0; i < colLength; i++) {
			//不要第一行：无效的
			for (int j = 1; j < rowLength; j++) {
				row = sheet.getRow(j);
				cell = row.getCell(i);
				// 得到单元格的内容
				String s = cell.getStringCellValue();
				if (s=="") {
					continue;
				}
				if (location.indexOf(s) != -1) {
					if(i==colLength-1) {
						//将地点转换为省+省会
						// 工作表对象
						Sheet sheet1 = workbook.getSheetAt(1);
						// 总行数
						int rowLength1 = sheet1.getLastRowNum() + 1;
						for (int k = 1; k < rowLength1; k++) {
							Row row1 = sheet1.getRow(k);
							Cell cell1 = row1.getCell(0);
							String loc=cell1.getStringCellValue();
							if (loc.equals(s)) {
								if(this.location.charAt(this.location.length()-1)=='省')
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
	 * 分析提取查询参数（实况天气）
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 * @throws EncryptedDocumentException 
	 * */
	public void analyzeNowWeather() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		
		//1.询问地点
		WriteTimer.println("对了，我应该帮你查哪的天气？");
		WriteTimer.println("你可以像这样输入：“查下石家庄的天气”或者直接输入“石家庄”，之类之类的。");
		
		while(true) {
			//2.读入用户输入
			userText=null;
			while(userText==null || userText.length()==0) {
				waitForLine();
				if(userText==null || userText.length()==0)
					WriteTimer.println("你不能什么都不输入！这样不行！");
			}
			//3.实例化Nlp对象，分析地点
			Nlp locationNlp=null;
			location = null;
			netanswer = false;
			while(!(locationNlp!=null && location!=null)) {
				try {
					/*超时监听线程：若等待时间过长，给出提示*/
					WaitMessageThread.startThread();
					locationNlp = new Nlp(userText);			
					//4.设置地点
					location = locationNlp.getLocaction();
					WaitMessageThread.stopThread();
				} catch (aipNoConnectionException e) {
					WaitMessageThread.stopThread();
					checkNet();
				}
			}
			
			//扩展功能增加处。判断一下是否是新增地点。
			//5.通过查询Excel表格，判断是否是支持的地点
			int f = judgeLocation(location);
			if(f==1 || f==2) {
				//如果支持
				if(f==1) {
					WriteTimer.println(location+"？我又知道了一个人类世界的地方！");
					WriteTimer.println("你等着，我这就给你查查"+location+"今天的天气。");
					break;
				}
				else if(f==2) {
					WriteTimer.println("不够具体啊。要不我直接帮你查省会"+location+"的吧？");
					//画选项按钮
					MainWindows.createButton("查省会的", true, 1);
					MainWindows.createButton("换个地方吧", false, 2);
					
					waitForIndex();
					if(answer.equals("1"))
						WriteTimer.println("你等着，我这就给你查省会"+location+"今天的天气。");
					else {
						WriteTimer.println("换成什么地方？");
						continue;
					}
				}
				break;
			}
			else {
				WriteTimer.println("日志：地点不支持。");
				WriteTimer.println("噢。出现了非常抱歉的问题。");
				WriteTimer.println("要不要换一个地方？");
				
				//画选项按钮
				MainWindows.createButton("换一个", true, 1);
				MainWindows.createButton("算了吧", false, 2);
				
				waitForIndex();
				
				if(answer.equals("1")) {
					WriteTimer.println("要换成什么地方？");
					continue;
				}
				else break;
			}
		}
		
		
	}
	
	/**
	 * 分析提取查询参数（逐日天气）
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 * @throws EncryptedDocumentException 
	 * */
	public void analyzeDailyWeather() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {	
		//1.询问地点
		WriteTimer.println("对了，你还没告诉我要查询的位置？");
		WriteTimer.println("你可以像这样输入：“查下石家庄的天气”或者直接输入“石家庄”，之类之类的。");

		while(true) {
			//2.读入用户输入
			userText=null;
			while(userText==null || userText.length()==0) {
				waitForLine();
				if(userText==null || userText.length()==0)
					WriteTimer.println("你不能什么都不输入！这样不行！");
			}
			
			//3.实例化Nlp对象，分析地点
			Nlp locationNlp=null;
			netanswer = false;
			while(!(locationNlp!=null && location!=null)) {
				try {
					/*超时监听线程：如果时间过长，给出提示*/
					WaitMessageThread.startThread();
					locationNlp = new Nlp(userText);				
					//4.设置地点
					location = locationNlp.getLocaction();
					WaitMessageThread.stopThread();
				} catch (aipNoConnectionException e) {
					// TODO Auto-generated catch block
	//				e.printStackTrace();
					WaitMessageThread.stopThread();
					checkNet();
				}
			}
			
			//分析是否是支持的地点
			int f = judgeLocation(location);
			if(f==1 || f==2) {
				//如果支持
				if(f==1) {
					WriteTimer.println(location+"？我又知道了一个人类世界的地方！");
					WriteTimer.println("你等着，我这就给你查查"+location+"今天的天气。");
					break;
				}
				else if(f==2) {
					WriteTimer.println("不够具体啊。要不我直接帮你查省会"+location+"的吧？");
					//画选项按钮
					MainWindows.createButton("查省会的", true, 1);
					MainWindows.createButton("换个地方吧", false, 2);
					
					waitForIndex();
					if(answer.equals("1")) {
						WriteTimer.println("你等着，我这就给你查省会"+location+"今天的天气。");
						break;
					}
					else {
						WriteTimer.println("换成什么地方？");
						continue;
					}
				}
			}
			else {
				WriteTimer.println("日志：地点不支持。");
				WriteTimer.println("噢。出现了非常抱歉的问题。");
				WriteTimer.println("要不要换一个地方？");
				
				//画选项按钮
				MainWindows.createButton("换一个", true, 1);
				MainWindows.createButton("算了吧", false, 2);
				
				waitForIndex();
				
				if(answer.equals("1")) {
					WriteTimer.println("要换成什么地方？");
					continue;
				}
				else return;
			}
		}
		
		//5.询问要查询的日期
		WriteTimer.println("嘿嘿嘿。");
		WriteTimer.println("忘了告诉你，凭借我的能力，只能帮你最近三天的天气。");
		WriteTimer.println("今天，明天，后天，选一天吧？");
		
		//6.接收日期
		int times = 0;
		while(true) {
			times++;
			userText=null;
			while(userText==null || userText.length()==0) {
				waitForLine();
				if(userText==null || userText.length()==0)
					WriteTimer.println("你不能什么都不输入！这样不行！");
			}
			
			//7.分析
			Nlp day=null;
			netanswer = false;
			while(day==null) {
				try {
					/*超时监听线程：若执行时间过长，给出提示*/
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
				case "今天":
					dailyLife[0]=true;
					break;
				case "明天":
					dailyLife[1]=true;
					break;
				case "后天":
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
					WriteTimer.println("我……");
					WriteTimer.println("你认真点好不好，只能从我刚才说的那几天里面选一个！");
				}
				else if(times>5){
					WriteTimer.println("我……我都佩服你了");
					WriteTimer.println("我再说一遍，只能从我刚才说的那几天里面选一个！");
				}
				else if(times>8) {
					WriteTimer.println("我真的不是故意要生气的！我是一个绅士！");
					WriteTimer.println("但我现在甚至想把我的机器脑袋送给你！");
					WriteTimer.println("我再说一遍，只能从我刚才说的选项里面选一个！");
				}
				else if(times>9) {
					WriteTimer.println("今天、明天、后天……");
					WriteTimer.println("你知道我有多绝望吗= =。");
					WriteTimer.println("你莫不是在调戏我。");
				}
				else
					WriteTimer.println("抱歉，你真的理解我的意思吗？只能从今天、明天、后天中选一天！");
			}
			else break;
		}
		
	}
	
	/**
	 * 分析提取查询参数（生活指数）
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 * @throws EncryptedDocumentException 
	 * */
	public void analyzeLifeSuggestion() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		//1.询问地点
		WriteTimer.println("对了，你还没告诉我要查询的位置？");
		WriteTimer.println("你可以像这样输入：“查下石家庄的天气”或者直接输入“石家庄”，之类之类的。");
		
		while(true) {
			//2.读入用户输入
			userText=null;
			while(userText==null || userText.length()==0) {
				waitForLine();
				if(userText==null || userText.length()==0)
					WriteTimer.println("你不能什么都不输入！这样不行！");
			}
			
			//3.实例化Nlp对象，分析地点
			Nlp locationNlp=null;
			location = null;
			netanswer = false;
			while(!(locationNlp!=null && location!=null)) {
				try {
					/*执行时间监听线程：若执行时间过长，给出提示*/
					WaitMessageThread.startThread();
					locationNlp = new Nlp(userText);				
					//4.设置地点
					location = locationNlp.getLocaction();
					WaitMessageThread.stopThread();
				} catch (aipNoConnectionException e) {
					WaitMessageThread.stopThread();
					checkNet();
				}
			}
			
			//判断是否支持
			int f = judgeLocation(location);
			if(f==1 || f==2) {
				//如果支持
				if(f==1) {
					WriteTimer.println(location+"？我又知道了一个人类世界的地方！");
					WriteTimer.println("你等着，我这就给你查查"+location+"今天的天气。");
					break;
				}
				else if(f==2) {
					WriteTimer.println("不够具体啊。要不我直接帮你查省会"+location+"的吧？");
					//画选项按钮
					MainWindows.createButton("查省会的", true, 1);
					MainWindows.createButton("换个地方吧", false, 2);
					
					waitForIndex();
					if(answer.equals("1")) {
						WriteTimer.println("你等着，我这就给你查省会"+location+"今天的天气。");
						break;
					}
					else {
						WriteTimer.println("换成什么地方？");
						continue;
					}
				}
			}
			else {
				WriteTimer.println("日志：地点不支持。");
				WriteTimer.println("噢。出现了非常抱歉的问题。");
				WriteTimer.println("要不要换一个地方？");
				
				//画选项按钮
				MainWindows.createButton("换一个", true, 1);
				MainWindows.createButton("算了吧", false, 2);
				
				waitForIndex();
				
				if(answer.equals("1")) {
					WriteTimer.println("要换成什么地方？");
					continue;
				}
				else return;
			}
		}
		
		Arrays.fill(lifeSuggestion, false);

		//提示
		WriteTimer.println("你想知道什么信息呢?");
		WriteTimer.println("我现在能帮你的很有限,只能告诉你下面几个哦");
		WriteTimer.println("[穿衣指数] [洗车] [会不会感冒] [运动] [旅行] [紫外线强度]");
		
		int times=0;
		while(true) {
			times++;
			userText=null;
			while(userText==null || userText.length()==0) {
				waitForLine();
				if(userText==null || userText.length()==0)
					WriteTimer.println("你不能什么都不输入！这样不行！");
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
				case "穿衣":
					lifeSuggestion[0]=true;
					break;
				case "衣服":
					lifeSuggestion[0]=true;
					break;
				case "穿衣服":
					lifeSuggestion[0]=true;
					break;
				case "穿":
					lifeSuggestion[0]=true;
					break;
				case "洗车":
					lifeSuggestion[1]=true;
					break;
				case "感冒":
					lifeSuggestion[2]=true;	
					break;
				case "运动":
					lifeSuggestion[3]=true;
					break;
				case "旅行":
					lifeSuggestion[4]=true;
					break;
				case "旅游":
					lifeSuggestion[4]=true;
					break;
				case "玩":
					lifeSuggestion[4]=true;
					break;
				case "紫外线":
					lifeSuggestion[5]=true;
					break;
				case "晒":
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
					WriteTimer.println("我……");
					WriteTimer.println("你认真点好不好，只能从我刚才说的选项里面选一个！");
				}
				else if(times>5){
					WriteTimer.println("我……我都佩服你了");
					WriteTimer.println("我再说一遍，只能从我刚才说的选项里面选一个！");
				}
				else if(times>8) {
					WriteTimer.println("我真的不是故意要生气的！我是一个绅士！");
					WriteTimer.println("但我现在甚至想把我的机器脑袋送给你！");
					WriteTimer.println("我再说一遍，只能从我刚才说的选项里面选一个！");
				}
				else
					WriteTimer.println("抱歉，你真的理解我的意思吗？只能从我刚才说的选项里面选一个！");
			}
			else break;
		}
	}
	
	/**
	 * 分析提取查询参数（用户详情）
	 * 
	 * <p>分析并设置用户想要查找的项目</p>
	 * */
	public void analyzeLifeDetil() {
		Arrays.fill(lifeDetil, false);
		//提示
		WriteTimer.println("我现在能帮你的很有限,只能只能问我下面几个，不许超纲！");
		WriteTimer.println("[温差]"+"[晚上的天气]"+"[降雨率]");
		WriteTimer.println("或者说你想一口气知道全部的信息？");
		
		int times=0;
		while(true) {
			times++;
			//用户输入生活信息
			userText=null;
			while(userText==null || userText.length()==0) {
				waitForLine();
				if(userText==null || userText.length()==0)
					WriteTimer.println("你不能什么都不输入！这样不行！");
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
				case "全部":
					lifeDetil[0]=true;
					break;
				case "温差" :
					lifeDetil[1]=true;
					break;
				case "差" :
					lifeDetil[1]=true;
					break;
				case"降雨":
					lifeDetil[3]=true;
					break;
				case"降雨率":
					lifeDetil[3]=true;
					break;
				case"下雨":
					lifeDetil[3]=true;
					break;
				case"雨":
					lifeDetil[3]=true;
					break;
				case "晚上":
					lifeDetil[2]=true;
					break;
				case "今晚":
					lifeDetil[2]=true;
					break;
				case "晚":
					lifeDetil[2]=true;
					break;
				case "夜间":
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
					WriteTimer.println("我……");
					WriteTimer.println("你认真点好不好，只能从我刚才说的选项里面选一个！");
				}
				else if(times>5){
					WriteTimer.println("我……我都佩服你了");
					WriteTimer.println("我再说一遍，只能从我刚才说的选项里面选一个！");
				}
				else if(times>8) {
					WriteTimer.println("我真的不是故意要生气的！我是一个绅士！");
					WriteTimer.println("但我现在甚至想把我的机器脑袋送给你！");
					WriteTimer.println("我再说一遍，只能从我刚才说的选项里面选一个！");
				}
				else
					WriteTimer.println("抱歉，你真的理解我的意思吗？只能从我刚才说的选项里面选一个！");
			}
			else break;
		}
	}
	
	/**
	 * 提供服务（详细天气信息）
	 * 
	 * <p>设置用户想要查找的项目</p>
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
			if(start.equals("0")) day="今天";
			else if(start.equals("1")) day="明天";
			else if(start.equals("2")) day="后天";
			
			if(lifeDetil[0]) {
				//概况
				WriteTimer.println(day+"晚上"+dailyAsk.getText_night()+","
						+dailyAsk.getLow()+"到"+dailyAsk.getHigh()+"摄氏度。");
				
				//温差
				WriteTimer.println("昼夜温差"+hl+"摄氏度。");
				if(hl>=10) WriteTimer.println("昼夜温差有点大，记得增减衣服啊！");
				//降雨率
				if(dailyAsk.getPrecip()!=null && !dailyAsk.getPrecip().isEmpty()) {
					if(!wheatherRain) {
						WriteTimer.println("另外，"+day+"有"+dailyAsk.getPrecip()+"%的可能会降雨");
						if(dailyAsk.getPrecip().length()==2 && dailyAsk.getPrecip().charAt(0)>='6')
							WriteTimer.println("记得带好雨伞啊，我可不会给你送去的！");
					}
				}
				else {
					WriteTimer.println("降雨率……对不起，我没查到。");
					WriteTimer.println("大概不会下雨吧，嘿嘿。");
				}
			}
			else {
				if(lifeDetil[1]) {
					//概况
					WriteTimer.println(day+dailyAsk.getLow()+"到"+dailyAsk.getHigh()+"摄氏度。");
					//温差
					WriteTimer.println("昼夜温差"+hl+"摄氏度。");
					if(hl>=10) WriteTimer.println("昼夜温差有点大，记得增减衣服啊！");
				}
				else if(lifeDetil[2]) {
					WriteTimer.println(day+"晚上是"+dailyAsk.getText_night()+"天！");
				}
				else if(lifeDetil[3]) {
					if(dailyAsk.getPrecip()!=null && !dailyAsk.getPrecip().isEmpty()) {
						if(!wheatherRain) {
							WriteTimer.println("另外，"+day+"有"+dailyAsk.getPrecip()+"%的可能会降雨");
							if(dailyAsk.getPrecip().length()==2 && dailyAsk.getPrecip().charAt(0)>='6')
								WriteTimer.println("记得带好雨伞啊，我可不会给你送去的！");
						}
					}
					else {
						WriteTimer.println("降雨率……对不起，我没查到。");
						WriteTimer.println("大概不会下雨吧，嘿嘿。");
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
	 * 提供服务（实况天气）
	 * @throws InterruptedException 
	 * @throws InvalidFormatException 
	 * @throws EncryptedDocumentException 
	 * */
	public void offerNowWeather() throws InterruptedException, EncryptedDocumentException, InvalidFormatException {
		
		try {
			//1.设置相关参数
			netanswer = false;
			try {
				analyzeNowWeather();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
//				e1.printStackTrace();
				checkNet();
			}
			//2.实例化天气查询对象
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
			//3.给出反馈
			WriteTimer.println("我回来啦！查询完毕！");
			WriteTimer.println("今天"+location+askWheather.now.getText()+"，现在差不多"
					+askWheather.now.getTemperature()+"度的样子吧");
			if(askWheather.now.getText().indexOf('雨')!=-1)
				wheatherRain = true;
			//4.吐槽补充处（如对气温，还有一些关心）
			//这里！
			//询问是否还需要查询别的信息，如果要，就用逐日天气工具去查“今天”的信息
			WriteTimer.println("怎么样?我还能告诉你别的信息哦！");
			WriteTimer.println("比如说降雨的概率之类的。");
			//按钮添加
			MainWindows.createButton("好啊", true, 1);
			MainWindows.createButton("没兴趣", false, 2);
			waitForIndex();
			if(answer.equals("1"))
				//1.是
				offerDetilLife("0","1");
			else
				//2.否
				WriteTimer.println("那好吧。");
			
		}
		catch(ErrorMessageException e) {
			excReac(Weather.errorManager.getStatus_code());
		}
	}
	
	/**
	 * 回应函数：将逐日天气的查询结果回应给用户
	 * 
	 * @param Weather askWeather 天气信息查询结果
	 * @param int day 要查询的日子
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
			WriteTimer.println("我回来啦！查询完毕！");
			switch(day) {
			case 0:
				WriteTimer.println("今日");
				break;
			case 1:
				WriteTimer.println("明日");
				break;
			case 2:
				WriteTimer.println("后天");
				break;
			}
			WriteTimer.println("白天"+askWeather.daily.getDailyAsk().getText_day()+"，"
								+"晚间"+askWeather.daily.getDailyAsk().getText_night()+"，"
								+askWeather.daily.getDailyAsk().getLow()+"到"+askWeather.daily.getDailyAsk().getHigh()+"度");
		}
		//附加属性添加处	
		//这里！
		WriteTimer.println("怎么样？还想知道点别的天气情况不。");
		MainWindows.createButton("好啊。", true, 1);
		MainWindows.createButton("算了吧。", false, 2);
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
		//2.否
			WriteTimer.println("那好吧。");
		//询问还需要查询什么信息并给出回复
	}
	
	/**
	 * 提供服务（逐日天气）
	 * @throws ErrorMessageException 
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws InvalidFormatException 
	 * @throws EncryptedDocumentException 
	 * */
	public void offerDailyWeather() throws ErrorMessageException, InterruptedException, EncryptedDocumentException, InvalidFormatException {
		try {
			//1.设置相关参数
			netanswer = false;
			try {
				analyzeDailyWeather();
			} catch (IOException e1) {
				checkNet();
			}
			
			//2.实例化天气查询对象
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
					//3.给出反馈
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
	 * 用于处理心知天气错误代码的方法
	 * 
	 * @param String e 错误代码
	 * */
	public void excReac(String e) {
		switch(e) {
			case "AP010006":
				WriteTimer.println("对不起，我没有查询这个地点的权限。");
				break;
			case "AP010010":
				WriteTimer.println("对不起，这个地点似乎不存在。");
				break;
			case "AP010011":
				WriteTimer.println("我好像迷失了方向……我找不到这个城市！");
				break;
			case "AP010014":
				WriteTimer.println("这个小时的查询次数已经用完了，我得歇会儿。");
				break;
			default:
				WriteTimer.println("日志：系统内部发生了不可名状的错误。");
				break;
		}
	}
	
	/**
	 * 回应函数：将生活指数的查询结果回复给用户
	 * @param Weather askLifeSuggestion 天气查询信息
	 * 
	 * */
	public void lifeSuggestionReaction(Weather askLifeSuggestion) {
		for(int i=0;i<=5;i++) {
			if(lifeSuggestion[i]) {
				switch (i) {
				case 0:
					switch (askLifeSuggestion.lifeSuggestion.getDressing()) {
					case "炎热":
						WriteTimer.println("今天天气"+askLifeSuggestion.lifeSuggestion.getDressing()
							+",建议穿轻棉织物制作的短衣、短裙、薄短裙、短裤。");
						break;
					case "较热":
						WriteTimer.println("今天天气"+askLifeSuggestion.lifeSuggestion.getDressing()
							+",建议穿棉麻面料的衬衫、薄长裙、薄T恤。");
						break;
					case "寒冷":
						WriteTimer.println("今天天气"+askLifeSuggestion.lifeSuggestion.getDressing()
							+",建议穿棉衣、冬大衣、皮夹克、厚呢外套、呢帽、手套、羽绒服、皮袄。");					
						break;
					case "较冷":
						WriteTimer.println("今天天气"+askLifeSuggestion.lifeSuggestion.getDressing()
							+",建议穿毛衣、风衣、毛套装、西服套装");
						break;
					case "舒适":
						WriteTimer.println("今天天气"+askLifeSuggestion.lifeSuggestion.getDressing()
							+",建议穿单层棉麻面料的短套装、T恤衫、薄牛仔衫裤、休闲服、职业套装。");
						break;
					case "较舒适":
						WriteTimer.println("今天天气"+askLifeSuggestion.lifeSuggestion.getDressing()
							+",建议穿套装、夹衣、风衣、休闲装、夹克衫、西装、薄毛衣。");
						break;
					case "热":
						WriteTimer.println("今天天气"+askLifeSuggestion.lifeSuggestion.getDressing()
							+",建议穿单层棉麻面料的短套装、T恤衫、薄牛仔衫裤、休闲服、职业套装。");
						break;
					case "冷":
						WriteTimer.println("今天天气"+askLifeSuggestion.lifeSuggestion.getDressing()
							+",建议穿风衣、大衣、夹大衣、外套、毛衣、毛套装、西装、防寒服。");
						break;
					default:
						WriteTimer.println("嗯……我没什么建设性的建议。");
						break;
					}
					break;
				case 1:
					WriteTimer.println("今天"+askLifeSuggestion.lifeSuggestion.getCar_washing()+"洗车。");
					break;
				case 2:
					WriteTimer.println("今天"+askLifeSuggestion.lifeSuggestion.getFlu()+"感冒。");
					break;
				case 3:
					WriteTimer.println("今天"+askLifeSuggestion.lifeSuggestion.getSport()+"运动。");
					break;
				case 4:
					WriteTimer.println("今天"+askLifeSuggestion.lifeSuggestion.getTravel()+"旅行。");
					break;
				case 5:
					WriteTimer.println("今天紫外线"+askLifeSuggestion.lifeSuggestion.getUv()+"。");
					break;
				default:
					break;
				}
			}
		}
	}

	
	/**
	 * 提供服务（生活指数）
	 * @throws ErrorMessageException 
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws InvalidFormatException 
	 * @throws EncryptedDocumentException 
	 * */
	public void offerLifeSuggestion() throws  ErrorMessageException, InterruptedException, EncryptedDocumentException, InvalidFormatException {
		//1.对分析生活信息进行输出
		netanswer = false;
		try {
			analyzeLifeSuggestion();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
//			e1.printStackTrace();
			checkNet();
		}
		//2.获取所有天气信息
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
		//3.给出反馈
		lifeSuggestionReaction(askLifeSuggestion);
		
		
	}
	
	/**
	 * 主服务
	 * 进入服务分支的窗口
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
			//如果是第一次使用
			if(whetherFirstTime) {
				//1.抛出问题
				WriteTimer.println("那么，你找我有何贵干呢？");
				//2.显示按钮
				MainWindows.createButton("帮我查一下今天的天气:)", true, 1);
				MainWindows.createButton("我想知道最近的天气:)", true, 2);
				MainWindows.createButton("我今天……", false, 3);
				waitForIndex();
				whetherFirstTime=false;
			}
			//否则
			else {
				//1.抛出问题
				WriteTimer.println("还有什么想知道的吗？");
				//2.显示按钮
				MainWindows.createButton("帮我查一下今天的天气:)", true, 1);
				MainWindows.createButton("我想知道最近的天气:)", true, 2);
				MainWindows.createButton("我今天……", true, 3);
				MainWindows.createButton("拜拜！", false, 4);
				//3.接收选项
				waitForIndex();
			}
			//4.进入相应模块
			System.out.println(answer);
			if(answer.equals("1")) {
				WriteTimer.println("噢，好的，我明白了！");
				offerNowWeather();
			}
			else if(answer.equals("2")) {
				WriteTimer.println("噢，好的，我明白了！");
				offerDailyWeather();
			}
			else if(answer.equals("3")) {
				WriteTimer.println("噢，好的，我明白了！");
				offerLifeSuggestion();
			}
			else if(answer.equals("4")) {
				WriteTimer.println("那么再见啦！");
				System.exit(0);
			}
		}
		
	}
	
	/**
	 * 主调方法
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
			//解决输入法切换导致的白屏问题
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
			//初始化成功，开启服务
			demo.server();
		}
	}
}
