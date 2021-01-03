package graphe;

import java.util.List;

/**
 * @author Tabary
 */
public class SommetMalColore {
    private final Sommet sommet;
    private final List<Couleur> couleursVoisins;

    public SommetMalColore(Sommet sommet, List<Couleur> couleursVoisins) {
        this.sommet = sommet;
        this.couleursVoisins = couleursVoisins;
    }

    public Sommet getSommet() {
        return sommet;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Sommet : ").append(sommet).append("\tCouleurs des voisins : ");
        for(Couleur couleur : couleursVoisins){
            stringBuilder.append(couleur.asMessage());
        }
        stringBuilder.append(Couleur.colorCode(Couleur.NOCOULEUR));
        return stringBuilder.toString();
    }
}
