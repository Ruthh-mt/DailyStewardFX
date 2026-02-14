package repository;

import database.Database;
import model.Utilisateur;

import java.sql.*;
import java.util.ArrayList;


public class UtilisateurRepository {
    private Connection connection;

    public UtilisateurRepository() {
        this.connection = Database.getConnexion();
    }

    public boolean createUser(Utilisateur utilisateur) {
        String add = "INSERT INTO utilisateur (nom,prenom,email,role,mot_de_passe) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(add);
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPrenom());
            stmt.setString(3, utilisateur.getEmail());
            stmt.setString(4, utilisateur.getRole());
            stmt.setString(5, utilisateur.getMdp());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public Utilisateur getUserByMail(Utilisateur user) {
        String get = "SELECT * FROM utilisateur WHERE email=? ";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(get);
            stmt.setString(1, user.getEmail());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new Utilisateur(
                        rs.getInt("id_user"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"),
                        rs.getString("role")
                );
            }
            return user;
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + '\n' + " >>" + e.getMessage());
            return null;
        }
    }

    public boolean supprimerUtilisateurParEmail(String email) {
        String delete = "DELETE FROM utilisateur WHERE email=? ";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(delete);
            stmt.setString(1, email);
            stmt.executeUpdate();
            System.out.println("User deleted with sucess : " + '\n' + ">> mail was : " + email);
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppresion : " + '\n' + " >>" + e.getMessage());
            return false;
        }
    }

    public boolean mettreAJourUtilisateur(Utilisateur utilisateur) {
        String update = "UPDATE utilisateur SET nom=?,prenom=?,email=?,role=? WHERE id_user=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(update);
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPrenom());
            stmt.setString(3, utilisateur.getEmail());
            stmt.setString(4, utilisateur.getRole());
            stmt.setInt(5, utilisateur.getIdUser());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise a jour de l'utilisateur : " + '\n' + " >>" + e.getMessage());
            return false;
        }
    }

    public ArrayList<Utilisateur> getTousLesUtilisateurs() {
        Utilisateur user = null;
        String getAll = "SELECT* FROM utilisateur";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(getAll);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
            while (rs.next()) {
                user = new Utilisateur(
                        rs.getInt("id_user"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"),
                        rs.getString("role")
                );
                utilisateurs.add(user);
            }
            return utilisateurs;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recuperation des utilisateurs : " + '\n' + " >>" + e.getMessage());
            return null;
        }
    }

    public boolean createCode(String code, String email) {
        String addCode = "UPDATE utilisateur SET code=? WHERE email=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(addCode);
            stmt.setString(1, code);
            stmt.setString(2, email);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du code  : " + '\n' + " >>" + e.getMessage());
            return false;
        }
    }

    public String getCode(String email) {
        String getCode = "SELECT code FROM utilisateur WHERE email=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(getCode);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("code");
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recuperation du code  : " + '\n' + " >>" + e.getMessage());
            return null;
        }

    }

    public boolean deleteCode(String email) {
        String deleteCode = "UPDATE utilisateur SET code=? WHERE email=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(deleteCode);
            stmt.setString(1, null);
            stmt.setString(2, email);
            stmt.executeUpdate();
            System.out.println("Code deleted with sucess : " + '\n' + ">> mail was : " + email);
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppresion : " + '\n' + " >>" + e.getMessage());
            return false;
        }
    }
    public boolean mettreAJourMdp(Utilisateur utilisateur) {
        String update = "UPDATE utilisateur SET mot_de_passe=? WHERE email=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(update);
            stmt.setString(1, utilisateur.getMdp());
            stmt.setString(2, utilisateur.getEmail());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise a jour du mdp: " + '\n' + " >>" + e.getMessage());
            return false;
        }
    }

}
