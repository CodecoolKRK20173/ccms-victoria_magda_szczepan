package models.users;

import controllers.EmployeeController;
import models.users.User;

public class Employee extends User {
    public Employee(String login) {
        super(login);
    }

    @Override
    public void runController(){
        EmployeeController employeeController = new EmployeeController();
        employeeController.run(this);
    }
}
