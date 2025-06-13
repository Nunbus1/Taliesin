/**
 * Associe un comportement de like à un bouton donné.
 * Alterne les classes "liked", "fa-regular" et "fa-solid" à chaque clic.
 *
 * @param {HTMLElement} button - Le bouton auquel attacher l'événement.
 */
function bindLikeButton(button) {
  button.addEventListener("click", () => {
    button.classList.toggle("liked");

    const icon = button.querySelector("i");
    icon.classList.toggle("fa-regular");
    icon.classList.toggle("fa-solid");
  });
}

// Initialisation : lie les événements à tous les boutons de like existants
document.querySelectorAll(".like-button").forEach(bindLikeButton);
