public class ProjectileNuke extends Projectile{
  // private int temps;
  public ProjectileNuke(double positionX){
    super(positionX);
  }

    public EnsembleChaines getEnsembleChaines(){
      EnsembleChaines projectileNuke = new EnsembleChaines();
  
      // Incrémenter la variable de temps à chaque appel
      for(int i = 0; i < 100; i++){
        projectileNuke.ajouteChaine((int)positionX, (int) positionY+i,"       █████████████       ");
    }
    return  projectileNuke;
    }

    @Override
    public void evolue(){
      this.positionY += 3;
    }
}