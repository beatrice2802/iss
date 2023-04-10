package com.example.proiect_iss;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoaderClient = new FXMLLoader(HelloApplication.class.getResource("loginView.fxml"));
        AnchorPane clientLayout = fxmlLoaderClient.load();
        Stage stageClient = new Stage();
        stageClient.setScene(new Scene(clientLayout));
        stageClient.setTitle("Client");
        stageClient.show();

        FXMLLoader fxmlLoaderLibrarian = new FXMLLoader(HelloApplication.class.getResource("loginView.fxml"));
        AnchorPane librarianLayout = fxmlLoaderLibrarian.load();
        Stage stageAux = new Stage();
        stageAux.setScene(new Scene(librarianLayout));
        stageAux.setTitle("Librarian");
        stageAux.show();
    }

    public static void main(String[] args) {
        launch();
    }
}