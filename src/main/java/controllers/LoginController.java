package controllers;

public class LoginController {

    private String login;
    private String password;


    public User run(){
        getUserLoginInfo();
        return tryLogin();
    }

    private User tryLogin() {
        SQLController sqlController = new SQLController();
        UserFactory userFactory = new UserFactory();
        if (sqlController.isUserDataCorrect(login, password)){
                return userFactory.createUser(sqlController.getUserType(login));
        };
        return run();
    }

    private void getUserLoginInfo() {
        View.printMessage("Login: ");
        login = View.getInput();
        View.printMessage("Password: ");
        password = View.getInput();
    }


    public void logOut() {
        RootController root = new RootController();
        root.run();
    }
}
