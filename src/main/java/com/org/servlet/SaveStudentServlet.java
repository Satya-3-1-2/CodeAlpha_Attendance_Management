package com.org.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.org.dao.StudentDao;
@WebServlet("/savestudent")
public class SaveStudentServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id=Integer.parseInt(req.getParameter("id"));
		String name=req.getParameter("name");
		int age=Integer.parseInt(req.getParameter("age"));
		String gender= req.getParameter("gender");
		String status= req.getParameter("status");
		
		int res=StudentDao.SaveStudent(id, name, age, gender, status);
		PrintWriter pw=resp.getWriter();
		RequestDispatcher rd=req.getRequestDispatcher("home.html");
		
		if(res>0) {
			pw.write("<html><body><h1>"+name+" data saved successfully</h1></body></html>");
		}
		else {
			pw.write("<html><body><h1>some error</h1></body></html>");
		}
		rd.include(req, resp);
		
		

		
	}

}

