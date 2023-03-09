package com.example.studentsystemserveltandjsp.Dao;


import com.example.studentsystemserveltandjsp.Model.LoginInfo;

import java.sql.SQLException;

public interface ILoginInfo {

    public LoginInfo checkCredentials(LoginInfo loginInfo) throws SQLException;

    public void addLogin(LoginInfo loginInfo) throws SQLException ;
}
