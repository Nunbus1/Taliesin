package com.taliesin.taliesin.artist;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ArtistComponent {

    private final ArtistRepository artistRepository;

    public ArtistComponent(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<ArtistDTO> getAllArtists() {
        return artistRepository.findAll().stream()
                .map(ArtistDTO::new)
                .toList();
    }
}