package controllers;

import models.users.Mentor;
import view.View;

import java.util.List;

public class MentorController {

    SQLController sqlController = new SQLController();

    public void run(Mentor mentor){
        System.out.println("YOU ARE A MENTOR");
        String[] options = {"View a list of students.","Add an assignment.","Grade an assignment.",
                "Check attendance.", "Add a student.", "Remove a student.",
                "Edit student's data", "Logout."};
        View.displayMenu(options);
        int userChoice = View.getUserChoice(options.length);
        switch (userChoice){
            case 1:
                viewStudents();
                break;
            case 2:
                addAnAssigment();
                break;
            case 3:
                gradeAssigment();
                break;
            case 4:
                checkAttendance();
                break;
            case 5:
                addStudent();
                break;
            case 6:
                removeStudent();
                break;
            case 7:
                editStudent();
                break;
            case 8:
                LoginController controller = new LoginController();
                controller.logOut();
                break;

        }

    }

    private void viewStudents(){
        View.showPersonList(sqlController.getUsersNames("Student"));
    }

    private void addAnAssigment(){

    }

    private void gradeAssigment(){

    }

    private void checkAttendance(){

    }

    private void addStudent(){
        View.printMessage("Please provide student's name: ");
        String name = View.getUserInput();
        String[] data = {name, "Student"};
        sqlController.addUser(data);
        View.printMessage("Student " + name + " has been added.");
    }

    private void removeStudent(){
        sqlController.removeUser(getStudentNameFromStudentsList());
    }

    private void editStudent(){
        String nameToBeEdited = getStudentNameFromStudentsList();
        View.printMessage("Please provide new name: ");
        String newName = View.getUserInput();
        sqlController.editUser(nameToBeEdited, "NAME", newName);
        System.out.println("Name successfully edited from " +nameToBeEdited + " to " + newName +".");
    }

    private String getStudentNameFromStudentsList(){
        viewStudents();
        List<String> studentsList = sqlController.getUsersNames("Student");
        int chosenIndex = View.getUserChoice(studentsList.size()) - 1;
        return studentsList.get(chosenIndex);
    }


}
