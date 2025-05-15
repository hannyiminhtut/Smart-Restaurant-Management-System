package com.example.firstproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static Stage stage;
    public static BorderPane borderpane;
    public static Users user = new Users(0,"","",false,0);
    public static Dishes dish = new Dishes(0,"",0,false);
    public static Tables table = new Tables(0,0,false,0);
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        borderpane = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        AnchorPane login = FXMLLoader.load(getClass().getResource("login.fxml"));
        this.borderpane.setCenter(login);
        Scene scene = new Scene(borderpane, 1000, 500);
        this.stage.setTitle("Smart Restaurant Management System");
        this.stage.setScene(scene);
        this.stage.show();
    }

    public static BorderPane getBorderPane(){
        return borderpane;
    }

    public static Stage getStage(){
        return stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}