package com.example.firstproject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FailLogin {

    public static void showDialog(String message){
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        Text text = new Text(message);
        Button btn = new Button("OK");
        btn.setOnAction(e->{stage.close();});
        VBox vbox = new VBox(text,btn);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(15));
        vbox.setSpacing(10);
        stage.setScene(new Scene(vbox));
        stage.show();
    }
}
