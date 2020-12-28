package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.menus.AlertPage;
import sample.menus.UiManager;
import sample.model.Database;

public class SettingMenuControllers extends Controller {
    @FXML
    private TextField changeUsernameField;
    @FXML
    private PasswordField changePasswordField;

    public void settingMenuBackButtonPressed() {
        UiManager.gameMenu.changeScene();
    }

    public void settingMenuChangeUsernameButtonPressed() {
        String username = changeUsernameField.getText();
        changeUsernameField.clear();
        if (username.length() == 0)
            AlertPage.showPage("Error", "change username process failed because username field is empty.");
        else if (UiManager.gameMenu.getCurrentUser().getUsername().equals(username))
            AlertPage.showPage("Error", "this is your former username.");
        else if (AuthenticationChecker.isExistUserWithThisUsername(username))
            AlertPage.showPage("Error", "username is already taken");
        else {
            UiManager.gameMenu.getCurrentUser().setUsername(username);
            Database.saveUsers();
            UiManager.gameMenu.changeScene();
        }
    }

    public void settingMenuChangePasswordButtonPressed() {
        String password = changePasswordField.getText();
        changePasswordField.clear();
        if (password.length() == 0)
            AlertPage.showPage("Error", "change password process failed because password field is empty.");
        else if (UiManager.gameMenu.getCurrentUser().getPassword().equals(password))
            AlertPage.showPage("Error", "this is your former password.");
        else {
            UiManager.gameMenu.getCurrentUser().setPassword(password);
            Database.saveUsers();
            UiManager.gameMenu.changeScene();
        }
    }
}
