package com.taliesin.taliesin.Music; 

import com.taliesin.taliesin.artist.Artist;
import jakarta.persistence.*;

/**
 * Entité représentant une musique dans la base de données.
 * Chaque musique est associée à un artiste.
 */
@Entity
@Table(name = "music")
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String src;
    private int duration;
    private boolean liked;
    private String picture;

    @ManyToOne
    @JoinColumn(name = "id_artist")
    private Artist artist;

    /**
     * @return Identifiant unique de la musique.
     */
    public int getId() {
        return id;
    }

    /**
     * @return URL ou chemin vers l'image de la musique.
     */
    public String getPicture() {
        return picture;
    }

    /**
     * @return Titre de la musique.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return Durée de la musique (en secondes).
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @return Artiste associé à la musique.
     */
    public Artist getArtist() {
        return artist;
    }

    /**
     * @return Chemin source du fichier audio.
     */
    public String getSrc() {
        return src;
    }

    /**
     * @return Indique si l'utilisateur a liké cette musique.
     */
    public boolean isLiked() {
        return liked;
    }

    /**
     * Définit l'état like de la musique.
     *
     * @param liked vrai si likée, faux sinon.
     */
    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
