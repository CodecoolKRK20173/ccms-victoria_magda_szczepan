package models.users;

import controllers.SQLController;
import controllers.StudentController;
import view.View;
import java.util.*;

public class Student extends User{
    public Student(String login) {
        super(login);
    }

    @Override
    public void runController() {
        StudentController controller = new StudentController();
        controller.run(this);
    }
    public void viewGrades(){
        SQLController sql = new SQLController();
        Map<String,Integer> grades = sql.getStudentGrades(getName());
        View.displayGrades(grades);
    }
    public void submitAnAssigment(){

    }
}