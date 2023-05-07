public class Objet{
  private int nbTir;
  private double posXObj;
  private double posYObj;

  
  public Objet(double posXObj, double posYObj) {
    this.posXObj = posXObj;
    this.posYObj = posYObj;
  }

  public String typeObjet(){
    return "";
  }
  
  public EnsembleChaines getEnsembleChaines() {
    EnsembleChaines objet = new EnsembleChaines();
    objet.ajouteChaine((int) posXObj, (int) posYObj, "⋐☢⋑");
    return objet;
  }

  public void evolue(){
    this.posYObj -= 0.2;
  }
  
  public double getPosYObj() {
    return this.posYObj;
  }

  public double getPosXObj() {
    return this.posYObj;
  }
}