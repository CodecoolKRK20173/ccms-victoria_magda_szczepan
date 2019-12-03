package models.users;

import controllers.EmployeeController;

public class Employee extends User{
    @Override
    public void runController(){
        EmployeeController employeeController = new EmployeeController();
        employeeController.run(this);
    }
}
