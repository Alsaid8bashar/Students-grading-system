package com.example.StudentsGradingSystem.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class LoginInfo {

    private String password;
    private String username;

    private int userId;


}
