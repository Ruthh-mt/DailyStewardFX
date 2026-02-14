package appli.accueil;

import appli.StartApplication;
import appli.session.SessionUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Utilisateur;
import repository.UtilisateurRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField mdpField;

    @FXML
    void onConnexion(ActionEvent event) throws IOException {
        if(emailField.getText().isEmpty() || mdpField.getText().isEmpty()){
            showAlert(Alert.AlertType.WARNING, "Veuillez saisir votre email et votre mot de passe.");

        }else{
            UtilisateurRepository userRepo = new UtilisateurRepository();
            Utilisateur possibleUser=userRepo.getUserByMail(new Utilisateur(emailField.getText(),mdpField.getText()));
            System.out.println(possibleUser);
            if(possibleUser!=null){
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                if(encoder.matches(mdpField.getText(), possibleUser.getMdp())){
                    System.out.println("Connexion réussie pour : " + possibleUser.getNom());
                    SessionUser.getInstance().sauvegardeSession(possibleUser);
                    StartApplication.changeScene("accueil/homePage","Accueil");
                }else{
                    showAlert(Alert.AlertType.WARNING, "Mot de passe ou Email  incorrect");
                }
            }else{
                showAlert(Alert.AlertType.ERROR, "Erreur lors de la connexion");
            }

        }
    }

    @FXML
    void onForgottenMdp(ActionEvent event) throws IOException {
        StartApplication.changeScene("motDePasse/saisieEmail","Mot de passe Oublie");
    }

    @FXML
    void onInscription(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/register","Inscription");
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Connexion");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
