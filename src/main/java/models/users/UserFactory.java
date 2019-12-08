package models.users;

public class UserFactory {
    public User createUser(String userType,String login) {
        switch (userType) {
            case "Manager":
                return new Manager(login);
            case "Mentor":
                return new Mentor(login);
            case "Employee":
                return new Employee(login);
            case "Student":
                return new Student(login);
            default:
                return null;
        }
    }
}
