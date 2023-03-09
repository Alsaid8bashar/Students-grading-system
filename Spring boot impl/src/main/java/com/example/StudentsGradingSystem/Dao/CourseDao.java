package com.example.StudentsGradingSystem.Dao;

import com.example.StudentsGradingSystem.Model.Course;

import java.sql.SQLException;
import java.util.List;

public interface CourseDao {

     int save(Course course);
    Course getById(int id);
    int update(Course course);
    int deleteById(int id);
    List<Course> getAll();
    List<Course> getAllCoursesByStudentId(int id) throws SQLException;

}
