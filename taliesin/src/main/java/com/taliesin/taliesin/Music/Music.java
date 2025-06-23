package com.taliesin.taliesin.Music;

import com.taliesin.taliesin.artist.Artist;

import jakarta.persistence.*;

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
    private int tempo;

    @ManyToOne
    @JoinColumn(name = "id_artist")
    private Artist artist;

    public int getId() {
        return id;
    }

    public String getPicture() {
       return picture;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
       return duration;
    }

    public Object getArtist() {
        return artist;
    }

    public String getSrc() {
       return src;
    }

}