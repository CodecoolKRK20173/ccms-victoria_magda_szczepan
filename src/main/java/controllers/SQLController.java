package controllers;

import models.users.User;
import models.users.UserFactory;
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
        return true;
    }

    public String getUserType(String login) {
        return "Student";
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void editUser(User user) {
        View.printMessage("Which " + user.getClass().getSimpleName() + " would you like to edit?");
    }

    @Override
    public void removeUser(User user) {

    }

    @Override
    public List<String> gerUsersNames() throws SQLException {
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
}
