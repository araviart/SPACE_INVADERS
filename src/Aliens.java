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
    alien.ajouteChaine((int) posX, (int) posY + 4, "  ▀▄   ▄▀  ", "0x32CD32");
    alien.ajouteChaine((int) posX, (int) posY + 3, " ▄█▀███▀█▄ ", "0x32CD32");
    alien.ajouteChaine((int) posX, (int) posY + 2, "█▀███████ █", "0x32CD32");
    alien.ajouteChaine((int) posX, (int) posY + 1, "█ █▀▀▀▀▀█ █", "0x32CD32");

    if (tour % 20 == 0) {
      estApparenceSpeciale = !estApparenceSpeciale;
    } else if (estApparenceSpeciale) {
      alien.ajouteChaine((int) posX, (int) posY, "  ▀▀   ▀▀ ", "0x32CD32");
    } else if (!estApparenceSpeciale || this.tour < 20) {
      alien.ajouteChaine((int) posX, (int) posY, " ▀▀     ▀▀", "0x32CD32");
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

  public double positionCanon(){
    return this.getEnsembleChaines().chaines.get(1).c.length()/2 + this.posX;
  }
}