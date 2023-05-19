package com.example.proiect_iss.domain;

import java.util.Objects;

public class Librarian {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Librarian librarian)) return false;
        return Objects.equals(getName(), librarian.getName()) && Objects.equals(getUsername(), librarian.getUsername()) && Objects.equals(getPassword(), librarian.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getUsername(), getPassword());
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    private String name,username,password;

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Librarian(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
