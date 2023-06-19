package com.course.course;

import com.course.course.third.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ThirdController {
    @FXML
    private ListView<String> firstListView;

    @FXML
    private ListView<String> secondListView;

    @FXML
    private ListView<String> thirdListView;
    @FXML
    private ComboBox<String> hedgehogs;
    @FXML
    private ComboBox<String> manuls;
    @FXML
    private ComboBox<String> lynxs;
    private final Map<String, List<? super Hedgehog>> firstMap = new HashMap<>();
    private final Map<String, List<? super Manul>> secondMap = new HashMap<>();
    private final Map<String, List<? super Lynx>> thirdMap = new HashMap<>();
    private String lastFirst;
    private String lastSecond;
    private String lastThird;
    private final List<Chordates> animals = new ArrayList<>();

    public void initialize() {
        lastFirst = hedgehogs.getValue();
        lastSecond = manuls.getValue();
        lastThird = lynxs.getValue();
        restart();
    }
    private void restart() {
        List<Hedgehogs> hedgehogsToFirst = new ArrayList<>();
        List<Insectivores> insectivoresToFirst = new ArrayList<>();
        List<Mammals> mammalsToFirst = new ArrayList<>();
        List<Chordates> chordatesToFirst = new ArrayList<>();
        List<Feline> felineToSecond = new ArrayList<>();
        List<Predatory> predatoryToSecond = new ArrayList<>();
        List<Mammals> mammalsToSecond = new ArrayList<>();
        List<Chordates> chordatesToSecond = new ArrayList<>();
        List<Feline> felineToThird = new ArrayList<>();
        List<Predatory> predatoryToThird = new ArrayList<>();
        List<Mammals> mammalsToThird = new ArrayList<>();
        List<Chordates> chordatesToThird = new ArrayList<>();
        firstMap.put("Ежовые", hedgehogsToFirst);
        firstMap.put("Насекомоядные", insectivoresToFirst);
        firstMap.put("Млекопитающие", mammalsToFirst);
        firstMap.put("Хордовые", chordatesToFirst);

        secondMap.put("Кошачьи", felineToSecond);
        secondMap.put("Хищные", predatoryToSecond);
        secondMap.put("Млекопитающие", mammalsToSecond);
        secondMap.put("Хордовые", chordatesToSecond);

        thirdMap.put("Кошачьи", felineToThird);
        thirdMap.put("Хищные", predatoryToThird);
        thirdMap.put("Млекопитающие", mammalsToThird);
        thirdMap.put("Хордовые", chordatesToThird);
    }

    public void addLynx() {
        if(!lastThird.equals(lynxs.getValue())){
            animals.clear();
            lastThird = lynxs.getValue();
        }
        Lynx lynx = new Lynx();
        animals.add(lynx);
        selectToSegregate();
    }

    public void addManul() {
        if(!lastSecond.equals(manuls.getValue())){
            animals.clear();
            lastSecond = manuls.getValue();
        }
        Manul manul = new Manul();
        animals.add(manul);
        selectToSegregate();
    }

    public void addHedgehog() {
        if(!lastFirst.equals(hedgehogs.getValue())){
            animals.clear();
            lastFirst = hedgehogs.getValue();
        }
        Hedgehog hedgehog = new Hedgehog();
        animals.add(hedgehog);
        selectToSegregate();
    }

    private void updateListView(List<? super Hedgehog> firstTo, List<? super Manul> secondTo,
                                List<? super Lynx> thirdTo) {
        firstListView.setItems(getObservableList(firstTo));
        secondListView.setItems(getObservableList(secondTo));
        thirdListView.setItems(getObservableList(thirdTo));
    }

    private ObservableList<String> getObservableList(List<?> list) {
        return FXCollections.observableArrayList(list.stream()
                .map(Object::getClass)
                .map(Class::getSimpleName)
                .collect(Collectors.toList()));
    }

    private void selectToSegregate(){
        String first = hedgehogs.getValue();
        String second = manuls.getValue();
        String third = lynxs.getValue();
        restart();
        if (firstMap.containsKey(first) && secondMap.containsKey(second) && thirdMap.containsKey(third)) {
            List<? super Hedgehog> firstList = firstMap.get(first);
            List<? super Manul> secondList = secondMap.get(second);
            List<? super Lynx> thirdList = thirdMap.get(third);

            ThirdTask.segregate(animals, firstList, secondList, thirdList);
            updateListView(firstList, secondList, thirdList);
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
