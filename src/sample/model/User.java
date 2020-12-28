package sample.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User implements Comparable {
    private SimpleStringProperty username;
    private SimpleStringProperty password;
    private SimpleIntegerProperty highScore;

    public User(String username, String password) {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.highScore = new SimpleIntegerProperty(0);
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username = new SimpleStringProperty(username);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password = new SimpleStringProperty(password);
    }

    public int getHighScore() {
        return highScore.get();
    }

    public void setHighScore(int highScore) {
        this.highScore = new SimpleIntegerProperty(highScore);
    }

    @Override
    public int compareTo(Object o) {
        return ((User) o).getHighScore() - this.getHighScore();
    }
}
