package pers.chhuai.storm.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.chhuai.storm.beans.Jobinfo;
import pers.chhuai.storm.beans.Submission;
import pers.chhuai.storm.service.bean.JobService;
import pers.chhuai.storm.service.bean.SubmissionService;
import pers.chhuai.storm.service.remote.impl.RemoteUploadImpl;
import pers.chhuai.storm.service.schedule.TaskMonitor;
import pers.chhuai.storm.service.schedule.TaskThread;
import pers.chhuai.storm.utils.DeleteFileInTmp;



@WebServlet(name = "Upload", urlPatterns = "/Upload")
public class
Upload extends HttpServlet {
    private static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    private static final long serialVersionUID = 1L;
    private static String REMOTE_URL = "/export/servers/upload";
    // 文件缓存目录
    private static final String UPLOAD_DIRECTORY = "upload";

    // 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String id = null;
        String testID = null;
        String testName = null;
        String jobname=null;
        String date=null;

        // 检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 如果不是则停止
            PrintWriter writer = response.getWriter();

            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;

        }


        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // 中文处理
        upload.setHeaderEncoding("UTF-8");

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        //file.separator这个代表系统目录中的间隔符/  getRealPath项目在服务器的绝对路径
        String uploadPath = this.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;


        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            // 解析请求，将表单中每个输入项封装成一个FileItem对象
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    //判断输入的类型是 普通输入项 还是文件
                    if (item.isFormField()) {
                        // 是普通输入项
                        if ("id".equals(item.getFieldName())) {
                            id = new String(item.getString("UTF-8"));
                        }
                        // 测试id
                        if ("testID".equals(item.getFieldName())) {
                            testID = new String(item.getString("UTF-8"));
                        }
                        // 测试名
                        if ("testName".equals(item.getFieldName())) {
                            testName = new String(item.getString("UTF-8"));
                        }
                    }
                }
            }
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {



                        String fileName = new File(item.getName()).getName();
                        //String jobName=fileName.substring(0,fileName.length()-17);

                        //String fileName = id + "-" +new File(item.getName()).getName()+"-"+testID ; //用户名-测试编号.jar
                        //String fileName = id +"-"+testID+".jar";
                        String filePath = uploadPath + File.separator + fileName;
                        //System.out.println("delete file in tmp: "+ DeleteFileInTmp.deleteFile(filePath));
                        File storeFile = new File(filePath);
                        // 在控制台输出文件的上传路径
                        System.out.println(filePath);
                        // 保存文件到硬盘
                        item.write(storeFile);
                        request.setAttribute("message", "文件已上传至服务器");

                        JobService js = ac.getBean("jobService", JobService.class);
                        Jobinfo currentJob = js.Jobs(testID, testName, fileName, date, TaskMonitor.SUBMITTED_STATE, "等待运行",id);



//
//
//                        SubmissionService ss = ac.getBean("submissionService", SubmissionService.class);
//                        Submission currentSub = ss.subTest(id, testID, testName, filePath,
//                                TaskMonitor.SUBMITTED_STATE, "等待运行");
                        // 任务调度入口c
                        {
                            System.out.println("uploaded to server");
                            // 提交jar到集群
                            RemoteUploadImpl rp = ac.getBean("uploader", RemoteUploadImpl.class);
                            rp.upload(filePath, REMOTE_URL);

                            ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                                    new ArrayBlockingQueue<Runnable>(10));


//                            TaskThread uploadTask = new TaskThread(currentSub);//Runable
//                            Thread task = new Thread(uploadTask, "task");
//                            task.start();
                            response.sendRedirect(request.getContextPath()+"/mySubmission.jsp");
                        }
                    }
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("message", "错误信息: " + ex.getMessage());
        }
        return;
    }
}