package com.collegeDemo;

//Source code is decompiled from a .class file using FernFlower decompiler.

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
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentLoginServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
private static final String jdbc_url = "jdbc:mysql://localhost:3306/college";
private static final String jdbc_user = "root";
private static final String jdbc_pass = "Hema2728@2024";

public StudentLoginServlet() {
}

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   PrintWriter out = response.getWriter();
   String userName = request.getParameter("username");
   String pass = request.getParameter("password");
   String query = "select username, password from students where username = ? and password = ?";

   try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection(jdbc_url, jdbc_user, jdbc_pass);
      PreparedStatement stmt = con.prepareStatement(query);
      stmt.setString(1, userName);
      stmt.setString(2, pass);
      
      ResultSet rs = stmt.executeQuery();
      RequestDispatcher rd;
      
      if (rs.next()) {
         request.setAttribute("username", userName);
         rd = request.getRequestDispatcher("StudentMarksServlet");
         rd.include(request, response);
         out.println("<h4>Login SucessFull</h4>");
      } 
      else {
         out.println("<html><body>");
         out.println("<h4>Invalid User Name or Password</h4>");
         out.println("</body></html>");
         rd = request.getRequestDispatcher("Student_Login.html");
         rd.include(request, response);
      }

      rs.close();
      stmt.close();
      con.close();
      
   } 
   catch (ClassNotFoundException | SQLException var11) {
      out.println("<html><body>");
      out.println("<h2>Error: " + var11.getMessage() + "</h2>");
      out.println("</body></html>");
   }

   out.close();
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   this.doGet(request, response);
}
}
