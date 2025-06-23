package com.taliesin.taliesin.artist;

import java.util.List;
import java.util.stream.Collectors;

import com.taliesin.taliesin.Music.MusicDTO;

public class ArtistDTO {
    public int id;
    public String name;
    public String image;
    public List<MusicDTO> musics;

    public ArtistDTO(Artist artist) {
        this.id = artist.getId();
        this.name = artist.getName();
        this.image = artist.getPicture();
        this.musics = artist.getMusics().stream()
                            .map(MusicDTO::new)
                            .collect(Collectors.toList());
    }
}
