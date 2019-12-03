package controllers;

import models.users.User;
import java.util.List;

public interface DAO {
    public void addUser(String[] data);
    public void editUser(User user);
    public void removeUser(User user);
    public List<User> gerUsers();
    public void addAssignment(String assignment);
    public void submitAssignment(int id);
}
