package users;

import controllers.StudentController;
import view.View;
import java.util.*;

public class Student extends User{
    @Override
    public void runController() {
        StudentController controller = new StudentController();
        controller.run(this);
    }
    public void viewGrades(){
        View.displayGrades(new HashMap<String,Integer>());
    }
    public void submitAnAssigment(){

    }
}
