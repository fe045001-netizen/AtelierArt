package app;

import entities.Client;
import services.ClientService;
import java.util.List;
import java.util.Scanner;

public class AppClient {
    public static void main(String[] args) {
        ClientService cs = new ClientService();

        cs.create(new Client("Ahmed","sassi" ,"ahmed@gmail.com"));
        cs.create(new Client("Sara", "amrani","sara@hotmail.com"));
 
        List<Client> clients = cs.findAll();
        for(Client cli : clients) {
            System.out.println(cli);
        }
 
Client c = cs.findById(2);
if(c != null) {
    System.out.println("Client trouvé: " + c);
    c.setNom(c.getNom() + " Modifié");
    c.setEmail("nouvelEmail@example.com");  
    cs.update(c);
    System.out.println("Client après modification: " + c);
} else {
    System.out.println("Client non trouvé.");
}


        // Supprimer un client (optionnel)
        // cs.delete(cs.findById(2));
    }
}
