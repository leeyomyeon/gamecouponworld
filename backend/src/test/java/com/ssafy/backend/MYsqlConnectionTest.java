package com.ssafy.backend;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;



public class MYsqlConnectionTest {
	
	private static final String Driver="com.mysql.cj.jdbc.Driver";
	private static final String URL="jdbc:mysql://127.0.0.1:3306/test";
	private static final String USER="root";
	private static final String PW="ssafy";
	
	@Test
	public void testConnection() throws ClassNotFoundException {
		Class.forName(Driver);
		try(Connection con = DriverManager.getConnection(URL,USER,PW)){
			System.out.println(con);
		}catch(Exception e) {
			System.err.println(e);
		}
	}

}
