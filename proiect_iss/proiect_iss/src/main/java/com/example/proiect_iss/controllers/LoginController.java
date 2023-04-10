package com.example.proiect_iss.controllers;

import com.example.proiect_iss.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    public TextField textPassword;
    public TextField textUsername;

    public void onLogin(ActionEvent actionEvent) throws IOException {
        //daca e client
//        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("userView.fxml"));
//        Scene mainLayout=new Scene(fxmlLoader.load());
//        Stage stage = new Stage();
//        stage.setTitle("User view");
//        stage.setScene(mainLayout);
//        UserController userController=fxmlLoader.getController();
//        stage.show();
        //daca e bibliotecar

        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("librarianView.fxml"));
        Scene mainLayout=new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Librarian view");
        stage.setScene(mainLayout);
        LibrarianController librarianController=fxmlLoader.getController();
        stage.show();
    }

    public void handleLibrarian(ActionEvent actionEvent) {
    }

    public void onCreateNewAccount(ActionEvent actionEvent) throws IOException {
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("createAccountView.fxml"));
        Scene mainLayout=new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Create new Account");
        stage.setScene(mainLayout);
        CreateAccController createAccController=fxmlLoader.getController();
        stage.show();
    }
}
