// ===============================
// Gestion des likes synchronisés avec la BDD
// ===============================

/**
 * Associe un comportement de like à un bouton donné.
 * Alterne les classes "liked", "fa-regular" et "fa-solid",
 * et synchronise avec la BDD via un appel PUT.
 *
 * @param {HTMLElement} button - Le bouton auquel attacher l'événement.
 */
function bindLikeButton(button) {
  button.addEventListener("click", () => {
    // Cas général (queue, slide)
    let id = null;
    const parent = button.closest(".song, .swiper-slide");

    if (parent?.dataset?.id) {
      id = parent.dataset.id;
    } else if (button.classList.contains("main-like-button")) {
      // Cas du bouton principal du player
      id = button.dataset.id;
    }

    if (!id) return;

    fetch(`http://localhost:8080/api/music/${id}/liked`, {
      method: "PUT"
    })
      .then(res => res.json())
      .then(updatedMusic => {
        // Mets à jour tous les boutons visibles
        updateLikeButtonsState(updatedMusic.id, updatedMusic.liked);

        // Mets à jour les données internes (fullData)
        fullData.forEach(artist => {
          artist.musics.forEach(music => {
            if (music.id === updatedMusic.id) {
              music.liked = updatedMusic.liked;
            }
          });
        });
      })
      .catch((e) => {
        console.error("Erreur lors du like :", e);
      });
  });
}


/**
 * Met à jour tous les boutons de like correspondant à un ID de musique donné
 * en fonction de son état de like.
 *
 * @param {number} musicId - L'identifiant de la musique.
 * @param {boolean} isLiked - L'état liked de la musique.
 */
function updateLikeButtonsState(musicId, isLiked) {
  // Tous les boutons "like" ayant un parent avec data-id = musicId
  const contextualButtons = document.querySelectorAll(`[data-id="${musicId}"] .like-button`);
  
  contextualButtons.forEach((btn) => {
    const icon = btn.querySelector("i");
    btn.classList.toggle("liked", isLiked);
    icon.classList.toggle("fa-solid", isLiked);
    icon.classList.toggle("fa-regular", !isLiked);
  });

  // Le bouton principal du lecteur s’il existe
  const mainLikeButton = document.querySelector(`.main-like-button[data-id="${musicId}"]`);
  if (mainLikeButton) {
    const icon = mainLikeButton.querySelector("i");
    mainLikeButton.classList.toggle("liked", isLiked);
    icon.classList.toggle("fa-solid", isLiked);
    icon.classList.toggle("fa-regular", !isLiked);
  }
}


/**
 * Ajoute un bouton like à un élément de musique et initialise son état.
 *
 * @param {HTMLElement} parentElement - L'élément HTML contenant le bouton.
 * @param {boolean} isLiked - L'état liked de la musique.
 */
function addLikeButton(parentElement, isLiked) {
  const likeButton = parentElement.querySelector(".like-button");
  if (!likeButton) return;

  if (isLiked) {
    likeButton.classList.add("liked");
    likeButton.querySelector("i").classList.add("fa-solid");
    likeButton.querySelector("i").classList.remove("fa-regular");
  }

  bindLikeButton(likeButton);
}

// Exemple dans addToQueue (ou autre endroit où on ajoute dynamiquement une musique)
// à la fin :
// addLikeButton(song, music.liked);

// Initialisation des boutons existants
document.querySelectorAll(".like-button").forEach(bindLikeButton);
