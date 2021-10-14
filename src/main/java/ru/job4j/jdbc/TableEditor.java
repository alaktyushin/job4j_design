package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private final String configFile = "./src/main/resources/app.properties";
    private final Properties properties;
    private Connection connection;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            properties.load(new FileInputStream(configFile));
            System.out.println("Properties loaded.");
            Class.forName(properties.getProperty("driver_class"));
            String url = properties.getProperty("url");
            String login = properties.getProperty("username");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, login, password);
            System.out.println("Connection to database established.");
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    private void executeSQLQuery (String query) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
            System.out.println("Query executed successfully: " + query );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        String sql = "create table if not exists " + tableName +  "();";
        executeSQLQuery(sql);
        System.out.println(getTableScheme(connection, tableName));
    }

    public void dropTable(String tableName){
        String sql = "drop table " + tableName +  ";";
        executeSQLQuery(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = "alter table " + tableName + " add column if not exists " + columnName + " " + type + ";";
        executeSQLQuery(sql);
        System.out.println(getTableScheme(connection, tableName));
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = "alter table " + tableName + " drop column if exists " + columnName + ";";
        executeSQLQuery(sql);
        System.out.println(getTableScheme(connection, tableName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = "alter table " + tableName + " rename column " + columnName + " to " + newColumnName + ";";
        executeSQLQuery(sql);
        System.out.println(getTableScheme(connection, tableName));
    }

    public static String getTableScheme(Connection connection, String tableName) {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        TableEditor tableEditor = new TableEditor(new Properties());
        tableEditor.createTable("table1");
        tableEditor.addColumn("table1", "id", "serial");
        tableEditor.addColumn("table1", "name", "varchar(255)");
        tableEditor.renameColumn("table1", "name", "name1");
        tableEditor.dropColumn("table1", "name1");
        tableEditor.dropColumn("table1", "name");
        tableEditor.dropTable("table1");
    }
}