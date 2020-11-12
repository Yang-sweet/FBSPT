package pers.chhuai.storm.servlet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.chhuai.storm.service.bean.TestService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


@WebServlet(name = "DelTestServlet", urlPatterns = "/DelTestServlet")
public class DelTestServlet extends HttpServlet {
    private static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String testID=request.getParameter("testID");

        TestService ss = ac.getBean("testService", TestService.class);
        ss.deleteTest(testID);
        response.sendRedirect("myTest.jsp");

    }


}

