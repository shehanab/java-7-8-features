package com.learn.java;

import junit.framework.TestCase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Created by Shehan on 5/10/15.
 */
public class ResourceManagementTest extends TestCase {

    private ResourceManagement resourceManagement;
    private Connection connection;

    @Override
    public void setUp() throws Exception {
        resourceManagement = new ResourceManagement();
        connection = mock(Connection.class);
        resourceManagement.setConnection(connection);
    }

    public void testDoSomething() throws Exception {
        PreparedStatement statement = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(mock(ResultSet.class));

        resourceManagement.doSomething();

        verify(connection, times(1)).prepareStatement(anyString());
        verify(statement, times(1)).executeQuery();
        verify(statement, times(1)).close();
    }
}