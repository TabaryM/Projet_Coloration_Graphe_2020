import graphe.Couleur;
import graphe.Graphe;
import graphe.Sommet;

import java.io.File;
import java.util.Random;

/**
 * @author Tabary
 */
public class Main {
    public static void main(String[] args) {
        File[] files = getGraphesFromDir("ressources");
        if(files == null){
            System.out.println("ALED");
            return;
        }
        Graphe graphe;
        String messageColoriage;
        long debut, fin;
        debut = System.currentTimeMillis();
        for(File file : files){
            System.out.println("\nColoration du graphe "+file.getName());
            graphe = Graphe.getGrapheFromTxt(file.getPath());
            messageColoriage = graphe.coloriage(null, Couleur.getCouleur3Coloriage());
            if(!messageColoriage.equals("Coloriage réussis")){
                System.out.println(messageColoriage);
            } else {
                System.out.println(graphe.sommetsColores());
            }
        }
        fin = System.currentTimeMillis();
        System.out.println("\nTemps d'execution : "+((fin-debut)/1000f)+" secondes");
    }

    public static File[] getGraphesFromDir(String directory){
        File dir = new File(directory);
        if(dir.isDirectory()){
            return dir.listFiles();
        } else {
            System.out.println("ALERT");
            return null;
        }
    }
}
