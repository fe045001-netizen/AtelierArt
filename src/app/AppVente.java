package app;

import entities.Client;
import entities.Oeuvre;
import entities.VenteArt;
import services.ClientService;
import services.OeuvreService;
import services.VenteArtService;
import java.util.Date;
import java.util.List;

public class AppVente {
    public static void main(String[] args) {
        ClientService cs = new ClientService();
        OeuvreService os = new OeuvreService();
        VenteArtService vs = new VenteArtService();

        Client c = cs.findById(1);
        Oeuvre o = os.findById(2);
        
         if (c == null) {
            System.out.println("Le client ID=1 n'existe pas.");
        }

        if (o == null) {
            System.out.println(" L'œuvre ID=2 n'existe pas.");
        }

        if (c != null && o != null) {
            VenteArt vente = new VenteArt(o, c, new Date());
            vs.create(vente);
            System.out.println(" Vente enregistrée !");
        }

        List<VenteArt> ventes = vs.findAll();
        System.out.println("Toutes les ventes:");
        for(VenteArt v : ventes) {
            System.out.println(v);
        }
        System.out.println("\n===== VENTES PAR ARTISTE : Picasso =====");
        List<VenteArt> vPicasso = vs.findByArtiste("Picasso");
        for (VenteArt v : vPicasso) {
            System.out.println(v);
        }

        System.out.println("\n===== TOP ARTISTES (classement par ventes) =====");
        List<String[]> top = vs.topArtistes();
        for (String[] row : top) {
            System.out.println("Artiste : " + row[0] + " | Ventes : " + row[1]);
        }
       


        
    }

}
