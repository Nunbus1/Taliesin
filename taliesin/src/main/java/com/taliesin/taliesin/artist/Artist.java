package com.taliesin.taliesin.artist;


import java.util.List;

import com.taliesin.taliesin.Music.Music;

import jakarta.persistence.*;


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

    public int getId() {
        return id;
    }

    public String getName() {
       return name;
    }

    public String getPicture() {
        return picture;
    }

    public List<Music> getMusics() {
        return musics;
    }

}
