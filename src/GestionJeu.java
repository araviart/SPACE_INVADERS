import java.util.List;
public class GestionJeu {

    private int hauteur;
    private int largeur;
    private int positionX = 50;
    private Vaisseau vaisseau;

    public GestionJeu() {
        this.largeur = 100;
        this.hauteur = 60;
        this.vaisseau = new Vaisseau(positionX);
    }

    public GestionJeu(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.vaisseau = new Vaisseau(positionX);

    }

    public int getHauteur() {
        return this.hauteur;
    }

    public int getLargeur() {
        return this.largeur;
    }

    public void toucheGauche() {
        this.vaisseau.deplace(-1);
    }

    public void toucheDroite() {
        if (this.vaisseau.getPosXvaisseau()+ this.vaisseau.getEnsembleChaines().chaines.get(0).c.length() < this.largeur) {
            this.vaisseau.deplace(1);
        }
    }

    public void toucheEspace() {
        // key.getCode()==KeyCode.SPACE
    }

    public EnsembleChaines getChaines() {
        EnsembleChaines ensemble = new EnsembleChaines();
        ensemble.union(this.vaisseau.getEnsembleChaines());
        return ensemble;

    }

    public void jouerUnTour() {
    }

}