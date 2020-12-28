package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.menus.AlertPage;
import sample.menus.UiManager;

public class LoginMenuController extends Controller {
    @FXML
    private TextField signInUsernameTextField;
    @FXML
    private PasswordField signInPasswordTextField;

    public void signInMenuBackButtonPressed() {
        UiManager.mainMenu.changeScene();
    }

    public void signInMenuLoginButtonPressed() {
        String username = signInUsernameTextField.getText();
        String password = signInPasswordTextField.getText();
        signInUsernameTextField.clear();
        signInPasswordTextField.clear();
        if (username.length() == 0 || password.length() == 0)
            AlertPage.showPage("Error", "login process failed because a field is empty.");
        else if (!AuthenticationChecker.isExistUserWithThisUsername(username))
            AlertPage.showPage("Error", "username is invalid.");
        else if (AuthenticationChecker.findUserByUsername(username) != null && !AuthenticationChecker.findUserByUsername(username).getPassword().equals(password))
            AlertPage.showPage("Error", "password is invalid.");
        else {
            UiManager.gameMenu.setCurrentUser(AuthenticationChecker.findUserByUsername(username));
            UiManager.gameMenu.changeScene();
        }
    }
}
