/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author HP
 */

import connexion.Connexion;
import dao.IDao;
import entities.VenteArt;
import entities.Oeuvre;
import entities.Client;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VenteArtService implements IDao<VenteArt> {

  public VenteArtService() {
    
}



    @Override
    public boolean create(VenteArt c) {
         try{
            String req = "INSERT INTO venteart (oeuvre_id, client_id, dateVente) VALUES (?, ?, ?)";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, c.getOeuvre().getId());
            ps.setInt(2, c.getClient().getId());
            ps.setDate(3, new java.sql.Date(c.getDateVente().getTime()));
           
            ps.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VenteArtService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean update(VenteArt c) {
       try {
            String req = "UPDATE venteart SET oeuvre_id = ?, client_id = ?, dateVente = ? WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, c.getOeuvre().getId());
            ps.setInt(2, c.getClient().getId());
            ps.setDate(3, new java.sql.Date(c.getDateVente().getTime()));
            ps.setInt(4, c.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VenteArtService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(VenteArt c) {
          try {
            String req = "DELETE FROM venteart WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, c.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VenteArtService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public VenteArt findById(int id) {
          try {
            String req = "SELECT * FROM venteart WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
               OeuvreService os = new OeuvreService();
                ClientService cs = new ClientService();
                return new VenteArt(rs.getInt("id"),
                                    os.findById(rs.getInt("oeuvre_id")),
                                    cs.findById(rs.getInt("client_id")),
                                    rs.getDate("dateVente")); 
            }
          } catch (SQLException ex) {
            Logger.getLogger(VenteArtService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<VenteArt> findAll() {
        List<VenteArt> ventes = new ArrayList<>();
        try {
            String req = "SELECT * FROM venteart";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            OeuvreService os = new OeuvreService();
            ClientService cs = new ClientService();
            while(rs.next()) {
                ventes.add(new VenteArt(rs.getInt("id"),
                                        os.findById(rs.getInt("oeuvre_id")),
                                        cs.findById(rs.getInt("client_id")),
                                        rs.getDate("dateVente")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VenteArtService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ventes;
    }
     public List<VenteArt> findByArtiste(String artiste) {
    List<VenteArt> ventes = new ArrayList<>();
    try {
        String req = "SELECT v.* FROM venteart v "
                   + "JOIN oeuvre o ON v.oeuvre_id = o.id "
                   + "WHERE o.artiste = ?";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ps.setString(1, artiste);
        ResultSet rs = ps.executeQuery();

        OeuvreService os = new OeuvreService();
        ClientService cs = new ClientService();

        while(rs.next()) {
            ventes.add(new VenteArt(
                rs.getInt("id"),
                os.findById(rs.getInt("oeuvre_id")),
                cs.findById(rs.getInt("client_id")),
                rs.getDate("dateVente")
            ));
        }
    } catch(SQLException ex) {
        Logger.getLogger(VenteArtService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return ventes;
}
public List<String[]> topArtistes() {
    List<String[]> list = new ArrayList<>();
    try {
        String req = "SELECT o.artiste, COUNT(*) AS ventes "
                   + "FROM venteart v "
                   + "JOIN oeuvre o ON v.oeuvre_id = o.id "
                   + "GROUP BY o.artiste "
                   + "ORDER BY ventes DESC";

        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            list.add(new String[]{
                rs.getString("artiste"),
                rs.getString("ventes")
            });
        }
    } catch (SQLException ex) {
        Logger.getLogger(VenteArtService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
}
public Map<String, Integer> getVentesParArtiste() {
    Map<String, Integer> data = new HashMap<>();
    try {
      String req = "SELECT artiste, COUNT(*) AS total FROM venteart v JOIN oeuvre o ON v.oeuvre_id = o.id GROUP BY artiste";

        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            data.put(rs.getString("artiste"), rs.getInt("total"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return data;
}

}
