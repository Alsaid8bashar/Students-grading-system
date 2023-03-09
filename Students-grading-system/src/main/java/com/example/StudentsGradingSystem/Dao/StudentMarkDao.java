package com.example.StudentsGradingSystem.Dao;

import com.example.StudentsGradingSystem.Model.Course;
import com.example.StudentsGradingSystem.Model.StudentMark;

import java.sql.SQLException;
import java.util.List;

public interface StudentMarkDao {
     List<Double> getAllStudentMarkInParticularCourse (int courseId) throws SQLException;
     public StudentMark getMarkByStudentId(int id,int courseId) throws SQLException ;

}
