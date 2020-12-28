package sample.model;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import sample.controller.SoundManager;

public class Bullet extends Rectangle {
    boolean isAvailable;
    int x, y;
    boolean isPlayerBullet;

    public Bullet(double width, double height, int x, int y, Paint paint, boolean type) {
        super(width, height, paint);
        this.isAvailable = true;
        this.x = x;
        this.y = y;
        this.isPlayerBullet = type;
        setLayoutX(x);
        setLayoutY(y);
    }

    public void move() {
        if (isPlayerBullet)
            this.y -= 5;
        else
            this.y += 5;
        setLayoutY(y);
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isPlayerBullet() {
        return isPlayerBullet;
    }

    public void playShootSound() {
        new Thread(() -> SoundManager.playEffects(SoundManager.EffectType.SHOOT)).start();
    }
}
