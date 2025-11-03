package com.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private String username;
    private String password;
    private String host;
    private int port;
    private String database;
    private Connection connection;

    public DatabaseConnector(String username, String password, String host, int port, String database) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
        this.database = database;
        try {
            connect();
        } catch (SQLException e) {
            System.err.println("Failed to establish database connection: " + e.getMessage());
        }
    }

    private void connect() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%d/%s", host, port, database);
        this.connection = DriverManager.getConnection(url, username, password);
        System.out.println("Database connection established");
    }

    public Connection getConnection() {
        return connection;
    }

    public Connection createConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%d/%s", host, port, database);
        return DriverManager.getConnection(url, username, password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
}
