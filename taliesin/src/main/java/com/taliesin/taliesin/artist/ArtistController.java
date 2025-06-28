package com.taliesin.taliesin.artist;

import java.util.List;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur REST exposant les endpoints liés aux artistes.
 * Fournit un accès en lecture à la liste des artistes.
 */
@RestController
@RequestMapping("/api/artists")
@CrossOrigin(origins = "*")
public class ArtistController {

    private final ArtistComponent artistComponent;

    /**
     * Constructeur avec injection de dépendance du composant Artist.
     *
     * @param artistComponent le composant métier pour la gestion des artistes
     */
    public ArtistController(ArtistComponent artistComponent) {
        this.artistComponent = artistComponent;
    }

    /**
     * Endpoint GET pour récupérer tous les artistes.
     *
     * @return liste des artistes au format {@link ArtistDTO}
     */
    @GetMapping
    public List<ArtistDTO> getAllArtists() {
        return artistComponent.getAllArtists();
    }
}
