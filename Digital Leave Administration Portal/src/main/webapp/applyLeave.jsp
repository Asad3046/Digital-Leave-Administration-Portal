<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="leavemanagement.Leave"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Apply for Leave</title>
<link rel="stylesheet" type="text/css" href="css/applyLeave.css">
</head>
<body>
<%
  Leave leave = (Leave)session.getAttribute("leave");
%>
<form action="ApplyLeave" method="post">
  Emp ID: <input type="text" name="id" value="<%= leave.getEmpId() %>" readonly><br>
  Name: <input type="text" name="name" value="<%= leave.getEmpName() %>" readonly><br>
  Date of Joining: <input type="text" name="doj" value="<%= leave.getDateOfJoining() %>" readonly><br>
  Remaining Leaves: <input type="text" name="remainingLeaves" value="<%= leave.getRemainingLeaves() %>" readonly><br>
  No of Leaves: <input type="number" name="noOfLeaves"><br>
  From Date: <input type="date" name="fromDate" id="fromDate"><br>
  To Date: <input type="date" name="toDate"><br>
  Reason: <input type="text" name="reason"><br>
  Manager: <select name="manager">
    <option value="Manas Kumar">Manas Kumar</option>
    <option value="Md Osman Khan">Md Osman Khan</option>
    <option value="Apaar Agarwal">Apaar Agarwal</option>
    <option value="Ananya Sharma">Ananya sharma<option>
    <option value="Naresh Reddy">Naresh Reddy</option>
    <option value="Aditya Verma">Aditya Verma</option>
  </select><br>
  <input type="submit" value="Submit">
</form>
<script>
document.querySelector("form").addEventListener("submit", function(e) {
  const fromDateInput = document.getElementById("fromDate");
  const fromDate = new Date(fromDateInput.value);
  const today = new Date();

  // Reset time to midnight for accurate comparison
  today.setHours(0, 0, 0, 0);

  if (fromDate < today) {
    alert("From Date cannot be earlier than today's date.");
    e.preventDefault(); // Prevent form submission
  }
});
</script>

</body>
</html>