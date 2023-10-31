package com.org.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.org.dao.StudentDao;
@WebServlet("/updatestudent")
public class UpdateStudent extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("id");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance_management?user=root&password=satya@2000rb");
			String sql="SELECT * FROM student_att where id="+id;
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			rs.next();
			PrintWriter pw=resp.getWriter();
			pw.write("<html><body><form action=update>");
			pw.write("ID:<input type=text value="+rs.getInt(1)+" name=id readonly><br><br>");
			pw.write("NAME:<input type=text value="+rs.getString(2)+" name=name><br><br>");
			pw.write("AGE:<input type=text value="+rs.getInt(3)+" name=age><br><br>");
			pw.write("GENDER:<input type=text value="+rs.getString(4)+" name=gender><br><br>");
			pw.write("STATUS:<input type=text value="+rs.getString(4)+" name=status><br><br>");

			
			pw.write("<input type=submit value=update>");
			pw.write("</form></body></html>");
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}



	}


