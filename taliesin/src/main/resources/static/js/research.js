var research = document.getElementById('search')

research.addEventListener('input', (e) => {
    console.log(e);
    loadAllMusics(research.value);
})
