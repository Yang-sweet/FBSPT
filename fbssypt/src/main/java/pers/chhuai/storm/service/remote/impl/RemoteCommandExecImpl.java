package pers.chhuai.storm.service.remote.impl;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.sql.SQLTransactionRollbackException;

import ch.ethz.ssh2.ChannelCondition;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import pers.chhuai.storm.beans.Application;
import pers.chhuai.storm.beans.Jobinfo;
import pers.chhuai.storm.beans.Submission;
import pers.chhuai.storm.service.remote.RemoteCommandExec;
import pers.chhuai.storm.service.schedule.TaskMonitor;
public class RemoteCommandExecImpl implements RemoteCommandExec {
    private final Logger log = LoggerFactory.getLogger(RemoteCommandExecImpl.class);
    private static String DEFAULTCHART ="UTF-8";
    private String ip;
    private String username;
    private String password;
    private String tmpLocation;
    private Connection conn;
    private static String REMOTE_URL = "/export/servers/upload/";

    private String charset = Charset.defaultCharset().toString();
    private static final int TIME_OUT = 1000 * 5 * 60;

    public RemoteCommandExecImpl(String ip, String username, String password, String tmpLocation) {
        this.ip = ip;
        this.username = username;
        this.password = password;
        this.tmpLocation = tmpLocation;
		this.conn = login(ip, username, password);
	}


