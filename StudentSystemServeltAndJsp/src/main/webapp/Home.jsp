<%@ page import="com.example.studentsystemserveltandjsp.Dao.CourseDao" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.studentsystemserveltandjsp.Model.Course" %>
<%@ page import="com.example.studentsystemserveltandjsp.Model.Student" %>
<%@ page import="com.example.studentsystemserveltandjsp.Dao.StudentMarkDao" %>
<%@ page import="com.example.studentsystemserveltandjsp.Model.LoginInfo" %><%--
  Created by IntelliJ IDEA.
  User: BasharAlsaid
  Date: 3/6/2023
  Time: 8:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    StudentMarkDao dao = new StudentMarkDao();
    LoginInfo student=(LoginInfo)session.getAttribute("user");
    List<Course> courses = dao.getAllCoursesByStudentId(student.getUserId());
%>
<!DOCTYPE html>
<html>
<head>
    <title>Online Courses</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Custom CSS -->
    <style>
        .course-card {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Online Courses</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Courses</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Contact</a>
            </li>
        </ul>
    </div>
</nav>

<div class="container mt-4">
    <div class="row">
        <% for (Course course:courses) { %>
        <div class="col-md-4">
            <div class="card course-card">
                <div class="card-body">
                    <h5 class="card-title"><%=course.getCourseName()%></h5>
                    <a href="/CourseServlet?courseId=<%=course.getId()%>" class="btn btn-primary">Grades</a>
                </div>
            </div>
        </div>
        <%}%>
    </div>
</div>

<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Bootstrap JS -->
<script>
</script>
</body>
</html>
