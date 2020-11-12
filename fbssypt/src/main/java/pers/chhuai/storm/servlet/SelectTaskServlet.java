package pers.chhuai.storm.servlet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.chhuai.storm.beans.Application;
import pers.chhuai.storm.beans.Test;
import pers.chhuai.storm.service.bean.AppService;
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


@WebServlet(name = "SelectTaskServlet", urlPatterns = "/SelectTaskServlet")
public class SelectTaskServlet extends HttpServlet {
    private static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String[] id={request.getParameter("id")};
        String[] FinalSatatus={request.getParameter("FinalSatatus")};


        Map<String,String[]> conditions=new HashMap();
        if(id!=null||!"".equals(id)){
            conditions.put("id",id);
        }
        if(FinalSatatus!=null||!"".equals(FinalSatatus)){
            conditions.put("FinalSatatus",FinalSatatus);
        }

        AppService as = ac.getBean("appService", AppService.class);
//        List<Test> testList=ss.selectTest(testID,testName);
        List<Application> appList=as.selectTask(conditions);
        request.setAttribute("appList",appList);

        request.getRequestDispatcher("/myMonitor.jsp").forward(request,response);
//        response.sendRedirect("myTest.jsp");

    }


}

