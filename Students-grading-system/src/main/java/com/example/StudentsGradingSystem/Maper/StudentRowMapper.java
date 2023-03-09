package com.example.StudentsGradingSystem.Maper;

import com.example.StudentsGradingSystem.Model.Student;
import com.example.StudentsGradingSystem.Model.StudentMark;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper  implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Student student = new Student(rs.getInt("student_id"), rs.getString("name"));
        return student;
    }
}
