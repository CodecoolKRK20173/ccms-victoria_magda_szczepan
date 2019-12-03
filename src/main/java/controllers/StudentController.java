package controllers;

import users.Student;

public class StudentController {
    public void run(Student student){
        System.out.println("YOU ARE STUDENT");
        String[] options = {"Submit an assigment.","View grades.","Logout."};
        View.displayMenu(options);
        int userChoice = View.getUserChoice(options.length);
        switch (userChoice){
            case 1:
                break;
            case 2:
                break;
            case 3:
                LoginController controller = new LoginController();
                controller.logOut();
        }
    }
}
