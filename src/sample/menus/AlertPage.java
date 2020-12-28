package sample.menus;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertPage {
    public static void showPage(String title, String comment) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.getIcons().add(new Image("file:pics\\spaceIcon.jpg"));
        window.setResizable(false);
        window.setTitle(title);
        window.setMinWidth(200);
        VBox vBox = new VBox(30);
        Label label = new Label(comment);
        label.setTextFill(Color.web("#ff0000", 0.8));
        label.setFont(Font.font("georgia", FontWeight.BOLD, 15));
        Button button = new Button("Ok");
        button.setOnAction(e -> window.close());
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(label, button);
        Scene scene = new Scene(vBox, 500, 150);
        window.setScene(scene);
        window.show();
    }
}
