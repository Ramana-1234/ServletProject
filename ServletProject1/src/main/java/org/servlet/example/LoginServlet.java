package org.servlet.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	 
		response.sendRedirect("Login.html");

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	response.setContentType("text/html");
	PrintWriter out= response.getWriter();

	String username = request.getParameter("username");
	String password = request.getParameter("password");
	

	boolean isValidUser = authenticateUser(username, password);
	
	if (isValidUser) {
		 out.println("<html><body>");
	        out.println("<h1>Login Successful</h1>");
	        out.println("<p>Welcome, " + username + "!</p>");
	        out.println("<a href='home'>Go to Home</a>");
	        out.println("</body></html>");
	}
	else {
		out.println("<h3 style='color:red;'>Invalid Username or Password </h3>");
		out.println("<a href='Login.html'> Try Again</a>");
	}
}

private boolean authenticateUser(String username, String password) {
	boolean isValid = false;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con= DriverManager.
				getConnection("jdbc:mysql://localhost:3306/userdb","root","ramana123");
		
		String query = "SELECT * FROM users WHERE username=? AND password=? ";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			isValid = true;
			System.out.println("isValid is true");
		}
		rs.close();
		ps.close();
		con.close();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return isValid;
	    

	
	
}
}