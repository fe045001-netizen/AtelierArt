#  AtelierArt
##  Atelier d’Art — Gestion des Œuvres, Clients et Ventes

**Atelier d’Art** est une application de gestion permettant d’administrer les œuvres d’art, de suivre les ventes, de filtrer par artiste ou catégorie et d’analyser les chiffres grâce à un graphique des ventes par artiste.


##  Table des matières

-  [Contexte](#-contexte)  
-  [Problématique](#-problématique)  
-  [Objectifs](#-objectifs)  
-  [Fonctionnalités](#-fonctionnalités)  
-  [Structure des tables](#-structure-des-tables)  
-  [Requêtes SQL](#-requêtes-sql)  
-  [Architecture](#-architecture)  
-  [Technologies utilisées](#-technologies-utilisées)  
-  [Démo](#-démo)

##  Contexte

Dans le cadre de la gestion d’un atelier d’art, il est important de centraliser les informations concernant les œuvres, les clients et les ventes.
Actuellement, la gestion se fait de manière manuelle, ce qui entraîne des erreurs dans le suivi des ventes et rend difficile l’analyse de la performance des artistes

## Problématique

Les principaux problèmes rencontrés sont :
Difficulté à suivre l’inventaire des œuvres d’art.
Perte d’informations sur les clients et les ventes.
Absence de statistiques pour mesurer les ventes par artiste ou par catégorie.
Processus manuel long et sujet à erreurs.

## Objectifs
L’objectif de l’application AtelierArt est de :
Centraliser la gestion des œuvres, des clients et des ventes.
Faciliter l’ajout, la modification et la suppression d’œuvres et de clients.
Permettre un suivi précis des ventes.
Fournir des outils d’analyse grâce à des graphiques de ventes par artiste ou par catégorie.

## Fonctionnalités
L’application permet de :
Ajouter, modifier, supprimer et lister les œuvres.
Ajouter, modifier, supprimer et lister les clients.
Enregistrer les ventes et consulter l’historique.
Filtrer les ventes par artiste ou catégorie.
Visualiser un graphique des ventes par artiste.

## Daigramme
# Diagramme de classe:

## Requêtes SQL
```sql
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
```

## Architecture


##  Technologies utilisées

Java (Swing pour l’interface graphique)
MySQL pour la base de données
JDBC pour la connexion à la base de données
JFreeChart pour les graphiques


## Démo

Lien ou description de la démo ici…
