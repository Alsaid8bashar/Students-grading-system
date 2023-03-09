package com.example.studentsystemserveltandjsp.Dao;


import com.example.studentsystemserveltandjsp.Model.Course;

import java.sql.SQLException;
import java.util.List;

public interface ICourse {
    List<Course> getAllCourses() throws SQLException;
     Course getCourseById(int id) throws SQLException;
     void addCourse(Course course) throws SQLException;

}
