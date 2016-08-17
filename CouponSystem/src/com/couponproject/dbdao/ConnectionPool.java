package com.couponproject.dbdao;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

//External connection pool
public class ConnectionPool {
	private static final String URL = "jdbc:mysql://localhost:3306/coupondb";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "zaq1zaq1zaq1";
	
	private static ConnectionPool connectionPool;
	private ComboPooledDataSource cpds;

	private ConnectionPool() throws IOException, SQLException, PropertyVetoException {
		cpds = new ComboPooledDataSource();
		cpds.setDriverClass("com.mysql.jdbc.Driver"); // loads the jdbc driver
		// Path
		cpds.setJdbcUrl(URL);
		// User name
		cpds.setUser(USER_NAME);
		// Password
		cpds.setPassword(PASSWORD);

		// the settings below are optional -- c3p0 can work with defaults
		cpds.setMinPoolSize(5);
		cpds.setAcquireIncrement(5);
		cpds.setMaxPoolSize(10);
		cpds.setMaxStatements(180);
	}

	public static ConnectionPool getInstance() throws IOException, SQLException, PropertyVetoException {
		if (connectionPool == null) {
			connectionPool = new ConnectionPool();
		}
		return connectionPool;

	}

	public Connection getConnection() throws SQLException {
		return this.cpds.getConnection();
	}
	
	public void shutDown () {
		cpds.close();
	}

}