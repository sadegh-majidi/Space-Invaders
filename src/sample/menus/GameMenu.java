package sample.menus;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import sample.model.User;

import java.io.IOException;

public class GameMenu implements IMenu {
    private User currentUser;

    @Override
    public void changeScene() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("GameMenu.fxml"));
            UiManager.getWindow().setResizable(true);
            UiManager.getWindow().setScene(new Scene(parent, 600, 400));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public String getUserScore() {
        return Integer.toString(currentUser.getHighScore());
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
