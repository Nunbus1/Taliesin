/**
 * Contient l'ensemble des données musicales chargées depuis le JSON.
 * @type {Array<Object>}
 */
let fullData = [];

// ==========================
// Sélecteurs DOM principaux
// ==========================
const wrapper = document.querySelector(".swiper-wrapper");
const nextSongsContainer = document.querySelector(".next-songs .song-container");
const shownIds = new Set();
const homeButton = document.getElementById("home-button");
const containers = document.querySelectorAll(".containers");
const favoriteButton = document.getElementById("home-favorite");

// ==========================
// Initialisation
// ==========================
document.addEventListener("DOMContentLoaded", () => {
    loadAllMusics();
});

const mainLikeButton = document.querySelector(".main-like-button");
if (mainLikeButton) bindLikeButton(mainLikeButton);

homeButton.addEventListener("click", loadAllMusics);

// ==========================
// Fonctions principales
// ==========================

/**
 * Crée un slide Swiper pour chaque musique d'un artiste.
 * @param {Object} artist - Données de l'artiste contenant un tableau de musiques.
 */
function createMusicSlide(artist) {
    artist.musics.forEach((music) => {
        if (shownIds.has(music.id)) return;
        shownIds.add(music.id);

        const slide = document.createElement("div");
        slide.classList.add("swiper-slide");
        slide.setAttribute("data-id", music.id);

        slide.innerHTML = `
            <img src="${music.image}" alt="Playlist Cover" />
            <div class="slide-overlay">
                <h2>${music.title}</h2>
                <div class="slide-buttons">
                    <button class="listen-button">
                        Listen Now <i class="fa-solid fa-circle-play"></i>
                    </button>
                    <button class="queue-button" data-id="${music.id}">
                        Queue <i class="fa-solid fa-music"></i>
                    </button>
                </div>
            </div>
        `;

        wrapper.appendChild(slide);

        slide.querySelector(".listen-button").addEventListener("click", () => {
            window.loadTrackFromData(music);
        });

        slide.querySelector(".queue-button").addEventListener("click", () => {
            addToQueue(music, artist);
        });
    });
}

/**
 * Ajoute une musique à la file d'attente visuelle.
 * @param {Object} music - Objet musique.
 * @param {Object} artist - Objet artiste parent (pour le nom).
 */
function addToQueue(music, artist) {
    const song = document.createElement("div");
    song.classList.add("song");

    song.dataset.music = JSON.stringify({
        title: music.title,
        artist: music.artist || artist.name,
        src: music.src,
        image: music.image,
        duration: music.duration,
        liked: music.liked
    });

    song.dataset.id = music.id;

    song.dataset.music = JSON.stringify(music);

    const isLiked = music.liked === true;

    song.innerHTML = `
        <div class="song-img">
            <img src="${music.image}" alt="album cover">
            <div class="overlay"><i class="fa-solid fa-play"></i></div>
        </div>
        <div class="song-title">
            <h2>${music.title}</h2>
            <p>${artist.name}</p>
        </div>
        <span>${music.duration}</span>
        <button class="like-button ${isLiked ? 'liked' : ''}" data-id="${music.id}">
            <i class="${isLiked ? 'fa-solid' : 'fa-regular'} fa-heart"></i>
        </button>
    `;

    nextSongsContainer.appendChild(song);

    const likeButton = song.querySelector(".like-button");
    if (likeButton) bindLikeButton(likeButton);
}


/**
 * Affiche uniquement les musiques d'un artiste donné dans le slider.
 * @param {Object} artist - Données de l'artiste.
 */
function renderMusicSlides(artist) {
    wrapper.innerHTML = "";
    shownIds.clear();
    createMusicSlide(artist);
    if (window.swiper) window.swiper.update();
}

/**
 * Charge toutes les musiques depuis le fichier JSON et les affiche dans le slider.
 */
function loadAllMusics(search = "") {
    fetch("/api/artists")
        .then((response) => response.json())
        .then((data) => {
            fullData = data;
            wrapper.innerHTML = "";
            shownIds.clear();

            data.forEach((artist) => {
                // si le champ de recherche n'est pas nul, on filtre les artistes et musiques qui contiennent le texte voulu 
                if (search != "") {
                    const filteredMusics = artist.musics.filter(music =>
                        music.title.toLowerCase().includes(search) ||
                        artist.name.toLowerCase().includes(search)
                    );

                    // si la recherche a des résultats, on remplace la liste de toutes les musiques par les résultats 
                    if (filteredMusics.length > 0) {
                        const artistCopy = {
                            ...artist,
                            musics: filteredMusics
                        };

                        // suppression des sliders de toutes les musiques et création des sliders pour les résultats
                        wrapper.innerHTML = "";
                        createMusicSlide(artistCopy);
                        return;
                    }

                }
                createMusicSlide(artist);
            });

            if (window.swiper) window.swiper.update();

            if (!window.artistClickBound) {
                document.addEventListener("click", (e) => {
                    const artistEl = e.target.closest(".artist");
                    if (!artistEl || !artistEl.dataset.artistId) return;

                    const id = parseInt(artistEl.dataset.artistId, 10);
                    const artist = fullData.find((a) => a.id === id);
                    if (artist) renderMusicSlides(artist);
                });
                window.artistClickBound = true;
            }
        });
}

/**
 * Affiche uniquement les musiques likées par l'utilisateur.
 * Réinitialise l'affichage du slider et recharge les slides avec les musiques favorites.
 */
favoriteButton.addEventListener("click", () => {
    wrapper.innerHTML = "";
    shownIds.clear();

    const favoriteMusics = fullData
        .map(artist => {
            const likedMusics = artist.musics.filter(music => music.liked);
            return likedMusics.length > 0 ? { ...artist, musics: likedMusics } : null;
        })
        .filter(Boolean);

    favoriteMusics.forEach(createMusicSlide);

    if (window.swiper) window.swiper.update();
});


// ==========================
// SwiperJS Configuration
// ==========================
document.addEventListener("DOMContentLoaded", function () {
    new Swiper(".swiper", {
        effect: "coverflow",
        grabCursor: true,
        centeredSlides: true,
        loop: false,
        speed: 600,
        slidesPerView: "auto",
        coverflowEffect: {
            rotate: 10,
            stretch: 120,
            depth: 200,
            modifier: 1,
            slideShadows: false,
        },
        on: {
            click(event) {
                this.slideTo(this.clickedIndex);
            },
        },
        pagination: {
            el: ".swiper-pagination",
        },
    });
});

// ==========================
// Scroll drag horizontal custom
// ==========================
containers.forEach((container) => {
    let isDragging = false;
    let startX;
    let scrollLeft;

    container.addEventListener("mousedown", (e) => {
        isDragging = true;
        startX = e.pageX - container.offsetLeft;
        scrollLeft = container.scrollLeft;
    });

    container.addEventListener("mousemove", (e) => {
        if (!isDragging) return;
        e.preventDefault();
        const x = e.pageX - container.offsetLeft;
        const step = (x - startX) * 0.6;
        container.scrollLeft = scrollLeft - step;
    });

    container.addEventListener("mouseup", () => {
        isDragging = false;
    });

    container.addEventListener("mouseleave", () => {
        isDragging = false;
    });
});