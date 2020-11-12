package pers.chhuai.storm.service.remote;

public interface RemoteUpload {
    /**
     * 上传文件
     * @param source 本地url
     * @param remote 远程地址
     */
    public void upload(String source, String remote);

    /**
     * upload()的实现方法
     * @param localFile
     * @param remoteTargetDirectory
     */
    public void putFile(String localFile, String remoteTargetDirectory);

    /**
     * 远程登录
     * @return
     */
    public boolean isAuth();

    /**
     * 上述方法实现
     * @param user
     * @param password
     * @return
     */
    public boolean isAuthedWithPassword(String user, String password);
}
