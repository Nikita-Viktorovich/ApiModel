package com.example.restapi.database;

import com.example.restapi.model.Model;
import com.example.restapi.repository.ModelDao;

import java.sql.*;

public class ModelService implements ModelDao {

    private ModelDao dao;

    private static final String jdbcURL = "jdbc:h2:file:/Users/nikita/Downloads/h2/db/model";
    private static final String jdbcUsername = "Nick";
    private static final String jdbcPassword = "9597";

    private static final String CREATE = " CREATE TABLE MODEL (\r\n" +
            " NAME VARCHAR(40),\r\n" +
            " SURNAME VARCHAR(40),\r\n" +
            " PRIMARY KEY(NAME));";

    private static final String INSERT = "INSERT INTO MODEL VALUES (?, ?);";

    private static final String SELECT = "SELECT * FROM MODEL WHERE NAME IN ( ? );";

    private static final String UPDATE = "UPDATE MODEL SET SURNAME = ? WHERE NAME = ?;";

    public ModelService(ModelDao dao) {
        this.dao = dao;
    }


    public static Connection getConnection() throws ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void createTable() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();) {
             statement.execute(CREATE);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertRecord(String name, String surname){
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateRecord(String setSurname, String name) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, setSurname);
            preparedStatement.setString(2, name);

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Model getData(String name){
        String surname = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT)) {
             preparedStatement.setString(1, name);
             ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                surname = rs.getString("surname");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (surname == null) return null;
        return new Model(name, surname);
    }

    @Override
    public Model getModel(String name){
        return getData(name);
    }

    @Override
    public void changeSurname(String surname, String name){
        updateRecord(surname,name);
    }
}

