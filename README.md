# AtelierArt
🎨 # Atelier d’Art — Gestion des Œuvres, Clients et Ventes

Atelier d’Art est une application de gestion permettant d’administrer les œuvres d’art, de suivre les ventes, de filtrer par artiste ou catégorie et d’analyser les chiffres grâce à un graphique des ventes par artiste.

#📁# Table de matières

🗂 Contexte

❓ Problématique

🎯 Objectif

📊 Diagrammes

🗃 Tables de Données

✨ Fonctionnalités Principales

🔍 Requêtes SQL

🏛 Architecture

🛠 Technologies Utilisées

📈 Graphique des ventes

🎥 Démo Vidéo (optionnel)

📁 .exe (optionnel)

🗂 Contexte

La gestion d’un atelier d’art nécessite une organisation précise : suivi des œuvres disponibles, gestion des clients, enregistrement des ventes, et mise en valeur des artistes.
Les solutions manuelles (Excel, papier) entraînent souvent des erreurs, des doublons et une perte de temps.
Une application informatique dédiée permet d’automatiser ces tâches et d’optimiser le suivi de l’activité artistique.

❓ Problématique

Les ateliers et galeries d’art rencontrent des difficultés telles que :

Absence de suivi structuré des œuvres et artistes

Gestion compliquée des ventes

Manque de statistiques sur les artistes les plus performants

Difficulté à filtrer les œuvres par catégorie ou artiste

Aucun système automatisé pour la consultation des données

🎯 Objectif

Développer une application permettant :

Une gestion complète des œuvres (ajout, mise à jour, suppression)

Un suivi clair des ventes

Un accès rapide aux informations clients

Un filtrage avancé par artiste ou catégorie

Une visualisation graphique des ventes par artiste

📊 Diagrammes
Diagramme de cas d’utilisation (Use Case)

Exemples d’acteurs : Administrateur – Employé

Gérer œuvres

Gérer clients

Enregistrer une vente

Filtrer œuvres

Afficher statistiques

(Ajouter vos diagrammes exportés depuis StarUML)

Diagramme de classes

Classes recommandées :

Oeuvre

Client

VenteArt

Services : OeuvreService, ClientService, VenteService

(Ajouter votre diagramme UML ici)

🗃 Tables de Données
Table : Oeuvre
Champ	Type	Description
id	INT (PK)	Identifiant
titre	VARCHAR(100)	Nom de l'œuvre
artiste	VARCHAR(100)	Nom de l’artiste
categorie	VARCHAR(50)	Catégorie artistique
prix	DECIMAL(10,2)	Prix de vente
Table : Client
Champ	Type
id	INT (PK)
nom	VARCHAR(50)
email	VARCHAR(100)
Table : VenteArt
Champ	Type
oeuvre_id	INT (FK)
client_id	INT (FK)
dateVente	DATE
✨ Fonctionnalités Principales
1️⃣ Gestion des œuvres

Ajouter une œuvre

Modifier une œuvre

Supprimer une œuvre

Lister toutes les œuvres

2️⃣ Gestion des clients

Ajouter un client

Modifier / supprimer

Afficher la liste des clients

3️⃣ Gestion des ventes d’art

Enregistrer une vente (sélection œuvre + client)

Lister les ventes

Voir les détails d’une vente

4️⃣ Filtrage

Par artiste

Par catégorie

5️⃣ Statistiques graphiques

Graphique : Nombre de ventes par artiste
(UI : Java Swing + JFreeChart ou JavaFX Chart)

🔍 Requêtes SQL
Création des tables :
CREATE TABLE Oeuvre (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(100) NOT NULL,
    artiste VARCHAR(100) NOT NULL,
    categorie VARCHAR(50) NOT NULL,
    prix DECIMAL(10,2) NOT NULL
);

CREATE TABLE Client (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE VenteArt (
    oeuvre_id INT NOT NULL,
    client_id INT NOT NULL,
    dateVente DATE DEFAULT CURRENT_DATE,
    PRIMARY KEY (oeuvre_id, client_id),
    FOREIGN KEY (oeuvre_id) REFERENCES Oeuvre(id),
    FOREIGN KEY (client_id) REFERENCES Client(id)
);

🏛 Architecture (MVC)
1. Interface (UI – Java Swing)

Fenêtres :

Gestion Œuvres

Gestion Clients

Gestion Ventes

Statistiques (graphique)

2. Services (Business Logic)

OeuvreService

ClientService

VenteService

3. DAO (Accès à la base)

JDBC

Requêtes SQL paramétrées

4. Base de données MySQL

Tables : Oeuvre, Client, VenteArt

🛠 Technologies Utilisées
Domaine	Technologie
Interface graphique	Java Swing
Base de données	MySQL
Connexion BD	JDBC
IDE	NetBeans
Diagrammes UML	StarUML
Gestion BD	phpMyAdmin
Graphique	JFreeChart (ou JavaFX Charts)
Icônes	Icons8
📈 Graphique des ventes par artiste
Exemple de calcul :
SELECT artiste, COUNT(*) AS nb_ventes
FROM Oeuvre o
JOIN VenteArt v ON o.id = v.oeuvre_id
GROUP BY artiste;


Affichage graphique Java :

Axe X : Artistes

Axe Y : Nombre de ventes

Type : Bar Chart
