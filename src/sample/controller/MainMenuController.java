package sample.controller;

import sample.menus.Game;
import sample.menus.UiManager;

public class MainMenuController extends Controller {
    public void signInButtonPressed() {
        UiManager.signInMenu.changeScene();
    }

    public void signUpButtonPressed() {
        UiManager.signUpMenu.changeScene();
    }

    public void playButtonMainMenuPressed() {
        try {
            Game.startWithoutUser = true;
            new Game().start(UiManager.getWindow());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
