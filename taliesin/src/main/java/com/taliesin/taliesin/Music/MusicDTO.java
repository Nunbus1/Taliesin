package com.taliesin.taliesin.Music;

public class MusicDTO {
    public int id;
    public String title;
    public String image;
    public String duration;
    public String artist;
    public String src;

    public MusicDTO(Music music) {
        this.id = music.getId();
        this.title = music.getTitle();
        this.image = music.getPicture();
        this.duration = String.format("%d:%02d", music.getDuration() / 60, music.getDuration() % 60);
        this.artist = music.getArtist().getClass().getName();
        this.src = music.getSrc();
    }
}
