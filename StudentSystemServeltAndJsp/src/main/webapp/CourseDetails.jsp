<%@ page import="com.example.studentsystemserveltandjsp.Service.CourseService" %>
<%@ page import="com.example.studentsystemserveltandjsp.Dao.CourseDao" %>
<%@ page import="com.example.studentsystemserveltandjsp.Model.Course" %>
<%@ page import="com.example.studentsystemserveltandjsp.Dao.StudentMarks" %>
<%@ page import="com.example.studentsystemserveltandjsp.Dao.StudentMarkDao" %>
<%@ page import="com.example.studentsystemserveltandjsp.Model.LoginInfo" %><%--
  Created by IntelliJ IDEA.
  User: BasharAlsaid
  Date: 3/6/2023
  Time: 10:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    CourseService courseService=new CourseService();
    CourseDao courseDao=new CourseDao();
    int courseId =Integer.parseInt(request.getParameter("courseId"));
    LoginInfo student=(LoginInfo)session.getAttribute("user");

    StudentMarks studentMarks=new StudentMarkDao();

    Course course=courseDao.getCourseById(courseId);
%>
<!DOCTYPE html>
<html>
<head>
    <title>Course Grades</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row justify-content-center mt-5">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header bg-primary text-white">
                    <h4>Course Grades</h4>
                </div>
                <div class="card-body">
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>Course Name</th>
                            <th>Grade</th>
                            <th>Min</th>
                            <th>Max</th>
                            <th>Median</th>
                            <th>Average</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><%=course.getCourseName()%></td>
                            <td><%= studentMarks.getMarkByStudentId(student.getUserId(),course.getId()).getCourseMark()%></td>
                            <td><%=courseService.lowest(courseId).getAsDouble()%></td>
                            <td><%=courseService.highest(courseId).getAsDouble()%></td>
                            <td><%=courseService.median(courseId)%></td>
                            <td><%=courseService.average(courseId).getAsDouble()%></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

