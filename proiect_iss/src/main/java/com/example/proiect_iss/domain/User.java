package com.example.proiect_iss.domain;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Objects;

public class User{
    private String username, password,cnp,name,address,phoneNumber;
    private Boolean isLibrarian;
    public User(){
        this.cnp="-1";
    }
    public User(Librarian librarian){
        this.username=librarian.getUsername();
        this.password=librarian.getPassword();
        this.isLibrarian=Boolean.TRUE;
        this.cnp="";
        this.name="";
        this.address="";
        this.phoneNumber="";
    }

    public User(String cnp, String name, String address, String phone, String username, String password, Boolean isLibrarian) {
        this.cnp = cnp;
        this.name = name;
        this.address = address;
        this.phoneNumber = phone;
        this.username = username;
        this.password = password;
        this.isLibrarian = isLibrarian;
    }

    public User(Subscriber subscriber){
        this.username=subscriber.getUsername();
        this.password=subscriber.getPassword();
        this.cnp=subscriber.getCnp();
        this.name=subscriber.getName();
        this.address=subscriber.getAddress();
        this.phoneNumber=subscriber.getPhoneNumber();
        this.isLibrarian=Boolean.FALSE;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", cnp='" + cnp + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isLibrarian=" + isLibrarian +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getCnp(), user.getCnp()) && Objects.equals(getName(), user.getName()) && Objects.equals(getAddress(), user.getAddress()) && Objects.equals(getPhoneNumber(), user.getPhoneNumber()) && Objects.equals(isLibrarian, user.isLibrarian);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getCnp(), getName(), getAddress(), getPhoneNumber(), isLibrarian);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getIsLibrarian() {
        return isLibrarian;
    }

    public void setIsLibrarian(Boolean librarian) {
        isLibrarian = librarian;
    }
}
