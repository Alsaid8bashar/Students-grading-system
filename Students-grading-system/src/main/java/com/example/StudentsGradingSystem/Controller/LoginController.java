package com.example.StudentsGradingSystem.Controller;


import com.example.StudentsGradingSystem.Model.LoginInfo;
import com.example.StudentsGradingSystem.Service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.sql.SQLException;

@Controller
@RequestMapping("/Login")
public class LoginController {


    final
    LoginService loginService;
    @Autowired
    private HttpSession session;
    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping("/")
    public String loginPage() {
        return "Login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) throws SQLException {
        LoginInfo loginInfo = loginService.authenticate(username, password);
        if (loginInfo != null) {
            session.setAttribute("user",loginInfo);
            return "redirect:/Home/";
        }else
            return "Login";
    }
}
