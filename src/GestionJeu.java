import java.util.ArrayList;
import java.util.List;

public class GestionJeu {

    private int hauteur;
    private int largeur;
    private int positionX = 50;
    private Vaisseau vaisseau;
    private List<Projectile> projectiles;
    private long tempsDernierTir = 0;
    private long intervalleEntreTirs = 500;
    private int score;

    public GestionJeu() {
        this.largeur = 100;
        this.hauteur = 60;
        this.vaisseau = new Vaisseau(positionX);
        this.projectiles = new ArrayList<>();
    }

    public GestionJeu(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.vaisseau = new Vaisseau(positionX);
        this.projectiles = new ArrayList<>();
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
        if (this.vaisseau.getPosXvaisseau()
                + this.vaisseau.getEnsembleChaines().chaines.get(0).c.length() < this.largeur) {
            this.vaisseau.deplace(1);
        }
    }

    public void toucheEspace() {
        long tempsActuel = System.currentTimeMillis();
        if (tempsActuel - tempsDernierTir < intervalleEntreTirs) {
            System.out.println("Attendez un peu avant de tirer à nouveau !");
            return;
        }
        double positionCanon = this.vaisseau.positionCanon();
        this.projectiles.add(new Projectile((int) positionCanon));
        tempsDernierTir = tempsActuel;

    }

    public EnsembleChaines getChaines() {
        EnsembleChaines ensemble = new EnsembleChaines();
        ensemble.union(this.vaisseau.getEnsembleChaines());
        for (Projectile projectile : this.projectiles) {
            ensemble.union(projectile.getEnsembleChaines());
        }
        ensemble.ajouteChaine(5, this.hauteur-3, String.valueOf(this.score));
        return ensemble;
    }

    public void jouerUnTour() {
        for (int i = 0; i < this.projectiles.size(); ++i) {
            projectiles.get(i).evolue();
            System.out.println(projectiles);
            if (projectiles.get(i).getPosProjectileY() > this.largeur - 50) {
                projectiles.remove(i);
                i--;
            }
        }
    }
}