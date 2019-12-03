package controllers;

import models.users.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RootController {
    public void run() {
        SQLController sql = new SQLController();
        String statement = getStatementsFromtxt();
        sql.initializeDB(statement);
        LoginController loginController = new LoginController();
        User user = loginController.run();
        user.runController();
    }

    private String getStatementsFromtxt() {
        File file = new File("src/main/resources/dbInit.txt");
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
