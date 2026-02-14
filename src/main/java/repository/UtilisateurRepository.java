package repository;

import database.Database;
import model.Utilisateur;

import java.sql.*;


public class UtilisateurRepository {
    private Connection connection;

    public UtilisateurRepository() {
        this.connection= Database.getConnexion();
    }

    public boolean createUser(Utilisateur utilisateur) {
        String add="INSERT INTO utilisateur (nom,prenom,email,role,mot_de_passe) VALUES (?,?,?,?,?)";
        try{
            PreparedStatement stmt=this.connection.prepareStatement(add);
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPrenom());
            stmt.setString(3, utilisateur.getEmail());
            stmt.setString(4, utilisateur.getRole());
            stmt.setString(5,utilisateur.getMdp());
            stmt.executeUpdate();
            return true;
        }catch (SQLException e){
            return false;
        }
    }

    public Utilisateur getUserByMail(Utilisateur user) {
        String get="SELECT * FROM utilisateur WHERE email=? ";
        try{
            PreparedStatement stmt=this.connection.prepareStatement(get);
            stmt.setString(1, user.getEmail());
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
                user=new Utilisateur(
                       rs.getInt("id_user"),
                       rs.getString("nom"),
                       rs.getString("prenom"),
                       rs.getString("email"),
                       rs.getString("mot_de_passe"),
                        rs.getString("role")
               );
            }
            return user;
        }catch(SQLException e){
            System.out.println("Erreur de connexion : "+'\n'+" >>"+e.getMessage());
            return null;
        }
    }

}
