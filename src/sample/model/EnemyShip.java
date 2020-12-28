package sample.model;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import sample.controller.SoundManager;

public class EnemyShip extends Ship {
    public EnemyShip(int x, int y, int width, int height, boolean enemyType) {
        super(new Image("file:pics\\secondEnemyType.jpg"), x, y, width, height);
        if (!enemyType)
            this.setImage(new Image("file:pics\\firstEnemyType.png"));
    }

    @Override
    public void shoot(Pane root) {
        Bullet bullet = new Bullet(3, 12, (int) getLayoutX() + 15, (int) getLayoutY() + 15, Color.RED, false);
        root.getChildren().add(bullet);
        bullet.playShootSound();
    }

    @Override
    public void playSoundWhenKilled() {
        new Thread(() -> SoundManager.playEffects(SoundManager.EffectType.ENEMY_DEAD)).start();
    }
}
