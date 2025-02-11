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

public class UpdateStudentMarksServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   private static final String jdbc_url = "jdbc:mysql://localhost:3306/college";
   private static final String jdbc_user = "root";
   private static final String jdbc_pass = "Hema2728@2024";

   public UpdateStudentMarksServlet() {
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

      String query = "UPDATE student_marks SET mathematics = ?, physics = ?, chemistry = ?, english = ?, computer_science = ?, total_marks = ?, grade = ? WHERE student_id = ?";
      
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection con = DriverManager.getConnection(jdbc_url, jdbc_user, jdbc_pass);
         PreparedStatement stmt = con.prepareStatement(query);
         
         stmt.setInt(1, mathematics);
         stmt.setInt(2, physics);
         stmt.setInt(3, chemistry);
         stmt.setInt(4, english);
         stmt.setInt(5, computer_science);
         stmt.setInt(6, total_marks);
         stmt.setString(7, grade);
         stmt.setLong(8, id);
         
         int rowsAffected = stmt.executeUpdate();
         RequestDispatcher rd;
         
         if (rowsAffected > 0) {
            rd = request.getRequestDispatcher("AdminPageServlet");
            rd.include(request, response);
            out.println("<h3>Student marks updated successfully!</h3>");
         } 
         else {
            rd = request.getRequestDispatcher("EditStudentMarksServlet");
            rd.include(request, response);
            
         }

         stmt.close();
         con.close();
      } 
      catch (ClassNotFoundException | SQLException | NumberFormatException var16) {
         out.println("<html><body><h3 style='color:red;'>Error: " + var16.getMessage() + "</h3></body></html>");
      }
      
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doGet(request, response);
   }
}
