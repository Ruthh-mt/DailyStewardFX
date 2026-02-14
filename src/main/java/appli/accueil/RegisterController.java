package appli.accueil;

import appli.StartApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Utilisateur;
import repository.UtilisateurRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;

public class RegisterController {

    @FXML
    private PasswordField confirmationMdpField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField mdpField;

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    void onConnexion(ActionEvent event) throws IOException {
        StartApplication.changeScene("accueil/login","Connexion");
    }

    @FXML
    void onInscription(ActionEvent event) throws IOException {
        if(emailField.getText().isEmpty() || mdpField.getText().isEmpty() || nomField.getText().isEmpty()
        || prenomField.getText().isEmpty() || confirmationMdpField.getText().isEmpty()){
            showAlert(Alert.AlertType.WARNING,"Veuillez remplir tout les champs");
        }else{
            if(mdpField.getText().equals(confirmationMdpField.getText())){
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                String mdpHashed=encoder.encode(mdpField.getText());
                UtilisateurRepository userRepo=new UtilisateurRepository();
                Utilisateur utilisateur = new Utilisateur(nomField.getText(),prenomField.getText(),emailField.getText(),mdpHashed,"user");
                if(userRepo.createUser(utilisateur)){
                    showAlert(Alert.AlertType.INFORMATION,"Inscription Reussi. Veuillez vous connecter.");
                    StartApplication.changeScene("accueil/login","Connexion");
                }else{
                    showAlert(Alert.AlertType.ERROR, "Erreur lors de l'inscription");
                }
            } else{
              showAlert(Alert.AlertType.WARNING,"Le mot de passe et la confirmation du mot de passe sont different");
            }
        }
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Inscription");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
