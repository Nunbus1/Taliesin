# Taliesin

Taliesin est une application web développée avec **Spring Boot**.  
Elle propose une bibliothèque musicale accessible après authentification, avec la
gestion des artistes, des morceaux et du profil utilisateur.

## Fonctionnalités

- Authentification et inscription d’utilisateurs (upload de photo de profil).
- Consultation des artistes et de leurs musiques.
- Lecture et mise en favori des morceaux.
- API REST exposant :
  - `/api/music` : liste des musiques.
  - `/api/music/{id}` : détails d’une musique.
  - `/api/music/{id}/liked` : bascule de l’état « aimé ».
  - `/api/artists` : liste des artistes.
  - `/api/user` : informations de l’utilisateur connecté.
- Interface web statique (HTML/CSS/JS) incluant un lecteur audio.

## Pré‑requis

- Java 17
- Maven
- Base de données MariaDB accessible sur `localhost:3307`
  avec un schéma `taliesin` et l’utilisateur `taliesin`  
  (mot de passe : `Romain#BG22` — à adapter au besoin).
- Un script SQL pour créer la base et l’utilisateur est disponible dans
  `taliesin/src/main/resources/static/data/database.sql`.

Les paramètres de connexion se trouvent dans
`taliesin/src/main/resources/application.properties`.

## Lancement de l’application

```bash
# depuis le dossier racine
cd taliesin
./mvnw spring-boot:run
```

L’application est alors disponible sur `http://localhost:8080`.

## Exécution des tests

```bash
./mvnw test
```

## Structure du projet

```
taliesin/
├── src/main/java/com/taliesin/taliesin
│   ├── Music/             # entités et contrôleurs pour les musiques
│   ├── artist/            # entités, composant et contrôleur des artistes
│   ├── security/          # configuration Spring Security
│   ├── user/              # gestion des utilisateurs
│   └── TaliesinApplication.java
├── src/main/resources
│   ├── application.properties
│   └── static/            # fichiers HTML, CSS et JavaScript
└── pom.xml                # configuration Maven
```

## Licence

Projet académique sans licence spécifique.