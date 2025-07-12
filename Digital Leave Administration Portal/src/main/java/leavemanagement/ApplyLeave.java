package leavemanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ApplyLeave")
public class ApplyLeave extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ApplyLeave() {
        super();
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		int noOfLeaves = Integer.parseInt(request.getParameter("noOfLeaves"));
		int remainingLeaves = Integer.parseInt(request.getParameter("remainingLeaves"));

		String insertQuery = "INSERT INTO leave_requests (employee_id, employee_name, no_of_leaves, from_date, to_date, reason, manager) VALUES (?, ?, ?, ?, ?, ?, ?)";
		String updateQuery = "UPDATE employees SET AvailedLeaves = AvailedLeaves + ?, RemainingLeaves = RemainingLeaves - ? WHERE EmpId = ?";

		if(noOfLeaves > 2 || noOfLeaves > remainingLeaves)
		{
			out.println("<h3 style='color:red;'>You can't apply for more than 2 or more than your remaining leaves.</h3>");
			return;
		}

		try {
			Connection conn = (Connection)getServletContext().getAttribute("connection");

			//inserting into leave_requests
			PreparedStatement ps = conn.prepareStatement(insertQuery);
			ps.setInt(1, Integer.parseInt(request.getParameter("id")));
			ps.setString(2, request.getParameter("name"));
			ps.setInt(3, noOfLeaves);
			ps.setString(4, request.getParameter("fromDate"));
			ps.setString(5, request.getParameter("toDate"));
			ps.setString(6, request.getParameter("reason"));
			ps.setString(7, request.getParameter("manager"));
			
			ps.executeUpdate();

			//updating employee leave counts
			PreparedStatement ps2 = conn.prepareStatement(updateQuery);
			ps2.setInt(1, noOfLeaves);
			ps2.setInt(2, noOfLeaves);
			ps2.setInt(3, Integer.parseInt(request.getParameter("id")));
			ps2.executeUpdate();

			out.println("<h3 style='color:green;'>Leave applied succesfully.</h3>");
		} catch(Exception e) {
			e.printStackTrace();
			out.println("<h3 style='color:red;'>An error occured: " + e.getMessage() + "</h3>");
		}
	}

}
