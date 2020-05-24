package com.learn;

import org.apache.commons.dbutils.ResultSetIterator;
import org.h2.tools.DeleteDbFiles;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.joining;

/**
 * Created by Shehan on 6/15/15.
 */
public class DBAsStreamTest {


    @Test
    public void testToStream() throws Exception {

        DeleteDbFiles.execute("~", "test", true);

        Class.forName("org.h2.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test");
             Statement statement = connection.createStatement()) {

            statement.execute("create table test(id int primary key, name varchar(255))");
            statement.execute("insert into test values(1, 'Hello')");

            String query = "select * from test";
            ResultSet resultSet = statement.executeQuery(query);
            Stream<Object[]> stream = StreamSupport.stream(
                    Spliterators.spliteratorUnknownSize(
                            new ResultSetIterator(resultSet), 0), false);
            stream.map(this::objects2String).forEach(System.out::println);
        }

    }

    private String objects2String(Object[] objects) {
        return Arrays.stream(objects).map(Object::toString).collect(joining(","));
    }

}