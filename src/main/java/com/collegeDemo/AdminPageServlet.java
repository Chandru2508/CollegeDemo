package com.collegeDemo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class ViewStudentsServlet
 */

public class AdminPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String jdbc_url = "jdbc:mysql://localhost:3306/college";
    private static final String jdbc_user = "root";
    private static final String jdbc_pass = "Hema2728@2024";

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // HTML Structure
        out.println("<html>");
        out.println("<head><title>Students Details</title>");
        out.println("<style>");
        out.println("table { width: 80%; margin: auto; border-collapse: collapse; text-align: center; }");
        out.println("th, td { padding: 10px; border: 1px solid black; }");
        out.println("th { background-color: #f2f2f2; }");
        out.println("body { text-align: center; font-family: Arial, sans-serif; }");
        out.println("</style></head>");
        out.println("<body>");
        out.println("<h2>All Students Details</h2>");

      
            try {
            	Class.forName("com.mysql.cj.jdbc.Driver");
            	Connection con = DriverManager.getConnection(jdbc_url, jdbc_user, jdbc_pass);
                PreparedStatement stmt = con.prepareStatement("SELECT * FROM students");
                ResultSet rs = stmt.executeQuery();
                // Display Student Details Table
                out.println("<h3>Student Details</h3>");
                out.println("<table>");
                out.println("<tr><th>Student ID</th><th>Username</th><th>Email</th><th>Mobile</th><th>Gender</th><th>DOB</th><th>City</th></tr>");

                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getLong("student_id") + "</td>");
                    out.println("<td>" + rs.getString("username") + "</td>");
                    out.println("<td>" + rs.getString("email") + "</td>");
                    out.println("<td>" + rs.getString("mobile") + "</td>");
                    out.println("<td>" + rs.getString("gender") + "</td>");
                    out.println("<td>" + rs.getDate("dob") + "</td>");
                    out.println("<td>" + rs.getString("city") + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            
			} 
            catch (ClassNotFoundException | SQLException e) {
				out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
				System.out.println("Error: " + e.getMessage());
				e.printStackTrace();
				
			}
            // Display Student Marks Table
            out.println("<h3>Student Marks</h3>");
            
            try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            try(
            	Connection con = DriverManager.getConnection(jdbc_url, jdbc_user, jdbc_pass);
                PreparedStatement stmt = con.prepareStatement("SELECT * FROM student_marks")) {
            	
                
                ResultSet rs = stmt.executeQuery();
                out.println("<table>");
                out.println("<tr><th>Student ID</th><th>Mathematics</th><th>Physics</th><th>Chemistry</th><th>English</th><th>Computer Science</th><th>Total Marks</th><th>Grade</th></tr>");

                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getLong("student_id") + "</td>");
                    out.println("<td>" + rs.getInt("mathematics") + "</td>");
                    out.println("<td>" + rs.getInt("physics") + "</td>");
                    out.println("<td>" + rs.getInt("chemistry") + "</td>");
                    out.println("<td>" + rs.getInt("english") + "</td>");
                    out.println("<td>" + rs.getInt("computer_science") + "</td>");
                    out.println("<td>" + rs.getInt("total_marks") + "</td>");
                    out.println("<td>" + rs.getString("grade") + "</td>");
                    out.println("<td><button type='button'><a href='EditStudentMarksServlet?id=" + rs.getLong("student_id") + "'>Edit</a></td>");
                    out.println("<td><button type='button'><a href='DeleteStudentMarksServlet?id=" + rs.getLong("student_id") + "'>Delete</a></button></td>");
                    out.println("</tr>");
                 } 

                 out.println("<tr>");
                 out.println("<td><button type='button'><a href='InsertStudentMarks.html'>Insert</a></button></td>");
                 out.println("</tr>");
                 out.println("</table>");
                 
                 rs.close();
			     stmt.close();
			     con.close();
        } catch (SQLException e) {
            out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
        }

        out.println("</body>");
        out.println("</html>");
        
        
        
    }
}
