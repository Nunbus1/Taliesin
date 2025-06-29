package com.taliesin.taliesin.artist; 

import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Composant Spring permettant de gérer la récupération des artistes.
 * Sert d'intermédiaire entre le contrôleur et le repository.
 */
@Component
public class ArtistComponent {

    private final ArtistRepository artistRepository;

    /**
     * Constructeur avec injection de dépendance du repository.
     *
     * @param artistRepository le repository des artistes
     */
    public ArtistComponent(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    /**
     * Récupère la liste de tous les artistes sous forme de DTO.
     *
     * @return liste des artistes transformés en {@link ArtistDTO}
     */
    public List<ArtistDTO> getAllArtists() {
        return artistRepository.findAll().stream()
                .map(ArtistDTO::new)
                .toList();
    }
}
