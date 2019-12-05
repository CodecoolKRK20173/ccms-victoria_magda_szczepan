package controllers;

import models.users.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RootController {
    public void run() {
        SQLController sql = new SQLController();
        String statement = getStatementsFromtxt("src/main/resources/dbInit.txt");
        sql.initializeDB(statement);
        LoginController loginController = new LoginController();
        User user = loginController.run();
        user.runController();
    }

    public String getStatementsFromtxt(String fileName) { //TODO change to private when tested
        File file = new File(fileName);
        String content = "";
        try {
            Scanner scan = new Scanner(file);
            while(scan.hasNext())
                content += scan.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return content;
    }
}
