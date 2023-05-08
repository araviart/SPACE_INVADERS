public class AlienEvoUn extends Aliens {
  private double posX;
  private double posY;
  private boolean etat;

  public AlienEvoUn(double posX, double posY) {
    super(posX, posY);
    this.etat = false;
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

  public void evolue(double dx) {
    this.posX += dx;
  }


  public void setEtat(){
    this.etat = true;
  }
}
