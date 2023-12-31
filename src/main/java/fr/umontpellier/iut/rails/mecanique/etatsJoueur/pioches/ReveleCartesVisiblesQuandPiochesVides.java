package fr.umontpellier.iut.rails.mecanique.etatsJoueur.pioches;

import fr.umontpellier.iut.rails.mecanique.etatsJoueur.EtatJoueur;
import fr.umontpellier.iut.rails.mecanique.Joueur;

public class ReveleCartesVisiblesQuandPiochesVides extends EtatJoueur {

    private int nbCartesAReveler;

    public ReveleCartesVisiblesQuandPiochesVides(Joueur joueurCourant, int nbCartesAReveler) {
        super(joueurCourant);
        this.nbCartesAReveler = nbCartesAReveler;
    }

    public boolean piocherWagon() {
        boolean aPioche;
        if (!getJeu().piocheWagonEstVide()) {
            joueurCourant.revelerCarteTransport("WAGON");
            nbCartesAReveler--;
            String pluriel = nbCartesAReveler > 1 ? "s" :"";
            getJeu().instructionProperty().setValue("Vous devez d'abord réveler " + nbCartesAReveler + " carte" + pluriel);
            if (nbCartesAReveler == 0)
                joueurCourant.jouerTour();
            aPioche = true;
        } else {
            if (!getJeu().piocheBateauEstVide())
                getJeu().instructionProperty().setValue("Cette pioche est vide - Choisissez l'autre pioche");
            aPioche = false;
        }
        return aPioche;
    }

    public boolean piocherBateau() {
        if (!getJeu().piocheBateauEstVide()) {
            joueurCourant.revelerCarteTransport("BATEAU");
            nbCartesAReveler--;
            if (nbCartesAReveler == 0)
                joueurCourant.jouerTour();
            return true;
        } else {
            if (!getJeu().piocheWagonEstVide())
                getJeu().instructionProperty().setValue("Cette pioche est vide - Choisissez l'autre pioche");
            return false;
        }
    }
}
