
package services;

import connexion.Connexion;
import dao.IDao;
import entities.Client;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientService implements IDao<Client> {

@Override
public boolean create(Client c) {
    try {
        String req = "INSERT INTO client ( nom,prenom, email) VALUES (?, ?, ?)";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ps.setString(1, c.getNom());
        ps.setString(2, c.getPrenom());
        ps.setString(3, c.getEmail());
        ps.executeUpdate();
        return true;
    } catch (SQLException ex) {
        Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
}

@Override
public boolean update(Client c) {
    try {
        String req = "UPDATE client SET  nom = ?,prenom = ?, email = ? WHERE id = ?";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
         ps.setString(1, c.getNom());
        ps.setString(2, c.getPrenom());
        ps.setString(3, c.getEmail());
        ps.setInt(4, c.getId());
        ps.executeUpdate();
        return true;
    } catch (SQLException ex) {
        Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
}


    @Override
public boolean delete(Client c) {
    try {
        String req = "DELETE FROM client WHERE id = ?";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ps.setInt(1, c.getId());
        ps.executeUpdate();
        return true;
    } catch (SQLException ex) {
        Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
}

    @Override
    public Client findById(int id) {
         try {
        String req = "SELECT * FROM client WHERE id = ?";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            return new Client(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom") , rs.getString("email"));
        }
    } catch (SQLException ex) {
        Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
}
    @Override
public List<Client> findAll() {
    List<Client> clients = new ArrayList<>();
    try {
        String req = "SELECT * FROM client";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            clients.add(new Client(rs.getInt("id"), rs.getString("nom"),rs.getString("prenom"), rs.getString("email")));
        }
    } catch (SQLException ex) {
        Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return clients;
}


}
