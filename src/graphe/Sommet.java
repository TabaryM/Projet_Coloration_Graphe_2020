package graphe;

/**
 * @author Tabary
 */
public class Sommet {
    private final int numSommet;
    private Couleur couleur;

    public Sommet(int numSommet) {
        this.numSommet = numSommet;
        couleur = Couleur.NOCOULEUR;
    }

    public int getNumSommet() {
        return numSommet;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public boolean isColorie(){
        return couleur != Couleur.NOCOULEUR;
    }

    @Override
    public String toString() {
        return Couleur.colorCode(couleur) + numSommet + Couleur.colorCode(Couleur.NOCOULEUR);
    }

}
