public class Score {
  int score;

  /**
   * Création d'une classe score, son constructeur initalise sa valeur à 0
   */
  public Score() {
    score = 0;
  }

  public void ajoute(int valeur){
    score+=valeur;
  }

  @Override
  public String toString(){
    return this.score+"";
  }

}
