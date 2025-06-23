fetch('/api/user')
          .then(response => response.json())
          .then(user => {
            document.getElementById("profilepicture").src = user.picture;
            console.log(user)
          });