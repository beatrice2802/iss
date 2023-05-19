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

public class CreateAccController {
    public TextField textCNP;
    public TextField textPassword;
    public TextField textPassword1;
    public TextField textNume;
    public TextField textAdresa;
    public TextField textTelefon;
    public TextField textUsername;
    private Service srv;
    public void initialize(Service srv){this.srv=srv;}
    public void onCreate(ActionEvent actionEvent) throws IOException {
        //doar clientii fac cont, bibliotecarul are deja
        String cnp = textCNP.getText();
        String password = textPassword.getText();
        String password2 = textPassword1.getText();
        String nume = textNume.getText();
        String adresa = textAdresa.getText();
        String telefon = textTelefon.getText();
        String username = textUsername.getText();
        if(cnp.equals("") || username.equals("") || password.equals("")|| password2.equals("") || nume.equals("")|| adresa.equals("") || telefon.equals("")){
            MessageAlert.showErrorMessage(null, "incorrect info!");
            return;
        }
        if(!password.equals(password2)){
            MessageAlert.showErrorMessage(null, "passwords not matching!");
            return;
        }
        var u=this.srv.createAcc(cnp,nume,adresa,telefon,username,password,Boolean.FALSE);
        if(u==null){
            MessageAlert.showErrorMessage(null, "you already have an account!");
            return;
        }


        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("userView.fxml"));
        Scene mainLayout=new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("User view");
        stage.setScene(mainLayout);
        UserController userController=fxmlLoader.getController();
        stage.show();
//        //daca e bibliotecar
//
//        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("librarianView.fxml"));
//        Scene mainLayout=new Scene(fxmlLoader.load());
//        Stage stage = new Stage();
//        stage.setTitle("Librarian view");
//        stage.setScene(mainLayout);
//        LibrarianController librarianController=fxmlLoader.getController();
//        stage.show();
    }

    public void onBack(ActionEvent actionEvent) throws IOException {
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();

    }
}
