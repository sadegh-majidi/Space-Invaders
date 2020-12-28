package sample.menus;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfigurationPage {
    private static boolean result;

    public static boolean showPage(String title, String comment) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.getIcons().add(new Image("file:pics\\spaceIcon.jpg"));
        window.setResizable(false);
        window.setTitle(title);
        window.setMinWidth(250);
        VBox vBox = new VBox(15);
        HBox hBox = new HBox(20);
        Button buttonYes = new Button("yes");
        Button buttonNo = new Button("no");

        buttonYes.setOnAction(e -> {
            result = true;
            window.close();
        });
        buttonNo.setOnAction(e -> {
            result = false;
            window.close();
        });
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(buttonYes, buttonNo);
        Label label = new Label(comment);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(label, hBox);
        Scene scene = new Scene(vBox, 200, 100);
        window.setScene(scene);
        window.showAndWait();
        return result;
    }
}
