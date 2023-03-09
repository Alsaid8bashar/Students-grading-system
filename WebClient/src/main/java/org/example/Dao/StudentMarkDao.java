package org.example.Dao;

import org.example.Model.Course;
import org.example.Model.StudentMark;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentMarkDao  implements StudentMarks {

    private DataSource ds;

    public StudentMarkDao() throws SQLException {
        this.ds = DbConnection.getDataSource();
    }


    public List<Course> getAllCoursesByStudentId(int id) throws SQLException {
        List<Course> courses = new ArrayList<>();
        try (Connection conn = ds.getConnection()) {
            PreparedStatement pStmt = conn.prepareStatement("SELECT course.name ,course.course_id\n" +
                    "FROM student\n" +
                    "JOIN course_student ON student.student_id = course_student.student_id\n" +
                    "JOIN course ON course.course_id = course_student.course_id\n" +
                    "WHERE student.student_id =  ?");
            pStmt.setInt(1, id);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("course_id"));
                course.setCourseName(rs.getString("name"));
                courses.add(course);
            }
            return courses;
        }
    }


    public StudentMark getMarkByStudentId(int id,int courseId) throws SQLException {
        try (Connection conn = ds.getConnection()) {
            Statement stmt = conn.createStatement();
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM course_student WHERE student_id = ? and course_id=?");
            pStmt.setInt(1, id);
            pStmt.setInt(2, courseId);
            ResultSet rs = pStmt.executeQuery();
            StudentMark studentMark = new StudentMark();
            while (rs.next()) {
            studentMark.setStudentId(rs.getInt("student_id"));
            studentMark.setCourseId(rs.getInt("course_id"));
            studentMark.setCourseMark(rs.getDouble("mark"));
            }
            return studentMark;
        }
    }

    public List<Double> getAllStudentMarkInParticularCourse (int courseId) throws SQLException {
        List<Double> studentMarks = new ArrayList<>();
        try (Connection conn = ds.getConnection()) {
            Statement stmt = conn.createStatement();
            PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM course_student WHERE course_id=?");
            pStmt.setInt(1, courseId);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                studentMarks.add(rs.getDouble("mark"));
            }
               return studentMarks;
        }
    }
}
