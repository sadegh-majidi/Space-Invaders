package sample.menus;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainMenu implements IMenu {
    List<IMenu> childMenus;

    public MainMenu() {
        childMenus = new ArrayList<>();
        childMenus.add(new SignInMenu());
        childMenus.add(new SignUpMenu());
    }

    @Override
    public void changeScene() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            UiManager.getWindow().setResizable(true);
            UiManager.getWindow().setScene(new Scene(parent, 600, 400));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
