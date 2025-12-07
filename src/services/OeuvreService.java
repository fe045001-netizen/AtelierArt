package services;

import connexion.Connexion;
import dao.IDao;
import entities.Oeuvre;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class OeuvreService implements IDao<Oeuvre>{
            
    @Override
    public boolean create(Oeuvre c) {
       try{
        String req="INSERT INTO `oeuvre` ( `titre`, `artiste`, `categorie`, `prix`) VALUES ( ?, ?, ?, ?)";
       PreparedStatement ps =Connexion.getConnection().prepareStatement(req);
            ps.setString(1,c.getTitre());
            ps.setString(2, c.getArtiste());
            ps.setString(3, c.getCategorie());
            ps.setDouble(4, c.getPrix());
            ps.executeUpdate();
            return true;
       }catch (SQLException ex) {
            Logger.getLogger(OeuvreService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    
    @Override
    public boolean update(Oeuvre c) {
        try{
         String req = "UPDATE oeuvre SET titre = ?, artiste = ?, categorie = ?, prix = ? WHERE id = ?";
            PreparedStatement ps =Connexion.getConnection().prepareStatement(req);
            ps.setString(1,c.getTitre());
            ps.setString(2, c.getArtiste());
            ps.setString(3, c.getCategorie());
            ps.setDouble(4, c.getPrix());
            ps.setInt(5, c.getId());
            ps.executeUpdate();
            return true;
       }catch (SQLException ex) {
            Logger.getLogger(OeuvreService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }


    @Override
    public boolean delete(Oeuvre c) {       
     try {
            String req = "DELETE FROM oeuvre WHERE id = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, c.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(OeuvreService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Oeuvre findById(int id) {
        try{
            String req="Select * FROM oeuvre WHERE id =?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return new Oeuvre(rs.getInt("id"), rs.getString("titre"), rs.getString("artiste"),
                                  rs.getString("categorie"), rs.getDouble("prix"));
            }
        }catch(SQLException ex){
            Logger.getLogger(OeuvreService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        }

    @Override
    public List<Oeuvre> findAll() {
     List<Oeuvre> oeuvres=new ArrayList<>();
     try{
         String req = "SELECT * FROM oeuvre";
         PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
               oeuvres.add(new Oeuvre(rs.getInt("id"), rs.getString("titre"), rs.getString("artiste"),
                                       rs.getString("categorie"), rs.getDouble("prix")));
         }
     }catch (SQLException ex) {
            Logger.getLogger(OeuvreService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return oeuvres;
}
    
public List<Oeuvre> findByArtiste(String artiste) {
    List<Oeuvre> list = new ArrayList<>();
    try {
        String req = "SELECT * FROM oeuvre WHERE artiste = ?";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ps.setString(1, artiste);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            list.add(new Oeuvre(
                rs.getInt("id"),
                rs.getString("titre"),
                rs.getString("artiste"),
                rs.getString("categorie"),
                rs.getDouble("prix")
            ));
        }
    } catch (SQLException ex) {
        Logger.getLogger(OeuvreService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
}

public List<Oeuvre> findByCategorie(String categorie) {
    List<Oeuvre> list = new ArrayList<>();
    try {
        String req = "SELECT * FROM oeuvre WHERE categorie = ?";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ps.setString(1, categorie);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            list.add(new Oeuvre(
                rs.getInt("id"),
                rs.getString("titre"),
                rs.getString("artiste"),
                rs.getString("categorie"),
                rs.getDouble("prix")
            ));
        }
    } catch(SQLException ex) {
        Logger.getLogger(OeuvreService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
}

    public List<String> findCategories() {
    List<String> categories = new ArrayList<>();
    try {
        String req = "SELECT DISTINCT categorie FROM oeuvre";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            categories.add(rs.getString("categorie"));
        }
    } catch(SQLException ex) {
        Logger.getLogger(OeuvreService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return categories;
}

    


    public List<String> findArtiste() {
List<String> artistes = new ArrayList<>();
    try {
        String req = "SELECT DISTINCT artiste FROM oeuvre";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            artistes.add(rs.getString("artiste"));
        }
    } catch (SQLException ex) {
        Logger.getLogger(OeuvreService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return artistes;    }
}

