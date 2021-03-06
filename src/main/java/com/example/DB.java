package com.example;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DB {

    public DB(){

    }

    public static void createTable(Connection connection, String tableName, Expense expense) throws SQLException {
        Statement stmt = connection.createStatement();
        String inquiry = "CREATE TABLE IF NOT EXISTS " + tableName;
        inquiry += " (" + expense.getAttributes() +")";
        stmt.executeUpdate(inquiry);
    }

    private static void insertExpense(Connection connection, String tableName, Expense expense) throws SQLException {
        Statement stmt = connection.createStatement();
        String inquiry = "INSERT INTO " + tableName + " (userid, name, cost, note, dateofpurchase, id) VALUES";
        inquiry += " (" + "'" + expense.getUserId() + "'" + ", " +
                "'" + expense.getName() + "'" + ", " + expense.getCost() + ", " + "'" + expense.getNote() + "'" + ", "
                + "'" + expense.getDateOfPurchase() + "'" + ", " + "'" + expense.getId() + "'" +
                ")"; // TODO: clean this up
        System.out.println(inquiry);
        stmt.executeUpdate(inquiry);
        System.out.println("inserted expense " + expense.toString());
    }

    public static void writeToTable(String tableName, Expense expense, DataSource dataSource) {
        try(Connection connection = dataSource.getConnection()) {
            createTable(connection, tableName, expense);
            insertExpense(connection, tableName, expense);
        } catch (Exception e) {
            System.err.println("write to a table Failed " + e.getMessage());
        }
    }

    public static ArrayList<Expense> getExpenses(String userId, String tableName, DataSource dataSource) {
        try(Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            String queryString = getSelectQueryString("*", tableName, userId);
            ResultSet rs = stmt.executeQuery(queryString);

            return DB.formatGetExpensesQueryResults(rs);

        } catch (Exception e) {
            System.err.println("getting expenses failed " + e.getMessage());
        }

        return null;
    }

    private static String getSelectQueryString(
            String selectString,
            String tableName,
            String userId)
    {
        String queryString = "SELECT" + " " + selectString + " " + tableName + " WHERE " +
                "userId =" + " " + "'" + userId + "'";

        return queryString;
    }

    public static ArrayList<Expense> formatGetExpensesQueryResults(ResultSet rs) throws SQLException {
        ArrayList<Expense> expenses = new ArrayList<>();

        while (rs.next()) {
            expenses.add(new Expense(
                    rs.getString("userId"),
                    rs.getString("name"),
                    rs.getDouble("cost"),
                    rs.getString("note"),
                    rs.getString("dateOfPurchase")
            ));
        }

        System.out.println("expenses is " + expenses.toString());

        return expenses;
    }


}
