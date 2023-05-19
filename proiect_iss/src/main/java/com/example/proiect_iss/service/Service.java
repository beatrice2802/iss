package com.example.proiect_iss.service;

import com.example.proiect_iss.domain.User;
import com.example.proiect_iss.repository.UserRepo;
//import com.example.proiect_iss.repository.UserRepo;

public class Service {
    private UserRepo userRepo;
    //private BookRepo bookRepo; TODO
    public Service(UserRepo userRepo){
        this.userRepo=userRepo;
    }
    public User login(String username,String password){
        return this.userRepo.findOne(username,password);
    }
    public User createAcc(String cnp,String name,String address,String phone,String username,String password,Boolean is_librarian)
    {
        User u=new User(username,password,cnp,name,address,phone,is_librarian);
        return this.userRepo.add(u);
    }
}
