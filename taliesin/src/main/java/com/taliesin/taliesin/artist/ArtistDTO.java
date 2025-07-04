package com.taliesin.taliesin.artist;

import java.util.List;
import java.util.stream.Collectors;
import com.taliesin.taliesin.Music.MusicDTO;

/**
 * DTO (Data Transfer Object) représentant un artiste côté client.
 * Contient les informations essentielles d'un artiste ainsi que ses musiques associées.
 */
public class ArtistDTO {

    public int id;
    public String name;
    public String image;
    public List<MusicDTO> musics;

    /**
     * Construit un ArtistDTO à partir d'une entité {@link Artist}.
     *
     * @param artist l'entité d'origine
     */
    public ArtistDTO(Artist artist) {
        this.id = artist.getId();
        this.name = artist.getName();
        this.image = artist.getPicture();
        this.musics = artist.getMusics().stream()
                            .map(MusicDTO::new)
                            .collect(Collectors.toList());
    }
}
