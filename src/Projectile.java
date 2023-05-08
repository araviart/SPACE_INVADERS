public class Projectile{
  public double positionX;
  public double positionY;
  private boolean estAlien;
  public double vitesseTir;

  public Projectile(double positionX){
    this.positionX = positionX;
    this.positionY = 5;
    this.estAlien = false;
    this.vitesseTir = 0.2;
  }

  public Projectile(double positionX,double positionY){
    this.positionX = positionX;
    this.positionY = positionY;
    this.estAlien = true;
    this.vitesseTir = 0.2;
  }

  public Projectile(double positionX,double positionY, double vitesseTir){
    this.positionX = positionX;
    this.positionY = positionY;
    this.estAlien = true;
    this.vitesseTir = vitesseTir;
  }

  public EnsembleChaines getEnsembleChaines(){
    EnsembleChaines vaisseau = new EnsembleChaines();
    vaisseau.ajouteChaine((int)positionX, (int)positionY, "▄");
    return vaisseau;
  }

  public void evolue(){
    this.positionY+=vitesseTir;
  }


  public void evolue(double vitesse){
    this.positionY -= vitesse;
  }


  public double getPosProjectileY(){
    return this.positionY;
  }


  public double getPosProjectileX(){
    return this.positionX;
  }

  public boolean estAlien(){
    return this.estAlien;
  }




}