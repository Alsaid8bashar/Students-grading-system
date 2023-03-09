package org.example.Dao;

import org.example.Model.Course;
import org.example.Model.StudentMark;

import java.sql.SQLException;
import java.util.List;

public interface StudentMarks {

    public List<Double> getAllStudentMarkInParticularCourse (int courseId) throws SQLException;
    public StudentMark getMarkByStudentId(int id,int courseId) throws SQLException ;

    public List<Course> getAllCoursesByStudentId(int id) throws SQLException;
}
