package com.example.proiect_iss.controllers;

import com.example.proiect_iss.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;

public class UserController {
    public TableColumn columnTitle;
    public TableColumn columnAuthor;

    public void handleRent(ActionEvent actionEvent) {
    }



    public void handleLogout(ActionEvent actionEvent) throws IOException {
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginView.fxml"));
//        Scene mainLayout=new Scene(fxmlLoader.load());
//        Stage stage = new Stage();
//        stage.setTitle("Log in");
//        stage.setScene(mainLayout);
//        LoginController loginController=fxmlLoader.getController();
//        stage.show();
    }
}
