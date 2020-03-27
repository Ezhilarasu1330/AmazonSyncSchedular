package com.example.amazonsync.Service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class DatabaseConnectionService {

    private String jdbcURL = "jdbc:mysql://localhost:3306/AMAZON_SYNC";
    private String userName = "root";
    private String password = "";

    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Connection getDatabaseConnection() throws ClassNotFoundException, SQLException {

        System.out.println( "Database object created" );
        Class.forName( "com.mysql.cj.jdbc.Driver" );
        Connection connection = DriverManager.getConnection( this.jdbcURL, this.userName, this.password );
        return connection;

    }
}