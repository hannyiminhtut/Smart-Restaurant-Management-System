package com.example.firstproject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ActiveOrdersController implements Initializable {
    @FXML
    private TableColumn<ActiveOrderCheck, Integer> active_table_count;

    @FXML
    private TableColumn<ActiveOrderCheck, Integer> active_table_id;

    @FXML
    private TableColumn<ActiveOrderCheck, String> active_table_name;

    @FXML
    private TableColumn<ActiveOrderCheck, Integer> active_table_price;

    @FXML
    private TableColumn<ActiveOrderCheck, Integer> active_table_total;

    @FXML
    private ListView<String> activelist;

    @FXML
    private TableView<ActiveOrderCheck> activeTable;

    @FXML
    private Label active_order_grand_total;

    @FXML
    private Label active_order_tax;

    @FXML
    private Label active_order_total;


    @FXML
    void start_billout(ActionEvent event) throws SQLException {
        FailLogin.showDialog("Orders have been cashed out");
        OrderService service = new OrderService();
        String deleteTable_id = activelist.getSelectionModel().getSelectedItem();
        int table_id = Integer.parseInt(deleteTable_id);
        if(service.deleteOrder(table_id)){
            activeTable.getItems().clear();
            addUnderTable.clear();
            active_order_total.setText("Total : 0");
            active_order_tax.setText("Tax : 0");
            active_order_grand_total.setText("Grand Total : 0");
        }


    }



    ObservableList<String> activetablelist = FXCollections.observableArrayList();
    ObservableList<ActiveOrderCheck> addUnderTable = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Tableservice tableservice = new Tableservice();
        List<Tables> activetable = tableservice.showActiveTables();
        for(Tables table:activetable){
            activetablelist.add(String.valueOf(table.getId()));
        }
        activelist.getItems().addAll(activetablelist);
        activelist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
               OrderService orderService = new OrderService();
                try {
                    List<ActiveOrderCheck> orders =  orderService.getOrderDetailByTableId(Integer.parseInt(t1));
                    activeTable.getItems().remove(0,activeTable.getItems().size());
                    //Initialize total as 0
                    int total = 0;
                    for(ActiveOrderCheck order:orders){
                        addUnderTable.add(order);
                        total += order.getPrice() * order.getCount();
                    }
                    active_order_total.setText("Total : "+total);
                    active_order_tax.setText("Tax 500");
                    active_order_grand_total.setText("Grand Total : "+ (total+500));
                    active_table_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                    active_table_name.setCellValueFactory(new PropertyValueFactory<>("name"));
                    active_table_price.setCellValueFactory(new PropertyValueFactory<>("price"));
                    active_table_count.setCellValueFactory(new PropertyValueFactory<>("count"));
                    active_table_total.setCellValueFactory(new PropertyValueFactory<>("total"));
                    activeTable.setItems(addUnderTable);


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });


    }
   /* public void createTableDetail(int id) throws SQLException {
        OrderService orderService = new OrderService();
        List<ActiveOrderCheck> orders = orderService.getOrderDetailByTableId(id);
        for(ActiveOrderCheck obj:orders){
            System.out.println(obj);
        }

    }*/
}
