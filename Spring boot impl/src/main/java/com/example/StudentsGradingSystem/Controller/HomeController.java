package com.example.StudentsGradingSystem.Controller;


import com.example.StudentsGradingSystem.Dao.CourseDao;
import com.example.StudentsGradingSystem.Model.LoginInfo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
@RequestMapping("/Home")
public class HomeController {


    final CourseDao courseDao;
    private final HttpSession session;

    @Autowired
    public HomeController(CourseDao courseDao, HttpSession session) {
        this.courseDao = courseDao;
        this.session = session;
    }

    @GetMapping("/")
    public String homePage(Model model) throws SQLException {
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("user");
        model.addAttribute("courses", courseDao.getAllCoursesByStudentId(loginInfo.getUserId()));
        return "Home";
    }
}
