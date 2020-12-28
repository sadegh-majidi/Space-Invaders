package sample.model;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public abstract class Ship extends Rectangle {
    private boolean alive;
    private int x;
    private int y;

    public Ship(Image image, int x, int y, int width, int height) {
        super(width, height);
        this.x = x;
        this.y = y;
        this.alive = true;
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.setFill(new ImagePattern(image));
    }

    public void setImage(Image image) {
        this.setFill(new ImagePattern(image));
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    private void updateSpriteLocation() {
        this.setLayoutX(this.x);
        this.setLayoutY(this.y);
    }

    public void moveRight() {
        if (this.x < 707)
            this.x += 7;
        updateSpriteLocation();
    }

    public void moveLeft() {
        if (this.x > 5)
            this.x -= 7;
        updateSpriteLocation();
    }

    public void moveUp() {
        if (this.y > 0)
            this.y -= 15;
        updateSpriteLocation();
    }

    public void moveDown() {
        if (this.y < 495)
            this.y += 15;
        updateSpriteLocation();
    }

    abstract public void shoot(Pane root);

    abstract public void playSoundWhenKilled();
}
