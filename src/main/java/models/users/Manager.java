package models.users;

import controllers.ManagerController;

import java.sql.SQLException;

public class Manager extends User {
    public Manager(String login) {
        super(login);
    }

    @Override
    public void runController() {
        ManagerController managerController = null;
        try {
            managerController = new ManagerController();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        managerController.run();
    }
}
