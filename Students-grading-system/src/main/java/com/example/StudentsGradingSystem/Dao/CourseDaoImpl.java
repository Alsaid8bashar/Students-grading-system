package com.example.StudentsGradingSystem.Dao;

import com.example.StudentsGradingSystem.Maper.CourseRowMapper;
import com.example.StudentsGradingSystem.Model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;


@Repository
public class CourseDaoImpl implements CourseDao{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CourseDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int save(Course course) {
        return jdbcTemplate.update("INSERT INTO COURSE VALUES(default,?)", course.getCourseName());
    }

    @Override
    public Course getById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM course WHERE course_id = ?", new Object[] {id}, new CourseRowMapper());
    }

    @Override
    public int update(Course employee) {
        return 0;
    }

    @Override
    public int deleteById(int id) {
        return 0;
    }
    @Override
    public List<Course> getAll() {
        return jdbcTemplate.query("SELECT * FROM Course", new CourseRowMapper());
    }

    @Override
    public List<Course> getAllCoursesByStudentId(int id) throws SQLException {
        String query = "SELECT course.name, course.course_id " +
                "FROM student " +
                "JOIN course_student ON student.student_id = course_student.student_id " +
                "JOIN course ON course.course_id = course_student.course_id " +
                "WHERE student.student_id = ?";
        return jdbcTemplate.query(query, new Object[] {id},  new CourseRowMapper());
    }
    }



