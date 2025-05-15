package com.example.firstproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class SaleHistoryTableviewController implements Initializable {
    @FXML
    private TableColumn<SalesHistory, String> sale_date_col;

    @FXML
    private TableColumn<SalesHistory, Integer> sale_total_col;

    @FXML
    private TableView<SalesHistory> salehistory_tableview;

    @FXML
    void admin_show_dishes(ActionEvent event) throws IOException {
        VBox adminViewAllDishes = FXMLLoader.load(getClass().getResource("admin_all_dishes_view.fxml"));
        HelloApplication.getBorderPane().setCenter(adminViewAllDishes);

    }

    @FXML
    void admin_show_saleshistory(ActionEvent event) throws IOException {
        HBox saleshistory_view = FXMLLoader.load(getClass().getResource("sale_history_view.fxml"));
        HelloApplication.getBorderPane().setCenter(saleshistory_view);
    }

    @FXML
    void admin_show_tables(ActionEvent event) throws IOException {
        VBox adminViewAllTables = FXMLLoader.load(getClass().getResource("admin_all_tables_view.fxml"));
        HelloApplication.getBorderPane().setCenter(adminViewAllTables);

    }

    @FXML
    void admin_show_todaysales(ActionEvent event) throws IOException {
        VBox todaySalesView = FXMLLoader.load(getClass().getResource("today_sales.fxml"));
        HelloApplication.getBorderPane().setCenter(todaySalesView);

    }

    @FXML
    void admin_show_users(ActionEvent event) throws IOException {
        VBox adminViewAllUsers = FXMLLoader.load(getClass().getResource("admin_all_users_view.fxml"));
        HelloApplication.getBorderPane().setCenter(adminViewAllUsers);

    }
    ObservableList<SalesHistory> soblist = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sale_date_col.setCellValueFactory(new PropertyValueFactory<>("saleDate"));
        sale_total_col.setCellValueFactory(new PropertyValueFactory<>("saleTotal"));
        SalesHistoryservice service = new SalesHistoryservice();
        try {
            List<SalesHistory> histories = service.getAllHistory();
            for(SalesHistory history:histories){
                soblist.add(history);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        salehistory_tableview.setItems(soblist);

    }
}
