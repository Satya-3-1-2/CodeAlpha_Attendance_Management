package com.org.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/displaystudent")
public class DisplayStudent extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance_management?user=root&password=satya@2000rb");
			String sql="SELECT * FROM student_att ";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			PrintWriter pw=resp.getWriter();
			pw.write("<html><body>");
			pw.write("<table border=2px>");
			pw.write("<tr>");
			pw.write("<th>ID</th>");
			pw.write("<th>NAME</th>");
			pw.write("<th>AGE</th>");
			pw.write("<th>GENDER</th>");
			pw.write("<th>STATUS</th>");
			

			

			pw.write("</tr>");
			while(rs.next()) {
				pw.write("<tr>");
				pw.write("<td>"+rs.getInt(1)+"</td>");
				pw.write("<td>"+rs.getString(2)+"</td>");
				pw.write("<td>"+rs.getInt(3)+"</td>");
				pw.write("<td>"+rs.getString(4)+"</td>");
				pw.write("<td>"+rs.getString(5)+"</td>");


				
				String updateLink = "<form action='updatestudent' method='post'>" +
		                  "<input type='hidden' name='id' value='" + rs.getInt(1) + "'>" +
		                  "<input type='submit' value='Update'>" +
		                  "</form>";
		pw.write("<td>" + updateLink + "</td>");


				pw.write("</tr>");
			}
			pw.write("</table></body></html>");
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
