public class RootController {
    LoginController loginController = new LoginControler();
    User user = loginController.run();
    user.runController();
}
