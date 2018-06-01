/**
* Team Zhinengxianfeng Hebei Normal University
* FileName: Write.java
* 定时输出语句，起到一种思考等待的仿真效果
*
* @author Deng Yang
    * @Date    2018-05-04
* @version 0.00
*/

package com.janborn.www;

import java.awt.Graphics2D;
import java.util.Date;
import java.util.Random;

public final class WriteTimer {
	public WriteTimer() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 定时输出
	 * 
	 * @param String words 需要输出的字符串语句
	 * @return none
	 */
	public static void println(String words) {
		Date begin = new Date();
		Date end = new Date();
		long mintime = 1200;
		long maxtime = 1800;
		Random random = new Random();
		long timer = (long)(random.nextInt()*maxtime%(maxtime-mintime+1) + mintime);
		while(end.getTime()-begin.getTime()<timer) {
			end= new Date();
		}
		Communicate.addRobot(words);
	}

}
