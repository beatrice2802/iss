package com.example.proiect_iss.controllers;

import com.example.proiect_iss.HelloApplication;
import com.example.proiect_iss.domain.Book;
import com.example.proiect_iss.service.Service;
import com.example.proiect_iss.utils.events.BookEntityChangeEvent;
import com.example.proiect_iss.utils.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibrarianController implements Observer<BookEntityChangeEvent> {
    public TableColumn columnBookId;
    public TableColumn columnTitle;
    public TableColumn columnStatus;
    public Service srv;
    public TableView tableBooks;
    public TextField textTitle;
    public TextField textAuthor;
    public TableColumn columnAuthor;
    ObservableList<Book> model = FXCollections.observableArrayList();

    public void initModel(){
        columnBookId.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        columnAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<Book, String>("status"));
        var allBooks=this.srv.findAllBooks();
        model.setAll(allBooks);
        tableBooks.setItems(model);
    }

    public void initialize(Service srv){
        this.srv=srv;
        this.srv.addObserver(this);
        this.initModel();
    }

    public void handleAdd(ActionEvent actionEvent) {
        var title=textTitle.getText();
        var author=textAuthor.getText();
        if(title.equals("") || author.equals("")){
            MessageAlert.showErrorMessage(null, "incorrect info!");
            return;
        }
        Book book=new Book(author,title);
        book.setStatus("returned");
        var book_new=this.srv.saveBook(book);
//        model.add(book_new);
//        tableBooks.setItems(model);
    }


    public void handleDelete(ActionEvent actionEvent) {
        Book selected= (Book) tableBooks.getSelectionModel().getSelectedItem();
        if(selected!=null) {
            Integer id=selected.getId();
            this.srv.deleteBook(selected);
            model.clear();
            var all=this.srv.findAllBooks();
            model.setAll(all);
            tableBooks.setItems(model);
        }
        else{
            MessageAlert.showErrorMessage(null, "Book not selected!");
            return;
        }
    }

    public void handleReturn(ActionEvent actionEvent) {
        Book selected= (Book) tableBooks.getSelectionModel().getSelectedItem();
        if(selected!=null) {
            Integer id=selected.getId();

            this.srv.returnBook(selected);
            model.clear();
            var all=this.srv.findAllBooks();
            model.setAll(all);
            tableBooks.setItems(model);
        }
        else{
            MessageAlert.showErrorMessage(null, "Book not selected!");
            return;
        }
    }

    public void handleLogOut(ActionEvent actionEvent) throws IOException {
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
    }

    @Override
    public void update(BookEntityChangeEvent bookEntityChangeEvent) {
        initModel();
    }
}
