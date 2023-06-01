package com.example.proiect_iss.controllers;

import com.example.proiect_iss.HelloApplication;
import com.example.proiect_iss.domain.User;
import com.example.proiect_iss.service.Service;
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
    private Service srv;
    public void initialize(Service srv){this.srv=srv;}

    public void onLogin(ActionEvent actionEvent) throws IOException {
        //daca e client
        String username = textUsername.getText();
        String password = textPassword.getText();
        if(username.equals("") || password.equals("")){
            MessageAlert.showErrorMessage(null, "incorrect email or password!");
            return;
        }

        var usr =srv.login(username,password);
        if(usr==null)
        {
            MessageAlert.showErrorMessage(null, "incorrect email or password!");
            return;
        }
        if(usr.getIsLibrarian()==Boolean.FALSE) {
//            ((Stage) (((Button) actionEvent.getSource()).getScene().getWindow())).close();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("userView.fxml"));
            Scene mainLayout = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("CLIENT");
            stage.setScene(mainLayout);
            UserController userController = fxmlLoader.getController();
            userController.initialize(this.srv);
            stage.show();
        }
        else {
            //daca e bibliotecar

//            ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("librarianView.fxml"));
            Scene mainLayout=new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("BIBLIOTECAR");
            stage.setScene(mainLayout);
            LibrarianController librarianController=fxmlLoader.getController();
            librarianController.initialize(this.srv);
            stage.show();
        }
    }


    public void onCreateNewAccount(ActionEvent actionEvent) throws IOException {
//        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("createAccountView.fxml"));
        Scene mainLayout=new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Create new Account");
        stage.setScene(mainLayout);
        CreateAccController createAccController=fxmlLoader.getController();
        createAccController.initialize(this.srv);
        stage.show();
    }
}
