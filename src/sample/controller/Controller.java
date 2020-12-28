package sample.controller;

import sample.menus.AlertPage;
import sample.menus.UiManager;

public abstract class Controller {
    public void exitFromAppAndSave() {
        UiManager.closeProgram();
    }

    public void showAbout() {
        AlertPage.showPage("About", "This game is developed by sadegh majidi =)");
    }
}
