public class Objet{
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
    objet.ajouteChaine((int) posXObj, (int) posYObj+1, "              ");
    objet.ajouteChaine((int) posXObj, (int) posYObj, "     ⋐☢⋑     ");
    objet.ajouteChaine((int) posXObj, (int) posYObj-1, "              ");
    return objet;
  }

  
  public void evolue(){
    this.posYObj -= 0.1;
  }
  
  public double getPosYObj() {
    return this.posYObj;
  }

  public double getPosXObj() {
    return this.posXObj;
  }
}