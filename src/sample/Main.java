package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.controller.SoundManager;
import sample.menus.UiManager;
import sample.model.Database;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        new UiManager().start(primaryStage);
    }

    public static void main(String[] args) {
        Database.loadUsers();
        new Thread(SoundManager::playBackGroundSound).start();
        launch(args);
    }
}
