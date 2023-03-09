package com.example.studentsystemserveltandjsp.Dao;


import com.example.studentsystemserveltandjsp.Model.Course;
import com.example.studentsystemserveltandjsp.Model.StudentMark;

import java.sql.SQLException;
import java.util.List;

public interface StudentMarks {

    public List<Double> getAllStudentMarkInParticularCourse (int courseId) throws SQLException;
    public StudentMark getMarkByStudentId(int userId, int id) throws SQLException ;

    public List<Course> getAllCoursesByStudentId(int id) throws SQLException;
}
