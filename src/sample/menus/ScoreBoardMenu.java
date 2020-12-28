package sample.menus;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class ScoreBoardMenu implements IMenu {
    @Override
    public void changeScene() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("ScoreBoard.fxml"));
            UiManager.getWindow().setResizable(true);
            UiManager.getWindow().setScene(new Scene(parent, 600, 400));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
