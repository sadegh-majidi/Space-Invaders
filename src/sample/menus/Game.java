package sample.menus;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.model.*;

import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class Game extends Application {
    private Pane root = new Pane();
    private PlayerShip player;
    private Label scoreLabel = new Label("Score : 0");
    private boolean isShootingAllowed = true;
    private boolean bonusActive = false;
    private double time = 0.0;
    private double gettingBonusTimer = 0.0;
    private double bonusActiveTime = 0.0;
    private int score = 0;
    private boolean rightMove = true;
    Timer enemyHorizontalMoveTimer = new Timer();
    Timer enemyVerticalMoveTimer = new Timer();
    public static boolean startWithoutUser;
    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            updateContent();
        }
    };
    Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(initializeLayout(), 750, 500);
        processKeys(scene);
        stage.setResizable(false);
        stage.setScene(scene);
    }

    private void processKeys(Scene scene) {
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case RIGHT:
                    player.moveRight();
                    break;
                case LEFT:
                    player.moveLeft();
                    break;
                case SPACE:
                    if (player.isAlive() && isShootingAllowed) {
                        player.shoot(root);
                        if (bonusActive)
                            player.improveShoot(root);
                        isShootingAllowed = false;
                    }
                    break;
            }
        });
    }

    private Parent initializeLayout() {
        root.setPrefSize(750, 500);
        player = new PlayerShip(350, 460, 32, 16);
        root.getChildren().add(player);
        setBackgroundImage();
        timer.start();
        enemyHorizontalMoveTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                setMovementOrientation();
                moveEnemyHorizontally();
            }
        }, 1500, 1500);
        enemyVerticalMoveTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                moveEnemyVertically();
            }
        }, 7000, 7000);
        scoreLabel.setFont(Font.font(17));
        scoreLabel.setTextFill(Color.YELLOW);
        scoreLabel.setLayoutX(45);
        scoreLabel.setLayoutY(25);
        root.getChildren().add(scoreLabel);
        addEnemiesToScreen();
        createShields();
        return root;
    }

    private void setBackgroundImage() {
        Image image = new Image("file:pics\\gameBackground.jpg");
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundimage);
        root.setBackground(background);
    }

    private void createShields() {
        for (int i = 0; i < 3; i++) {
            root.getChildren().add(new Shield(50, 30, 150 + 200 * i, 380, Color.BLUE));
        }
    }

    private void addEnemiesToScreen() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 4; j += 2)
                root.getChildren().add(new EnemyShip(115 + 70 * i, 90 + 50 * j, 27, 24, false));
            for (int j = 0; j < 4; j += 2) {
                EnemyShip enemy = new EnemyShip(115 + 70 * i, 140 + 50 * j, 25, 25, true);
                root.getChildren().add(enemy);
            }
        }
    }

    private void updateContent() {
        time += 0.015;
        gettingBonusTimer += 0.015;
        if (bonusActive)
            bonusActiveTime += 0.015;
        updateBullets();
        updateEnemies();
        updateShields();
        updateScreen();
        updateBonuses();
        if (time > 2)
            time = 0;
        if (bonusActive && bonusActiveTime > 5) {
            bonusActiveTime = 0;
            bonusActive = false;
        }
        if (gettingBonusTimer > 20) {
            createBonusItem();
            gettingBonusTimer = 0.0;
        }
        checkConditionsForEndGame();
    }

    private void createBonusItem() {
        root.getChildren().add(new ImprovedGunBonus((int) (75 + 600 * Math.random()), 5));
    }

    private void checkConditionsForEndGame() {
        if (!player.isAlive()) {
            stopGame();
            afterLose();
        }
        if (getEnemiesInScreen().size() == 0) {
            stopGame();
            afterWin();
        }
    }

    private void stopGame() {
        enemyVerticalMoveTimer.cancel();
        enemyHorizontalMoveTimer.cancel();
        timer.stop();
    }

    private void afterLose() {
        if (!startWithoutUser) {
            checkForHighScore();
            UiManager.gameMenu.changeScene();
        } else {
            UiManager.mainMenu.changeScene();
        }
        AlertPage.showPage("lose", "oops, you lose :(");
    }

    private void afterWin() {
        if (!startWithoutUser) {
            checkForHighScore();
            UiManager.gameMenu.changeScene();
        } else {
            UiManager.mainMenu.changeScene();
        }
        AlertPage.showPage("win", "oh yess, you win =))");
    }

    private void checkForHighScore() {
        if (score > UiManager.gameMenu.getCurrentUser().getHighScore()) {
            UiManager.gameMenu.getCurrentUser().setHighScore(score);
            Database.saveUsers();
        }
    }

    private void updateBonuses() {
        root.getChildren().stream().filter(node -> node instanceof ImprovedGunBonus).forEach(node -> {
            ImprovedGunBonus bonus = (ImprovedGunBonus) node;
            bonus.move();
            if (bonus.isAvailable() && bonus.getLayoutY() > 500)
                bonus.setAvailable(false);
        });
    }

    private void updateScreen() {
        root.getChildren().removeIf(o -> {
            if (o instanceof Bullet)
                return !((Bullet) o).isAvailable();
            if (o instanceof Ship)
                return !((Ship) o).isAlive();
            if (o instanceof Shield)
                return !((Shield) o).isAvailable();
            if (o instanceof ImprovedGunBonus)
                return !((ImprovedGunBonus) o).isAvailable();
            return false;
        });
    }

    private void updateShields() {
        getShieldsInScreen().forEach(shield -> {
            if (shield.getHealth() == 10)
                shield.setHeight(20);
            else if (shield.getHealth() == 5)
                shield.setHeight(10);
            else if (shield.getHealth() == 0)
                shield.setAvailable(false);
        });
    }

    private void updateEnemies() {
        getEnemiesInScreen().forEach(s -> {
            if (s.getLayoutY() > player.getLayoutY() - 115) {
                player.setAlive(false);
                player.playSoundWhenKilled();
                return;
            }
            if (time > 2) {
                if (Math.random() < 0.06)
                    s.shoot(root);
            }
        });
    }

    private void updateBullets() {
        getBulletsInScreen().forEach(bullet -> {
            bullet.move();
            updateBulletWithRespectToShields(bullet);
            if (bullet.isPlayerBullet()) {
                updatePlayerBullet(bullet);
                checkConditionsForAllowingShooting(bullet);
            } else
                updateEnemyBullets(bullet);
        });
    }

    private void updateBulletWithRespectToShields(Bullet bullet) {
        getShieldsInScreen().forEach(shield -> {
            if (bullet.getBoundsInParent().intersects(shield.getBoundsInParent())) {
                shield.decreaseBulletDamage();
                bullet.setAvailable(false);
            }
        });
    }

    private void updatePlayerBullet(Bullet bullet) {
        getEnemiesInScreen().forEach(enemySprite -> {
            if (bullet.getBoundsInParent().intersects(enemySprite.getBoundsInParent())) {
                enemySprite.setAlive(false);
                enemySprite.playSoundWhenKilled();
                bullet.setAvailable(false);
                score++;
                scoreLabel.setText("Score : " + score);
            }
        });
        root.getChildren().stream().filter(node -> node instanceof ImprovedGunBonus).map(
                node -> (ImprovedGunBonus) node).forEach(bonus -> {
                    if (bullet.getBoundsInParent().intersects(bonus.getBoundsInParent())) {
                        bullet.setAvailable(false);
                        bonus.setAvailable(false);
                        bonusActive = true;
                    }
                }
        );
    }

    private void checkConditionsForAllowingShooting(Bullet bullet) {
        if (!bullet.isAvailable() || bullet.getLayoutY() < 0) {
            root.getChildren().remove(bullet);
            isShootingAllowed = true;
        }
    }

    private void updateEnemyBullets(Bullet bullet) {
        if (bullet.getBoundsInParent().intersects(player.getBoundsInParent())) {
            player.setAlive(false);
            player.playSoundWhenKilled();
            bullet.setAvailable(false);
        }
        if (bullet.getLayoutY() > 500)
            root.getChildren().remove(bullet);
    }

    private List<EnemyShip> getEnemiesInScreen() {
        return root.getChildren().stream().filter(o -> o instanceof EnemyShip).map(o -> (EnemyShip) o).collect(Collectors.toList());
    }

    private List<Bullet> getBulletsInScreen() {
        return root.getChildren().stream().filter(o -> o instanceof Bullet).map(o -> (Bullet) o).collect(Collectors.toList());
    }

    private List<Shield> getShieldsInScreen() {
        return root.getChildren().stream().filter(o -> o instanceof Shield).map(o -> (Shield) o).collect(Collectors.toList());
    }

    private void moveEnemyVertically() {
        getEnemiesInScreen().forEach(Ship::moveDown);
    }

    private double getMinXLayoutOfEnemies() {
        return Collections.min(getEnemiesInScreen().stream().map(Node::getLayoutX).collect(Collectors.toList()));
    }

    private double getMaxXLayoutOfEnemies() {
        return Collections.max(getEnemiesInScreen().stream().map(Node::getLayoutX).collect(Collectors.toList()));
    }

    private void setMovementOrientation() {
        if (rightMove && getMaxXLayoutOfEnemies() > 670)
            rightMove = false;
        if (!rightMove && getMinXLayoutOfEnemies() < 50)
            rightMove = true;
    }

    private void moveEnemyHorizontally() {
        getEnemiesInScreen().forEach(enemySprite -> {
            if (rightMove)
                enemySprite.moveRight();
            else
                enemySprite.moveLeft();
        });
    }
}
