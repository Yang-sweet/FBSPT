package pers.chhuai.storm.service.remote;

import ch.ethz.ssh2.Connection;
import pers.chhuai.storm.beans.Submission;

import java.io.InputStream;

public interface RemoteCommandExec {
    /**
     * 登录主机
     * @return
     *      登录成功返回true，否则返回false
     */
    public Connection login(String ip, String username, String pwd);
    /**
     * 远程执行shll脚本或者命令
     * @param cmd
     *      即将执行的命令
     * @return
     *      命令执行完后返回的结果值
     */
    public String execute(Connection conn, String cmd);
    /**
     * 解析脚本执行返回的结果集
     * @param in 输入流对象
     * @param charset 编码
     * @return
     *       以纯文本的格式返回
     */
    public String processStdout(InputStream in, String charset);

    /**
     * 提交topo到集群
]\

     * @param runTime
     * @return
     * @throws InterruptedException
     */
    public String runTopo(Submission sub, int runTime) throws InterruptedException;

    public String KillTask(String applicationId) throws InterruptedException;


    /**
     * 从HDFS读取结果
     * @param sub
     * @return
     */
    public String getResultFormHDFS(Submission sub);

    public String getResultFormHDFS(String id, String testID);

    /**
     * 提交状态
     * @param result
     * @return
     */
    public String submitState(String result);

    /**
     * 关闭连接
     */
    public void closeConnection();
}
