package com.example.firstproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DishesController implements Initializable {
    @FXML
    private TableColumn<Dishes, Boolean> enablecol;

    @FXML
    private TableColumn<Dishes, Integer> idcol;

    @FXML
    private TableColumn<Dishes, String> namecol;

    @FXML
    private TableColumn<Dishes, Integer> pricecol;

    @FXML
    private TableView<Dishes> tableview;

   ObservableList<Dishes> oblist = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Dishservice dishservice = new Dishservice();
        List<Dishes> dishes = dishservice.getAllDishes();
        for(Dishes dish:dishes){
           oblist.add(dish);
        }
       // idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));
       // enablecol.setCellValueFactory(new PropertyValueFactory<>("enable"));
        tableview.setItems(oblist);
       // addActionColumn();

    }
   /* public void addActionColumn(){
        TableColumn<Dishes, Void> action = new TableColumn<>("action");
        Callback<TableColumn<Dishes, Void>, TableCell<Dishes, Void>> factory = new Callback<TableColumn<Dishes, Void>, TableCell<Dishes, Void>>() {
            @Override
            public TableCell<Dishes, Void> call(TableColumn<Dishes, Void> dishesVoidTableColumn) {
                final TableCell cell = new TableCell<>(){
                    final Button button = new Button("Action");
                    {
                        button.setOnAction(e->{
                            int index = tableview.getSelectionModel().getFocusedIndex();
                            if(index != -1){
                                Dishes dish = oblist.get(index);
                                System.out.println(dish);
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
        tableview.getColumns().add(action);

    }*/
}
