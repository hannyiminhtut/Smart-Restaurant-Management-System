package com.example.firstproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class LoginController {
    @FXML
    private Button tvLogin;

    @FXML
    private PasswordField tvPassword;

    @FXML
    private TextField tvUser;



    @FXML
    void loginData(ActionEvent event) throws IOException {
        String name = tvUser.getText().toString();
        String password = tvPassword.getText().toString();
       // System.out.println("Username: "+ name + " Password: "+password);
        Userservice userservice = new Userservice();
        Adminservice adminservice = new Adminservice();
        boolean test = userservice.isUserInclude(name,password);
        boolean test1 = adminservice.isAdminInclude(name,password);
        if(test){
            HelloController.isLoggedIn = true;
            try {
                AnchorPane homeview = FXMLLoader.load(getClass().getResource("home.fxml"));
                HelloApplication.getBorderPane().setCenter(homeview);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        else if(test1){
            HelloController.isLoggedIn = true;
            VBox vbox = FXMLLoader.load(getClass().getResource("admin_all_users_view.fxml"));
            HelloApplication.getBorderPane().setCenter(vbox);
        }
        else{
            FailLogin.showDialog("Username and Password aren't correct,TRY AGAIN!");
        }


    }

    @FXML
    void admin_login(ActionEvent event) throws IOException {
        AnchorPane adminLogin = FXMLLoader.load(getClass().getResource("admin_login.fxml"));
        HelloApplication.getBorderPane().setCenter(adminLogin);

    }

}
