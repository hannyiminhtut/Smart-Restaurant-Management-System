package com.example.firstproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateDishesController implements Initializable {
    @FXML
    private ComboBox<String> dish_enable;

    @FXML
    private TextField dish_name;

    @FXML
    private TextField dish_price;

    @FXML
    private Button dish_save;

    @FXML
    void back_to(ActionEvent event) throws IOException {
        VBox adminViewAllDishes = FXMLLoader.load(getClass().getResource("admin_all_dishes_view.fxml"));
        HelloApplication.getBorderPane().setCenter(adminViewAllDishes);

    }


    Dishes dish = HelloApplication.dish;
    @FXML
    void save_as_new_dish(ActionEvent event) throws IOException {

        dish.setName(dish_name.getText());
        dish.setPrice(Integer.parseInt(dish_price.getText()));
        if(dish_enable.getSelectionModel().getSelectedItem().contentEquals("false")){
            dish.setEnable(false);
        }else{
            dish.setEnable(true);
        }
        Dishservice service = new Dishservice();
        if(dish.getId()!=0){
            if(service.updateDishes(dish)){
                VBox adminViewAllDishes = FXMLLoader.load(getClass().getResource("admin_all_dishes_view.fxml"));
                HelloApplication.getBorderPane().setCenter(adminViewAllDishes);

            }else{
                FailLogin.showDialog("Update dish error!");
            }
        }else {
            if (service.saveDishes(dish)) {
                VBox adminViewAllDishes = FXMLLoader.load(getClass().getResource("admin_all_dishes_view.fxml"));
                HelloApplication.getBorderPane().setCenter(adminViewAllDishes);
            } else {
                FailLogin.showDialog("Insert dish error!");
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dish_enable.getItems().addAll("false","true");
        dish_enable.getSelectionModel().select("true");
        if(dish.getId()!=0){
            dish_name.setText(dish.getName());
            dish_price.setText(String.valueOf(dish.getPrice()));
            if(dish.isEnable()){
                dish_enable.getSelectionModel().select("true");
            }else{
                dish_enable.getSelectionModel().select("false");
            }
        }

    }
}
