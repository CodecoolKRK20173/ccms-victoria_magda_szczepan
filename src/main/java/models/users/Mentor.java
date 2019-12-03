package models.users;

import controllers.MentorController;

public class Mentor extends User{
    @Override
    public void runController(){
        MentorController mentorController = new MentorController();
        mentorController.run(this);
    }

}
