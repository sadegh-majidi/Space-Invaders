package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.menus.UiManager;
import sample.model.User;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class ScoreBoardController extends Controller implements Initializable {
    @FXML
    private TableView<User> scoreBoardTable;
    @FXML
    private TableColumn<User, String> userColumn;
    @FXML
    private TableColumn<User, Integer> scoreColumn;

    public void scoreboardMenuBackButtonPressed() {
        UiManager.gameMenu.changeScene();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Collections.sort(AuthenticationChecker.getAllUsers());
        ObservableList<User> users = FXCollections.observableList(AuthenticationChecker.getAllUsers());
        userColumn.setCellValueFactory(new PropertyValueFactory<>("Username"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("HighScore"));
        scoreBoardTable.setItems(users);
    }
}
