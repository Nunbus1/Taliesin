package com.taliesin.taliesin.artist;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/artists")
@CrossOrigin(origins = "*") // ou spécifie ton domaine
public class ArtistController {

    private final ArtistComponent artistComponent;

    public ArtistController(ArtistComponent artistComponent) {
        this.artistComponent = artistComponent;
    }

    @GetMapping
    public List<ArtistDTO> getAllArtists() {
        return artistComponent.getAllArtists();
    }
}
