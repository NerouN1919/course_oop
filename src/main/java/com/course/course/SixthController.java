package com.course.course;

import com.course.course.sixth.Daemon;
import com.course.course.sixth.ProgramState;
import com.course.course.sixth.Supervisor;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.course.course.sixth.AbstractProgram;
import javafx.util.Duration;

public class SixthController {
    public Button startButton;
    @FXML
    private TextArea output;

    @FXML
    public void handleStartButtonAction() {
        startButton.setDisable(true);
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                AbstractProgram abstractProgram = new AbstractProgram();
                Supervisor supervisor = new Supervisor(abstractProgram);
                Daemon daemon = new Daemon(abstractProgram);
                new Thread(supervisor).start();
                Thread daemonThread = new Thread(daemon);
                daemonThread.setDaemon(true);
                daemonThread.start();
                new Thread(() -> {
                    ProgramState state;
                    do {
                        state = abstractProgram.getState();
                        output.setText(state.name());
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } while (state != ProgramState.FATAL_ERROR);
                }).start();
                return null;
            }
        };

        task.setOnSucceeded(event -> {
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e -> startButton.setDisable(false));
            pause.play();
        });

        new Thread(task).start();
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
