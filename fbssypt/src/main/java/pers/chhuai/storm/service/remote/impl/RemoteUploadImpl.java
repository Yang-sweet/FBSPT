package pers.chhuai.storm.service.remote.impl;


import java.io.IOException;  
import ch.ethz.ssh2.Connection;  
import ch.ethz.ssh2.SCPClient;
import pers.chhuai.storm.service.remote.RemoteUpload;
public class RemoteUploadImpl implements RemoteUpload {
    private String ip;
    private Integer port;
    private String username;//  远程Linux服务器的用户名
    private String password;// 远程Linux服务器的登录密码
    private Connection connection; // 不能用静态
    private Boolean usePassword = true;// 使用用户名和密码来进行登录验证
	public RemoteUploadImpl(String ip, int port, String username, String password) {
		this.ip = ip;
		this.port = port;
		this.username = username;
		this.password = password;
		this.connection = new Connection(ip, port);
	}

	/**
     * ssh用户登录验证，使用用户名和密码来认证 
     *  
     * @param user 
     * @param password 
     * @return 
     */  
    
    
    public void upload(String source, String remote) {  
    	try {   
    		putFile(source, remote);  // 报错
    	} catch (Exception e) {  
    		e.printStackTrace();  
    	}  
    }

	@Override
	public void putFile(String localFile, String remoteTargetDirectory) {
    	try {  
    		connection.connect();  
    		boolean isAuthed = isAuth();  
    		if (isAuthed) {  
    			SCPClient scpClient = connection.createSCPClient();  
    			scpClient.put(localFile, remoteTargetDirectory);   // 报错
    			System.out.println("File uploaded successful");
    		} else {  
    			System.out.println("Upload falied, please try again!\n");
    		}  
    	} catch (Exception ex) {  
    		ex.printStackTrace();  
    	} finally {  
    		connection.close();  
    	}  
    }  
    
    public boolean isAuth() {  
    	if (usePassword) {  
    		return isAuthedWithPassword(username, password);
    	}
    	return false;
    }  
    
    
    public boolean isAuthedWithPassword(String user, String password) {  
        try {  
            return connection.authenticateWithPassword(user, password);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return false;  
    }

	@Override
	public String toString() {
		return "RemoteUploadImpl{" +
				"ip='" + ip + '\'' +
				", port=" + port +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", connection=" + connection +
				", usePassword=" + usePassword +
				'}';
	}
}
