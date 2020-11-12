package pers.chhuai.storm.service.schedule;
import java.io.*;
import java.net.*;

public class ForwardThread extends Thread{
	static String IP = "127.0.0.1";
	static int PORT = 3389;
	private Socket socket = null;
	private int no;
	public ForwardThread (Socket Socket) throws UnknownHostException, IOException  {
		this.socket = Socket;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String currentSubInfo = null;
		InputStream input = null;
		OutputStream output = null;
		BufferedReader bf = null;
		PrintWriter out = null;
		try {
			@SuppressWarnings("resource")
			Socket forward_socket = new Socket(IP, PORT);
			// 来自3389的输入流
			System.out.println("接收任务信息");
			input = socket.getInputStream();
			output = forward_socket.getOutputStream();	
			bf = new BufferedReader(new InputStreamReader(input));
			out = new PrintWriter(output);
			currentSubInfo=bf.readLine();
			out.println(currentSubInfo);
			System.err.println(currentSubInfo);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
			 System.out.println("客户端" + no + "已断开与服务器的连接");
		} finally {
			try {
				if (input != null)
				    input.close();
				if (output != null)
					output.close();
				if (bf != null)
					bf.close();
				if (out != null)
					out.close();
				if (socket != null)
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}	
}

