package com.taliesin.taliesin.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    private String first;
    private String last;

    @Id
    private String email;  // utilisé comme identifiant

    private String password;

    private String role = "USER";

    private String picture; // URL ou last de fichier (optionnel)

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

    public void setpicture(String picture) {
        this.picture = picture;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
