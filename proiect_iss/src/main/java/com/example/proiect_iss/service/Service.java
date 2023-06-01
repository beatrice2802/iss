package com.example.proiect_iss.service;

import com.example.proiect_iss.domain.Book;
import com.example.proiect_iss.domain.User;
import com.example.proiect_iss.repository.BookRepo;
import com.example.proiect_iss.repository.UserRepo;
import com.example.proiect_iss.utils.events.BookEntityChangeEvent;
import com.example.proiect_iss.utils.events.ChangeEventType;
import com.example.proiect_iss.utils.observer.Observable;
import com.example.proiect_iss.utils.observer.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
//import com.example.proiect_iss.repository.UserRepo;

public class Service implements Observable<BookEntityChangeEvent> {
    private UserRepo userRepo;
    private BookRepo bookRepo;
    private List<Observer<BookEntityChangeEvent>> observers=new ArrayList<>();

    public Service(UserRepo userRepo,BookRepo bookRepo){
        this.userRepo=userRepo;
        this.bookRepo=bookRepo;
    }

    public User login(String username,String password){
        return this.userRepo.findOne(username,password);
    }
    public User createAcc(String cnp,String name,String address,String phone,String username,String password,Boolean is_librarian)
    {
//        User u=new User(username,password,cnp,name,address,phone,is_librarian);
        User u=new User(cnp,name,address,phone,username,password,is_librarian);
        return this.userRepo.add(u);
    }
    public List<Book> findAllBooks(){return this.bookRepo.findAll();}
    public Book saveBook(Book book){
        var res=this.bookRepo.save(book);
        if(res!=null){
            notifyObservers(new BookEntityChangeEvent(ChangeEventType.ADD,book));
        }
        return res;
    }
    public Book deleteBook(Book book){
        var res=this.bookRepo.delete(book);
        if(res!=null){
            notifyObservers(new BookEntityChangeEvent(ChangeEventType.DELETE,book));
        }
        return res;
    }
    public Book returnBook(Book book){
        book.setStatus("returned");
        var res= this.bookRepo.update(book);
        if(res!=null){
            notifyObservers(new BookEntityChangeEvent(ChangeEventType.UPDATE,book));
        }
        return res;
    }
    public Book rentBook(Book book){
        book.setStatus("rent");
        var res= this.bookRepo.update(book);
        if(res!=null){
            notifyObservers(new BookEntityChangeEvent(ChangeEventType.UPDATE,book));
        }
        return res;
    }
    public List<Book> findAllBooksReturned(){return this.bookRepo.findAllReturned();}

    @Override
    public void addObserver(Observer<BookEntityChangeEvent> e) {
        observers.add(e);

    }

    @Override
    public void removeObserver(Observer<BookEntityChangeEvent> e) {

    }

    @Override
    public void notifyObservers(BookEntityChangeEvent t) {
        observers.stream().forEach(x->x.update(t));

    }
}
