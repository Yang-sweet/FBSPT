package pers.chhuai.storm.service.schedule;
import pers.chhuai.storm.beans.Submission;

import java.net.*;
import java.io.*;

public class TaskThread implements Runnable {
	// 定义端口和IP地址
	static int PORT = 8080;
	static String IP = "127.0.0.1";
	
	Submission current_sub = null;
	String submission = null; 
	String echoMessage = null;
	
	public TaskThread(Submission current_sub) {
		this.current_sub = current_sub;
		submission = current_sub.toString();
	}
	
	@Override
	public void run() {
		// 定义客户端输入和服务器响应	
		try {
			// 将键盘输入读入缓冲区stdIn
			ByteArrayInputStream stdIn = new ByteArrayInputStream(submission.getBytes());
			//BufferedReader stdIn = new BufferedReader(new InputStreamReader(submission.getBytes()));
			// 定义客户端套接字，发送到PORT = 3389
			Socket socket = new Socket(IP, PORT);//用于创建一个链接，向指定的IP地址上指定的端口的服务器端程序发送连接请求
			System.out.println("开始向8080端口发送任务信息");
			
			// 客户端套接字上的getOutputStream方法得到的输出流是发给服务器端的数据
			OutputStream outStream = socket.getOutputStream();
			
			// 定义向服务器发送数据流的对象out
			PrintWriter out = new PrintWriter(outStream);
			if (stdIn.toString()!=null) {
				// 向服务器发送任务信息
	            out.println(submission);
				//System.out.println(submission);
				// flush方法的作用是刷新缓冲区
				out.flush();
			}
			socket.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}				
	}		
}
