import javafx.scene.paint.Color;
public class ChainePositionnee {
    int x, y;
    String c;
    private String couleur;

    public ChainePositionnee(int a, int b, String d) {
        x = a;
        y = b;
        c = d;
        couleur = null;

    }

    public ChainePositionnee(int a, int b, String d, String couleur) {
        x = a;
        y = b;
        c = d;
        this.couleur = couleur;
    }

    public int getXChaine() {
        return this.x;
    }

    public boolean contient(int posX, int posY) {
        return this.x == posX && this.y == posY;
    }

    public int getYChaine() {
        return this.y;
    }

    public String getCouleur(){
        return this.couleur;
    }

}
