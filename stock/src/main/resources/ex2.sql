CREATE TABLE categorie (
    id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(50),
    libelle VARCHAR(50)
);

CREATE TABLE commande (
    id INT PRIMARY KEY AUTO_INCREMENT,
    date_commande DATE
);

CREATE TABLE produit (
    id INT PRIMARY KEY AUTO_INCREMENT,
    reference VARCHAR(255),
    prix DOUBLE,
    categorie_id int,
    foreign key (categorie_id) references categorie(id)
);

CREATE TABLE ligneCommandeProduit (
    commande_id INT,
    produit_id INT,
    quantity INT,
    PRIMARY KEY (commande_id,produit_id),
    FOREIGN KEY (commande_id) REFERENCES commande(id),
    FOREIGN KEY (produit_id) REFERENCES produit(id)
);
