package controllers;

import users.Student;
import view.View;

public class StudentController {
    public void run(Student student){
        System.out.println("YOU ARE STUDENT");
        String[] options = {"Submit an assigment.","View grades.","Logout."};
        View.displayMenu(options);
        int userChoice = View.getUserChoice(options.length);
        switch (userChoice){
            case 1:
                submitAnAssigment(student);
                break;
            case 2:
                viewGrades(student);
                break;
            case 3:
                LoginController controller = new LoginController();
                controller.logOut();
        }
    }

    private void viewGrades(Student student) {
        student.viewGrades();
    }

    private void submitAnAssigment(Student student) {
        student.submitAnAssigment();
    }
}
