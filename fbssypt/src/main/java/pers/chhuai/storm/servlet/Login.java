package pers.chhuai.storm.servlet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pers.chhuai.storm.beans.User;
import pers.chhuai.storm.service.login.LoginService;
import pers.chhuai.storm.service.login.impl.LoginServiceImpl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 登录
 */
@WebServlet(name = "Login", urlPatterns = "/Login")
public class Login extends HttpServlet {
    // 初始化ac容器
    ApplicationContext ac = null;
    @Override
    public void init() throws ServletException {
        ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 获取请求信息
        String id=request.getParameter("uname");
        String pwd=request.getParameter("pwd");
        String type = request.getParameter("type");
        System.out.println(id + " : " + pwd + " : " + type);
        // 处理请求信息
        // 获取业务层对象
        LoginService ls =  new LoginServiceImpl();
        User user = ls.checkLoginService(id, pwd, type);
         System.out.println(user);
        // 响应处理结果
        if (user!=null) {
            HttpSession session = request.getSession();
            session.setAttribute("User", user);
            session.setAttribute("type", type);
            // 传入容器
            session.setAttribute("applicationContext", ac);

            response.sendRedirect("Main");
        } else {
            request.setAttribute("str", "用户名或密码错误");
            // 请求转发 重新登录
            request.getRequestDispatcher("/loginPage.jsp").forward(request, response);
            return;
        }

    }
}
