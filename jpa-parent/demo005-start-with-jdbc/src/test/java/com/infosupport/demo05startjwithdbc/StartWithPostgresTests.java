package com.infosupport.demo05startjwithdbc;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.*;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StartWithPostgresTests {

    @Test
    @Order(1)
    void showThatTheConnectionToPostgresIsNotNull() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/cursistdb";
        String username = "cursist";
        String password = "PaSSw0rd";
        Connection connection = DriverManager.getConnection(url, username, password);
        assertThat(connection).isNotNull();
    }

    private Connection createConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/cursistdb";
        String username = "cursist";
        String password = "PaSSw0rd";
        return DriverManager.getConnection(url, username, password);
    }

    @Test
    @Order(2)
    void dropTableCursist() throws SQLException {

        boolean execute;
        try (Connection connection = createConnection();
             Statement statement = connection.createStatement()) {
            execute = statement.execute("DROP TABLE IF EXISTS Cursist;");
        }
        assertThat(execute).isEqualTo(false);
        /*
        Returns:
            true if the first result is a ResultSet object;
            false if it is an update count or there are no results
         */

    }

    @Test
    @Order(3)
    void createTable() throws SQLException {

        int executeUpdate;
        try (Connection connection = createConnection();
             Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE Cursist " +
                    "(ID SERIAL PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " AGE            INT     NOT NULL, " +
                    " COUNTRY        CHAR(50)" +
                    ")";
        /*
        SERIAL is an autoincrement integer running from	1 to 2147483647
        The postgres integer is similar to the java int type
         */
            executeUpdate = statement.executeUpdate(sql);
        }
        assertThat(executeUpdate).isEqualTo(0);
        /*
            From Javadoc:Returns:
            either (1) the row count for SQL Data Manipulation Language (DML) statements
            or (2) 0 for SQL statements that return nothing
         */
    }



    @Test
    @Order(4)
    void insertRows() throws SQLException {
        try (Connection connection = createConnection();
             Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            System.out.println("Opened database successfully");

            int numberOfRowsInserted = 0;

            String sql = "INSERT INTO Cursist (NAME,AGE,COUNTRY) "
                    + "VALUES('Johan',43,'Nederland');";
            numberOfRowsInserted += statement.executeUpdate(sql);

            sql = "INSERT INTO Cursist (NAME,AGE,COUNTRY) "
                    + "VALUES ( 'Allen', 25, 'Texas');";
            numberOfRowsInserted += statement.executeUpdate(sql);

            sql = "INSERT INTO Cursist (NAME,AGE,COUNTRY) "
                    + "VALUES ('Teddy', 23, 'Norway');";
            numberOfRowsInserted += statement.executeUpdate(sql);

            sql = "INSERT INTO Cursist (NAME,AGE,COUNTRY) "
                    + "VALUES ('Mark', 25, 'Rich-Mond ');";
            numberOfRowsInserted += statement.executeUpdate(sql);

            assertThat(numberOfRowsInserted).isEqualTo(4);

            sql = "INSERT INTO Cursist (NAME,AGE,COUNTRY) "
                    + "VALUES('Johan',43,'Nederland') RETURNING *;";
            try (ResultSet rs = statement.executeQuery(sql)) {
                rs.next();
                assertThat(rs.getInt("id")).isEqualTo(5L);
                connection.commit();
            }
        }

    }

    @Test
    @Order(5)
    void countNumberOfRowsInTableCursist() throws SQLException {


        String sql = "SELECT COUNT(*) NumberOfRows FROM Cursist";
        try (Connection connection = createConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            resultSet.next();
            long numberOfRows = resultSet.getLong("NumberOfRows");
            assertThat(numberOfRows).isEqualTo(5);
        }


    }
}
