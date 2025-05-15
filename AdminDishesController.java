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

public class AdminDishesController implements Initializable {

    @FXML
    private TableColumn<Dishes, Boolean> admin_dishenable;

    @FXML
    private TableColumn<Dishes, Integer> admin_dishid;

    @FXML
    private TableColumn<Dishes, String> admin_dishname;

    @FXML
    private TableColumn<Dishes, Integer> admin_dishprice;

    @FXML
    private TableView<Dishes> dish_tableview;

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
    void show_creation(ActionEvent event) throws IOException {
        HelloApplication.dish = new Dishes(0,"",0,false);
        AnchorPane dishCreatePanel = FXMLLoader.load(getClass().getResource("create_new_dish.fxml"));
        HelloApplication.getBorderPane().setCenter(dishCreatePanel);

    }
    ObservableList<Dishes> obdishes = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Dishservice service = new Dishservice();
        List<Dishes> dishes = service.AllDishes();
        for(Dishes dish:dishes){
            obdishes.add(dish);
        }
        admin_dishid.setCellValueFactory(new PropertyValueFactory<>("id"));
        admin_dishname.setCellValueFactory(new PropertyValueFactory<>("name"));
        admin_dishprice.setCellValueFactory(new PropertyValueFactory<>("price"));
        admin_dishenable.setCellValueFactory(new PropertyValueFactory<>("enable"));
        dish_tableview.getItems().addAll(obdishes);
        addUserActionColumn("Delete","Delete");
        addUserActionColumn("Edit","Edit");

    }

    public void addUserActionColumn(String column,String btn){
        TableColumn<Dishes, Void> action = new TableColumn<>(column);
        Callback<TableColumn<Dishes, Void>, TableCell<Dishes, Void>> factory = new Callback<TableColumn<Dishes, Void>, TableCell<Dishes, Void>>() {
            @Override
            public TableCell<Dishes, Void> call(TableColumn<Dishes, Void> dishesVoidTableColumn) {
                final TableCell cell = new TableCell<>(){
                    final Button button = new Button(btn);
                    {
                        button.setOnAction(e->{
                            int index = dish_tableview.getSelectionModel().getFocusedIndex();
                            if(index != -1){
                                HelloApplication.dish = obdishes.get(index);
                               if(btn.contentEquals("Edit")) {

                                    try {
                                        AnchorPane dishCreatePanel = FXMLLoader.load(getClass().getResource("create_new_dish.fxml"));
                                        HelloApplication.getBorderPane().setCenter(dishCreatePanel);
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }else{
                                      Dishservice service = new Dishservice();
                                      service.deleteDishes(HelloApplication.dish.getId());

                                   try {
                                       VBox adminViewAllDishes = FXMLLoader.load(getClass().getResource("admin_all_dishes_view.fxml"));
                                       HelloApplication.getBorderPane().setCenter(adminViewAllDishes);
                                   } catch (IOException ex) {
                                       throw new RuntimeException(ex);
                                   }


                               }


                               /* HelloApplication.user = checkUsers.get(index);
                                if(btn.contentEquals("edit")) {

                                    try {
                                        AnchorPane updateuser = FXMLLoader.load(getClass().getResource("update_user.fxml"));
                                        HelloApplication.getBorderPane().setCenter(updateuser);

                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }else{
                                    try{
                                        Userservice service = new Userservice();
                                        service.deleteUser(checkUsers.get(index).getId());
                                        VBox vbox = FXMLLoader.load(getClass().getResource("admin_all_users_view.fxml"));
                                        HelloApplication.getBorderPane().setCenter(vbox);
                                    }catch (IOException ex){
                                        throw new  RuntimeException(ex);
                                    }
                                }*/


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
        dish_tableview.getColumns().add(action);

    }
}
