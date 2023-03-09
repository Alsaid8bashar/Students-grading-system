package com.example.StudentsGradingSystem.Dao;

import com.example.StudentsGradingSystem.Model.Course;
import com.example.StudentsGradingSystem.Model.LoginInfo;

import java.sql.SQLException;

public interface LoginDao {



    public LoginInfo checkCredentials(String username , String password) throws SQLException;

    int save(LoginInfo loginInfo);
}
