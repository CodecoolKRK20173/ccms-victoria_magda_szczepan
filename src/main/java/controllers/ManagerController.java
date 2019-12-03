package controllers;

import users.Mentor;
import users.Student;
import view.View;

import java.util.List;

public class ManagerController {

    private List<String> mentorsNameList;
    private List<String> studentsNameList;
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

    private void getMentorsList() {
        for (Mentor mentor: mentors) {
            mentorsNameList.add(mentor.getName());
        }
    }

    private void getStudentsList() {
        for (Student student: students) {
            mentorsNameList.add(student.getName());
        }
    }

    private void editMentor() {
        View.printMessage("Which mentor would you like to edit?");
        View.showPersonList(mentorsNameList);
        int mentorToEdit = View.getUserChoice(mentorsNameList.size());

        String[] options = {"Name"};

        int valueToEdit = choseValueToEdit();
        if (valueToEdit == 1) {
            for (int i = 0; i < mentors.size(); i++) {
                mentors.get(mentorToEdit).setName();
            }
        } else {
            View.printMessage("Invalid input");
            choseValueToEdit();
        }

    }

    private int choseValueToEdit() {
        View.printMessage("What would you like to change?");
        return View.getUserChoice(options.length);
    }

    private void removeMentor() {
    }

    private void addMentor() {
    }

}
