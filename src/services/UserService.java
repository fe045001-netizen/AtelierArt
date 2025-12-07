package services;

import connexion.Connexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.mindrot.jbcrypt.BCrypt;
import javax.swing.JOptionPane;

public class UserService {

    public boolean updatePasswordByEmail(String email, String newPass) {
        try {
            String hashedPassword = BCrypt.hashpw(newPass, BCrypt.gensalt());

            String sql = "UPDATE utilisateur SET password = ? WHERE email = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);

            ps.setString(1, hashedPassword);
            ps.setString(2, email);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erreur updatePasswordByEmail : " + e.getMessage());
            return false;
        }
    }

public boolean checkLogin(String nom, String password) {
    try {
        String sql = "SELECT password FROM utilisateur WHERE nom = ?";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
        ps.setString(1, nom);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String hashed = rs.getString("password");
            return BCrypt.checkpw (password, hashed);
        }

        return false;

    } catch (Exception e) {
        System.out.println("Erreur checkLogin : " + e.getMessage());
        return false;
    }
}



    public void resetPassword(String emailTo) {

        // 1. Générer un nouveau mot de passe
        final String newPassword = "Art" + (int)(Math.random() * 900 + 100);

        // 2. Mettre à jour dans la base
        boolean updated = updatePasswordByEmail(emailTo, newPassword);

        if (!updated) {
            JOptionPane.showMessageDialog(null,
                    "Erreur : Email introuvable dans la base !",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // 3. Informations email
        final String senderEmail = "f.sahmad6480@uca.ac.ma";
        final String senderPassword = "rjnm ybah unwl nmom"; // mot de passe d'application

        final String subject = "Réinitialisation du mot de passe";
        String msg = 
                "Bonjour,\n\nVotre nouveau mot de passe est : " 
                + newPassword 
                + "\n\nStudio Art";

        try {
            java.util.Properties props = new java.util.Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");

            javax.mail.Session session = javax.mail.Session.getInstance(
                    props,
                    new javax.mail.Authenticator() {
                        @Override
                        protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                            return new javax.mail.PasswordAuthentication(senderEmail, senderPassword);
                        }
                    }
            );

            javax.mail.Message message = new javax.mail.internet.MimeMessage(session);
            message.setFrom(new javax.mail.internet.InternetAddress(senderEmail));
            message.setRecipients(javax.mail.Message.RecipientType.TO,
                    javax.mail.internet.InternetAddress.parse(emailTo));
            message.setSubject(subject);
            message.setText(msg);

            javax.mail.Transport.send(message);

            JOptionPane.showMessageDialog(null,
                    "Nouveau mot de passe envoyé ET enregistré !",
                    "Succès",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Erreur envoi email : " + e.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    public boolean checkUserExists(String email) {
    try {
        String sql = "SELECT * FROM utilisateur WHERE email = ?";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    } catch(Exception e) {
        System.out.println("Erreur checkUserExists : " + e.getMessage());
        return false;
    }
}

   
}
