package com.collegeDemo;
// Source code is decompiled from a .class file using FernFlower decompiler.

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

public class EditStudentMarksServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private static final String jdbc_url = "jdbc:mysql://localhost:3306/college";
   private static final String jdbc_user = "root";
   private static final String jdbc_pass = "Hema2728@2024";

   public EditStudentMarksServlet() {
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
	   PrintWriter out = response.getWriter();
      Long id = Long.parseLong(request.getParameter("id"));
      
      String query = "SELECT * FROM student_marks WHERE student_id = ?";

      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection con = DriverManager.getConnection(jdbc_url, jdbc_user, jdbc_pass);
         PreparedStatement stmt = con.prepareStatement(query);
         
         stmt.setLong(1, id);
         ResultSet rs = stmt.executeQuery();

         while(rs.next()) {
            int mathematics = rs.getInt("mathematics");
            int physics = rs.getInt("physics");
            int chemistry = rs.getInt("chemistry");
            int english = rs.getInt("english");
            int computer_science = rs.getInt("computer_science");
            out.println("<html>");
            out.println("<head><title>Edit Student Marks</title>");
            out.println("</head>");
            out.println("<body style = 'background-color: beige'>");
            out.println("<h2>Edit Student Marks</h2>");
            out.println("<form action='UpdateStudentMarksServlet' method='get'>");
            out.println("<h5>Student ID: " + String.valueOf(id) + "</h5>");
            out.println("<table>");
            out.println("<tr><td><h5>Student ID:</h5></td><td><input type='text' name='id' value='" + String.valueOf(id) + "'readonly></td></tr>");
            out.println("<tr><td>Mathematics:</td><td><input type='text' name='mathematics' value='" + mathematics + "' required></td></tr>");
            out.println("<tr><td>Physics:</td><td><input type='text' name='physics' value='" + physics + "' required></td></tr>");
            out.println("<tr><td>Chemistry:</td><td><input type='text' name='chemistry' value='" + chemistry + "' required></td></tr>");
            out.println("<tr><td>English:</td><td><input type='text' name='english' value='" + english + "' required></td></tr>");
            out.println("<tr><td>Computer_Science:</td><td><input type='text' name='computer_science' value='" + computer_science + "' required></td></tr>");
            out.println("<tr><td><input type='submit' value='Update'></td></tr>");
            out.println("</table>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
         }
      } 
      catch (ClassNotFoundException | SQLException e) {
    	  out.println("<html><body>");
          out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
          out.println("</html></body>");
      }
      out.close();

   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doGet(request, response);
   }
}
