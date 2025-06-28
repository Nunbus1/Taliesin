package com.taliesin.taliesin.Music;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository JPA pour la gestion des entités {@link Music}.
 * Fournit automatiquement les opérations CRUD de base.
 */
public interface MusicRepository extends JpaRepository<Music, Integer> {
    // Méthodes personnalisées à ajouter ici si besoin
}
