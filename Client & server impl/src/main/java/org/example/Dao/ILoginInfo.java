package org.example.Dao;

import org.example.Model.LoginInfo;

import java.sql.SQLException;

public interface ILoginInfo {

    public LoginInfo checkCredentials(String username ,String password) throws SQLException;

    public void addLogin(LoginInfo loginInfo) throws SQLException ;
}
