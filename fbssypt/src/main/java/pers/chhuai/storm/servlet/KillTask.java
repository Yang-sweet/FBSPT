package pers.chhuai.storm.servlet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.chhuai.storm.service.bean.UserService;
import pers.chhuai.storm.service.remote.RemoteCommandExec;
import pers.chhuai.storm.service.remote.impl.RemoteCommandExecImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "KillTask", urlPatterns = "/KillTask")

public class KillTask extends HttpServlet {
    private static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

//        ApplicationContext ac = (ApplicationContext)request.getSession().getAttribute("applicationContext");
//        UserService ss = ac.getBean("userService", UserService.class);
        String ApplicationId = request.getParameter("ApplicationId");
        System.out.println(ApplicationId);
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        RemoteCommandExec rc = ac.getBean("remoteExecutor", RemoteCommandExecImpl.class);
        try {
            rc.KillTask(ApplicationId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        response.sendRedirect("myMonitor.jsp");

    }

}
