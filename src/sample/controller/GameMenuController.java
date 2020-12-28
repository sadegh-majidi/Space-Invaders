package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import sample.menus.Game;
import sample.menus.UiManager;
import sample.model.Database;

import java.net.URL;
import java.util.ResourceBundle;

public class GameMenuController extends Controller implements Initializable {
    @FXML
    private Label gameMenuUsername;
    @FXML
    private Label gameMenuScore;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameMenuUsername.setText(UiManager.gameMenu.getCurrentUser().getUsername());
        gameMenuScore.setText(UiManager.gameMenu.getUserScore());
    }

    public void gameMenuPlayButtonPressed() {
        try {
            Game.startWithoutUser = false;
            new Game().start(UiManager.getWindow());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gameMenuSettingButtonPressed() {
        UiManager.settingMenu.changeScene();
    }

    public void gameMenuScoreBoardButtonPressed() {
        UiManager.scoreBoardMenu.changeScene();
    }

    public void gameMenuLogoutButtonPressed() {
        Database.saveUsers();
        UiManager.gameMenu.setCurrentUser(null);
        UiManager.mainMenu.changeScene();
    }
}
