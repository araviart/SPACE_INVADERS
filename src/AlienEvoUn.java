public class AlienEvoUn extends Aliens {
  private double posX;
  private double posY;
private int tour;
private boolean droite;


/**
 * Alien évolue pour le niveau 2, 
 * @param posX
 * @param posY
 */

 
  public AlienEvoUn(double posX, double posY) {
    super(posX, posY);
    this.posX = posX;
    this.posY = posY;
    tour = 0;
  }

  @Override
   public EnsembleChaines getEnsembleChaines() {
    EnsembleChaines alienEvoUn = new EnsembleChaines();
    alienEvoUn.ajouteChaine((int) posX, (int) posY + 4, "      ▄██▄       ", "0x32CD32");
    alienEvoUn.ajouteChaine((int) posX, (int) posY + 3, "    ▄██████▄     ", "0x32CD32");
    alienEvoUn.ajouteChaine((int) posX, (int) posY + 2, "   ███▄██▄███    ", "0x32CD32");
    alienEvoUn.ajouteChaine((int) posX, (int) posY + 1, "     ▄▀▄▄▀▄      ", "0x32CD32");
    alienEvoUn.ajouteChaine((int) posX, (int) posY + 1, "    ▀ ▀  ▀ ▀     ", "0x32CD32");
    return alienEvoUn;
  }

  @Override
  public void evolue() {
    if (tour > 30) {
      droite = !droite;
      tour = 0;
  }
  if (droite) {
      this.posX += 0.4;
  } else {
      this.posX -= 0.4;
  }
  this.posY -= 0.1;
  tour++;
}


  public double getPosXAlien() {
    return this.posX;
  }

  public double getPosYAlien() {
    return this.posY;
  }
}
