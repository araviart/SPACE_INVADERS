import java.util.List;

public class Vaisseau {
  private double posX;

  public Vaisseau(double posX) {
    this.posX = posX;
}

  public void deplace(double dx){
      this.posX+=dx;
  }

  public EnsembleChaines getEnsembleChaines(){
    EnsembleChaines vaisseau = new EnsembleChaines();
    vaisseau.ajouteChaine((int)posX, 1, "█████████████");
    vaisseau.ajouteChaine((int)posX, 2, "█████████████");
    vaisseau.ajouteChaine((int)posX, 3, "▄███████████▄");
    vaisseau.ajouteChaine((int)posX, 4, "     ███     ");
    vaisseau.ajouteChaine((int)posX, 5, "      ▄      ");
    return vaisseau;
  }

  public double positionCanon(){
    return this.getEnsembleChaines().chaines.get(1).c.length()/2 + this.posX;
  }

  public double getPosXvaisseau(){
    return this.posX;
  }


}
