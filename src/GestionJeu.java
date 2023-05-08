import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Constructeur de la classe GestionJeu.
 * 
 * @param hauteur  la hauteur de l'écran de jeu
 * @param largeur la largeur de l'écran de jeu
 * @param vaisseau  le vaisseau contrôlé par le joueur
 * @param aliens la liste des ennemis présents sur l'écran de jeu
 * @param score  le score actuel du joueur
 * @param objetsPossible la liste des types d'objets qui peuvent apparaître sur l'écran de jeu
 * @param modeObjet l'effet actuelle de l'objet acquis dans le tour précedent
 * @param alienTouche liste  servant à supprimez à chaque tour les aliens touchées par un projectile
 * @param tempsDernierTir utilisation de la classe System pour permettre de faire la différence entre le tir actuelle et l'ancien
 * @param intervalleEntreTirs valeur exigée minimum entre les tirs
 */

public class GestionJeu {
    private int hauteur;
    private int largeur;
    private int positionX = largeur;
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

    /**
     * Creez un jeu avec une hauteur et une largeur définie pour la zone du jeu,
     * initialise une liste vide projectiles, d'objets, et d'aliens, initialise une
     * liste de String
     * d'objets possible pour ensuite permettre de prendre un des types d'objets
     * aléatoirement, initialise le score et créez les aliens pour le niveau 1.
     */
    public GestionJeu() {
        this.largeur = 100;
        this.hauteur = 60;
        this.vaisseau = new Vaisseau(largeur/2-5);
        this.projectiles = new ArrayList<>();
        this.objet = new ArrayList<>();
        this.aliens = new ArrayList<>();
        this.alienTouche = new ArrayList<>();
        this.objetsPossible = Arrays.asList("Nuke", "Multiple"); // à maintenir si l'on souhaite rajouter des objets en
                                                                 // plus
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

    /**
     * Retourne la hauteur de la zone de jeu
     * 
     * @return int la hauteur de la zone de jeu
     */
    public int getHauteur() {
        return this.hauteur;
    }

    /**
     * Retourne la largeur de la zone de jeu
     * 
     * @return int la largeur de la zone de jeu
     */
    public int getLargeur() {
        return this.largeur;
    }

    /**
     * Vérifie si le vaisseau peut se déplacer vers la gauche (si la longueur de la
     * chaine du vaisseau + sa position en x est supérieur à sa longueur en étant à
     * posX de 0)
     * et le fait se déplacer vers la gauche si la condition est vérifié.
     * la vitesse du vaisseau augmente au fur et à mesure des niveaux passer (on
     * pourrait répartir autrement dans le cas ou il y'a beaucoup de niveaux).
     */
    public void toucheGauche() {
        double vitesse_deplacement = niveau;
        if (niveau > 3) {
            vitesse_deplacement = 3;
        }
        if (this.vaisseau.getPosXvaisseau()
                + this.vaisseau.getEnsembleChaines().chaines.get(0).c.length() > 0
                        + this.vaisseau.getEnsembleChaines().chaines.get(0).c.length()) {
            this.vaisseau.deplace(-vitesse_deplacement);
        }
    }

    /**
     * Vérifie si le vaisseau peut se déplacer vers la droite (si la nouvelle
     * position en X comprenenant la place que fait le vaisseau reste infèrieur à la
     * bordure droite de la zone)
     * et le fait se déplacer vers la droite si la condition est vérifié
     * la vitesse du vaisseau augmente au fur et à mesure des niveaux passer (on
     * pourrait répartir autrement dans le cas ou il y'a beaucoup de niveaux).
     */
    public void toucheDroite() {
        double vitesse_deplacement = niveau;
        if (niveau > 3) {
            vitesse_deplacement = 3;
        }
        if (this.vaisseau.getPosXvaisseau()
                + this.vaisseau.getEnsembleChaines().chaines.get(0).c.length() <= this.largeur) {
            this.vaisseau.deplace(vitesse_deplacement);
        }
    }

    /**
     * chaque tir se doit d'être espacé l'un de l'autre avec un délai de 0.5s, ces
     * tirs peuvent être différents en fonction d'un objet acquis par le vaisseau
     * entèrieurement,
     * traiter par un switch case, on veut qu'un tir spéciale après l'acquisition
     * d'un objet donc modeObjet est réinitialisé à défaut "Vanilla"
     */
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

    /**
     * Créez un ensemble de chaines constituant les élements du space Invaders
     * 
     * @return
     */
    public EnsembleChaines getChaines() {
        EnsembleChaines ensemble = new EnsembleChaines();
        if (this.niveau == 3) { // a maintenir en fonction du niveau max implémentée
            String messageNivTrois = "FIN DU JEU (oui)";
            ensemble.ajouteChaine(this.largeur / 2 - (messageNivTrois.length() / 2), this.hauteur / 2, messageNivTrois);
            return ensemble;
        }
        ensemble.union(this.vaisseau.getEnsembleChaines());
        for (Projectile projectile : this.projectiles) {
            ensemble.union(projectile.getEnsembleChaines());
        }
        for (Aliens alien : this.aliens) {
            ensemble.union(alien.getEnsembleChaines());
        }
        ensemble.ajouteChaine(5, this.hauteur - 3, String.valueOf(score));
        ensemble.ajouteChaine(this.largeur - 10, this.hauteur - 3, this.modeObjet); // affiche le statut du prochain tir
        ensemble.ajouteChaine(this.largeur - 20, this.hauteur - 3, "PV :" + vaisseau.vie); // affiche les pv restants
                                                                                           // avant de perdre la partie
        ensemble.ajouteChaine(this.largeur - 30, this.hauteur - 3, "NIVEAU :" + this.niveau); // affiche le niveau dans
                                                                                              // lequel on se situe
        for (Objet objet : this.objet) {
            ensemble.union(objet.getEnsembleChaines());
        }
        if (vaisseau.vie == 0) { // dans le cas ou l'on n'a plus de vie
            String messageDefaite = "VOUS AVEZ PERDUE";
            ensemble.ajouteChaine(this.largeur / 2 - (messageDefaite.length() / 2), this.hauteur / 2, messageDefaite);
        }
        return ensemble;

    }

    /**
     * Controle le jeu à chaque tour, sa continuité, le comportement des élements
     * (projectiles, alien, vaisseau, et les collision)
     */
    public void jouerUnTour() {
        if (vaisseau.vie == 0) { // dans le cas ou le vaisseau na plus de vie, on clear les listes pour ne plus
                                 // afficher les élements du jeu
            projectiles.clear();
            aliens.clear();
            return;
        }
        List<Projectile> projectileQuiOntTouche = new ArrayList<>();
        Random rand = new Random();
        boolean alienATouche = false;

        // si un projectile rentre en collision avec un alien, l'alien touché est ajouté
        // à une liste et la variable alienAtouche est affecté à true pour signaler au
        // moins un alien toucher durant le tour
        for (Projectile proj : this.projectiles) {
            for (Aliens alien : this.aliens) {
                if (!(proj.estAlien()) && alien.contient((int) proj.positionX, (int) proj.positionY)) { // on s'assure
                                                                                                        // que le
                                                                                                        // projectile
                                                                                                        // n'est pas
                                                                                                        // issue d'un
                                                                                                        // alien (au
                                                                                                        // quel cas
                                                                                                        // l'alien meurt
                                                                                                        // en tirant)
                    alienTouche.add(alien);
                    alienATouche = true;
                    projectileQuiOntTouche.add(proj);
                }
            }
        }

        // si un alien à était touché durant le tour actuelle, il y'a une possibilité de
        // 30% qu'un objet soit drop
        if (alienATouche) {
            double posYSpawnObjet = alienTouche.get(alienTouche.size() - 1).getPosYAlien(); // on s'assure que l'objet
                                                                                            // drop à la position du
                                                                                            // dernier alien touché
            double posXSpawnObjet = alienTouche.get(alienTouche.size() - 1).getPosXAlien();
            if (rand.nextDouble() <= 0.3) { // objet tombe avec une probabilité de 30%
                this.objet.add(new Objet((int) posXSpawnObjet, (int) posYSpawnObjet));
            }
        }

        // gère les collisions entre les objets et le vaisseau, et dans le cas d'une
        // collision attribut un statut aléatoire au vaisseau (l'aléatoire n'est pas
        // calculée au moment ou l'instance d'un objet apparaît)
        for (Objet objetActu : this.objet) {
            if (vaisseau.contient((int) objetActu.getPosXObj(), (int) objetActu.getPosYObj())) {
                this.modeObjet = objetsPossible.get(rand.nextInt(objetsPossible.size()));

            }
        }

        // gère les collisions entre les projectiles tirées par des alien qui touche le
        // vaisseau, décréemente de 1 la vie dans ce cas
        for (Projectile projectile : this.projectiles) {
            if (projectile.estAlien()) {
                if (vaisseau.contient((int) projectile.getPosProjectileX(), (int) projectile.getPosProjectileY())) {
                    projectileQuiOntTouche.add(projectile);
                    vaisseau.vie -= 1;
                }
            }
        }

        // actualise continuellement les projectiles et liste d'aliens légitime à rester
        // sur le jeu (évite des calculs pour des projectiles qui ont touchées des
        // aliens, évite conserver en mémoire des aliens touchées)
        this.aliens.removeAll(alienTouche);
        this.projectiles.removeAll(projectileQuiOntTouche);
        this.score.ajoute(alienTouche.size());

        // ajoute la fonctionnalité de tir aux aliens, avec une probabilité croissante
        // en fonction du niveau et une part d'aléatoire
        for (Aliens alien : this.aliens) {
            alien.evolue();
            if (rand.nextDouble() <= niveau * 0.003) {
                double max = this.niveau;
                this.projectiles.add(new Projectile((int) alien.positionCanon(), (int) alien.getPosYAlien(),
                        -(0.2 + (max - 0.2) * rand.nextDouble())));
            }
        }

        // fait avancer les projectiles déjà tirées et supprime les projectiles hors de
        // la zone de jeu
        for (int i = 0; i < this.projectiles.size(); ++i) {
            Projectile projectile = this.projectiles.get(i);
            if (projectile.positionY > this.largeur - 30) {
                this.projectiles.remove(i);
                i--;
            }
            projectile.evolue();
        }

        // fait avancer (tomber) les objets et supprime ceux hors de la zone de jeu
        for (int j = 0; j < this.objet.size(); ++j) {
            Objet objetActu = this.objet.get(j);
            objetActu.evolue();
            // System.out.println(objetActu);
            if (objetActu.getPosYObj() <= 1) {
                this.objet.remove(j);
                j--;
            }
        }

        // si la liste d'alien durant la manche (niveau) actuelle est de 0 , passe au
        // niveau prochain niveau (possibilité de regarder en amont si un niveau+1
        // existe)
        if (this.aliens.isEmpty()) {
            this.niveau += 1;
            niveauSuivant();
        }

        alienTouche.clear();
        alienATouche = false;
    }

    /**
     * Ajoute une mécanique de niveau au jeu, permet setup l'environnement d'un
     * niveau en réaction à la valeur de l'attribut niveau
     */
    public void niveauSuivant() {
        switch (niveau) {
            case 2:
                this.aliens.addAll(Arrays.asList(
                        new AlienEvoUn(this.largeur - 25, 50),
                        new AlienEvoUn(this.largeur - 55, 60),
                        new AlienEvoUn(this.largeur - 20, 90),
                        new AlienEvoUn(this.largeur - 20, 100),
                        new AlienEvoUn(this.largeur - 40, 100),
                        new AlienEvoUn(this.largeur - 60, 120), // dépasse de la hauteur mais permet de faire des
                                                                // manches (les aliens arrivent après tout le monde)
                        new AlienEvoUn(this.largeur - 60, 120), // on pourrait aussi bien combiner un compteur et
                                                                // s'assurer que tout les aliens sont mort pour faire
                        new AlienEvoUn(this.largeur - 60, 120))); // pop des nouveaux aliens au sein d'un même niveau
                                                                  // (implique de changer la condition qui incrémente le
                                                                  // niveau dans GestionJeui)
            case 3:
                this.getChaines();

        }
    }
}
