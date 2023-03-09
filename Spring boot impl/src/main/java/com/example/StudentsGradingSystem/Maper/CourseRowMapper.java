package com.example.StudentsGradingSystem.Maper;

import com.example.StudentsGradingSystem.Model.Course;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRowMapper  implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Course course = new Course(rs.getInt("course_id"), rs.getString("name"));
        return course;
    }
}
