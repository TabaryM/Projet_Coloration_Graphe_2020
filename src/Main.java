import graphe.Couleur;
import graphe.Graphe;
import graphe.SommetMalColore;

import java.io.File;

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
        boolean clique4;
        long debut, fin;
        debut = System.currentTimeMillis();
        for(File file : files){
            test3coloriage(file);
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

    public static void test3coloriage(File file){
        SommetMalColore sommetMalColore;
        boolean clique4;
        System.out.println("\nColoration du graphe "+file.getName());
        Graphe graphe = Graphe.getGrapheFromTxt(file.getPath());
        sommetMalColore = graphe.coloriage(null, Couleur.getCouleur3Coloriage());
        if(sommetMalColore.getSommet() != null){
            clique4 = graphe.clique4(sommetMalColore.getSommet(), null, null);
            if(clique4){
                System.out.println("C'est normal que la coloration ai raté");
            } else {
                System.out.println("\033[33mJ'ai chié dans la colle\033[0m");
            }
            System.out.println(sommetMalColore);
        } else {
            if(graphe.valideColoration()){
                System.out.println("\033[36mJ'ai VRAIMENT chié dans la colle\033[0m");
            }
            System.out.println(graphe.sommetsColores());
        }
    }
}
