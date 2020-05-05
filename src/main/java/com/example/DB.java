package com.example;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DB {

    //private DataSource dataSource;

    public DB(){

    }

    /*
     String db(Map<String, Object> model) {
    System.out.println("DB MODEL IS " + model);
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
      stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
      ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

      ArrayList<String> output = new ArrayList<String>();
      while (rs.next()) {
        output.add("Read from DB: " + rs.getTimestamp("tick"));
      }

      model.put("records", output);
      return "db";
    } catch (Exception e) {
      model.put("message", e.getMessage());
      return "error";
    }
  }
     */

    public static void createTable(Connection connection, String tableName, Expense expense) throws SQLException {
        System.out.println("@createTable");
        Statement stmt = connection.createStatement();
        String inquiry = "CREATE TABLE IF NOT EXISTS " + tableName;
        inquiry += " (" + expense.getAttributes() +")";
        stmt.executeUpdate(inquiry);
        System.out.println("created table called " + tableName);
    }

    private static void insertExpense(Connection connection, String tableName, Expense expense) throws SQLException {
        System.out.println("@insertExpense");
        Statement stmt = connection.createStatement();
        String inquiry = "INSERT INTO " + tableName + " VALUES";
        inquiry += " (" + expense.getUserId() + ", " +
                expense.getName()+ ", " + expense.getCost()+ ", " + expense.getNote()+ ", "
                + expense.getDateOfPurchase() + ", " + expense.getId() + ")";
        System.out.println(inquiry);
        stmt.executeUpdate(inquiry);
        System.out.println("inserted expense " + expense.toString());
    }

    public static void writeToTable(String tableName, Expense expense, DataSource dataSource){
        try(Connection connection = dataSource.getConnection()){
            createTable(connection, tableName, expense);
            insertExpense(connection, tableName, expense);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);

            while (rs.next()) {
                System.out.println(rs.toString());
            }

        } catch (Exception e) {
            System.err.println("write to a table Failed " + e.getMessage());
        }
    }


}
