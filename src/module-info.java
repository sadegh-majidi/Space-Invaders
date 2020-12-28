module space.invaders {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.graphics;

    opens sample;
    opens sample.model;
    opens sample.controller;
}