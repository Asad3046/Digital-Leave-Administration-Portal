<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="leavemanagement.Leave"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" type="text/css" href="css/employeeHome.css">
</head>
<body>
<%
  Leave leave = (Leave)session.getAttribute("leave");
%>
<h2>Welcome, <%= leave.getEmpName() %></h2>
<p>Total Leaves: <%= leave.getTotalNoOfLeaves() %></p>
<p>Availed Leaves: <%= leave.getAvailedLeaves() %></p>
<p>Remaining Leaves: <%= leave.getRemainingLeaves() %></p>
<a href="applyLeave.jsp">Apply for Leave</a>

</body>
</html>