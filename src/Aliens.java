
public class Aliens {
  private double posX;
  private double posY;
  private int tour;
  private boolean etat;
  private boolean estApparenceSpeciale;
  /**
  constructeur de la classe Aliens
  @param posX pos horizontal de l'alien
  @param posY pos vertical de l'alien
  */
  public Aliens(double posX, double posY) {
  this.posX = posX;
  this.posY = posY;
  this.etat = false;
  this.estApparenceSpeciale = false;
  }
  /**
  déplace l'alien d'une distance dx
  @param dx la distance de déplacement
  */



  public void deplaceAlien(double dx) {
  this.posX += dx;
  }


  /**
  l'ensemble de chaînes de caractères représentant l'alien. // alterne avec l'autre apparence de l'alien toutes les 20 itérations.
  @return l'ensemble de chaînes de caractères représentant l'alien
  */
  public EnsembleChaines getEnsembleChaines() {
  EnsembleChaines alien = new EnsembleChaines();
  alien.ajouteChaine((int) posX, (int) posY + 4, "   ▀▄ ▄▀ ", "0x32CD32");
  alien.ajouteChaine((int) posX, (int) posY + 3, " ▄█▀███▀█▄ ", "0x32CD32");
  alien.ajouteChaine((int) posX, (int) posY + 2, "█▀███████ █", "0x32CD32");
  alien.ajouteChaine((int) posX, (int) posY + 1, "█ █▀▀▀▀▀█ █", "0x32CD32");
  if (tour % 20 == 0) {
    estApparenceSpeciale = !estApparenceSpeciale;
  } else if (estApparenceSpeciale) {
    alien.ajouteChaine((int) posX, (int) posY, "  ▀▀   ▀▀ ", "0x32CD32");
  } else if (!estApparenceSpeciale || this.tour < 20) {
    alien.ajouteChaine((int) posX, (int) posY, " ▀▀     ▀▀", "0x32CD32");
  }
  return alien;
  }
  
  /**
  @return retourne le nombre de tours effectué par l'alien (j'aurais pu utiliser le nombre de tour dans gestionJeu )
  */
  public int getNbTour() {
  return this.tour;
  }
  /**
  
  renvoie la position horizontale de l'alien
  @return la position horizontale de l'alien
  */
  public double getPosXAlien() {
  return this.posX;
  }

  /**
   * renvoie la position verticale de l'alien
   * @return retourne la pos verticale de l'alien
   */
  public double getPosYAlien() {
    return this.posY;
  }

  // alterne l'apparence de l'alien
  public void evolue() {
    if (!etat && tour < 120) { // avance horizontalement pendant les 120 premiers tours
      posX += 0.1;
      tour++;
    } else if (!etat && tour >= 100) { // descend verticalement après 100 tours
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

  public boolean contient(int x, int y) {
    return this.getEnsembleChaines().contient(x, y);
  }

  /**
   * renvoie la position du canon qui égale à largeur du vaisseau diviser par 2 en admettant que le cannon soit au milieu du vaisseau
   * @return retourne la position du cannon
   */
  public double positionCanon() {
    return this.getEnsembleChaines().chaines.get(1).c.length() / 2 + this.posX;
  }
}