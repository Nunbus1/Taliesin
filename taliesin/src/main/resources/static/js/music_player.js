// ========================
// Sélecteurs DOM principaux
// ========================
const playBtn = document.querySelector(".play-button");
const prevBtn = document.querySelector(".prev-button");
const nextBtn = document.querySelector(".next-button");
const audio = document.querySelector(".audio-player");
const musicImage = document.querySelector(".music-image");
const title = document.querySelector(".music-title");
const artist = document.querySelector(".music-artist");
const progressBar = document.querySelector(".progress-bar");
const volumeSlider = document.querySelector(".volume-slider");

// ========================
// Variables d'état
// ========================
let isPlaying = false;
let history = [];

// ========================
// Fonctions principales
// ========================

/**
 * Charge et lit une piste musicale à partir d'un objet `music`.
 * Met à jour l'historique, les informations d'affichage et lance la lecture.
 *
 * @param {Object} music - Objet contenant les données de la musique.
 * @param {string} music.title - Titre de la chanson.
 * @param {string} music.id_artist - Nom de l'artiste.
 * @param {string} music.src - Chemin du fichier audio.
 * @param {string} music.image - Chemin de l'image de l'album.
 */
window.loadTrackFromData = function (music) {
  const last = history[history.length - 1];
  if (!last || last.src !== music.src) {
    history.push(music);
    if (history.length > 10) history.shift();
  }

  title.textContent = music.title;
  artist.textContent = music.artist;
  audio.src = music.src;
  musicImage.src = music.image;
  progressBar.value = 0;

  audio.play();
  isPlaying = true;
  playBtn.innerHTML = `<i class="fa-solid fa-pause"></i>`;
  musicImage.style.animationPlayState = "running";

  const mainLikeButton = document.querySelector(".main-like-button");
  mainLikeButton.setAttribute("data-id", music.id);

  const icon = mainLikeButton.querySelector("i");
  if (music.liked) {
    mainLikeButton.classList.add("liked");
    icon.classList.remove("fa-regular");
    icon.classList.add("fa-solid");
  } else {
    mainLikeButton.classList.remove("liked");
    icon.classList.remove("fa-solid");
    icon.classList.add("fa-regular");
  }
};

/**
 * Joue la prochaine chanson présente dans la file d'attente.
 */
window.playNextFromQueue = function () {
  const nextSongsContainer = document.querySelector(".next-songs .song-container");
  const nextSong = nextSongsContainer.querySelector(".song");
  if (!nextSong) return;

  nextSongsContainer.removeChild(nextSong);

  const musicData = nextSong.getAttribute("data-music");
  if (!musicData) return;

  try {
    const music = JSON.parse(musicData);
    window.loadTrackFromData(music);

  } catch (_) {
    console.warn("Erreur lors du parsing JSON ou de la mise à jour du like.");
  }
};

/**
 * Lit la piste précédente depuis l'historique d'écoute.
 */
function playPreviousFromHistory() {
  if (history.length <= 1) return;

  // Supprime la musique actuelle
  history.pop();

  // Récupère la précédente
  const previous = history[history.length - 1];
  if (previous) {
    window.loadTrackFromData(previous);
  }
}

/**
 * Met en pause ou reprend la lecture selon l'état actuel.
 */
function togglePlay() {
  if (!audio.src) return;

  if (audio.paused) {
    audio.play();
    isPlaying = true;
    playBtn.innerHTML = `<i class="fa-solid fa-pause"></i>`;
    musicImage.style.animationPlayState = "running";
  } else {
    audio.pause();
    isPlaying = false;
    playBtn.innerHTML = `<i class="fa-solid fa-play"></i>`;
    musicImage.style.animationPlayState = "paused";
  }
}

// ========================
// Gestionnaires d'événements
// ========================
playBtn.addEventListener("click", togglePlay);
prevBtn.addEventListener("click", playPreviousFromHistory);
nextBtn.addEventListener("click", window.playNextFromQueue);

audio.addEventListener("ended", window.playNextFromQueue);

audio.addEventListener("timeupdate", () => {
  if (!audio.duration) return;
  progressBar.value = (audio.currentTime / audio.duration) * 100;
});

progressBar.addEventListener("input", () => {
  audio.currentTime = (progressBar.value / 100) * audio.duration;
});

volumeSlider.addEventListener("input", () => {
  audio.volume = volumeSlider.value / 100;
});
