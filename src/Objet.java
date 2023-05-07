public class Objet{
  private int nbTir;
  private double posXObj;
  private double posYObj;

  public Objet(double posXObj, double posYObj) {
    this.posXObj = posXObj;
    this.posYObj = posYObj;
  }

  public EnsembleChaines getEnsembleChaines() {
    EnsembleChaines objet = new EnsembleChaines();
    objet.ajouteChaine((int) posXObj, (int) posYObj, "⋐☢⋑");
    return objet;
  }

  public void evolue(){
    this.posXObj -= 0.2;
  }

}