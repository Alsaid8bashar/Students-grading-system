package com.example.StudentsGradingSystem.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class StudentMark {

    private int studentId;
    private int courseId;

    private double courseMark;


}
