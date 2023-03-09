package com.example.StudentsGradingSystem.Maper;

import com.example.StudentsGradingSystem.Model.LoginInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginMapper implements RowMapper<LoginInfo> {
    @Override
    public LoginInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        final LoginInfo loginInfo = new LoginInfo( rs.getString("username"), rs.getString("password"),rs.getInt("student_id"));
        return loginInfo;
    }
}
