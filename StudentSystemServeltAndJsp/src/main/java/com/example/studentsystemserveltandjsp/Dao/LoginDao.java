package com.example.studentsystemserveltandjsp.Dao;


import com.example.studentsystemserveltandjsp.Model.LoginInfo;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao implements ILoginInfo {
    private DataSource ds;

    public LoginDao() throws SQLException {
        this.ds = DbConnection.getDataSource();
    }


    public LoginInfo checkCredentials(LoginInfo loginInfo) throws SQLException {
        String sql = "SELECT * FROM login where username=? and password =? ;";
          try (Connection conn = ds.getConnection()) {
            PreparedStatement pStmt = conn.prepareStatement(sql);
             pStmt.setString(1, loginInfo.getUsername());
             pStmt.setString(2, loginInfo.getPassword());
            ResultSet rs = pStmt.executeQuery();
            if (rs.next()) {
                LoginInfo login = new LoginInfo();
                login.setUsername(rs.getString("username"));
                login.setPassword(rs.getString("password"));
                login.setUserId(rs.getInt("student_id"));
                return login;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void addLogin(LoginInfo loginInfo) throws SQLException {
        try (Connection conn = ds.getConnection())  {
            String sql = "insert into login value(default,?,?,?);";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, loginInfo.getUserId());
            pStmt.setString(2, loginInfo.getUsername());
            pStmt.setString(3, loginInfo.getPassword());

            pStmt.executeUpdate();
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}
