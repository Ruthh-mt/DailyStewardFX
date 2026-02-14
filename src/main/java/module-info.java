module appli.accueil {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires spring.security.crypto;
    requires jakarta.mail;


    opens appli.accueil to javafx.fxml;
    exports appli.accueil;
    exports appli;
    opens appli to javafx.fxml;
    opens appli.motDePasse to javafx.fxml;
    exports appli.motDePasse;

    /*
    opens appli.list to javafx.fxml;
    exports appli.list to javafx.fxml;
    opens appli.admin to javafx.fxml;
    exports appli.admin to javafx.fxml;
    opens appli.type to javafx.fxml;
    exports appli.type to javafx.fxml;
    opens appli.tache to javafx.fxml;
    exports appli.tache to javafx.fxml;
    opens appli.user to javafx.fxml;
    exports appli.user to javafx.fxml;
    */
}
