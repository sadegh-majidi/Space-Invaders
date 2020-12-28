package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.menus.AlertPage;
import sample.menus.UiManager;
import sample.model.Database;
import sample.model.User;

public class SignUpMenuController extends Controller {
    @FXML
    private TextField signUpUsernameTextField;
    @FXML
    private PasswordField signUpPasswordTextField;

    public void signUpMenuRegisterButtonPressed() {
        String username = signUpUsernameTextField.getText();
        String password = signUpPasswordTextField.getText();
        signUpUsernameTextField.clear();
        signUpPasswordTextField.clear();
        if (username.length() == 0 || password.length() == 0)
            AlertPage.showPage("Error", "register process failed a field is empty.");
        else if (AuthenticationChecker.isExistUserWithThisUsername(username))
            AlertPage.showPage("Error", "This username is taken already.");
        else {
            AuthenticationChecker.getAllUsers().add(new User(username, password));
            Database.saveUsers();
            AlertPage.showPage("Registered", "you are successfully registered. you can login and enjoy game =)");
            UiManager.mainMenu.changeScene();
        }
    }

    public void signUpMenuBackButtonPressed() {
        UiManager.mainMenu.changeScene();
    }
}
