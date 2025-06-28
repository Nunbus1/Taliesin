package com.taliesin.taliesin.repository;

import com.taliesin.taliesin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository JPA pour l'entité {@link User}.
 * Fournit les opérations CRUD et une méthode de recherche par email.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Recherche un utilisateur par son adresse email.
     * Utilisé notamment par Spring Security pour l'authentification.
     *
     * @param email l'adresse email à rechercher
     * @return un {@link Optional} contenant l'utilisateur s'il est trouvé
     */
    Optional<User> findByEmail(String email);
}
