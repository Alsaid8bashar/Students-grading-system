package com.example.StudentsGradingSystem.Controller;

import com.example.StudentsGradingSystem.Dao.CourseDao;
import com.example.StudentsGradingSystem.Dao.StudentMarkDao;
import com.example.StudentsGradingSystem.Model.LoginInfo;
import com.example.StudentsGradingSystem.Service.CourseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("Course")
public class CourseMarkController {

    final CourseDao courseDao;

    @Qualifier("courseService1")

    final CourseService courseService;

    final StudentMarkDao studentMarkDao;

    private final HttpSession session;
    @Autowired
    public CourseMarkController(CourseDao courseDao, CourseService courseService, StudentMarkDao studentMarkDao, HttpSession session) {
        this.courseDao = courseDao;
        this.courseService = courseService;
        this.studentMarkDao = studentMarkDao;
        this.session = session;
    }


    @GetMapping("/statistic")
    public String detailsPage(Model model, @RequestParam("courseId") int courseId) throws SQLException {
        model.addAttribute(courseDao.getById(courseId));
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("user");
        Map<String, Double> courseStatic = new HashMap<>();
        courseStatic.put("max", courseService.highest(courseId).getAsDouble());
        courseStatic.put("min", courseService.lowest(courseId).getAsDouble());
        courseStatic.put("average", courseService.average(courseId).getAsDouble());
        courseStatic.put("grade", studentMarkDao.getMarkByStudentId(loginInfo.getUserId(),courseId).getCourseMark());
        courseStatic.put("median", courseService.median(courseId));

        model.addAllAttributes(courseStatic);
        return "CourseStatic";
    }
}
