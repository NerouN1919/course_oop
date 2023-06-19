package com.course.course;

import com.course.course.second.Counter;
import com.course.course.second.RobotVacuum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public class SecondController {
    private RobotVacuum robotVacuum = null;
    @FXML
    private TextArea outputArea;
    public void initialize(){
        StringBuilder result = new StringBuilder();
        robotVacuum = new RobotVacuum(result);
    }

    @FXML
    private void turnOn() {
        try {
            Method turnOn = RobotVacuum.class.getDeclaredMethod("turnOn");

            turnOn.setAccessible(true);
            turnOn.invoke(robotVacuum);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("Что-то пошло не так");
        }
        outputArea.setText(robotVacuum.getResult().toString());
    }

    @FXML
    private void turnOff() {
        try {
            Method turnOff = RobotVacuum.class.getDeclaredMethod("turnOff");
            turnOff.setAccessible(true);
            turnOff.invoke(robotVacuum);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            System.out.println("Что-то пошло не так");
        }
        outputArea.setText(robotVacuum.getResult().toString());
    }

    @FXML
    private void charge() {
        try {
            Method charge = RobotVacuum.class.getDeclaredMethod("chargeBattery");
            charge.setAccessible(true);
            charge.invoke(robotVacuum);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            System.out.println("Что-то пошло не так");
        }
        outputArea.setText(robotVacuum.getResult().toString());
    }

    @FXML
    private void clean() {
        try {
            Method clean = RobotVacuum.class.getDeclaredMethod("clean");
            clean.setAccessible(true);
            Counter counter = clean.getAnnotation(Counter.class);
            for (int i = 0; i < counter.value(); i++) {
                clean.invoke(robotVacuum);
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            System.out.println("Что-то пошло не так");
        }
        outputArea.setText(robotVacuum.getResult().toString());
    }

    @FXML
    private void move() {
        try {
            Method move = RobotVacuum.class.getDeclaredMethod("move", int.class);
            move.setAccessible(true);
            Counter counter = move.getAnnotation(Counter.class);
            for (int i = 0; i < counter.value(); i++) {
                move.invoke(robotVacuum, new Random().nextInt(0, 5));
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            System.out.println("Что-то пошло не так");
        }
        outputArea.setText(robotVacuum.getResult().toString());
    }

    @FXML
    private void checkBattery() {
        try {
            Method checkBattery = RobotVacuum.class.getDeclaredMethod("checkBatteryLevel");
            checkBattery.invoke(robotVacuum);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            System.out.println("Что-то пошло не так");
        }
        outputArea.setText(robotVacuum.getResult().toString());
    }

    @FXML
    private void getStatus() {
        try {
            Method getStatus = RobotVacuum.class.getDeclaredMethod("getStatus");
            getStatus.invoke(robotVacuum);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            System.out.println("Что-то пошло не так");
        }
        outputArea.setText(robotVacuum.getResult().toString());
    }

    @FXML
    private void detectObstacles() {
        try {
            Method detectObstacles = RobotVacuum.class.getDeclaredMethod("detectObstacles");
            detectObstacles.setAccessible(true);
            detectObstacles.invoke(robotVacuum);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            System.out.println("Что-то пошло не так");
        }
        outputArea.setText(robotVacuum.getResult().toString());
    }

    @FXML
    private void navigate() {
        try {
            Method navigate = RobotVacuum.class.getDeclaredMethod("navigate");
            navigate.setAccessible(true);
            navigate.invoke(robotVacuum);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            System.out.println("Что-то пошло не так");
        }
        outputArea.setText(robotVacuum.getResult().toString());
    }

    @FXML
    private void rotateBrush() {
        try {
            Method rotateBrush = RobotVacuum.class.getDeclaredMethod("rotateBrush");
            rotateBrush.setAccessible(true);
            Counter counter = rotateBrush.getAnnotation(Counter.class);
            for (int i = 0; i < counter.value(); i++) {
                rotateBrush.invoke(robotVacuum);
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            System.out.println("Что-то пошло не так");
        }
        outputArea.setText(robotVacuum.getResult().toString());
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
