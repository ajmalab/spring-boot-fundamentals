package com.wise.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private String username;
    private String password;
    private String name;
    private String mode;
    private Connection connection;

    public DatabaseConnector() {
        // Empty constructor for Spring initialization
        // Connection will be created after properties are set
    }

    public void init() {
        try {
            connect();
        } catch (SQLException e) {
            System.err.println("Failed to establish database connection: " + e.getMessage());
        }
    }

    private void connect() throws SQLException {
        // H2 connection URL format: jdbc:h2:mem:testdb or jdbc:h2:file:./data/testdb
        String url = String.format("jdbc:h2:%s:%s", mode, name);
        this.connection = DriverManager.getConnection(url, username, password);
        System.out.println("Database connection established");
    }

    public Connection getConnection() {
        return connection;
    }

    public Connection createConnection() throws SQLException {
        // H2 connection URL format: jdbc:h2:mem:testdb or jdbc:h2:file:./data/testdb
        String url = String.format("jdbc:h2:%s:%s", mode, name);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    // Deprecated methods for backward compatibility
    @Deprecated
    public String getDatabase() {
        return name;
    }

    @Deprecated
    public void setDatabase(String database) {
        this.name = database;
    }
}
