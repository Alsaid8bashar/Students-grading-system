package com.example.StudentsGradingSystem.Service;


import com.example.StudentsGradingSystem.Dao.StudentMarkDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Stream;

@Service
@Qualifier("courseService1")
public class CourseService {

    private final StudentMarkDao studentMarks;

    @Autowired
    public CourseService(StudentMarkDao studentMarks) {
        this.studentMarks = studentMarks;
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
