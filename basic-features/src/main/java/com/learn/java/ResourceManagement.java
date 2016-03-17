package com.learn.java;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Shehan on 5/10/15.
 */
public class ResourceManagement {

    private Connection connection;

    public void doSomething() throws SQLException, MyException {
//        A SQL statement is precompiled and stored in a PreparedStatement object. This object can then be used to efficiently execute this statement multiple times.
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM my_table where a=?");
            statement.setString(1, "Test");
//            statement.setBigDecimal(1, BigDecimal.valueOf(153833.00));
//            statement.setInt(2, 110592);
            ResultSet resultSet = statement.executeQuery();
            doSomethingWithResultSet(resultSet);
            resultSet.close();
        } catch (Exception e) {
            throw e;
        }

/*        try {
            statement = connection.prepareStatement("SELECT * FROM my_table");
            ResultSet resultSet = statement.executeQuery();
            doSomethingWithResultSet(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }*/
    }

    private void doSomethingWithResultSet(ResultSet resultSet) {
//        throw new MyException();
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private class MyException extends Exception {

    }
}
