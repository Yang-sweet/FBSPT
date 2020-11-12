package pers.chhuai.storm.service.schedule;

import pers.chhuai.storm.beans.Submission;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class SubmitThread implements Runnable{
	// 定义端口和IP地址
	static int PORT = 8080;
	static String IP = "127.0.0.1";

	
	// 监听任务请求，并提交到阻塞队列
	@Override
	public void run() {
		ServerSocket listenSocket;
		try {
			listenSocket = new ServerSocket(PORT);
			while(true){
				 Socket socket = listenSocket.accept();
				 // 客户端套接字上的getInputStream方法得到的输入流是从服务器端发回的数据
				 InputStream inStream = socket.getInputStream();
				 // 将服务器端转发到PORT=3390的数据读入缓冲区in
				 BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
				 if (in.toString()!=null) {
					String submission = in.readLine();

					Submission currentSub = new Submission(submission);


					// 同步锁--->提交队列
					synchronized (this) {
						System.out.println("加入阻塞队列");

						TaskList.SubmittedQueue.add(currentSub);

					}		
        			//TimeDelay.delay(200);
					socket.close();
				 }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
