package com.taliesin.taliesin.artist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository JPA pour accéder aux données des artistes.
 * Fournit les opérations CRUD de base via {@link JpaRepository}.
 */
@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    // Pas de méthodes personnalisées nécessaires pour l’instant
}
