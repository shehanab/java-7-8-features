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

    private static final String sql = "SELECT * FROM my_table where a=?";

    public void doSomething() throws SQLException, MyException {
//        All classes implementing java.lang.AutoCloseabl interface can be used inside the try-with-resources construct.
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            PreparedStatement speeds up query execution, by decreasing the parsing and query planning overhead of each execution.
            statement.setString(1, "Test");
//            statement.setBigDecimal(1, BigDecimal.valueOf(153833.00));
//            statement.setInt(2, 110592);
            ResultSet resultSet = statement.executeQuery();
            doSomethingWithResultSet(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            throw e;
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
