package controllers;


import models.users.User;
import models.users.UserFactory;
import view.View;

import view.View;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLController implements DAO {
    private Connection c;
    private Statement stmt;

    public void initializeDB(String statement){
        connectToSQL();
        try {
            stmt.executeUpdate(statement);
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void closeConnection() {
        try {
            stmt.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void connectToSQL() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/codecoolMS.db");
            c.setAutoCommit(true);

            stmt = c.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isUserDataCorrect(String login, String password) {
        return false;
    }

    public String getUserType(String login) {
        return null;
    }

    public void addUser(String[] data) {
        connectToSQL();
        String name = View.getUserInput();

        String sql = String.format("INSERT INTO USERS(NAME, TYPE_ID)VALUES('%s', 'typeID');", name);
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
    }


    @Override
    public void editUser(String login, String columnName, String data) {
        int userId = getIdByLogin(login);
        connectToSQL();
        try {
            stmt.executeUpdate(String.format("UPDATE USERS SET %s = '%s' WHERE USER_ID = '%d'", columnName, data, userId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    @Override
    public void removeUser(User user) {

    }

    @Override
    public List<String> getUsersNames() throws SQLException {
        List<String> userNames = new ArrayList<>();
        connectToSQL();
        ResultSet rs = stmt.executeQuery( "SELECT * FROM USER;" );
        while (rs.next()) {
            String name = rs.getString("NAME");
            userNames.add(name);
        }
        return userNames;
    }

    @Override
    public void addAssignment(String assignment) {

    }

    @Override
    public void submitAssignment(int id) {

    }

    public void removeUser(String login){
        int Id = getIdByLogin(login);
        connectToSQL();
        try {
            stmt.executeUpdate(String.format("DELETE FROM USER WHERE USER_ID = %d;",Id));
            stmt.executeUpdate(String.format("DELETE FROM LOGIN WHERE USER_ID = %d;",Id));
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private int getIdByLogin(String login) {
        connectToSQL();
        try {
            ResultSet rs = stmt.executeQuery(String.format("SELECT USER_ID FROM LOGIN WHERE login = '%s'", login));
            final int user_id = rs.getInt("USER_ID");
            closeConnection();
            return user_id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
