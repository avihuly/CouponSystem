package com.couponproject.dbdao;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

//External connection pool
public class ConnectionPool {
	private static final String URL = "jdbc:mysql://localhost:3306/coupondb";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "zaq1zaq1zaq1";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	
	private static ConnectionPool connectionPool;
	private AssafConnectionPool acp;

	public ConnectionPool() throws IOException, SQLException, PropertyVetoException {
		acp = new AssafConnectionPool(DRIVER, URL, USER_NAME, PASSWORD, 5, 10, true);
	}

	public static ConnectionPool getInstance() throws IOException, SQLException, PropertyVetoException {
		if (connectionPool == null) {
			connectionPool = new ConnectionPool();
		}
		return connectionPool;
	}

	public Connection getConnection() throws SQLException {
		return this.acp.getConnection();
	}
	
	public void shutDown () {
		acp.closeAllConnections();
	}

}