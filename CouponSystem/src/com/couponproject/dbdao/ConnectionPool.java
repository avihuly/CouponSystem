package com.couponproject.dbdao;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

//External connection pool
public class ConnectionPool {

    private static ConnectionPool     connectionPool;
    private ComboPooledDataSource cpds;

    private ConnectionPool() throws IOException, SQLException, PropertyVetoException {
        cpds = new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.jdbc.Driver"); //loads the jdbc driver
        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/coupondb");
        cpds.setUser("root");
        cpds.setPassword("zaq1zaq1zaq1");

        // the settings below are optional -- c3p0 can work with defaults
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(10);
        cpds.setMaxStatements(180);

    }

    public static ConnectionPool getInstance() throws IOException, SQLException, PropertyVetoException {
        if (connectionPool == null) {
        	connectionPool = new ConnectionPool();
            return connectionPool;
        } else {
            return connectionPool;
        }
    }

    public Connection getConnection() throws SQLException {
        return this.cpds.getConnection();
    }

}