public class ProjectileContagion extends Projectile {
  private boolean epidemie;


  public ProjectileContagion(double positionX) {
    super(positionX);
    this.epidemie = false;
  }

  public ProjectileContagion(double positionX, double positionY) {
    super(positionX);
    this.positionY = positionY;
    this.epidemie = false;
  }

  private int compteur = 0;

  public void evolueMinusX() {
    this.positionX -= 0.2;
  }

  public void evolueMinusY() {
    this.positionY -= 0.2;
  }

  public void evoluePlusX() {
    this.positionX += 0.2;
  }

  public void evoluePlusY() {
    this.positionY += 0.2;
  }

  @Override
  public void evolue() {
    if (compteur % 4 == 0) {
      evolueMinusY();
    } else if (compteur % 4 == 1) {
      evoluePlusY();
    } else if (compteur % 4 == 2) {
      evoluePlusX();
    } else {
      evolueMinusX();
    }
    compteur++;
  }

  public void setEpidemie() {
    this.epidemie = true;
  }

  public boolean getEpidemie() {
    return this.epidemie;
  }

  public String getType() {
    return "Contagion";
  }

}