package users;

import controllers.MentorController;

public class Mentor extends User{
    public void runController(){
        MentorController mentorController = new MentorController();
        mentorController.run(this);
    }

}
