
import java.util.ArrayList;


public class EnsembleChaines {
ArrayList<ChainePositionnee> chaines;

/**
 * initialise un ensemble de chaines vide
 */
public EnsembleChaines() {
    chaines = new ArrayList<ChainePositionnee>();
}

/**
 * ajoute une nouvelle chaine de caractères à l'ensemble de chaine de caractères
 *
 * @param x la position horizontale de la chaine
 * @param y la position verticale de la chaine
 * @param c la chaîne de caractères à ajouter
 */
public void ajouteChaine(int x, int y, String c) {
    chaines.add(new ChainePositionnee(x, y, c));
}

/**
 * ajoute une nouvelle chaîne de caractères à l'ensemble avec une couleur donnée
 * @param x la position horizontale de la chaine
 * @param y la position verticale de la chaine
 * @param c la chaîne de caractères à ajouter
 * @param couleur la couleur de la chaîne de caractères
 */
public void ajouteChaine(int x, int y, String c, String couleur) {
    chaines.add(new ChainePositionnee(x, y, c, couleur));
}

/**
 * ajoute toutes les chaines de caractères d'un autre ensemble à celui-ci
 * @param e l'ensemble à ajouter
 */
public void union(EnsembleChaines e) {
    for (ChainePositionnee c : e.chaines)
        chaines.add(c);
}

/**
 * vérifie si un point est contenu dans une des chaînes de caractères de l'ensemble
 *
 * @param x la position horizontale du point
 * @param y la position verticale du point
 * @return true si le point est contenu dans une des chaînes de caractères, false sinon
 */
public boolean contient(int x, int y) {
    for (ChainePositionnee chaine : chaines) {
        if (x >= chaine.x && x < chaine.x + chaine.c.length() && chaine.y == y) {
            return true;
        }
    }
    return false;
}
}