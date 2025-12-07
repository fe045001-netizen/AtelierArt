package entities;


public class Oeuvre {
    private int id;
    private String titre;
    private String artiste ;
    private String categorie;
    private double prix ;

    public Oeuvre() {
    }

    public Oeuvre(String titre, String artiste, String categorie, double prix) {
        this.titre = titre;
        this.artiste = artiste;
        this.categorie = categorie;
        this.prix = prix;
    }
    

    public Oeuvre(int id,String titre,String artiste,String categorie,double prix) {
       this.id =id;
       this.titre=titre;
       this.artiste=artiste;
       this.categorie=categorie;
       this.prix=prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getArtiste() {
        return artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return this.titre;
    }
        
   
}
