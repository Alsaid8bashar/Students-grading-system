package com.example.studentsystemserveltandjsp.Service;

import com.example.studentsystemserveltandjsp.Dao.LoginDao;
import com.example.studentsystemserveltandjsp.Model.LoginInfo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LoginService {
    private LoginDao loginDao;

    public LoginService() {
        try {
            loginDao = new LoginDao();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public LoginInfo authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginInfo loginBean = new LoginInfo();
        loginBean.setUsername(username);
        loginBean.setPassword(password);

        LoginInfo user= null;
        try {
            user = loginDao.checkCredentials(loginBean);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
            if (user!=null) {
                return user;
            }
            else{
               return null;
            }
        }

}
