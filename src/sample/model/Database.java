package sample.model;

import sample.controller.AuthenticationChecker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Database {

    static final String databasePath = "src\\Database.txt";

    public static void saveUsers() {
        try {
            FileWriter fileWriter = new FileWriter(databasePath);
            for (User user : AuthenticationChecker.getAllUsers()) {
                fileWriter.write(user.getUsername() + " " + user.getPassword() + " " + user.getHighScore() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadUsers() {
        try {
            File database = new File(databasePath);
            if (!database.exists())
                database.createNewFile();
            Scanner readDatabase = new Scanner(database);
            while (readDatabase.hasNextLine()) {
                String[] details = readDatabase.nextLine().split(" ");
                User user = new User(details[0], details[1]);
                user.setHighScore(Integer.parseInt(details[2]));
                AuthenticationChecker.getAllUsers().add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
