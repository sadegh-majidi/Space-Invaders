package sample.model;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Shield extends Rectangle {
    private int health;
    private boolean isAvailable;

    public Shield(double width, double height, int x, int y, Paint paint) {
        super(width, height, paint);
        this.isAvailable = true;
        this.health = 15;
        setLayoutX(x);
        setLayoutY(y);
    }

    public int getHealth() {
        return health;
    }

    public void decreaseBulletDamage() {
        this.health--;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
