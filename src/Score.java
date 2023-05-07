public class Score {
  int score;

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
