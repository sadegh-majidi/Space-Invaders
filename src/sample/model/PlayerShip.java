package sample.model;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import sample.controller.SoundManager;

public class PlayerShip extends Ship {
    public PlayerShip(int x, int y, int width, int height) {
        super(new Image("file:pics\\ship.png"), x, y, width, height);
    }

    @Override
    public void shoot(Pane root) {
        Bullet bullet = new Bullet(3, 12, (int) this.getLayoutX() + 15, (int) this.getLayoutY() - 10, Color.YELLOW, true);
        root.getChildren().add(bullet);
        bullet.playShootSound();
    }

    public void improveShoot(Pane root) {
        Bullet extraBullet1 = new Bullet(3, 12, (int) this.getLayoutX(), (int) this.getLayoutY() - 10, Color.YELLOW, true);
        Bullet extraBullet2 = new Bullet(3, 12, (int) this.getLayoutX() + 31, (int) this.getLayoutY() - 10, Color.YELLOW, true);
        root.getChildren().addAll(extraBullet1, extraBullet2);
    }

    @Override
    public void playSoundWhenKilled() {
        new Thread(() -> SoundManager.playEffects(SoundManager.EffectType.PLAYER_DEAD)).start();
    }
}
