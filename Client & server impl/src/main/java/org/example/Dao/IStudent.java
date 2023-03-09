package org.example.Dao;

import org.example.Model.Student;

import java.sql.SQLException;
import java.util.List;

public interface IStudent {
    List<Student> getAllStudent() throws SQLException;
    Student getStudentById(int id) throws SQLException;

    void addStudent(Student student) throws SQLException;
}
