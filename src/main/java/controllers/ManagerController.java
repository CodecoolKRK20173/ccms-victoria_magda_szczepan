package controllers;

import models.users.Mentor;
import models.users.Student;
import view.View;

import java.sql.SQLException;
import java.util.List;

public class ManagerController {

    SQLController sqlController = new SQLController();
    private List<String> mentorsNameList = sqlController.getUsersNames("Mentor");
    private List<String> studentsNameList = sqlController.getUsersNames("Student");;
    private List<Mentor> mentors;
    private List<Student> students;
    
    public void run() {
        System.out.println("YOU ARE MANAGER");
        String[] options = {"List mentors","List students","Add mentor", "Remove mentor", "Edit mentor", "Exit CcMs"};
        View.displayMenu(options);
        int userChoice = View.getUserChoice(options.length);
        switch (userChoice){
            case 1:
                View.showPersonList(mentorsNameList);
                break;
            case 2:
                View.showPersonList(studentsNameList);
                break;
            case 3:
                addMentor();
                break;
            case 4:
                removeMentor();
                break;
            case 5:
                editMentor();
                break;
            case 6:
                LoginController controller = new LoginController();
                controller.logOut();
        }
    }

    private void editMentor() {
        View.printMessage("Which mentor would you like to edit?");
        View.showPersonList(mentorsNameList);
        int userChoice = View.getUserChoice(mentorsNameList.size());
        String mentorLogin = mentorsNameList.get(userChoice - 1);

        String column = "";

        String[] options = {"Name"};

        int valueToEdit = choseValueToEdit(options);
        if (valueToEdit == 1) {
            column = "NAME";
        } else {
            View.printMessage("Invalid input");
            choseValueToEdit(options);
        }

        String newData = View.getUserInput();
        sqlController.editUser(mentorLogin, column, newData);
    }

    private int choseValueToEdit(String[] options) {
        View.printMessage("What would you like to change?");
        return View.getUserChoice(options.length);
    }

    private void removeMentor() {
        View.showPersonList(mentorsNameList);
        View.printMessage("Which mentor would you like to remove?");
        int userChoice = View.getUserChoice(mentorsNameList.size());
        String mentorToRemove = mentorsNameList.get(userChoice - 1);
        sqlController.removeUser(mentorToRemove);
    }

    private void addMentor() {
        View.printMessage("Please, provide mentor's name:");
        String name = View.getLoginInput();
        String [] data = {name, "Mentor"};
        sqlController.addUser(data);
    }

}
