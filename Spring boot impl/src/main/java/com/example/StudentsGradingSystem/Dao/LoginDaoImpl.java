package com.example.StudentsGradingSystem.Dao;

import com.example.StudentsGradingSystem.Maper.CourseRowMapper;
import com.example.StudentsGradingSystem.Maper.LoginMapper;
import com.example.StudentsGradingSystem.Model.LoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class LoginDaoImpl implements LoginDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LoginDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public LoginInfo checkCredentials(String username, String password) throws SQLException {
        String sql = "SELECT * FROM login where username=? and password =? ;";
        return jdbcTemplate.queryForObject(sql, new Object[] {username,password}, new LoginMapper());
    }

    @Override
    public int save(LoginInfo loginInfo) {
        return 0;
    }
}
