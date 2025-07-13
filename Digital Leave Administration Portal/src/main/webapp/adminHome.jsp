<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="leavemanagement.Leave" import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<link rel="stylesheet" type="text/css" href="css/adminHome.css">
</head>
<body>
	<h2>Welcome, Admin</h2>
	<h3>Employee Leave Details</h3>
  <table border="1">
    <tr>
      <th>Emp ID</th>
      <th>Name</th>
      <th>Age</th>
      <th>Date of Joining</th>
      <th>Total Leaves</th>
      <th>Availed Leaves</th>
      <th>Remaining Leaves</th>
    </tr>
    <%
      List<Leave> leaveList = (List<Leave>) request.getAttribute("leaveList");
      for (Leave leave : leaveList) {
    %>
    <tr>
      <td><%= leave.getEmpId() %></td>
      <td><%= leave.getEmpName() %></td>
      <td><%= leave.getAge() %></td>
      <td><%= leave.getDateOfJoining() %></td>
      <td><%= leave.getTotalNoOfLeaves() %></td>
      <td><%= leave.getAvailedLeaves() %></td>
      <td><%= leave.getRemainingLeaves() %></td>
    </tr>
    <% } %>
  </table>
</body>
</html>