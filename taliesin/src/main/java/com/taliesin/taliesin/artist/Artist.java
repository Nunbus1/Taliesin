package com.taliesin.taliesin.artist;

import java.util.List;
import com.taliesin.taliesin.Music.Music;
import jakarta.persistence.*;

/**
 * Représente un artiste dans la base de données.
 * Chaque artiste peut être associé à plusieurs musiques.
 */
@Entity
@Table(name = "artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String picture;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Music> musics;

    /**
     * @return Identifiant unique de l'artiste.
     */
    public int getId() {
        return id;
    }

    /**
     * @return Nom de l'artiste.
     */
    public String getName() {
        return name;
    }

    /**
     * @return URL de l'image de l'artiste.
     */
    public String getPicture() {
        return picture;
    }

    /**
     * @return Liste des musiques associées à l'artiste.
     */
    public List<Music> getMusics() {
        return musics;
    }
}
