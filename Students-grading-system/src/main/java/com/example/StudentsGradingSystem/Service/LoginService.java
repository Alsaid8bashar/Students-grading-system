package com.example.StudentsGradingSystem.Service;


import com.example.StudentsGradingSystem.Dao.LoginDao;
import com.example.StudentsGradingSystem.Model.LoginInfo;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;


@Service
public class LoginService {

    private final LoginDao loginDao;

    public LoginService(LoginDao loginDao) {
        this.loginDao = loginDao;
    }


    public LoginInfo authenticate(String username, String password) throws  SQLException {
           return loginDao.checkCredentials(username, password);

    }
}
