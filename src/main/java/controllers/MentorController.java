package controllers;

import users.Mentor;
import view.View;

public class MentorController {
    public void run(Mentor mentor){
        System.out.println("YOU ARE A MENTOR");
        String[] options = {"View a list of students.","Add an assignment.","Grade an assignment.",
                "Check attendance.", "Add a student.", "Remove a student.",
                "Edit student's data", "Logout."};
        View.displayMenu(options);
        int userChoice = View.getUserChoice(options.length);
        switch (userChoice){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                LoginController controller = new LoginController();
                controller.logOut();
                break;

        }

    }

    private void viewStudents(){

    }

    private void addAnAssigment(){

    }

    private void gradeAssigment(){

    }

    private void checkAttendance(){

    }

    private void addStudent(){

    }

    private void removeStudent(){

    }

    private void editStudent(){

    }


}
