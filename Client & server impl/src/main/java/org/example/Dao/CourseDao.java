package org.example.Dao;

import org.example.Model.Course;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDao implements ICourse {
    private DataSource ds;

    public CourseDao() throws SQLException {
        this.ds = DbConnection.getDataSource();
    }

    public List<Course> getAllCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        try (Connection conn = ds.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Course");
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setCourseName(rs.getString("courseName"));
                courses.add(course);
            }
            return courses;
        }
    }

    public Course getCourseById(int id) throws SQLException {
        try (Connection conn = ds.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Course WHERE id=?");
            Course course = new Course();
            course.setId(rs.getInt("id"));
            course.setCourseName(rs.getString("courseName"));
            return course;
        }
    }

    public void addCourse(Course course) throws SQLException {
        try (Connection conn = ds.getConnection()) {
            String sql = "INSERT INTO Course VALUES(?)";
            PreparedStatement pStmt = conn.prepareCall(sql);
            pStmt.setString(1, course.getCourseName());
            pStmt.executeUpdate();
        }
    }

}

