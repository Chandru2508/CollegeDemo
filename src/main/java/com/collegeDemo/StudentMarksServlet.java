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
 * Servlet implementation class StudentMarksServlet
 */
public class StudentMarksServlet extends HttpServlet {
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

        String userName = request.getParameter("username");

        // HTML structure
        out.println("<html>");
        out.println("<head><title>Student Marks</title>");
        out.println("<style>");
        out.println("table { width: 50%; margin: auto; border-collapse: collapse; text-align: center; }");
        out.println("th, td { padding: 10px; border: 1px solid black; }");
        out.println("th { background-color: #f2f2f2; }");
        out.println("body { text-align: center; font-family: Arial, sans-serif; }");
        out.println("</style></head>");
        out.println("<body>");
        out.println("<h2>Welcome, " + userName + "</h2>");
        out.println("<h3>Your Marks</h3>");

        String query = "SELECT * FROM student_marks WHERE student_id = (SELECT student_id FROM students WHERE username = ?)";
        // Fetch student marks
        
            try{
            	
                Class.forName("com.mysql.cj.jdbc.Driver");
            	Connection con = DriverManager.getConnection(jdbc_url, jdbc_user, jdbc_pass);
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1, userName);
                
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    out.println("<table>");
                    out.println("<tr><th>Mathematics</th><th>Physics</th><th>Chemistry</th><th>English</th><th>Computer Science</th><th>Total Marks</th><th>Grade</th></tr>");
                    out.println("<tr>");
                    out.println("<td>" + rs.getInt("mathematics") + "</td>");
                    out.println("<td>" + rs.getInt("physics") + "</td>");
                    out.println("<td>" + rs.getInt("chemistry") + "</td>");
                    out.println("<td>" + rs.getInt("english") + "</td>");
                    out.println("<td>" + rs.getInt("computer_science") + "</td>");
                    out.println("<td>" + rs.getInt("total_marks") + "</td>");
                    out.println("<td>" + rs.getString("grade") + "</td>");
                    out.println("</tr>");
                    out.println("</table>");
                } 
                else {
                    out.println("<p>No marks found for this student.</p>");
                }
                
                rs.close();
                stmt.close();
                con.close();
            
        } 
        catch (ClassNotFoundException | SQLException e) {
            out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
        }

        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
