import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
    List<Aliens> alienTouche;
    private List<Objet> objet;
    private List<Objet> objetsPossible;

    public GestionJeu() {
        this.largeur = 100;
        this.hauteur = 60;
        this.vaisseau = new Vaisseau(positionX - 5);
        this.projectiles = new ArrayList<>();
        this.objet = new ArrayList<>();
        this.aliens = new ArrayList<>();
        this.alienTouche = new ArrayList<>();
        // this.objetsPossible = n
        // Objet objetAleatoire = objets.get(random.nextInt(objets.size())); // l'objet aléatoire obtenu
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
        for (Objet objet : this.objet) {
            ensemble.union(objet.getEnsembleChaines());
        }
        return ensemble;
        
    }
    
    public void jouerUnTour() {
        List<Projectile> projectileQuiOntTouche = new ArrayList<>();
        Random rand = new Random();
        boolean alienATouche = false;
        for (Projectile proj : this.projectiles) {
            for (Aliens alien : this.aliens) {
                if (alien.contient((int) proj.positionX, (int) proj.positionY)) {
                    alienTouche.add(alien);
                    alienATouche = true;
                    projectileQuiOntTouche.add(proj);
                }
            }
        }
        
        for (Objet objetActu : this.objet) {
            if (vaisseau.contient((int) objetActu.getPosXObj(), (int) objetActu.getPosYObj())) {
                // objetActu.typeObjet();
                System.out.println("Objet reçu");
            }
        }
        
        // System.out.println(alienTouche);
        this.aliens.removeAll(alienTouche);
        this.projectiles.removeAll(projectileQuiOntTouche);
        this.score.ajoute(alienTouche.size());

        for (Aliens alien : this.aliens) {
            alien.evolue();
        }

        for (int i = 0; i < this.projectiles.size(); ++i) {
            Projectile projectile = this.projectiles.get(i);
            projectile.evolue();
            // System.out.println(projectile);
            if (projectile.positionY > this.largeur - 50) {
                this.projectiles.remove(i);
                i--;
            }
        }

        for (int j = 0; j < this.objet.size(); ++j) {
            Objet objetActu = this.objet.get(j);
            objetActu.evolue();
            // System.out.println(objetActu);
            if (objetActu.getPosYObj() <= 1) {
                this.objet.remove(j);
                j--;
            }
        }
        if (alienATouche) {
            double posYSpawnObjet = alienTouche.get(alienTouche.size() - 1).getPosYAlien();
            double posXSpawnObjet = alienTouche.get(alienTouche.size() - 1).getPosXAlien();
            if (rand.nextDouble() <= 0.2) { // objet tombe avec une probabilité de 30%
                System.out.println("l'objet doit pop");
                this.objet.add(new Objet((int) posXSpawnObjet, (int) posYSpawnObjet));
            }
        }
    
        alienTouche.clear();
        alienATouche = false;
    }
}
