package com.example.proiect_iss;

import com.example.proiect_iss.controllers.LoginController;
import com.example.proiect_iss.repository.UserRepo;
import com.example.proiect_iss.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        Properties props=new Properties();
//        try {
//            props.load(new FileReader("bd.config"));
//        } catch (IOException e) {
//            System.out.println("Cannot find bd.config "+e);
//        }
        UserRepo repoUser=new UserRepo();
        Service service=new Service(repoUser);
//        var a=service.login("user1","pass1");

        FXMLLoader fxmlLoaderClient = new FXMLLoader(HelloApplication.class.getResource("loginView.fxml"));
        AnchorPane clientLayout = fxmlLoaderClient.load();
        Stage stageClient = new Stage();
        stageClient.setScene(new Scene(clientLayout));
        stageClient.setTitle("Login");
        LoginController loginControllerClient=fxmlLoaderClient.getController();
        loginControllerClient.initialize(service);
        stageClient.show();

//        FXMLLoader fxmlLoaderLibrarian = new FXMLLoader(HelloApplication.class.getResource("loginView.fxml"));
//        AnchorPane librarianLayout = fxmlLoaderLibrarian.load();
//        Stage stageAux = new Stage();
//        stageAux.setScene(new Scene(librarianLayout));
//        stageAux.setTitle("Librarian");
//        stageAux.show();
    }

    public static void main(String[] args) {
        launch();
    }
}