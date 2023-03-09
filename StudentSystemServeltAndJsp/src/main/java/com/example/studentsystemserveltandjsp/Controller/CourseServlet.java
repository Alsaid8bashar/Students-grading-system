package com.example.studentsystemserveltandjsp.Controller;

import com.example.studentsystemserveltandjsp.Dao.CourseDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CourseServlet", value = "/CourseServlet")
public class CourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourseDao courseDao;
        int id=Integer.parseInt(request.getParameter("courseId"));
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/CourseDetails.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
