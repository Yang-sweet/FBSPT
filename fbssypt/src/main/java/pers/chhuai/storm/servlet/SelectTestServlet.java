package pers.chhuai.storm.servlet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.chhuai.storm.beans.Test;
import pers.chhuai.storm.service.bean.TestService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(name = "SelectTestServlet", urlPatterns = "/SelectTestServlet")
public class SelectTestServlet extends HttpServlet {
    private static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String[] testID={request.getParameter("testID")};
        String[] title={request.getParameter("testName")};


        Map<String,String[]> conditions=new HashMap();
        if(testID!=null||!"".equals(testID)){
            conditions.put("testID",testID);
        }
        if(title!=null||!"".equals(title)){
            conditions.put("title",title);
        }

        TestService ss = ac.getBean("testService", TestService.class);
//        List<Test> testList=ss.selectTest(testID,testName);
        List<Test> testList=ss.selectTest(conditions);
        System.out.println("testList="+testList);
        request.setAttribute("testList",testList);

        request.getRequestDispatcher("/myTest.jsp").forward(request,response);
//        response.sendRedirect("myTest.jsp");

    }


}

