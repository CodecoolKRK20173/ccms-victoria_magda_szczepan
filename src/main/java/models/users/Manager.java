package models.users;

import controllers.ManagerController;

public class Manager extends User {
    public Manager(String login) {
        super(login);
    }

    @Override
    public void runController() {
        ManagerController managerController = new ManagerController();
        managerController.run();
    }
}
