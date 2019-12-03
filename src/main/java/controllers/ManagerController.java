package controllers;

import view.View;

public class ManagerController {

    public void run() {
        System.out.println("YOU ARE MANAGER");
        String[] options = {"List mentors","List students","Add mentor", "Remove mentor", "Edit mentor", "Exit CcMs"};
        View.displayOptions(options);
        int userChoice = View.getUserChoice(options.length);
        switch (userChoice){
            case 1:
                //TODO check if method name in View is correct
                View.showList(mentorsList);
                break;
            case 2:
                //TODO check if method name in View is correct
                View.showList(studentsList);
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
    }

    private void removeMentor() {
    }

    private void addMentor() {
    }

}
