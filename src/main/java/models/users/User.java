package models.users;

public abstract class User {
    private String name;
    public User(String login){
        name = login;
    }
    public abstract void runController();
    public String getName(){
        return name;
    }
}
