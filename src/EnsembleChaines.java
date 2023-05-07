import java.util.ArrayList;
import java.util.List;

public class EnsembleChaines {
    ArrayList<ChainePositionnee> chaines;

    public EnsembleChaines() {
        chaines = new ArrayList<ChainePositionnee>();
    }

    public void ajouteChaine(int x, int y, String c) {
        chaines.add(new ChainePositionnee(x, y, c));
    }

    public void union(EnsembleChaines e) {
        for (ChainePositionnee c : e.chaines)
            chaines.add(c);
    }

    public boolean contient(int x, int y) {
        for (ChainePositionnee chaine : chaines) {
            if (x >= chaine.x && x < chaine.x + chaine.c.length() && chaine.y == y) {
                return true;
            }
        }
        return false;
    }
}