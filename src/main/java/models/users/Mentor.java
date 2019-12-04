package models.users;

import controllers.MentorController;

public class Mentor extends User{
    public Mentor(String login) {
        super(login);
    }

    @Override
    public void runController(){
        MentorController mentorController = new MentorController();
        mentorController.run(this);
    }

}
