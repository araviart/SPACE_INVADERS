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
    private int niveau;

    public GestionJeu() {
        this.largeur = 100;
        this.hauteur = 60;
        this.vaisseau = new Vaisseau(positionX - 5);
        this.projectiles = new ArrayList<>();
        this.objet = new ArrayList<>();
        this.aliens = new ArrayList<>();
        this.alienTouche = new ArrayList<>();
        this.objetsPossible = Arrays.asList("Nuke", "Multiple");
        this.niveau = 1;
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
        double vitesse_deplacement = niveau;
        if (niveau > 3){
        vitesse_deplacement = 3;
        }
        if (this.vaisseau.getPosXvaisseau()
                + this.vaisseau.getEnsembleChaines().chaines.get(0).c.length() > 0 + this.vaisseau.getEnsembleChaines().chaines.get(0).c.length()) {
            this.vaisseau.deplace(-vitesse_deplacement);
        }
    }

    public void toucheDroite() {
        double vitesse_deplacement = niveau;
        if (niveau > 3){
        vitesse_deplacement = 3;
        }
        if (this.vaisseau.getPosXvaisseau()
                + this.vaisseau.getEnsembleChaines().chaines.get(0).c.length() <= this.largeur) {
            this.vaisseau.deplace(vitesse_deplacement);
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
        switch (modeObjet) {
            case "Nuke":
                this.projectiles.add(new ProjectileNuke((int) positionCanon - 10));
                this.modeObjet = "Vanilla";
                break;
            case "Multiple":
                this.projectiles.add(new Projectile((int) positionCanon - 5));
                this.projectiles.add(new Projectile((int) positionCanon + 5)); // création de deux projectiles
                this.projectiles.add(new Projectile((int) positionCanon)); // projectile central
                this.modeObjet = "Vanilla";
            default:
            case "Vanilla":
                this.projectiles.add(new Projectile((int) positionCanon));
        }

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
        ensemble.ajouteChaine(this.largeur - 20, this.hauteur - 3, "PV :" + vaisseau.vie);
        ensemble.ajouteChaine(this.largeur - 30, this.hauteur - 3, "NIVEAU :" + this.niveau);
        for (Objet objet : this.objet) {
            ensemble.union(objet.getEnsembleChaines());
        }
        if(vaisseau.vie == 0){
            String messageDefaite = "VOUS AVEZ PERDUE";
            ensemble.ajouteChaine(this.largeur/2-(messageDefaite.length()/2), this.hauteur/2, messageDefaite);
        }
        if(this.niveau== 3){
            String messageNivTrois= "FIN DU JEU (oui)";
            ensemble.ajouteChaine(this.largeur/2-(messageNivTrois.length()/2), this.hauteur/2, messageNivTrois);
        }
        return ensemble;

    }

    public void jouerUnTour() {
        if(vaisseau.vie == 0){
            projectiles.clear();
            aliens.clear();
            return; 
        }
        List<Projectile> projectileQuiOntTouche = new ArrayList<>();
        Random rand = new Random();
        boolean alienATouche = false;
        for (Projectile proj : this.projectiles) {
            for (Aliens alien : this.aliens) {
                if (!(proj.estAlien()) && alien.contient((int) proj.positionX, (int) proj.positionY)) {
                    alienTouche.add(alien);
                    alienATouche = true;
                    projectileQuiOntTouche.add(proj);
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

        for (Projectile projectile : this.projectiles) {
            if (projectile.estAlien()) {
                if (vaisseau.contient((int) projectile.getPosProjectileX(), (int) projectile.getPosProjectileY())) {
                    projectileQuiOntTouche.add(projectile);
                    vaisseau.vie -= 1;
                }
            }
        }

        // System.out.println(alienTouche);
        this.aliens.removeAll(alienTouche);
        this.projectiles.removeAll(projectileQuiOntTouche);
        this.score.ajoute(alienTouche.size());

        for (Aliens alien : this.aliens) {
            alien.evolue();
            if (rand.nextDouble() <= niveau*0.003) {
                double max = this.niveau;
                this.projectiles.add(new Projectile((int) alien.positionCanon(), (int) alien.getPosYAlien(), -(0.2 + (max - 0.2) * rand.nextDouble())));
            }
        }

        for (int i = 0; i < this.projectiles.size(); ++i) {
            Projectile projectile = this.projectiles.get(i);
            if (projectile.estAlien()) {
                projectile.evolue();
            } else {
                projectile.evolue();
            }
            // System.out.println(projectile);
            if (projectile.positionY > this.largeur - 30) {
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

        if(this.aliens.isEmpty()){
            this.niveau +=1;
            niveauSuivant();
        }

        alienTouche.clear();
        alienATouche = false;
    }

    public void niveauSuivant(){
        switch(niveau){
            case 2:
            this.aliens.addAll(Arrays.asList(
                new AlienEvoUn(this.largeur - 25, 50),
                new AlienEvoUn(this.largeur - 55, 60),
                new AlienEvoUn(this.largeur - 20, 90),
                new AlienEvoUn(this.largeur - 20, 100),
                new AlienEvoUn(this.largeur - 40, 100),
                new AlienEvoUn(this.largeur - 60, 120),
                new AlienEvoUn(this.largeur - 60, 130),
                new AlienEvoUn(this.largeur - 60, 150)));
            case 3:
            

        }
    }
}
