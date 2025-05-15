package com.example.firstproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminTablesController implements Initializable {
    @FXML
    private TableColumn<Tables, Integer> admin_tablechair;

    @FXML
    private TableColumn<Tables, Integer> admin_tablecharge;

    @FXML
    private TableColumn<Tables, Boolean> admin_tableenable;

    @FXML
    private TableColumn<Tables, Integer> admin_tableid;

    @FXML
    private TableView<Tables> tables_view;

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

    @FXML
    void show_table_creation(ActionEvent event) throws IOException {
        HelloApplication.table = new Tables(0,0,false,0);
        AnchorPane showTablesCreation = FXMLLoader.load(getClass().getResource("create_new_table.fxml"));
        HelloApplication.getBorderPane().setCenter(showTablesCreation);

    }
    ObservableList<Tables> obtables = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Tableservice service = new Tableservice();
        List<Tables> tables = service.showAllTables();
        for(Tables table:tables){
            obtables.add(table);
        }
        admin_tableid.setCellValueFactory(new PropertyValueFactory<>("id"));
        /*admin_tablechair.setCellValueFactory(new PropertyValueFactory<>("chair"));
        admin_tablecharge.setCellValueFactory(new PropertyValueFactory<>("charge"));*/
        admin_tableenable.setCellValueFactory(new PropertyValueFactory<>("enable"));
        tables_view.getItems().addAll(obtables);
        addUserActionColumn("Edit","Edit");
        addUserActionColumn("Delete","Delete");

    }

    public void addUserActionColumn(String column,String btn){
        TableColumn<Tables, Void> action = new TableColumn<>(column);
        Callback<TableColumn<Tables, Void>, TableCell<Tables, Void>> factory = new Callback<TableColumn<Tables, Void>, TableCell<Tables, Void>>() {
            @Override
            public TableCell<Tables, Void> call(TableColumn<Tables, Void> tablesVoidTableColumn) {
                final TableCell cell = new TableCell<>(){
                    final Button button = new Button(btn);
                    {
                        button.setOnAction(e->{
                            int index = tables_view.getSelectionModel().getFocusedIndex();
                            if(index != -1){
                                HelloApplication.table = obtables.get(index);
                                if(btn.contentEquals("Edit")) {

                                    try {
                                        AnchorPane showTablesCreation = FXMLLoader.load(getClass().getResource("create_new_table.fxml"));
                                        HelloApplication.getBorderPane().setCenter(showTablesCreation);
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }else{
                                    Tableservice service = new Tableservice();
                                    service.deleteTables(HelloApplication.table.getId());

                                    try {
                                        VBox adminViewAllTables = FXMLLoader.load(getClass().getResource("admin_all_tables_view.fxml"));
                                        HelloApplication.getBorderPane().setCenter(adminViewAllTables);
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }


                                }





                            }
                        });
                    }

                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        // super.updateItem(o, b);
                        if(empty){
                            setGraphic(null);
                        }else{
                            setGraphic(button);
                        }
                    }
                };
                return cell;
            }
        };
        action.setCellFactory(factory);
        tables_view.getColumns().add(action);

    }
}
