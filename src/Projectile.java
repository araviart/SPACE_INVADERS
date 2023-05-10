public class Projectile {
  public double positionX;
  public double positionY;
  private boolean estAlien;
  public double vitesseTir;

  public Projectile(double positionX) {
    this.positionX = positionX;
    this.positionY = 5;
    this.estAlien = false;
    this.vitesseTir = 0.3;
  }

  /**
   * 
   * constructeur d'un projectile lancé
   * 
   * @param positionX  la position sur l'axe horizontale du projectile
   * @param positionY  la position sur l'axe vertical du projectile
   * @param vitesseTir la vitesse de déplacement du projectile
   * @param estAlien true si le projectile est lancé par l'alien, false sinon
   */
  public Projectile(double positionX, double positionY, double vitesseTir) {
    this.positionX = positionX;
    this.positionY = positionY;
    this.estAlien = true;
    this.vitesseTir = vitesseTir;
  }

  /**
   * 
   * retourne un ensemble de chaînes de caractères représentant le projectile
   * 
   * @return un ensemble de chaines de caractères (chaine positionne) représentant le projectile
   */
  public EnsembleChaines getEnsembleChaines() {
    EnsembleChaines projectile = new EnsembleChaines();
    projectile.ajouteChaine((int) positionX, (int) positionY, "▄");
    return projectile;
  }

  /**
   * 
   * fait évoluer la position du projectile en fonction de sa vitesse
   */
  public void evolue() {
    this.positionY += vitesseTir;
  }

  /**
   * 
   * fait évoluer la position du projectile en fonction d'une vitesse donnée
   * 
   * @param vitesse la vitesse de déplacement du projectile
   */
  public void evolue(double vitesse) {
    this.positionY -= vitesse;
  }

  /**
   * 
   * retourne la position sur l'axe vertical du projectile
   * 
   * @return la position  sur l'axe vertical du projectile
   */
  public double getPosProjectileY() {
    return this.positionY;
  }

  /**
   * 
   * retourne la position en horizontal du projectile
   * 
   * @return la position en horizontal du projectile
   */
  public double getPosProjectileX() {
    return this.positionX;
  }

  /**
   * 
   * retourne vrai si le projectile a été lancé par un ennemi, faux sinon
   * 
   * @return vrai si le projectile a été lancé par un alien, faux sinon
   */
  public boolean estAlien() {
    return this.estAlien;
  }
}