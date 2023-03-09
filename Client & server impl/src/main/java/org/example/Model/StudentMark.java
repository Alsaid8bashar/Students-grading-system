package org.example.Model;

public class StudentMark {

    private int studentId;
    private int courseId;

    private double courseMark;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public double getCourseMark() {
        return courseMark;
    }

    public void setCourseMark(double courseMark) {
        this.courseMark = courseMark;
    }
}
