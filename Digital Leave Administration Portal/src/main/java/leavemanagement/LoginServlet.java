package leavemanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String role = request.getParameter("role");

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Leave_Management_System", "root", "root");
			getServletContext().setAttribute("connection", conn);

			//admin login logic
			if("admin".equals(role))
			{
				if(username.equals("admin") && password.equals("admin123"))
				{
					PreparedStatement ps = conn.prepareStatement("SELECT * FROM employees");
					ResultSet rs = ps.executeQuery();

					List<Leave> leaveList = new ArrayList<>();
					while(rs.next())
					{
						Leave leave = new Leave();
						leave.setEmpId(rs.getInt("EmpId"));
						leave.setEmpName(rs.getString("EmpName"));
						leave.setAge(rs.getInt("Age"));
						leave.setDateOfJoining(rs.getString("DateOfJoining"));
						leave.setTotalNoOfLeaves(rs.getInt("TotalNoOfLeaves"));
						leave.setAvailedLeaves(rs.getInt("AvailedLeaves"));
						leave.setRemainingLeaves(rs.getInt("RemainingLeaves"));
						leaveList.add(leave);
					}

					request.setAttribute("leaveList", leaveList);
					request.getRequestDispatcher("adminHome.jsp").forward(request, response);
				}
				else
				{
					out.println("<h3>Invalid Admin credentials.</h3>");
				}
			}

			//employee login logic
			else if("employee".equals(role))
			{
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM employees WHERE EmpName=? AND Pass_word=?");
				ps.setString(1, username);
				ps.setString(2, password);

				ResultSet rs = ps.executeQuery();
				if(rs.next())
				{
					Leave leave = new Leave();
					leave.setEmpId(rs.getInt("EmpId"));
					leave.setEmpName(rs.getString("EmpName"));
					leave.setAge(rs.getInt("Age"));
					leave.setDateOfJoining(rs.getString("DateOfJoining"));
					leave.setTotalNoOfLeaves(rs.getInt("TotalNoOfLeaves"));
					leave.setAvailedLeaves(rs.getInt("AvailedLeaves"));
					leave.setRemainingLeaves(rs.getInt("RemainingLeaves"));

					HttpSession session = request.getSession();
					session.setAttribute("leave", leave);
					response.sendRedirect("employeeHome.jsp");
				}
				else
				{
					out.println("<h3 style='color:red;'>Invalid credentials.</h3>");
				}
			}
			else
			{

				out.println("<h3>An Error Occured. Please try again.</h3>");
			}


			} catch(Exception e) {
				out.println("<h3>Error occured:</h3>");
				e.printStackTrace();
			}
	}
}

