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

public class CreateTablesController implements Initializable {
    Tables table = HelloApplication.table;
    @FXML
    private Button added_to_newtable;

    @FXML
    private TextField chair_no;

    @FXML
    private ChoiceBox<String> create_enable;

    @FXML
    private PasswordField table_charge;

    @FXML
    void back_to(ActionEvent event) throws IOException {
        VBox adminViewAllTables = FXMLLoader.load(getClass().getResource("admin_all_tables_view.fxml"));
        HelloApplication.getBorderPane().setCenter(adminViewAllTables);

    }


    @FXML
    void save_new_table(ActionEvent event) throws IOException {
        /*table.setChair(Integer.parseInt(chair_no.getText()));
        table.setCharge(Integer.parseInt(table_charge.getText()));*/
        if(create_enable.getSelectionModel().getSelectedItem().contentEquals("false")){
            table.setEnable(false);
        }else{
            table.setEnable(true);
        }
        Tableservice service = new Tableservice();
        if(table.getId()!=0){
            if(service.updateTables(table)){
                VBox adminViewAllTables = FXMLLoader.load(getClass().getResource("admin_all_tables_view.fxml"));
                HelloApplication.getBorderPane().setCenter(adminViewAllTables);

            }else{
                FailLogin.showDialog("Table Update Error!");
            }

        }else {
            if (service.addTables(table)) {
                VBox adminViewAllTables = FXMLLoader.load(getClass().getResource("admin_all_tables_view.fxml"));
                HelloApplication.getBorderPane().setCenter(adminViewAllTables);
            } else {
                FailLogin.showDialog("Table Insert Error!");
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        create_enable.getItems().addAll("false","true");
        create_enable.getSelectionModel().select("true");
        if(table.getId()!=0) {
            //chair_no.setText(String.valueOf(table.getChair()));
           // table_charge.setText(String.valueOf(table.getCharge()));
            if (table.isEnable()) {
                create_enable.getSelectionModel().select("true");
            }else{
                create_enable.getSelectionModel().select("false");
            }
        }

    }
}
