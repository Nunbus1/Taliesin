/**
 * Récupère les informations de l'utilisateur connecté via l'API
 * et met à jour son image de profil et son prénom dans l'interface.
 */
fetch('/api/user')
  .then(response => response.json())
  .then(user => {
    document.getElementById("profilepicture").src = user.picture;
    document.getElementById("username").textContent = user.first;
  });
