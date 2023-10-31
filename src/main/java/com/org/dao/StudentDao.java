package com.org.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.org.model.Student;

public class StudentDao {
	private static String status;
	public static int SaveStudent(int id, String name, int age, String gender,String status) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance_management?user=root&password=root");
			String sql= "INSERT INTO student_att VALUES(?,?,?,?,?)";
			PreparedStatement ps= con.prepareStatement(sql);
			ps.setInt(1,id);
			ps.setString(2,name);
			ps.setInt(3,age);
			ps.setString(4,gender);
			ps.setString(5, status);
			int res=ps.executeUpdate();
			con.close();
			return res;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	public static int updateStudent(int id, String name, int age, String gender, String status) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance_management?user=root&password=root");
            String sql = "UPDATE student_att SET name=?, age=?, gender=?, status=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            ps.setString(4, status);
            ps.setInt(5, id);
            int res = ps.executeUpdate();
            con.close();
            return res;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
	
	public static Student getStudentById(int id) {
        Student student = null;
        try {
        	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance_management?user=root&password=satya@2000rb");
        		
            String sql = "SELECT * FROM student_att WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                String status = rs.getString("status");
                student = new Student(id, name, age, gender, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }



}

