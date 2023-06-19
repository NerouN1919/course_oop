package com.course.course;

import com.course.course.fifth.FifthTask;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FifthController {
    @FXML
    private TextField numberInput;

    @FXML
    private TextField stringInput;

    @FXML
    private Label numberResult;

    @FXML
    private Label stringResult;

    @FXML
    private TextField sortByInput;

    private List<Integer> readIntegerList() {
        String input = numberInput.getText().trim();
        if (input.equals("")) {
            return new ArrayList<>();
        }
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private List<String> readStringList() {
        String input = stringInput.getText().trim();
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    @FXML
    private void calculateAverage() {
        List<Integer> list = readIntegerList();
        if (list.size() == 0) {
            numberResult.setText("Данные не были введены");
            return;
        }
        double average = FifthTask.getAverage(list);
        numberResult.setText(Double.toString(average));
    }

    @FXML
    private void getUniqueSquares() {
        List<Integer> list = readIntegerList();
        if (list.size() == 0) {
            numberResult.setText("Данные не были введены");
            return;
        }
        List<Integer> uniqueSquares = FifthTask.getUniqueSquares(list);
        numberResult.setText(uniqueSquares.toString());
    }

    @FXML
    private void sumOfEvenNumbers() {
        List<Integer> list = readIntegerList();
        if (list.size() == 0) {
            numberResult.setText("Данные не были введены");
            return;
        }
        int result = FifthTask.sumOfEvenNumbers(list);
        numberResult.setText(String.valueOf(result));
    }

    @FXML
    private void toUpperCaseWithPrefix() {
        List<String> list = readStringList();
        if (list.size() == 1 && list.get(0).equals("")) {
            stringResult.setText("Данные не были введены");
            return;
        }
        List<String> result = FifthTask.toUpperCaseWithPrefix(list);
        stringResult.setText(result.toString());
    }

    @FXML
    private void filterAndSort() {
        if (sortByInput.getText().length() == 0) {
            stringResult.setText("Не выбран символ");
            return;
        }
        List<String> list = readStringList();
        if (list.size() == 1 && list.get(0).equals("")) {
            stringResult.setText("Данные не были введены");
            return;
        }
        List<String> result = FifthTask.filterAndSort(list, sortByInput.getText().toCharArray()[0]);
        stringResult.setText(result.toString());
    }

    @FXML
    private void getLastElement() {
        List<String> list = readStringList();
        if (list.size() == 1 && list.get(0).equals("")) {
            stringResult.setText("Данные не были введены");
            return;
        }
        try {
            String lastElement = FifthTask.getLastElement(list);
            stringResult.setText(lastElement);
        } catch (NoSuchElementException e) {
            stringResult.setText("Список пуст");
        }
    }

    @FXML
    private void toMap() {
        List<String> list = readStringList();
        if (list.size() == 1 && list.get(0).equals("")) {
            stringResult.setText("Данные не были введены");
            return;
        }
        Map<Character, String> result = FifthTask.toMap(list);
        stringResult.setText(result.toString());
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
