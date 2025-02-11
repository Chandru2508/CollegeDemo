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

public class ConfirmDeleteStudentMarksServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private static final String jdbc_url = "jdbc:mysql://localhost:3306/college";
   private static final String jdbc_user = "root";
   private static final String jdbc_pass = "Hema2728@2024";

   public ConfirmDeleteStudentMarksServlet() {
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
	   String student_id = request.getParameter("id");
      String confirmStudent_id = request.getParameter("cid");
      
      String query = "delete from student_marks where student_id = ?";
      
      PrintWriter out = response.getWriter();

      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         
         if (student_id.equals(confirmStudent_id)) {
        	 
            Connection con = DriverManager.getConnection(jdbc_url, jdbc_user, jdbc_pass);
            PreparedStatement stmt = con.prepareStatement(query);
            
            stmt.setString(1, student_id);
            stmt.executeUpdate();
            
            RequestDispatcher rd = request.getRequestDispatcher("AdminPageServlet");
            rd.include(request, response);
            out.println("<h4>Student Marks Deleted Successfully</h4>");
            
            con.close();
            stmt.close();
         } 
         else {
        	 
            request.setAttribute("id", student_id);
            RequestDispatcher rd = request.getRequestDispatcher("DeleteStudentMarksServlet");
            rd.include(request, response);
            out.println("<h4>Student Marks Deletion Failed</h4>");
         }
      } 
      catch (SQLException | ClassNotFoundException var10) {
         out.println("<html>");
         out.println("<body>");
         out.println("<p style='color:red;'>Error: " + var10.getMessage() + "</p>");
         out.println("</body>");
         out.println("</html>");
      }

   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doGet(request, response);
   }
}
