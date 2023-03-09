package org.example.Service;

import org.example.Dao.StudentMarkDao;
import org.example.Dao.StudentMarks;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

public class CourseService {
        private StudentMarks studentMarks;

    {
        try {
            studentMarks = new StudentMarkDao();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public OptionalDouble average(int courseId) throws SQLException {
        return studentMarks.getAllStudentMarkInParticularCourse(courseId).stream().mapToDouble(Double::doubleValue).average();
    }

    public OptionalDouble lowest (int courseId) throws SQLException {
        return studentMarks.getAllStudentMarkInParticularCourse(courseId).stream().mapToDouble(Double::doubleValue).min();
    }

    public OptionalDouble highest (int courseId) throws SQLException {
        return studentMarks.getAllStudentMarkInParticularCourse(courseId).stream().mapToDouble(Double::doubleValue).max();
    }
    public Double median (int courseId) throws SQLException {
        List<Double> marks =studentMarks.getAllStudentMarkInParticularCourse(courseId);
        Collections.sort(marks);
        double median;
        int size = marks.size();

        if (size % 2 == 0) {
            int midIndex = size / 2;
            median = (marks.get(midIndex - 1) + marks.get(midIndex)) / 2.0;
        } else {
            int midIndex = (size - 1) / 2;
            median = marks.get(midIndex);
        }
        return median;
    }
}
