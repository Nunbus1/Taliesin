/**
 * Déclenche la recherche de musiques à chaque saisie dans le champ de recherche.
 */
const searchInput = document.getElementById('search');

searchInput.addEventListener('input', (e) => {
  loadAllMusics(searchInput.value);
});
