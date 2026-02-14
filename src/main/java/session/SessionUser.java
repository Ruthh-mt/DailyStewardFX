package session;

import model.Utilisateur;

public class SessionUser {
    private static SessionUser instance;
    private Utilisateur utilisateurConnecte;

    private SessionUser() { }

    public static SessionUser getInstance() {
        if (instance == null) {
            instance = new SessionUser();
        }
        return instance;
    }
    public void sauvegardeSession(Utilisateur utilisateur) {
        if (this.utilisateurConnecte == null) {
            this.utilisateurConnecte = utilisateur;
        }
    }

    public Utilisateur getUtilisateur() {
        return utilisateurConnecte;
    }

    public void deconnecter() {
        utilisateurConnecte = null;
    }
}
