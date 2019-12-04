package controllers;

import models.users.User;

import java.sql.SQLException;
import java.util.List;

public interface DAO {
    public void addUser(String[] data);
    public void editUser(String login, String columnName, String data);
    public void removeUser(User user);
    public List<String> getUsersNames() throws SQLException;
    public void addAssignment(String assignment);
    public void submitAssignment(int id);
}
