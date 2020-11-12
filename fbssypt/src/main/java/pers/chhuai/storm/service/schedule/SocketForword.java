package pers.chhuai.storm.service.schedule;
import java.net.ServerSocket;
import java.net.Socket;


public class SocketForword implements Runnable {
	static int PORT = 3389;
	
	@Override
	// 多线程
	public void run() {
		// TODO Auto-generated method stub
		try {
			@SuppressWarnings("resource")
			ServerSocket listenSocket = new ServerSocket(PORT);
	        Socket socket = null;
	        System.out.println("服务器正在监听端口号<" + PORT + ">");
	        while(true){
	            socket = listenSocket.accept(); //接收客户端连接，返回一个新的socket
	            // 创建转发
	            ForwardThread forwardThread = new ForwardThread(socket);
	            forwardThread.start();
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
    }	
    
}

