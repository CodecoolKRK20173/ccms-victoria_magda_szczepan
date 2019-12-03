package controllers;

import users.User;

import java.sql.*;
import java.util.List;

public class SQLController implements DAO {
    private Connection c;
    private Statement stmt;

    public void initializeDB(String statement){
        connectToSQL();
        try {
            stmt.executeUpdate(statement);
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

    @Override
    public void addUser(User user) {

    }

    @Override
    public void editUser(User user) {

    }

    @Override
    public void removeUser(User user) {

    }

    @Override
    public List<User> gerUsers() {
        return null;
    }

    @Override
    public void addAssignment(String assignment) {

    }

    @Override
    public void submitAssignment(int id) {

    }
}
