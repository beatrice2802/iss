package com.example.proiect_iss.domain;

import java.util.Objects;

public class Subscriber{
    private String username, password,cnp,name,address,phoneNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subscriber that)) return false;
        return Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getPassword(), that.getPassword()) && Objects.equals(getCnp(), that.getCnp()) && Objects.equals(getName(), that.getName()) && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getPhoneNumber(), that.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getCnp(), getName(), getAddress(), getPhoneNumber());
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", cnp='" + cnp + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
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

    public Subscriber(String username, String password, String cnp, String name, String address, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.cnp = cnp;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
