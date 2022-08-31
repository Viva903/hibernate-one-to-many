package com.viva903.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb-03-one-to-many?useSSL=false&serverTimezone=UTC";
		String user ="hbstudent";
		String password = "hbstudent";
		try {
			System.out.println("Connection to database : " + jdbcUrl);
			
			Connection myConnection = DriverManager.getConnection(jdbcUrl, user, password);
			
			System.out.println("Connection Successful !!!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
