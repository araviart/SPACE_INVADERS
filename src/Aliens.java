import java.util.List;

public class Aliens {
  private double posX;
  private double posY;
  private int tour;
  private boolean etat;
  private boolean estApparenceSpeciale;

  public Aliens(double posX, double posY) {
    this.posX = posX;
    this.posY = posY;
    this.etat = false;
    this.estApparenceSpeciale = false;
  }

  public void deplaceAlien(double dx) {
    this.posX += dx;
  }

  public EnsembleChaines getEnsembleChaines() {
    EnsembleChaines alien = new EnsembleChaines();
    alien.ajouteChaine((int) posX, (int) posY + 4, "  ▀▄   ▄▀  ");
    alien.ajouteChaine((int) posX, (int) posY + 3, " ▄█▀███▀█▄ ");
    alien.ajouteChaine((int) posX, (int) posY + 2, "█▀███████ █");
    alien.ajouteChaine((int) posX, (int) posY + 1, "█ █▀▀▀▀▀█ █");

    if (tour % 20 == 0) {
      estApparenceSpeciale = !estApparenceSpeciale;
    } else if (estApparenceSpeciale) {
      alien.ajouteChaine((int) posX, (int) posY, "  ▀▀   ▀▀ ");
    } else if (!estApparenceSpeciale || this.tour < 20) {
      alien.ajouteChaine((int) posX, (int) posY, " ▀▀     ▀▀");
    }
    return alien;

  }

  public int getNbTour() {
    return this.tour;
  }

  public double getPosXAlien() {
    return this.posX;
  }

  public double getPosYAlien() {
    return this.posY;
  }

  public void evolue() {
    if (!etat && tour < 100) {
      posX += 0.1;
      tour++;
    } else if (!etat && tour >= 100) {
      posY -= 1;
      tour = 0;
      etat = true;
    } else if (etat && tour < 100) {
      posX -= 0.1;
      tour++;
    } else {
      posY -= 1;
      tour = 0;
      etat = false;
    }
  }

  public boolean contient(int x, int y) {
    return this.getEnsembleChaines().contient(x, y);
  }
}