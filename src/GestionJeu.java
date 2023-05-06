import java.util.List;

public class GestionJeu{
    
    private int hauteur;
    private int largeur;
    private EnsembleChaines ensemble;
    private int positionX = 0;
    private Vaisseau vaisseau;

    
    public GestionJeu(){
        this.largeur = 100;
        this.hauteur = 60;
        positionX = 0;
        this.ensemble.ajouteChaine(positionX, 30, "@@");
        this.vaisseau = new Vaisseau((double) positionX);
    }
    
    public GestionJeu(int largeur, int hauteur){
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.ensemble.ajouteChaine(positionX, 30, "@@");
        positionX = 0;
        this.vaisseau = new Vaisseau((double) positionX);
    }
    
    public int getHauteur() {
        return this.hauteur;
    }

    public int getLargeur() {
        return this.largeur;
    }

    public void toucheGauche(){
        System.out.println("Appui sur la touche espace");
    }

    public void toucheDroite(){
        this.positionX++;
        this.vaisseau.deplace(1.);
    }

    public void toucheEspace(){
        // key.getCode()==KeyCode.SPACE;
    }


    public EnsembleChaines getChaines(){
        return this.ensemble;
    }

    public void jouerUnTour(){

    }



    
    
}