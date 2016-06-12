package com.couponproject.dbdao;

import java.sql.*;


public class MysqlConnection {
	private static String dbUrl = "jdbc:mysql://localhost:3306/coupondb";
	private static String dbUserName = "root";
	private static String dbparword = "zaq1zaq1zaq1";
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(dbUrl, dbUserName, dbparword);
	}
	
	public static String getDbUrl() {
		return dbUrl;
	}
	public static String getUserName() {
		return dbUserName;
	}
	public static String getParword() {
		return dbparword;
	}
	
	
	
	
}
