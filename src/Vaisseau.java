import java.util.List;

public class Vaisseau {
  private double posX;
  private EnsembleChaines vaisseau;

  public Vaisseau(double posX) {
    this.posX = posX;
    this.vaisseau = new EnsembleChaines();
}

  public void deplace(double dx){
    this.posX+=dx;
  }

  public EnsembleChaines getEnsembleChaines(){
    vaisseau.ajouteChaine((int)posX, 1, "█████████████");
    vaisseau.ajouteChaine((int)posX, 2, "█████████████");
    vaisseau.ajouteChaine((int)posX, 3, "▄███████████▄");
    vaisseau.ajouteChaine((int)posX, 4, "     ███     ");
    vaisseau.ajouteChaine((int)posX, 5, "      ▄      ");
    return this.vaisseau;
  }

  public int positionCanon(){
    List<ChainePositionnee> vaiss = (List) vaisseau;
    return vaiss.get(vaiss.size()-1).getXChaine()/2; 
  }



}
