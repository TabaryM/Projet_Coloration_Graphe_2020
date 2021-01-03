package graphe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author Tabary
 */
public class Graphe implements Iterable<Sommet>{
    private final boolean[][] matriceAdjacence;
    private final int nbSommets;
    private final List<Sommet> sommets;

    public Graphe(boolean[][] matriceAdjacence, int nbSommets) {
        this.matriceAdjacence = matriceAdjacence;
        this.nbSommets = nbSommets;
        sommets = new ArrayList<>();
        for(int i = 0; i < nbSommets; i++){
            sommets.add(new Sommet(i));
        }
    }

    public static Graphe getGrapheFromTxt(String path){
        Graphe res = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            int taille = Integer.parseInt(reader.readLine());
            boolean[][] matrice = new boolean[taille][taille];
            String line;
            for(int i = 0; i < taille; i++){
                line = reader.readLine();
                for(int j = 0; j < taille; j++){
                    char tmp = line.charAt(j);
                    if(tmp == '1') matrice[i][j] = true;
                    else if(tmp == '0') matrice[i][j] = false;
                }
            }
            res = new Graphe(matrice, taille);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public Sommet plusPetitSommet(){
        Sommet res = null;
        int degre = Integer.MAX_VALUE, degreSommet;
        for(Sommet sommet : this){
            degreSommet = degre(sommet);
            if(degreSommet < degre){
                res = sommet;
                degre = degreSommet;
            }
        }
        return res;
    }

    public SommetMalColore coloriage(Sommet sommet, List<Couleur> couleursDispoDepart){
        SommetMalColore res = new SommetMalColore(null, new ArrayList<>());
        List<Couleur> couleursDispoFin = new ArrayList<>(couleursDispoDepart);
        List<Couleur> couleursVoisins = new ArrayList<>();

        if(sommet == null){
            sommet = plusPetitSommet();
        }

        List<Sommet> voisins = voisins(sommet);

        if(!sommet.isColorie()){
            for(Sommet voisin : voisins){
                couleursVoisins.add(voisin.getCouleur());
            }

            for(Couleur couleur : couleursDispoDepart){
                if(couleursVoisins.contains(couleur)){
                    while (couleursDispoFin.contains(couleur)) {
                        couleursDispoFin.remove(couleur);
                    }
                }
            }

            if(!couleursDispoFin.isEmpty()){
                sommet.setCouleur(couleursDispoFin.get(0));
                List<Couleur> couleursDispoSuccesseurs = Couleur.getCouleur3Coloriage();
                couleursDispoSuccesseurs.remove(sommet.getCouleur());
                for(Sommet voisin : voisinsSansCouleur(sommet)){
                    if(!voisin.isColorie()) {
                        res = coloriage(voisin, couleursDispoSuccesseurs);
                        if(res.getSommet() != null){
                            return res;
                        }
                    }
                }
            } else {
                res = new SommetMalColore(sommet, couleursVoisins);
            }

        }
        return res;
    }

    public List<Sommet> clique4(Sommet s1, Sommet s2, Sommet s3){
        List<Sommet> res = new ArrayList<>();
        if(s1 == null){
            for(Sommet sommet : this.sommets){
                if(res.size() < 4) {
                    res = clique4(sommet, null, null);
                }
            }
        } else if (s2 == null){
            for(Sommet voisin : voisins(s1)){
                if(res.size() < 4) {
                    res = clique4(s1, voisin, null);
                }
            }
        } else if (s3 == null){
            for(Sommet voisin : voisins(s1)){
                if(res.size() < 4) {
                    if(voisins(s2).contains(voisin)) {
                        res = clique4(s1, s2, voisin);
                    }
                }
            }
        } else {
            res.add(s1);
            res.add(s2);
            res.add(s3);
            for(Sommet voisin : voisins(s1)){
                if(res.size() < 4){
                    if(voisins(s2).contains(voisin) && voisins(s3).contains(voisin)){
                        res.add(voisin);
                    }
                }
            }
        }
        return res;
    }

    public boolean valideColoration(){
        boolean res = true;
        for(Sommet sommet : sommets){
            for(Sommet voisin : voisins(sommet)){
                res = res && sommet.getCouleur().equals(voisin.getCouleur());
                if(!res)
                    break;
            }
        }
        return res;
    }

    public int degre(Sommet sommet){
        int res = 0;
        for(boolean b : matriceAdjacence[sommet.getNumSommet()]){
            if(b) res++;
        }
        return res;
    }

    public int getNbSommets() {
        return nbSommets;
    }

    public List<Sommet> voisins(Sommet s){
        List<Sommet> res = new ArrayList<>();
        for(int i = 0; i < nbSommets; i++){
            if(matriceAdjacence[s.getNumSommet()][i]){
                res.add(sommets.get(i));
            }
        }

        return res;
    }

    public List<Sommet> voisinsSansCouleur(Sommet s){
        List<Sommet> res = new ArrayList<>();
        List<Sommet> voisins = voisins(s);
        for(Sommet sommet : voisins){
            if(!sommet.isColorie()){
                res.add(sommet);
            }
        }
        return res;
    }

    @Override
    public Iterator<Sommet> iterator() {
        return sommets.iterator();
    }

    @Override
    public String toString() {
        return getMatriceToString();
    }

    public String getMatriceToString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < nbSommets; i++){
            for (int j = 0; j < nbSommets; j++) {
                if(matriceAdjacence[i][j]) stringBuilder.append(1);
                else stringBuilder.append(0);
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }

    public String sommetsColores(){
        StringBuilder stringBuilder = new StringBuilder();
        for(Sommet sommet : this){
            stringBuilder.append(sommet.toString());
            stringBuilder.append(' ');
        }
        stringBuilder.append(Couleur.colorCode(Couleur.NOCOULEUR));
        return stringBuilder.toString();
    }
}
