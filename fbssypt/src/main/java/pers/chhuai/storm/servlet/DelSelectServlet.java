package pers.chhuai.storm.servlet;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.chhuai.storm.beans.Submission;
import pers.chhuai.storm.beans.Test;
import pers.chhuai.storm.beans.User;
import pers.chhuai.storm.dao.impl.TestDaoImpl;
import pers.chhuai.storm.service.bean.SubmissionService;
import pers.chhuai.storm.service.bean.TestService;
import pers.chhuai.storm.service.bean.UserService;
import pers.chhuai.storm.service.remote.impl.RemoteUploadImpl;
import pers.chhuai.storm.service.schedule.TaskMonitor;
import pers.chhuai.storm.service.schedule.TaskThread;
import pers.chhuai.storm.utils.DeleteFileInTmp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(name = "DeSelectServlet", urlPatterns = "/DeSelectServlet")
public class DelSelectServlet extends HttpServlet {
    private static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取所有id
        String[] seids=request.getParameterValues("seid");
        //调用service删除

        ApplicationContext ac = (ApplicationContext)request.getSession().getAttribute("applicationContext");
        TestService ss = ac.getBean("testService", TestService.class);
        ss.deleteSelectTest(seids);
        response.sendRedirect(request.getContextPath()+"myTest.jsp");

    }


}

