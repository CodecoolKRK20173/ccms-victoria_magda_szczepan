package models.users;

public class UserFactory {
    public User createUser(String userName) {
        switch (userName) {
            case "Manager":
                return new Manager();
            case "Mentor":
           //     return new Mentor();
            case "Employee":
           //     return new Employee();
            case "Student":
                return new Student();
            default:
                return null;
        }
    }
}
