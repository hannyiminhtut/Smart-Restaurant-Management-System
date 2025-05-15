package com.example.firstproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    //login flag
    public static boolean isLoggedIn = false;

    @FXML
    void showHome(ActionEvent event) throws IOException {

        if(!isLoggedIn){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Access Denied");
            alert.setHeaderText(null);
            alert.setContentText("You need to log in first!");
            alert.showAndWait();
            return;
        }
        AnchorPane homeview = FXMLLoader.load(getClass().getResource("home.fxml"));
        HelloApplication.getBorderPane().setCenter(homeview);


    }

    @FXML
    void showLogin(ActionEvent event) throws IOException {
        AnchorPane login = FXMLLoader.load(getClass().getResource("login.fxml"));
        HelloApplication.getBorderPane().setCenter(login);
       /* Boolean loginSuccess = LoginController.loginSuccessful;
        if (loginSuccess) {
            isLoggedIn = true;
        }*/
    }
}