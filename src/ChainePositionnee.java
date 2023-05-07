public class ChainePositionnee {
    int x, y;
    String c;

    public ChainePositionnee(int a, int b, String d) {
        x = a;
        y = b;
        c = d;
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

}
