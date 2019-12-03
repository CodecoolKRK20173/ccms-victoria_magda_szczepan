package users;

import controllers.StudentController;
import view.View;
import java.util.Map;

public class Student extends User{
    @Override
    public void runController() {
        StudentController controller = new StudentController();
        controller.run(this);
    }
    public void viewGrades(){
        View.displayGrades(new Map<String,Integer>());
    }
    public void submitAnAssigment(){

    }
}
