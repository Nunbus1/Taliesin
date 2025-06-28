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
  updateLikeButtonsState(music.id, music.liked);
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
 * Lit la piste précédente depuis l'historique d'écoute
 * et remet la piste actuelle au début de la file "next".
 */
function playPreviousFromHistory() {
  if (history.length <= 1) return;

  const current = history.pop(); // Supprime la musique actuelle
  const previous = history[history.length - 1];

  if (previous) {
    // Ajouter l'actuelle tout en haut de la file d'attente
    const nextSongsContainer = document.querySelector(".next-songs .song-container");
    const song = document.createElement("div");
    song.classList.add("song");

    song.dataset.id = current.id;
    song.dataset.music = JSON.stringify(current);

    song.innerHTML = `
      <div class="song-img">
        <img src="${current.image}" alt="album cover">
        <div class="overlay"><i class="fa-solid fa-play"></i></div>
      </div>
      <div class="song-title">
        <h2>${current.title}</h2>
        <p>${current.artist}</p>
      </div>
      <span>${current.duration}</span>
      <button class="like-button">
        <i class="fa-${current.liked ? 'solid' : 'regular'} fa-heart"></i>
      </button>
    `;

    // Ajout au début de la file
    nextSongsContainer.insertBefore(song, nextSongsContainer.firstChild);

    // Ajoute le bouton like avec son état
    addLikeButton(song, current.liked);

    // Charge la piste précédente
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
