/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author HP
 */
public class VenteArt {
    private int id;
    private Date datevente;
    private Client client;
    private Oeuvre oeuvre;

    public VenteArt() {
    }

    public VenteArt(int id, Date datevente, Client client, Oeuvre oeuvre) {
        this.id = id;
        this.datevente = datevente;
        this.client = client;
        this.oeuvre = oeuvre;
    }

    public VenteArt(Date datevente, Client client, Oeuvre oeuvre) {
        this.datevente = datevente;
        this.client = client;
        this.oeuvre = oeuvre;
    }
public VenteArt(Oeuvre o, Client c, Date date) {
    this.oeuvre = o;
    this.client = c;
    this.datevente = date;
}

public VenteArt(int id, Oeuvre o, Client c, Date date) {
    this.id = id;
    this.oeuvre = o;
    this.client = c;
    this.datevente = date;
}

   

    public int getId() {
        return id;
    }

    public Date getDateVente() {
        return datevente;
    }

    public Client getClient() {
        return client;
    }

    public Oeuvre getOeuvre() {
        return oeuvre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDateVente(Date datevente) {
        this.datevente = datevente;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setOeuvre(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
    }

    @Override
    public String toString() {
        return "VenteArt{" + "id=" + id + ", datevente=" + datevente + ", client=" + client + ", oeuvre=" + oeuvre + '}';
    }
   
    
    
    
    
}
