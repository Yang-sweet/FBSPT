package pers.chhuai.storm.service.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class HdfsUtil {
    /**
     * hdfs的访问路径 ， 需要改成从配置文件读取
     */
    private static FileSystem fs = null;
    static{
        //构造一个配置参数封装对象
        Configuration conf = new Configuration();
        //构造一个hdfs的客户端
        try {
            fs= FileSystem.get(new URI(conf.get("hdfs://192.168.8.115:8020")), conf);
        } catch (IOException e) {
            e.printStackTrace();
        }  catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件：从本地上传文件到hdfs中
     * @param sourcePath 本地文件路径
     * @param targetPath hdfs地址
     * @throws IllegalArgumentException
     * @throws IOException
     */
    public static void uploadFile(String sourcePath,String targetPath) throws IllegalArgumentException, IOException{
        fs.copyFromLocalFile(new Path(sourcePath), new Path(targetPath));
    }

    /**
     * 上传文件：从输入流传到hdfs
     * @param inputStream 文件输出流
     * @param hdfsTargetPath 文件输出到hdfs的路径
     * @throws IllegalArgumentException
     * @throws IOException
     */
    public static void uploadFile(FileInputStream inputStream, String hdfsTargetPath) throws IllegalArgumentException, IOException{
        FSDataOutputStream out = fs.create(new Path(hdfsTargetPath));
        //流对拷
        IOUtils.copyBytes(inputStream, out, new Configuration());
        IOUtils.closeStream(inputStream);
        IOUtils.closeStream(out);
    }


    /**
     * 文件下载：从hdfs中下载文件到本地
     * @param hdfsPath hdfs文件路径
     * @param localPath 本地路径
     * @throws IllegalArgumentException
     * @throws IOException
     */
    public static void downloadFile(String hdfsPath,String localPath) throws IllegalArgumentException, IOException{
        fs.copyToLocalFile(
                false, //是否删除原文件
                new Path(hdfsPath), //源
                new Path(localPath),//目标
                true//目标路径是否是本地文件系统
        );
    }

    /**
     * 文件下载：从hdfs下载到输出流
     * @param hdfsFilePath hdfs文件路径
     * @param fileOutputStream 输出流
     * @throws IllegalArgumentException
     * @throws IOException
     */
    public static void downloadFile(String hdfsFilePath,FileOutputStream fileOutputStream) throws IllegalArgumentException, IOException{
        FSDataInputStream in = fs.open(new Path(hdfsFilePath));
        //流对拷
        IOUtils.copyBytes(in,fileOutputStream,new Configuration());
        IOUtils.closeStream(in);
        IOUtils.closeStream(fileOutputStream);
    }

    /**
     * 创建文件夹
     * @param hdfsDirPath 要创建的hdfs文件夹路径
     * @throws IOException
     */
    public static void mkdir(String hdfsDirPath) throws IOException {
        fs.mkdirs(new Path(hdfsDirPath));
    }

    /**
     * 判断hdfs文件夹是否存在
     * @param hdfsDirPath hdfs文件夹路径
     * @return
     */
    public static boolean exists(String hdfsDirPath) throws IOException {
        boolean res = fs.exists(new Path(hdfsDirPath));
        return res;
    }

    /**
     * 列出指定目录下所有文件
     * @param hdfsDirPath hdfs目录
     */
    public static void showAllFile(String hdfsDirPath) throws IOException {
        //能列出文件和文件夹信息
        FileStatus[] listStatus = fs.listStatus(new Path(hdfsDirPath));
        for(FileStatus f:listStatus){
            String type="-";
            if(f.isDirectory()) type="d";
            System.out.println(type+"\t"+f.getPath().getName());
        }
    }

    /**
     * 删除文件或者文件夹
     * @param hdfsPath 地址
     * @throws IOException
     */
    public static void delete(String hdfsPath) throws IOException {
        fs.delete(new Path(hdfsPath),true);
    }

    /**
     * 读取文件内容
     */
    public static String cat( String remoteFilePath) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Path remotePath = new Path(remoteFilePath);
        FSDataInputStream in = fs.open(remotePath);
        BufferedReader d = new BufferedReader(new InputStreamReader(in));
        String line = null;
        while ((line = d.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append("\n");
        }
        d.close();
        in.close();
        return stringBuilder.toString();
    }



    /**
     * 回收HdfsUtil这个类时候再关连接就行了。。。
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        fs.close();
    }



}
