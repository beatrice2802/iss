package com.example.proiect_iss.controllers;

import com.example.proiect_iss.HelloApplication;
import com.example.proiect_iss.domain.Book;
import com.example.proiect_iss.service.Service;
import com.example.proiect_iss.utils.events.BookEntityChangeEvent;
import com.example.proiect_iss.utils.observer.Observable;
import com.example.proiect_iss.utils.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class UserController implements Observer<BookEntityChangeEvent> {
    public TableColumn columnTitle;
    public TableColumn columnAuthor;
    public Service srv;
    public TableColumn columnId;
    public TableView tableBooks;
    ObservableList<Book> model = FXCollections.observableArrayList();
    public void initModel(){
        columnId.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        columnAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        var allBooks=this.srv.findAllBooksReturned();
        model.setAll(allBooks);
        tableBooks.setItems(model);
    }
    public void initialize(Service srv){
        this.srv=srv;
        this.srv.addObserver(this);
        this.initModel();
    }
    public void handleRent(ActionEvent actionEvent) {
        Book selected= (Book) tableBooks.getSelectionModel().getSelectedItem();
        if(selected!=null) {
            this.srv.rentBook(selected);
            model.clear();
            var all=this.srv.findAllBooksReturned();
            model.setAll(all);
            tableBooks.setItems(model);
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION,"information","Successful rent!");
            return;
        }
        else{
            MessageAlert.showErrorMessage(null, "Book not selected!");
            return;
        }
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

    @Override
    public void update(BookEntityChangeEvent bookEntityChangeEvent) {
        initModel();
    }
}
