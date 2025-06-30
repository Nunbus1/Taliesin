/**
 * Charge les données des artistes depuis un fichier JSON
 * et génère dynamiquement les éléments HTML correspondants.
 */
fetch('/api/artists')
    .then((response) => response.json())
    .then((data) => {
        const artistContainer = document.querySelector('.artist-container');

        data.forEach((artist) => {
            const artistDiv = document.createElement('div');
            artistDiv.classList.add('artist');
            artistDiv.dataset.artistId = artist.id;

            const imgContainer = document.createElement('div');
            imgContainer.classList.add('artist-img-container');

            const img = document.createElement('img');
            img.src = artist.image;
            img.alt = artist.name;

            const name = document.createElement('p');
            name.textContent = artist.name;

            imgContainer.appendChild(img);
            artistDiv.appendChild(imgContainer);
            artistDiv.appendChild(name);
            artistContainer.appendChild(artistDiv);
        });
    })
    .catch(() => {
    });
