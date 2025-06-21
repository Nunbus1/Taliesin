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
        email    Varchar (50) NOT NULL ,
        token    Varchar (50) NOT NULL ,
        password Varchar (50) NOT NULL ,
        first    Varchar (20) NOT NULL ,
        last     Varchar (20) NOT NULL ,
        picture  Varchar (30)
	,CONSTRAINT user_PK PRIMARY KEY (email)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: artist
#------------------------------------------------------------

CREATE TABLE artist(
        id      Int  Auto_increment  NOT NULL ,
        name    Varchar (30) NOT NULL ,
        picture Varchar (500)
	,CONSTRAINT artist_PK PRIMARY KEY (id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: music
#------------------------------------------------------------

CREATE TABLE music(
        id        Int  Auto_increment  NOT NULL ,
        title     Varchar (30) NOT NULL ,
        src       Varchar (50) NOT NULL ,
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
        email Varchar (50) NOT NULL
	,CONSTRAINT has_favorite_PK PRIMARY KEY (id,email)

	,CONSTRAINT has_favorite_music_FK FOREIGN KEY (id) REFERENCES music(id)
	,CONSTRAINT has_favorite_user0_FK FOREIGN KEY (email) REFERENCES user(email)
)ENGINE=InnoDB;

