package users;

import controllers.StudentController;

public class Student extends User{
    @Override
    public void runController() {
        StudentController controller = new StudentController();
        controller.run(this);
    }
}
