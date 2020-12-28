package sample.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class SoundManager {
    public enum EffectType {
        PLAYER_DEAD,
        ENEMY_DEAD,
        SHOOT
    }

    public synchronized static void playEffects(EffectType effect) {
        switch (effect) {
            case SHOOT:
                playShootSound();
                break;
            case PLAYER_DEAD:
                playPlayerDeathSound();
                break;
            case ENEMY_DEAD:
                playEnemyDeathSound();
                break;
        }
    }

    private static void playShootSound() {
        String path = "audio\\shoot.wav";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    private static void playEnemyDeathSound() {
        String path = "audio\\invaderkilled.wav";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    private static void playPlayerDeathSound() {
        String path = "audio\\explosion.wav";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public static void playBackGroundSound() {
        String path = "audio\\triple-h-the-game-theme.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
        mediaPlayer.setVolume(0.65);
        mediaPlayer.setAutoPlay(true);
    }
}
