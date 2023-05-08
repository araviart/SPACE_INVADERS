public class Projectile{
  public double positionX;
  public double positionY;

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


  public double getPosProjectileX(){
    return this.positionY;
  }



}