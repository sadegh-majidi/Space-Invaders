package sample.menus;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.model.Database;

public class UiManager extends Application {
    public static MainMenu mainMenu;
    public static SignUpMenu signUpMenu;
    public static SignInMenu signInMenu;
    public static GameMenu gameMenu;
    public static ScoreBoardMenu scoreBoardMenu;
    public static SettingMenu settingMenu;

    private static Stage window;

    static {
        mainMenu = new MainMenu();
        signInMenu = new SignInMenu();
        signUpMenu = new SignUpMenu();
        gameMenu = new GameMenu();
        scoreBoardMenu = new ScoreBoardMenu();
        settingMenu = new SettingMenu();
    }

    public static Stage getWindow() {
        return window;
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        window.setTitle("Space Invaders");
        window.getIcons().add(new Image("file:pics\\spaceIcon.jpg"));
        stage.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        mainMenu.changeScene();
        window.show();
    }

    public static void closeProgram() {
        boolean result = ConfigurationPage.showPage("Exit", "Are you sure to exit?");
        if (result) {
            Database.saveUsers();
            window.close();
        }
    }
}
