public class ProjectileNuke extends Projectile{
  // private int temps;
  public ProjectileNuke(double positionX){
    super(positionX);
  }

    public EnsembleChaines getEnsembleChaines(){
      EnsembleChaines projectileNuke = new EnsembleChaines();
  
      // créez un rayon nuclaire (une colonne de chaine de caractère)
      for(int i = 0; i < 100; i++){
        projectileNuke.ajouteChaine((int)positionX, (int) positionY+i,"            █████████████           ");
    }
    return  projectileNuke;
    }

    public void evolue(){
      this.positionY += 2;
    }
}