    /**
     * 登录主机
     * @return
     *      登录成功返回true，否则返回false
     */
    public Connection login(String ip,
                            String userName,
                            String userPwd){

        boolean flg=false;
        Connection conn = null;
        try {
            conn = new Connection(ip);
            conn.connect();//连接
            flg=conn.authenticateWithPassword(userName, userPwd);//认证
            if(flg){
                log.info("=========login successfully========="+conn);
                return conn;
            }
        } catch (IOException e) {
            log.error("=========login failed========="+e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 远程执行shll脚本或者命令
     * @param cmd
     *      即将执行的命令
     * @return
     *      命令执行完后返回的结果值
     */
    public String execute(Connection conn, String cmd){
        String result="";

        InputStream stdOut = null;
        InputStream stdErr = null;
        String outStr = "";
        String outErr = "";
        int ret = -1;

        try {
            if(conn !=null){
            	Session session = conn.openSession();
                session.execCommand(cmd);//执行命令
//                as.addApplication(ApplicationId, Name,ApplicationType,State,FinalStatus,StartTime,FinishTime);

                stdOut = new StreamGobbler(session.getStdout());
                outStr = processStream(stdOut, charset);

                stdErr = new StreamGobbler(session.getStderr());
                outErr = processStream(stdErr, charset);

                session.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);

                System.out.println("outStr=" + outStr);
                System.out.println("outErr=" + outErr);

                ret = session.getExitStatus();
                System.out.println("ret="+ret);
                if(ret==0){
                    result="已完成";
                }else {
                    result="错误";
                }



//                result=processStdout(session.getStdout(),DEFAULTCHART);
//                //如果为得到标准输出为空，说明脚本执行出错了
//                if(StringUtils.isBlank(result)){
//                    log.info("得到标准输出为空,链接conn:"+conn+",执行的命令："+cmd);
//                    result=processStdout(session.getStderr(),DEFAULTCHART);
//                }else{
//                    log.info("执行命令成功,链接conn:"+conn+",执行的命令："+cmd);
//                }
                session.close();
            }
        }
//        catch (IOException e) {
//            log.info("执行命令失败,链接conn:"+conn+",执行的命令："+cmd+"  "+e.getMessage());
//            e.printStackTrace();
//        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        //return ret;
    }

    public String exegetresults(Connection conn, String cmd){
        String hdfsresult="";

        InputStream stdOut = null;
        InputStream stdErr = null;
        String outStr = "";
        String outErr = "";
        int ret = -1;

        try {
            if(conn !=null){
                Session session = conn.openSession();
                session.execCommand(cmd);//执行命令
                stdOut = new StreamGobbler(session.getStdout());
                outStr = processStream(stdOut, charset);

                stdErr = new StreamGobbler(session.getStderr());
                outErr = processStream(stdErr, charset);

                session.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);

                System.out.println("outStr=" + outStr);
                System.out.println("outErr=" + outErr);
                hdfsresult=outStr;
                System.out.println("hdfsre="+hdfsresult);
                ret = session.getExitStatus();

                session.close();
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        return hdfsresult;
        //return ret;
    }







    /**
     * 解析脚本执行返回的结果集
     * @param in 输入流对象
     * @param charset 编码
     * @return
     *       以纯文本的格式返回
     */
     public String processStdout(InputStream in, String charset){
         InputStream  stdout = new StreamGobbler(in);
         StringBuffer buffer = new StringBuffer();;
         try {
             @SuppressWarnings("resource")
			 BufferedReader br = new BufferedReader(new InputStreamReader(stdout,charset));
             String line=null;
             while((line=br.readLine()) != null){
                 buffer.append(line+"\n");
             }
         } catch (UnsupportedEncodingException e) {
             log.error("解析脚本出错："+e.getMessage());
             e.printStackTrace();
         } catch (IOException e) {
             log.error("解析脚本出错："+e.getMessage());
             e.printStackTrace();
         }
         return buffer.toString();
     }


    public String runTopo(Submission sub, int runTime) throws InterruptedException {
        String id = sub.getId();
        String testID = sub.getTestID();
        String jarName=sub.getJobname()+"-1.0-SNAPSHOT.jar";

//        String jarName = id + "-" + testID+".jar";
//        String submitTopo = "hadoop jar "+ REMOTE_URL + jarName+" JobMain" ;
        String submitTopo = "hadoop jar "+ REMOTE_URL + jarName+" hdfs://node01:8020/test1/input "+"hdfs://node01:8020/test1/"+id+"/output-"+testID ;
        System.out.println(submitTopo);
        //String killTopo = "yarn kill "+jarName;
        // 提交topo命令
        //System.out.println(submitTopo);
        // topo提交结果
        String submitResult = execute(conn, submitTopo);
        sub.updateState(TaskMonitor.ACCEPTED_STATE, "正在判断");

        // 更新提交结果
       // sub.updateState(submitState(submitResult), submitResult);
//        if (sub.getState().equals(TaskMonitor.RUNNING_STATE)) { // 提交成功
//            // 定时运行，时间为runTime秒
//            Thread.sleep(runTime*1000);
//            // 杀死topo
//            execute(conn, killTopo);
//        }
        closeConnection();
        return submitResult;
    }




    public String KillTask(String applicationId) throws InterruptedException {

         String killcmd = "yarn application -kill "+applicationId;

        String submitResult = execute(conn,killcmd);
        //sub.updateState(TaskMonitor.ACCEPTED_STATE, "被杀死");

        // 更新提交结果
        //sub.updateState(submitState(submitResult), submitResult);
//        if (sub.getState().equals(TaskMonitor.RUNNING_STATE)) { // 提交成功
//            // 定时运行，时间为runTime秒
//            Thread.sleep(runTime*1000);
//            // 杀死topo
//            execute(conn, killTopo);
//        }
        closeConnection();
        return submitResult;
    }



     // 从HDFS读取数据
     public String getResultFormHDFS(String id, String  testID) {
         //String getResult = "hadoop fs -cat /"+id+"/"+"test"+testID+"/1.txt";
         String getResult = "hadoop fs -cat /test"+testID+"/"+id+"/output-"+testID+"/part*";
         return execute(conn, getResult);
     }
     // 从HDFS读取数据
     public String getResultFormHDFS(Submission sub) {
         //String getResult = "hadoop fs -cat /"+sub.getId()+"/"+"test"+sub.getTestID()+"/1.txt";
         String getResult = "hadoop fs -cat /wordcount_out/part*";


         //return execute(conn, getResult);
         return exegetresults(conn,getResult);
     }


   public void hdfsRename(String id, int testID) throws IOException, InterruptedException, URISyntaxException {

        	//获取文件系统
        	Configuration configuration = new Configuration();
//        	FileSystem fs = FileSystem.get(new URI("hdfs://node01:8020/wordcount_out"), configuration, "hadoop");
            FileSystem fs = FileSystem.get(new URI("hdfs://node01:8020"), configuration);
       //修改文件名称
        	fs.rename(new Path("/wordcount_out"), new Path("wordcount_out-"+id+"test"+testID));
         	//关闭资源
        	fs.close();
         }



     public String submitState(String result) {
    	 if (result.substring(0, 5).equals(TaskMonitor.ERROR_STATE)) { // 错误
    		 return TaskMonitor.ERROR_STATE;
    	 } else if (result.substring(0, 9).equals(TaskMonitor.EXCEPTION_STATE)) { // 抛出异常
    		 return TaskMonitor.EXCEPTION_STATE;
    	 } else { // 提交成功
    		 return TaskMonitor.RUNNING_STATE;
    	 }
     }

     public void closeConnection() {
    	 conn.close();
     }

    private String processStream(InputStream in, String charset) throws Exception {
        byte[] buf = new byte[1024];
        StringBuilder sb = new StringBuilder();
        while (in.read(buf) != -1) {
            sb.append(new String(buf, charset));
        }
        return sb.toString();
    }

}

