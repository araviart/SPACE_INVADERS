public class Projectile{
  private double positionX;
  private double positionY;

  public Projectile(double positionX){
    this.positionX = positionX;
    this.positionY = 5;
  }

  public EnsembleChaines getEnsembleChaines(){
    EnsembleChaines vaisseau = new EnsembleChaines();
    vaisseau.ajouteChaine((int)positionX, (int)positionY, "▄");
    return vaisseau;
  }

  public void evolue(){
    this.positionY+=0.2;
  }


  public double getPosProjectileY(){
    return this.positionY;
  }





}