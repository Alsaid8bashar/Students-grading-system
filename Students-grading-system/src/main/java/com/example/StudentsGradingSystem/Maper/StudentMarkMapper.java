package com.example.StudentsGradingSystem.Maper;

import com.example.StudentsGradingSystem.Model.StudentMark;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMarkMapper implements RowMapper<StudentMark> {
    @Override
    public StudentMark mapRow(ResultSet rs, int rowNum) throws SQLException {
        final StudentMark studentMark = new StudentMark(rs.getInt("course_id"), rs.getInt("student_id"),rs.getDouble("mark"));
        return studentMark;
    }
}
