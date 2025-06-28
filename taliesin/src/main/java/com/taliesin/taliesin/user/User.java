package com.taliesin.taliesin.user;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    private String email;  // utilisé comme identifiant

    private String password;
    private String first;
    private String last;

    @Column(name = "picture")
    private String picture;

    // Getters et setters
    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
