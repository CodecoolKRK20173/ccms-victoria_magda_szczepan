package controllers;

import models.users.Manager;
import models.users.Mentor;
import models.users.User;
import users.User;
import view.View;

import java.sql.*;
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

    @Override
    public void addUser(String[] data) {

        connectToSQL();
        View.printMessage("Please provide student's name: ");
        String name = View.getUserInput();

        String sql = "INSERT INTO USERS(NAME, TYPE_ID)" +
                "VALUES('"+name+"', 'typeID');";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
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
