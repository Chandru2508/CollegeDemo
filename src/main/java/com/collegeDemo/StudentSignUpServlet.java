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

public class StudentSignUpServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private static final String jdbc_url = "jdbc:mysql://localhost:3306/college";
   private static final String jdbc_user = "root";
   private static final String jdbc_pass = "Hema2728@2024";

   public StudentSignUpServlet() {
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
         String userName = request.getParameter("username");
         String pass = request.getParameter("password");
         String mail = request.getParameter("email");
         String mnum = request.getParameter("mobile");
         String gender = request.getParameter("gender");
         String dob = request.getParameter("dob");
         String city = request.getParameter("city");
         PrintWriter out = response.getWriter();
         String query = "insert into students(username, password, email, mobile, gender, dob, city) values(?, ?, ?, ?, ?, ?, ?)";
         
         try {
         Class.forName("com.mysql.cj.jdbc.Driver");

               Connection con = DriverManager.getConnection(jdbc_url, jdbc_user, jdbc_pass);

                  PreparedStatement stmt = con.prepareStatement(query);

                  
                     stmt.setString(1, userName);
                     stmt.setString(2, pass);
                     stmt.setString(3, mail);
                     stmt.setString(4, mnum);
                     stmt.setString(5, gender);
                     stmt.setString(6, dob);
                     stmt.setString(7, city);
                     
                     int rowsAffected = stmt.executeUpdate();
                     RequestDispatcher rd;
                     
                     if (rowsAffected > 0) {
                        rd = request.getRequestDispatcher("Student_Login.html");
                        rd.include(request, response);
                        out.println("<h5>Data Inserted Successfully</h5>");
                     } 
                     else {
                        rd = request.getRequestDispatcher("Student_Signup.html");
                        rd.include(request, response);
                        out.println("<h2>Failed to Insert Data</h2>");
                     }

                     stmt.close();
                     con.close();
                  
             
         } 
         catch (SQLException var36) {
            out.println("<html><body>");
            out.print("<h2>Error: " + var36.getMessage() + "</h2>");
            out.println("</body></html>");
         }
      } 
      catch (IOException | ServletException | ClassNotFoundException var37) {
         System.out.println("Error: " + var37.getMessage());
      }

   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doGet(request, response);
   }
}
