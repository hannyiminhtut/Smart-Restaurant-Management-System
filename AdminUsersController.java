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

public class AdminUsersController implements Initializable {
    @FXML
    private TableColumn<Users, Boolean> admin_enable;

    @FXML
    private TableColumn<Users, Integer> admin_id;

    @FXML
    private TableColumn<Users, String> admin_name;

    @FXML
    private TableColumn<Users, String> admin_pass;

    @FXML
    private TableColumn<Users, Integer> admin_role;

    @FXML
    private TableView<Users> user_tableview;

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
    private Button all_buttons;

    @FXML
    void show_creation(ActionEvent event) throws IOException {
       // HelloApplication.user = new Users(0,"","",false,0);
        AnchorPane createuser = FXMLLoader.load(getClass().getResource("create_new_user.fxml"));
        HelloApplication.getBorderPane().setCenter(createuser);

    }

    ObservableList<Users> checkUsers = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Userservice userservice = new Userservice();
        List<Users> users = userservice.getAllUsers();
        for(Users user:users){
            checkUsers.add(user);
        }
        admin_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        admin_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        admin_pass.setCellValueFactory(new PropertyValueFactory<>("password"));
        admin_enable.setCellValueFactory(new PropertyValueFactory<>("access"));
      //  admin_role.setCellValueFactory(new PropertyValueFactory<>("role"));
        user_tableview.getItems().addAll(checkUsers);
        addUserActionColumn("Delete","delete");
        addUserActionColumn("Edit","edit");


    }

    public void addUserActionColumn(String column,String btn){
        TableColumn<Users, Void> action = new TableColumn<>(column);
        Callback<TableColumn<Users, Void>, TableCell<Users, Void>> factory = new Callback<TableColumn<Users, Void>, TableCell<Users, Void>>() {
            @Override
            public TableCell<Users, Void> call(TableColumn<Users, Void> dishesVoidTableColumn) {
                final TableCell cell = new TableCell<>(){
                    final Button button = new Button(btn);
                    {
                        button.setOnAction(e->{
                            int index = user_tableview.getSelectionModel().getFocusedIndex();
                            if(index != -1){
                                HelloApplication.user = checkUsers.get(index);
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
        user_tableview.getColumns().add(action);

    }
}
