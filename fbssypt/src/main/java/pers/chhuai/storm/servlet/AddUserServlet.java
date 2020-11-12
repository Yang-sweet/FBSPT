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


@WebServlet(name = "AddUserServlet", urlPatterns = "/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    private static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

        ApplicationContext ac = (ApplicationContext)request.getSession().getAttribute("applicationContext");
        UserService ss = ac.getBean("userService", UserService.class);
        //User user = (User)session.getAttribute("User");
        // List<User> userList = (List<User>)request.getSession().getAttribute("user_add");
        String user_ID = request.getParameter("id");
        String user_pwd = request.getParameter("pwd");
        String user_name = request.getParameter("name");
        String user_gender= request.getParameter("gender");
        String user_major = request.getParameter("major");
        String  user_type= request.getParameter("type");
        ss.addUser(user_ID,user_pwd,user_name,user_gender,user_major,"1");
        response.sendRedirect("allUser.jsp");

    }


}

