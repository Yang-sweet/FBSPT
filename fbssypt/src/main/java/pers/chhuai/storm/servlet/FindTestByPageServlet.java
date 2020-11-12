package pers.chhuai.storm.servlet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.chhuai.storm.beans.Page;
import pers.chhuai.storm.beans.Test;
import pers.chhuai.storm.service.bean.TestService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


@WebServlet(name = "FindTestByPageServlet", urlPatterns = "/FindTestByPageServlet")
public class FindTestByPageServlet extends HttpServlet {
    private static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String currentPge=request.getParameter("currentPage");
        String rows=request.getParameter("rows");
        if(currentPge==null||"".equals(currentPge)){
            currentPge="1";
        }
        if(rows==null||"".equals(rows)){
            rows="2";
        }
        //调用service
        ApplicationContext ac = (ApplicationContext)request.getSession().getAttribute("applicationContext");
        TestService ss = ac.getBean("testService", TestService.class);
        Page<Test> pb=ss.findTestByPage(currentPge,rows);
        request.setAttribute("pb",pb);
        request.getRequestDispatcher("/myTest.jsp").forward(request,response);


//        response.sendRedirect(request.getContextPath()+"myTest.jsp");




    }


}
