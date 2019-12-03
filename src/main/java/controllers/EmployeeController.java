package controllers;

import users.Employee;
import models.users.Mentor;
import view.View;

public class EmployeeController {

    public void run(Employee employee){
        System.out.println("YOU ARE AN EMPLOYEE");
        String[] options = {"View a list of students.", "Logout."};
            View.displayMenu(options);
        int userChoice = View.getUserChoice(options.length);
            switch (userChoice) {
                case 1:
                    viewStudents();
                    break;
                case 2:
                    LoginController controller = new LoginController();
                    controller.logOut();
                    break;
            }
    }

    private void viewStudents(){
            }


    }
