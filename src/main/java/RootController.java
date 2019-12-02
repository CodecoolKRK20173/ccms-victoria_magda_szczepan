public class RootController {
    public void run() {
        LoginController loginController = new LoginControler();
        User user = loginController.run();
        user.runController();
    }
}
