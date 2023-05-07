import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GestionJeu {

    private int hauteur;
    private int largeur;
    private int positionX = 50;
    private Vaisseau vaisseau;
    private List<Projectile> projectiles;
    private List<Aliens> aliens;
    private long tempsDernierTir = 0;
    private long intervalleEntreTirs = 500;
    private Score score;
    private Objet objet;

    public GestionJeu() {
        this.largeur = 100;
        this.hauteur = 60;
        this.vaisseau = new Vaisseau(positionX - 5);
        this.projectiles = new ArrayList<>();
        this.objet = new ArrayList<>();
        this.aliens = new ArrayList<>();
        this.aliens.addAll(Arrays.asList(
                new Aliens(this.largeur - 25, 50),
                new Aliens(this.largeur - 40, 50),
                new Aliens(this.largeur - 55, 50),
                new Aliens(this.largeur - 70, 50),
                new Aliens(this.largeur - 85, 50),
                new Aliens(this.largeur - 25, 40),
                new Aliens(this.largeur - 40, 40),
                new Aliens(this.largeur - 55, 40),
                new Aliens(this.largeur - 70, 40),
                new Aliens(this.largeur - 85, 40)));
        this.score = new Score();
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
        for (Aliens alien : this.aliens) {
            ensemble.union(alien.getEnsembleChaines());
        }
        ensemble.ajouteChaine(5, this.hauteur - 3, String.valueOf(score));
        return ensemble;
    }

    public void jouerUnTour() {
        List<Aliens> alienTouche = new ArrayList<>();
        List<Projectile> projectileQuiOntTouche = new ArrayList<>();
    
        for (Projectile proj : this.projectiles) {
            for (Aliens alien : this.aliens) {
                if (alien.contient((int) proj.positionX, (int) proj.positionY)) {
                    alienTouche.add(alien);
                    projectileQuiOntTouche.add(proj);
                }
            }
        }
        this.aliens.removeAll(alienTouche);
        this.projectiles.removeAll(projectileQuiOntTouche);
        this.score.ajoute(alienTouche.size());
    
        for (Aliens alien : this.aliens) {
            alien.evolue();
        }
    
        for (int i = 0; i < this.projectiles.size(); ++i) {
            Projectile projectile = this.projectiles.get(i);
            projectile.evolue();
            System.out.println(projectile);
            if (projectile.positionY > this.largeur - 50) {
                this.projectiles.remove(i);
                i--;
            }
        }
        }

}
