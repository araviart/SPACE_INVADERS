public class Projectile{
  public double positionX;
  public double positionY;
  private boolean estAlien;

  public Projectile(double positionX){
    this.positionX = positionX;
    this.positionY = 5;
    this.estAlien = false;
  }

  public Projectile(double positionX,double positionY){
    this.positionX = positionX;
    this.positionY = positionY;
    this.estAlien = true;
  }

  public EnsembleChaines getEnsembleChaines(){
    EnsembleChaines vaisseau = new EnsembleChaines();
    vaisseau.ajouteChaine((int)positionX, (int)positionY, "▄");
    return vaisseau;
  }

  public void evolue(){
    this.positionY+=0.2;
  }

  public void evolue(double vitesse){
    this.positionY -= vitesse;
  }


  public double getPosProjectileY(){
    return this.positionY;
  }


  public double getPosProjectileX(){
    return this.positionY;
  }

  public boolean estAlien(){
    return this.estAlien;
  }




}