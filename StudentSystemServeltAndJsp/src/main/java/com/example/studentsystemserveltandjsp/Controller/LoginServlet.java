package com.example.studentsystemserveltandjsp.Controller;

import com.example.studentsystemserveltandjsp.Dao.LoginDao;
import com.example.studentsystemserveltandjsp.Model.LoginInfo;
import com.example.studentsystemserveltandjsp.Service.LoginService;
import com.mysql.cj.Session;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private LoginService loginService;
    RequestDispatcher dispatcher;

    public void init() {
            loginService = new LoginService();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/LoginPage.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginInfo loginInfo=loginService.authenticate(request, response);
        if(loginInfo!=null){
            HttpSession httpSession=request.getSession();
            httpSession.setAttribute("user",loginInfo);

            HttpServletRequest newRequest = new HttpServletRequestWrapper(request) {
                @Override
                public String getMethod() {
                    return "GET";
                }
            };
            RequestDispatcher rd = request.getRequestDispatcher("/HomeServlet");
            rd.forward(newRequest, response);
        }
        else{
            doGet(request,response);
        }
    }

}
