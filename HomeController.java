package com.example.firstproject;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class HomeController {




    @FXML
    void showDishes(ActionEvent event) throws IOException {
        TableView tableview = FXMLLoader.load(getClass().getResource("dish.fxml"));
        HelloApplication.getBorderPane().setCenter(tableview);

    }

    @FXML
    void showSales(ActionEvent event) throws IOException {
        HBox saleview = FXMLLoader.load(getClass().getResource("sale.fxml"));
        HelloApplication.getBorderPane().setCenter(saleview);

    }

    @FXML

    void showTables(ActionEvent event) throws IOException {
      FlowPane flowpane = FXMLLoader.load(getClass().getResource("table.fxml"));
      HelloApplication.getBorderPane().setCenter(flowpane);

    }

}
