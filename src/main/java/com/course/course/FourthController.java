package com.course.course;

import com.course.course.fourth.FileReadException;
import com.course.course.fourth.Translator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FourthController {
    @FXML
    private TextField wordField;

    @FXML
    private TextField translationField;

    @FXML
    private TextArea inputTextArea;

    @FXML
    private TextArea translatedTextArea;

    private Translator translator;

    public void initialize() {
        translator = new Translator();
        translator.readDictionary();
    }

    @FXML
    void handleAddWord() {
        String word = wordField.getText().trim();
        String translation = translationField.getText().trim();

        if (!word.isEmpty() && !translation.isEmpty()) {
            translator.addWordToDictionary(word, translation);
            wordField.clear();
            translationField.clear();
        }
    }

    @FXML
    void handleTranslate() {
        String inputText = inputTextArea.getText();

        if (!inputText.isEmpty()) {
            try {
                Files.write(Paths.get("src/main/java/com/course/course/fourth/input.txt"), inputText.getBytes());
                String translatedText = translator.translateFile("src/main/java/com/course/course/fourth/input.txt");
                translatedTextArea.setText(translatedText);
            } catch (IOException | FileReadException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void handleLoadInputFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Input Text File");
        File inputFile = fileChooser.showOpenDialog(null);
        if (inputFile != null) {
            try {
                String inputText = Files.readString(inputFile.toPath());
                inputTextArea.setText(inputText);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
