package com.margaret;

import java.sql.*;

public class Main {


    private static String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/";
    private static String DB_NAME = "snakes"; //TODO CHANGE TO snakes
    private static final String USER = "root";
    private static final String PASS = "itecitec"; //TODO change!
    static Statement statement = null;
    static Connection conn = null;
    static ResultSet rs = null;
    public static void main(String[] args) {
//(If needed) create database and add sample data
        setup();
        try {
//Query the database to fetch all of the data
            String getAllData = "SELECT * FROM Snakes";
            rs = statement.executeQuery(getAllData);
            SnakeModel snakeModel = new SnakeModel(rs);
//Create and show the GUI
            SnakeInfoGUI tableGUI = new SnakeInfoGUI(snakeModel);
        } catch (Exception e) {
            e.printStackTrace();
            shutdown();
        }
    }
    public static void setup() {
        try {
            try {
                String Driver = "com.mysql.jdbc.Driver";
                Class.forName(Driver);
            } catch (ClassNotFoundException cnfe) {
                System.out.println("No database drivers found. Quitting");
                System.exit(-1);
            }
// static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/test";
            conn = DriverManager.getConnection(DB_CONNECTION_URL + DB_NAME, USER, PASS);
//The first argument allows us to move both forward and backwards through the RowSet.
//The TableModel will need to do this.
//by default, you can only move forward - it's what most apps do and it's less
//resource-intensive than being able to go in both directions.
//If you set one argument, you need the other. The second one means you will
//not be modifying the data in the RowSet (we'll change this later though)
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//Create a table in the database
//Simple two-column table.
            String createTableSQL = "CREATE TABLE Snakes (species varchar(30), venom int)";
            statement.executeUpdate(createTableSQL);
            System.out.println("Created Snakes table");
//Add some data
            String addDataSQL = "INSERT INTO Snakes VALUES ('Cobra', 5)";
            statement.executeUpdate(addDataSQL);
            addDataSQL = "INSERT INTO Snakes VALUES ('Boa Constrictor', 0)";
            statement.executeUpdate(addDataSQL);
            addDataSQL = "INSERT INTO Snakes VALUES ('Python', 7)";
            statement.executeUpdate(addDataSQL);
            System.out.println("Added three rows of data");
        } catch (SQLException sqle) {
            System.out.println("The Snakes table (probably) already exists, verify with following error message.");
            System.out.println(sqle);
        }
    }
    public static void shutdown() {
//A finally block runs whether an exception is thrown or not. Close resources and tidy up whether this code worked or not.
        try {
            if (statement != null) {
                statement.close();
                System.out.println("Statement closed");
            }
        } catch (SQLException se) {
//Closing the connection could throw an exception too
            se.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close(); //Close connection to database
                System.out.println("Database connection closed");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        System.exit(0);
    }
}
