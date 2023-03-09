package com.example.StudentsGradingSystem.Dao;

import com.example.StudentsGradingSystem.Maper.CourseRowMapper;
import com.example.StudentsGradingSystem.Maper.StudentMarkMapper;
import com.example.StudentsGradingSystem.Model.Course;
import com.example.StudentsGradingSystem.Model.StudentMark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class StudentDaoImpl implements StudentMarkDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Double> getAllStudentMarkInParticularCourse(int courseId) throws SQLException {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM course_student WHERE course_id=?", new Object[] {courseId});
        List<Double> marks = new ArrayList<>();
        for(Map<String, Object> row : rows) {
            marks.add((Double)row.get("mark"));
        }
        return marks;
    }

    @Override
    public StudentMark getMarkByStudentId(int id,int courseId) throws SQLException {
        return jdbcTemplate.queryForObject("SELECT * FROM course_student WHERE student_id = ? and course_id=?", new Object[] {id,courseId}, new StudentMarkMapper());
    }


}
