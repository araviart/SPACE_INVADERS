public class Objet{
  private double posXObj;
  private double posYObj;


  /**
   * 
   * @param posXObj la position horizontale de l'objet
   * @param posYObj la position verticale de l'objet
   */
  public Objet(double posXObj, double posYObj) {
  this.posXObj = posXObj;
  this.posYObj = posYObj;
  }
  /**
  
  retourne le type de l'objet.
  @return une chaîne de caractères représentant le type de l'objet
  */
  public String typeObjet() {
  return "";
  }
  /**
  
  retourne un ensemble de chaines représentant l'objet
  @return un EnsembleChaines représentant l'objet
  */
  public EnsembleChaines getEnsembleChaines() {
  EnsembleChaines objet = new EnsembleChaines();
  objet.ajouteChaine((int) posXObj, (int) posYObj+1, " ");
  objet.ajouteChaine((int) posXObj, (int) posYObj, " ⋐☢⋑ ");
  objet.ajouteChaine((int) posXObj, (int) posYObj-1, " ");
  return objet;
  }
  /**
  
  fait évoluer la position de l'obje
  */
  public void evolue() {
  this.posYObj -= 0.2;
  }
  /**
  
  retourne la position verticale
  @return la position verticale de l'objet
  */
  public double getPosYObj() {
  return this.posYObj;
  }
  /**
  

  retourne la position horizontale de l'objet
  @return la position horizontale de l'objet
  */
  public double getPosXObj() {
  return this.posXObj;
  }
  }