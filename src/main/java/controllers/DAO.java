package controllers;

import users.User;
import java.util.List;

public interface DAO {
    public void addUser(User user);
    public void editUser(User user);
    public void removeUser(User user);
    public List<User> gerUsers();
    public void addAssignment(String assignment);
    public void submitAssignment(int id);
}
