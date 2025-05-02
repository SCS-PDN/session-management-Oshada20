<%@ page import="java.util.*, model.courses" %>
<!DOCTYPE html>
<html>
<head><title>Dashboard</title></head>
<body>

<h2>Welcome to Dashboard</h2>

<% if (request.getAttribute("message") != null) { %>
    <p style="color: green;"><%= request.getAttribute("message") %></p>
<% } %>

<h3>Available Courses</h3>
<table border="1">
<tr><th>ID</th><th>Name</th><th>Instructor</th><th>Action</th></tr>
<%
    List<courses> courses = (List<courses>) request.getAttribute("courseList");
    for (courses course : courses) {
%>
<tr>
    <td><%= course.getCourseId() %></td>
    <td><%= course.getCourseName() %></td>
    <td><%= course.getInstructor() %></td>
    <td><a href="enroll?courseId=<%= course.getCourseId() %>">Enroll</a></td>
</tr>
<% } %>
</table>

<h3>Enrolled Courses</h3>
<%
    List<courses> enrolled = (List<courses>) request.getAttribute("enrolledCourses");
    if (enrolled != null && !enrolled.isEmpty()) {
%>
<table border="1">
<tr><th>ID</th><th>Name</th><th>Instructor</th></tr>
<% for (courses c : enrolled) { %>
<tr>
    <td><%= c.getCourseId() %></td>
    <td><%= c.getCourseName() %></td>
    <td><%= c.getInstructor() %></td>
</tr>
<% } %>
</table>
<% } else { %>
<p>No enrolled courses yet.</p>
<% } %>

<br><a href="logout">Logout</a>
</body>
</html>
