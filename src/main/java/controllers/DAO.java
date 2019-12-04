package controllers;

import models.users.User;

import java.sql.SQLException;
import java.util.List;

public interface DAO {
    public void addUser(User user);
    public void editUser(User user);
    public void removeUser(User user);
    public List<String> gerUsersNames() throws SQLException;
    public void addAssignment(String assignment);
    public void submitAssignment(int id);
}
