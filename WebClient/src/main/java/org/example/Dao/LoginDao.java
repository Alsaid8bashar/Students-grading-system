package org.example.Dao;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.example.Model.LoginInfo;

import javax.sql.DataSource;
import java.sql.*;

public class LoginDao implements ILoginInfo {
    private DataSource ds;

    public LoginDao() throws SQLException {
        this.ds = DbConnection.getDataSource();
    }


    public LoginInfo checkCredentials(String username, String password) throws SQLException {
        String sql = "SELECT * FROM login where username=? and password =? ;";
          try (Connection conn = ds.getConnection()) {
            PreparedStatement pStmt = conn.prepareStatement(sql);
             pStmt.setString(1, username);
             pStmt.setString(2, password);
            ResultSet rs = pStmt.executeQuery();
            if (rs.next()) {
                LoginInfo loginInfo = new LoginInfo();
                loginInfo.setUsername(rs.getString("username"));
                loginInfo.setPassword(rs.getString("password"));
                loginInfo.setUserId(rs.getInt("student_id"));
                return loginInfo;
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
