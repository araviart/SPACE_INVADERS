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
    private List<String> objetsPossible;
    private String modeObjet;
    public GestionJeu() {
        this.largeur = 100;
        this.hauteur = 60;
        this.vaisseau = new Vaisseau(positionX - 5);
        this.projectiles = new ArrayList<>();
        this.objet = new ArrayList<>();
        this.aliens = new ArrayList<>();
        this.alienTouche = new ArrayList<>();
        this.objetsPossible = Arrays.asList( "Nuke", "Contagion", "Multiple");

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
        this.modeObjet = "Vanilla";
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
        tempsDernierTir = tempsActuel;
        switch(modeObjet){
            case "Nuke":
            this.projectiles.add(new ProjectileNuke((int) positionCanon));
            this.modeObjet = "Vanilla";
            break;
            case "Contagion":
            this.projectiles.add(new Projectile((int) positionCanon));
            this.modeObjet = "Vanilla";
            case "Multiple":
            this.projectiles.add(new ProjectileNuke((int) positionCanon));
            this.modeObjet = "Vanilla";
            default:
            case "Vanilla":
            this.projectiles.add(new Projectile((int) positionCanon));
        }// case switch en fonction de l'objet

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
        ensemble.ajouteChaine(this.largeur - 10, this.hauteur - 3, this.modeObjet);
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
                    if(this.modeObjet == "Contagion"){
                        this.projectiles.add(new ProjectileContagion((int) alien.getPosXAlien(), alien.getPosYAlien()));
                        this.projectiles.add(new ProjectileContagion((int) alien.getPosXAlien(), alien.getPosYAlien()));
                        this.projectiles.add(new ProjectileContagion((int) alien.getPosXAlien(), alien.getPosYAlien()));
                        this.projectiles.add(new ProjectileContagion((int) alien.getPosXAlien(), alien.getPosYAlien()));
                    }
                }
            }
        }

        if (alienATouche) {
            double posYSpawnObjet = alienTouche.get(alienTouche.size() - 1).getPosYAlien();
            double posXSpawnObjet = alienTouche.get(alienTouche.size() - 1).getPosXAlien();
            if (rand.nextDouble() <= 0.3) { // objet tombe avec une probabilité de 30%
                this.objet.add(new Objet((int) posXSpawnObjet, (int) posYSpawnObjet));
            }
        }
        for (Objet objetActu : this.objet) {
            if (vaisseau.contient((int) objetActu.getPosXObj(), (int) objetActu.getPosYObj())) {
                this.modeObjet = objetsPossible.get(rand.nextInt(objetsPossible.size()));

            }
        }

        // System.out.println(alienTouche);
        this.aliens.removeAll(alienTouche);
        this.projectiles.removeAll(projectileQuiOntTouche);
        this.score.ajoute(alienTouche.size());

        for (Aliens alien : this.aliens) {
            alien.evolue();
        }

        // fait évoluer projectile
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

        alienTouche.clear();
        alienATouche = false;
    }
}
