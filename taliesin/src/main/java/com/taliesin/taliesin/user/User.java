package com.taliesin.taliesin.user;

import jakarta.persistence.*;

/**
 * Entité représentant un utilisateur de l'application.
 * Utilisée pour l'authentification et le profil utilisateur.
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    private String email;

    private String password;
    private String first;
    private String last;

    @Column(name = "picture")
    private String picture;

    /**
     * @return Nom de famille de l'utilisateur.
     */
    public String getLast() {
        return last;
    }

    /**
     * @param last Nom de famille à définir.
     */
    public void setLast(String last) {
        this.last = last;
    }

    /**
     * @return Prénom de l'utilisateur.
     */
    public String getFirst() {
        return first;
    }

    /**
     * @param first Prénom à définir.
     */
    public void setFirst(String first) {
        this.first = first;
    }

    /**
     * @return Email de l'utilisateur (identifiant principal).
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email Email à définir.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return Mot de passe de l'utilisateur (haché).
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password Mot de passe à définir (sera encodé).
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return Chemin de l'image de profil.
     */
    public String getPicture() {
        return picture;
    }

    /**
     * @param picture Chemin de l'image de profil à définir.
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }
}
