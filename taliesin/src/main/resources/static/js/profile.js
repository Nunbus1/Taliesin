fetch('/api/user')
  .then(response => response.json())
  .then(user => {
    document.getElementById("profilepicture").src = user.picture;
    document.getElementById("username").innerHTML = user.first;
});