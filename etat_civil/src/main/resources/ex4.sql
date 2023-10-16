CREATE TABLE personne (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255) ,
    prenom VARCHAR(255),
    address VARCHAR(255),
    telephone VARCHAR(255),
    dateNaissance DATE
);

CREATE TABLE homme (
    id INT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES personne(id)
);

CREATE TABLE femme (
    id INT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES personne(id)
);

CREATE TABLE marriage (
    dateDebut DATE NOT NULL,
    dateFin DATE,
    nbEnfants INT,
    homme_id INT,
    femme_id INT,
    PRIMARY KEY (homme_id,femme_id),
    FOREIGN KEY (homme_id) REFERENCES homme(id),
    FOREIGN KEY (femme_id) REFERENCES femme(id)
);
