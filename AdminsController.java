package com.example.firstproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AdminsController {
    @FXML
    private TextField admin_name;

    @FXML
    private PasswordField admin_password;

    @FXML
    void data_login(ActionEvent event) throws IOException {
        String name = admin_name.getText().toString();
        String password = admin_password.getText().toString();
        Adminservice service = new Adminservice();
        if(service.isAdminInclude(name,password)){
            HelloController.isLoggedIn = true;
            VBox vbox = FXMLLoader.load(getClass().getResource("admin_all_users_view.fxml"));
            HelloApplication.getBorderPane().setCenter(vbox);

        }else{
            FailLogin.showDialog("Wrong,Please try again!");
        }

    }

}
