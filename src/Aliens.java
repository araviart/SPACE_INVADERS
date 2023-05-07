import java.util.List;

public class Aliens {
  private double posX;
  private double posY;
  private int tour;
  private boolean etat;

  public Aliens(double posX, double posY) {
    this.posX = posX;
    this.posY = posY;
    this.etat = false;
}

  public void deplaceAlien(double dx){
      this.posX+=dx;
  }

  public EnsembleChaines getEnsembleChaines(){
    EnsembleChaines vaisseau = new EnsembleChaines();
    vaisseau.ajouteChaine((int)posX, (int)posY+4, "  ▀▄   ▄▀  ");
    vaisseau.ajouteChaine((int)posX, (int)posY+3, " ▄█▀███▀█▄ ");
    vaisseau.ajouteChaine((int)posX, (int)posY+2, "█▀███████ █");
    vaisseau.ajouteChaine((int)posX, (int)posY+1, "█ █▀▀▀▀▀█ █");
    vaisseau.ajouteChaine((int)posX, (int)posY,   "   ▀▀ ▀▀   ");
    return vaisseau;
  }

  public int getNbTour(){
    return this.tour;
  }


  public double getPosXAlien(){
    return this.posX;
  }


  public void evolue() {
    if (!etat && tour < 100) {
        posX += 0.1;
        tour++;
    } else if (!etat && tour >= 100) {
        posY -= 1;
        tour = 0;
        etat = true;
    } else if (etat && tour < 100) {
        posX -= 0.1;
        tour++;
    } else {
        posY -= 1;
        tour = 0;
        etat = false;
    }
    }

  }
