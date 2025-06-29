#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------
CREATE USER 'taliesin'@'localhost' IDENTIFIED VIA mysql_native_password USING PASSWORD('Romain#BG22');
    CREATE DATABASE taliesin;
    USE taliesin;
    GRANT ALL PRIVILEGES ON * . * TO 'taliesin'@'localhost';
    FLUSH PRIVILEGES;

#------------------------------------------------------------
# Table: user
#------------------------------------------------------------

CREATE TABLE user(
        email    Varchar (100) NOT NULL ,
        password Varchar (500) NOT NULL ,
        first    Varchar (20) NOT NULL ,
        last     Varchar (20) NOT NULL ,
        picture  Varchar (500)
	,CONSTRAINT user_PK PRIMARY KEY (email)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: artist
#------------------------------------------------------------

CREATE TABLE artist(
        id      Int  Auto_increment  NOT NULL ,
        name    Varchar (100) NOT NULL ,
        picture Varchar (500)
	,CONSTRAINT artist_PK PRIMARY KEY (id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: music
#------------------------------------------------------------

CREATE TABLE music(
        id        Int  Auto_increment  NOT NULL ,
        title     Varchar (30) NOT NULL ,
        src       Varchar (500) NOT NULL ,
        duration  Int NOT NULL ,
        liked     Bool NOT NULL ,
        picture   Varchar (500) ,
        tempo     Int NOT NULL ,
        id_artist Int NOT NULL
	,CONSTRAINT music_PK PRIMARY KEY (id)

	,CONSTRAINT music_artist_FK FOREIGN KEY (id_artist) REFERENCES artist(id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: has_favorite
#------------------------------------------------------------

CREATE TABLE has_favorite(
        id    Int NOT NULL ,
        email Varchar (100) NOT NULL
	,CONSTRAINT has_favorite_PK PRIMARY KEY (id,email)

	,CONSTRAINT has_favorite_music_FK FOREIGN KEY (id) REFERENCES music(id)
	,CONSTRAINT has_favorite_user0_FK FOREIGN KEY (email) REFERENCES user(email)
)ENGINE=InnoDB;


#-----------------------------------------------------------
# Insertion données 
#-----------------------------------------------------------

INSERT INTO `artist` (`id`, `name`, `picture`) VALUES 
(NULL, 'KarapiX', 'https://t4.ftcdn.net/jpg/03/03/62/45/360_F_303624505_u0bFT1Rnoj8CMUSs8wMCwoKlnWlh5Jiq.jpg');

INSERT INTO `artist` (`id`, `name`, `picture`) VALUES 
(NULL, 'Pier', 'https://www.megavoxels.com/wp-content/uploads/2023/09/Pixel-Art-Watermelon.png');

INSERT INTO `artist` (`id`, `name`, `picture`) VALUES 
(NULL, 'Aubin', 'https://st3.depositphotos.com/13193658/33561/i/450/depositphotos_335618508-stock-photo-serious-king-crown-holding-sword.jpg');

INSERT INTO `music` (`id`, `title`, `src`, `duration`, `liked`, `picture`, `tempo`, `id_artist`) 
VALUES (NULL, "KarapiX, l'Infin Live", "http://localhost:8080/data/Music/KarapiX,%20l'Infini%20Live.mp3", 
'239', '0', 'https://cdn.m7g.twitch.tv/ba46b4e5e395b11efd34/assets/uploads/core-header.png', '120', '1');

INSERT INTO `music` (`id`, `title`, `src`, `duration`, `liked`, `picture`, `tempo`, `id_artist`) 
VALUES (NULL, 'Pier et Montréal', 'http://localhost:8080/data/Music/Pier%20et%20Montréal.mp3', 
'239', '0', 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/c2/Mtl_from_mont_royal_%28cropped%29.jpg/500px-Mtl_from_mont_royal_%28cropped%29.jpg', '120', '2');

INSERT INTO `music` (`id`, `title`, `src`, `duration`, `liked`, `picture`, `tempo`, `id_artist`) 
VALUES (NULL, 'Mathys le musclé', 'http://localhost:8080/data/Music/Mathys%20Le%20Musclé.mp3', 
'239', '0', 'https://cdn.prod.website-files.com/64085217259d8631bfd59788/65b0e2e5d4655faa9e7058af_Muscler-dos_main.webp', '120', '1');

INSERT INTO `music` (`id`, `title`, `src`, `duration`, `liked`, `picture`, `tempo`, `id_artist`) 
VALUES (NULL, 'Aubin le Roi ultime', 'http://localhost:8080/data/Music/Aubin%20le%20Roi%20ultime.mp3', 
'239', '0', 'https://dalliesedenmariages.fr/cdn/shop/collections/couronne-mariage-166278.jpg?v=1691571601', '120', '3');

INSERT INTO `music` (`id`, `title`, `src`, `duration`, `liked`, `picture`, `tempo`, `id_artist`) 
VALUES (NULL, 'Pier le dieu du virtuel', 'http://localhost:8080/data/Music/Pier,%20le%20Dieu%20du%20virtuel.mp3', 
'239', '0', 'https://i.redd.it/og603oczp00d1.png', '120', '2');