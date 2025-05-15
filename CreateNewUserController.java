package com.example.firstproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateNewUserController implements Initializable {
    @FXML
    private Button added_to_newuser;

    @FXML
    private ChoiceBox<String> create_enable;

    @FXML
    private PasswordField create_pass;


    @FXML
    private ChoiceBox<String> create_role;

    @FXML
    private TextField create_username;
    Users user = HelloApplication.user;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        create_username.getText();
        create_pass.getText();
        create_enable.getItems().addAll("false","true");
        create_enable.getSelectionModel().select("true");
       /* create_role.getItems().addAll("1","2");
        create_role.getSelectionModel().select("1");*/
        /*if(user.getId() != 0){
            create_username.setText(user.getName());
            create_pass.setText(user.getPassword());
            if(user.getAccess()){
                create_enable.getSelectionModel().select(1);

            }else{
                create_enable.getSelectionModel().select(0);
            }
            if(user.getRole() == 0){
                create_role.getSelectionModel().select(1);
            }else{
                create_role.getSelectionModel().select(0);
            }
        }*/
    }

    @FXML
    void save_new_user(ActionEvent event) {
        Userservice userservice = new Userservice();

        user.setName(create_username.getText());
        user.setPassword(create_pass.getText());
        if(create_enable.getSelectionModel().getSelectedItem().contentEquals("false")) {
            user.setAccess(false);
        }else{
            user.setAccess(true);
        }
       // user.setRole(Integer.parseInt(create_role.getSelectionModel().getSelectedItem()));
       /* if(user.getId()!=0){
            if(userservice.updateUser(user)){
                FailLogin.showDialog("Successfully Updated.");
            }else{
                FailLogin.showDialog("Something Wrong!");
            }
        }else {*/
            if (userservice.saveNewUser(user)) {
                try {
                    VBox vbox = FXMLLoader.load(getClass().getResource("admin_all_users_view.fxml"));
                    HelloApplication.getBorderPane().setCenter(vbox);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                FailLogin.showDialog("Something wrong");
            }
        }

    @FXML
    void back_to(ActionEvent event) throws IOException {
        VBox adminViewAllUsers = FXMLLoader.load(getClass().getResource("admin_all_users_view.fxml"));
        HelloApplication.getBorderPane().setCenter(adminViewAllUsers);

    }


    }


