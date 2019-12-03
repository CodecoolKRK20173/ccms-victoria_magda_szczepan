package controllers;

import controllers.LoginController;

public class RootController {
    public void run() {
        LoginController loginController = new LoginController();
        User user = loginController.run();
        user.runController();
    }
}
