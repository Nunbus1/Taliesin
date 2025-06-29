package com.taliesin.taliesin.Music; 

/**
 * DTO (Data Transfer Object) représentant une musique.
 * Sert à exposer uniquement les données nécessaires côté client.
 */
public class MusicDTO {

    public int id;
    public String title;
    public String image;
    public String duration;
    public String artist;
    public String src;
    public boolean liked;

    /**
     * Construit un {@link MusicDTO} à partir de l'entité {@link Music}.
     *
     * @param music entité d'origine
     */
    public MusicDTO(Music music) {
        this.id = music.getId();
        this.title = music.getTitle();
        this.image = music.getPicture();
        this.duration = String.format("%d:%02d", music.getDuration() / 60, music.getDuration() % 60);
        this.artist = music.getArtist() != null ? music.getArtist().getName() : "Inconnu";
        this.src = music.getSrc();
        this.liked = music.isLiked();
    }
}
