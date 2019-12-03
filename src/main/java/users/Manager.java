package users;

import controllers.ManagerController;

public class Manager extends User {
    @Override
    public void runController() {
        ManagerController managerController = new ManagerController();
        managerController.run();
    }
}
