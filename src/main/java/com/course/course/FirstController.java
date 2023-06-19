package com.course.course;

import com.course.course.first.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class FirstController {
    private final Hero hero = new Hero();
    @FXML
    private TextArea outputArea;

    @FXML
    private TextField heroType;

    @FXML
    private void showCodeExecution() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        hero.move();
        System.out.flush();
        outputArea.setText(outputStream.toString());
    }

    @FXML
    private void setFlyHero() {
        hero.setActivity(new Fly());
        heroType.setText("Летающий герой");
    }

    @FXML
    private void setRideHero() {
        hero.setActivity(new Ride());
        heroType.setText("Ездящий герой");
    }

    @FXML
    private void setSwimHero() {
        hero.setActivity(new Swim());
        heroType.setText("Плавающий герой");
    }

    @FXML
    private void setWalkHero() {
        heroType.setText("Гуляющий герой");
        hero.setActivity(new Walk());
    }

    @FXML
    private void toStart(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("start.fxml"));
            Parent root = loader.load();
            Scene currentScene = ((Node) event.getSource()).getScene();
            Stage primaryStage = (Stage) currentScene.getWindow();
            Scene scene = new Scene(root, 600, 500);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}