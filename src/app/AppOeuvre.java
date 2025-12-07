package app;

import entities.Oeuvre;
import services.OeuvreService;
import java.util.List;

public class AppOeuvre {
    public static void main(String[] args) {
        OeuvreService os = new OeuvreService();

       os.create(new Oeuvre("Matin Bleu", "Amina B.", "Peinture", 1200.0));
        
       os.create(new Oeuvre("Guernica", "Picasso", "Peinture", 2000));

       
        Oeuvre o = os.findById(1);
        if (o != null) {
            System.out.println("\n Oeuvre trouvée : " + o);
            o.setPrix(1500); 
            os.update(o);
            System.out.println("Après modification : " + os.findById(1));
        } else {
            System.out.println("\n Œuvre ID=1 introuvable");
        }

        List<Oeuvre> oeuvres = os.findAll();
        System.out.println("Toutes les œuvres:");
        for(Oeuvre ov : oeuvres) {
            System.out.println(ov);
        }

    
         System.out.println("\n===== ŒUVRES DE PICASSO =====");
        List<Oeuvre> artiste = os.findByArtiste("Picasso");
        if (artiste.isEmpty()) {
            System.out.println("Aucune œuvre trouvée.");
        } else {
            artiste.forEach(System.out::println);
        }

        // Supprimer une œuvre (optionnel)
        // os.delete(os.findById(2));
    }
}
