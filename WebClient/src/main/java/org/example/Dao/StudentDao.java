package org.example.Dao;

import org.example.Model.Course;
import org.example.Model.Student;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements IStudent{

    private DataSource ds;

    public StudentDao(DataSource ds) throws SQLException {
        this.ds = DbConnection.getDataSource();
    }

    public List<Student> getAllStudent() throws SQLException {
        List<Student> students = new ArrayList<>();
        try (Connection conn = ds.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Student");
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                students.add(student);
            }
            return students;
        }
    }

    public Student getStudentById(int id) throws SQLException {
        try (Connection conn = ds.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Student WHERE id=?");
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            return student;
        }
    }

    public void addStudent(Student student) throws SQLException {
        try (Connection conn = ds.getConnection()) {
            String sql = "INSERT INTO Student VALUES(?)";
            PreparedStatement pStmt = conn.prepareCall(sql);
            pStmt.setString(1, student.getName());
            pStmt.executeUpdate();
        }
    }
}
