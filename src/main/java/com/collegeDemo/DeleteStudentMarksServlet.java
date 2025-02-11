package com.collegeDemo;

// Source code is decompiled from a .class file using FernFlower decompiler.

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteStudentMarksServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public DeleteStudentMarksServlet() {
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
	   try {
		  PrintWriter out = response.getWriter();
	      String student_id = request.getParameter("id");
	      
	      RequestDispatcher rd = request.getRequestDispatcher("AdminPageServlet");
	      rd.include(request, response);
	      out.println("<form action='ConfirmDeleteStudentMarksServlet' method='post'>");
	      out.println("<input type='hidden' name='id' value='" + student_id + "'>");
	      out.println("<lable for = cid>Confirm ID: </label> <input type='text' name='cid' id='cid' required><br><br>");
	      out.println("<button type='submit'>Delete</button>");
	      out.println("</form>");
      } 
	   catch (Exception e) {
       e.printStackTrace();
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doGet(request, response);
   }
}
