#  AtelierArt
##  Atelier d’Art — Gestion des Œuvres, Clients et Ventes

**Maison Art** est une application de gestion permettant d’administrer les œuvres d’art, de suivre les ventes, de filtrer par artiste ou catégorie et d’analyser les chiffres grâce à un graphique des ventes par artiste.



##  Table des matières

-  [Contexte](#-contexte)  
-  [Problématique](#-problématique)  
-  [Objectifs](#-objectifs)  
-  [Fonctionnalités](#-fonctionnalités)  
-  [Diagrammes](#-Diagrammes)
-  [Architecture](#-architecture)
-  [Requêtes SQL](#-requêtes-sql)  
-  [Technologies utilisées](#-technologies-utilisées) 
-  [Démo](#-démo)
-  [Auteur](#-Auteur)  

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

### Objectif général

Développer une application desktop pour gérer les œuvres d’art, suivre les ventes, filtrer les données et visualiser les statistiques sous forme graphique.

### Objectifs spécifiques

Mettre en place une interface graphique intuitive pour manipuler les œuvres et les ventes

Faciliter l’enregistrement d’une vente entre un client et une œuvre

Filtrer les œuvres (par artiste / par catégorie)

Afficher le classement des artistes les plus vendus

Générer un graphique des ventes par artiste

Assurer une communication fiable entre Java et MySQL via JDBC

# Diagramme
**Diagramme use classe**:
![URL image](https://github.com/fe045001-netizen/AtelierArt/blob/8200113183739f40971c8b13c1d307940d119925/images/3.png)

**Diagramme de classe**:

![URL image](https://github.com/fe045001-netizen/AtelierArt/blob/8200113183739f40971c8b13c1d307940d119925/images/3.png)

## Architecture
![URL image](https://github.com/fe045001-netizen/AtelierArt/blob/8200113183739f40971c8b13c1d307940d119925/images/architecture.png)

## Fonctionnalités

**Gestion des œuvres**

Ajouter une œuvre (titre, artiste, catégorie, prix)

Modifier une œuvre

Supprimer une œuvre

Afficher toutes les œuvres disponibles
**Gestion des vebntes**

Enregistrer une vente (sélectionner un client + une œuvre)

Supprimer ou consulter une vente

Suivre les ventes réalisées sur une période (optionnel)

**Filtrage**

Filtrer les œuvres par artiste

Filtrer les œuvres par catégorie

**Recherche**

Recherche d’une œuvre par titre

Recherche d’un artiste

**Statistiques**

Afficher le Top artistes (artistes les plus vendus)

Générer un graphique des ventes par artiste via JFreeChart

## Structure des données (Base de données)

Les trois entités principales du projet sont :

# Oeuvre

titre

artiste

categorie

prix

# Client

nom

email

# VenteArt

oeuvre

client

dateVente

# Relations

Une œuvre peut être vendue plusieurs fois

Un client peut acheter plusieurs œuvres

Relation N..N → VenteArt

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
    prenom VARCHAR(100) NOT NULL,
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

##  Technologies utilisées

- *Java* : Langage principal pour la logique applicative et le développement de l'interface.
- *Java Swing* : Pour la création de l'interface graphique (GUI).
- *JFreeChart* : Pour la génération de graphiques et la visualisation de données.
- *JavaMail API* : Pour l'envoi et la gestion des e-mails depuis l'application.
- *MySQL* : Base de données relationnelle pour stocker les données de l'application.
- *JDBC* : Pour la connexion et l'accès aux données depuis Java.
- *phpMyAdmin* : Outil de gestion de base de données MySQL.
- *NetBeans IDE* : Environnement de développement intégré pour le codage, le débogage et la compilation.
- *MagicDraw*: création des diagrammes UML (Use Case, classes, séquence)

## Démo



## Auteur

 Nom:Sahmad Fatima-ezzahra
 
 Cours:Programmation Java
 
 Date:Décembre 2025
 
 Encadré par: Mohamed LACHGAR
