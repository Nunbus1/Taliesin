package com.taliesin.taliesin.Music;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Contrôleur REST pour la gestion des musiques.
 * Fournit des endpoints pour consulter et aimer les musiques.
 */
@RestController
@RequestMapping("/api/music")
@CrossOrigin(origins = "*") // Autorise toutes les origines pour le frontend
public class MusicController {

    @Autowired
    private MusicRepository musicRepository;

    /**
     * Récupère la liste de toutes les musiques sous forme de DTO.
     *
     * @return liste de {@link MusicDTO}
     */
    @GetMapping
    public List<MusicDTO> getAllMusic() {
        return musicRepository.findAll()
                .stream()
                .map(MusicDTO::new)
                .collect(Collectors.toList());
    }

    /**
     * Récupère une musique spécifique par son identifiant.
     *
     * @param id identifiant de la musique
     * @return {@link MusicDTO} si trouvée, sinon 404
     */
    @GetMapping("/{id}")
    public ResponseEntity<MusicDTO> getMusicById(@PathVariable int id) {
        Optional<Music> optionalMusic = musicRepository.findById(id);
        return optionalMusic
                .map(music -> ResponseEntity.ok(new MusicDTO(music)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Inverse l’état du champ "liked" d’une musique (like/unlike).
     *
     * @param id identifiant de la musique
     * @return {@link MusicDTO} mis à jour, ou 404 si non trouvée
     */
    @PutMapping("/{id}/liked")
    public ResponseEntity<MusicDTO> toggleLike(@PathVariable int id) {
        Optional<Music> optionalMusic = musicRepository.findById(id);
        if (optionalMusic.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Music music = optionalMusic.get();
        music.setLiked(!music.isLiked());
        musicRepository.save(music);

        return ResponseEntity.ok(new MusicDTO(music));
    }
}
