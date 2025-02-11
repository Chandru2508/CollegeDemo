package com.collegeDemo;
// Source code is decompiled from a .class file using FernFlower decompiler.

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertStudentMarksServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private static final String jdbc_url = "jdbc:mysql://localhost:3306/college";
   private static final String jdbc_user = "root";
   private static final String jdbc_pass = "Hema2728@2024";

   public InsertStudentMarksServlet() {
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
	   PrintWriter out = response.getWriter();
	   
      Long id = Long.parseLong(request.getParameter("id"));
      int mathematics = Integer.parseInt(request.getParameter("mathematics"));
      int physics = Integer.parseInt(request.getParameter("physics"));
      int chemistry = Integer.parseInt(request.getParameter("chemistry"));
      int english = Integer.parseInt(request.getParameter("english"));
      int computer_science = Integer.parseInt(request.getParameter("computer_science"));
      
      int total_marks = mathematics + physics + chemistry + english + computer_science;
      
      String grade = "";
      if (total_marks >= 450) {
         grade = "A";
      } else if (total_marks >= 400) {
         grade = "B";
      } else if (total_marks >= 350) {
         grade = "C";
      } else if (total_marks >= 300) {
         grade = "D";
      } else if (total_marks >= 250) {
         grade = "E";
      } else {
         grade = "F";
      }

      String query = "INSERT INTO student_marks (student_id, mathematics, physics, chemistry, english, computer_science, total_marks, grade) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection con = DriverManager.getConnection(jdbc_url, jdbc_user, jdbc_pass);
         PreparedStatement stmt = con.prepareStatement(query);
         
         stmt.setLong(1, id);
         stmt.setInt(2, mathematics);
         stmt.setInt(3, physics);
         stmt.setInt(4, chemistry);
         stmt.setInt(5, english);
         stmt.setInt(6, computer_science);
         stmt.setInt(7, total_marks);
         stmt.setString(8, grade);
         
         int rowsAffected = stmt.executeUpdate();
         RequestDispatcher rd;
         
         if (rowsAffected > 0) {
            rd = request.getRequestDispatcher("AdminPageServlet");
            rd.include(request, response);
            out.println("<h6>Student marks inserted successfully.</h6>");
         } 
         else {
            out.println("<html>");
            out.println("<body>");
            out.println("<p>Failed to insert student marks.</p>");
            out.println("</body>");
            out.println("</html>");
            rd = request.getRequestDispatcher("InsertStudentMarks.html");
            rd.include(request, response);
         }

         stmt.close();
         con.close();
      } 
      catch (ClassNotFoundException | SQLException | NumberFormatException var17) {
         out.println("<html>");
         out.println("<body>");
         out.println("<p style='color:red;'>Error: " + var17.getMessage() + "</p>");
         out.println("</body>");
         out.println("</html>");
      }

   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doGet(request, response);
   }
}
