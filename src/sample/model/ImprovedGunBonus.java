package sample.model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class ImprovedGunBonus extends Rectangle {
    boolean isAvailable;
    int x, y;

    public ImprovedGunBonus(int x, int y) {
        super(20, 20);
        this.isAvailable = true;
        this.x = x;
        this.y = y;
        setLayoutX(x);
        setLayoutY(y);
        this.setFill(new ImagePattern(new Image("file:pics\\gun.png")));
    }

    public void move() {
        this.y += 1;
        setLayoutY(y);
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
