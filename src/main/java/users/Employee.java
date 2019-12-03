package users;

import controllers.EmployeeController;
import models.users.User;

public class Employee extends User {
    @Override
    public void runController(){
        EmployeeController employeeController = new EmployeeController();
        employeeController.run(this);
    }
}
