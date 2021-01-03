package graphe;

import java.util.ArrayList;
import java.util.List;

public enum Couleur {
    ROUGE, VERT, BLEU, NOCOULEUR;

    public int value(){
        int res;
        switch (this){
            case ROUGE:
                res = 0;
                break;
            case VERT:
                res = 1;
                break;
            case BLEU:
                res = 2;
                break;
            default:
                res = -1;
        }
        return res;
    }

    public String asMessage(){
        String res;
        switch (this){
            case ROUGE:
                res = "\033[31mR";
                break;
            case VERT:
                res = "\033[32mV";
                break;
            case BLEU:
                res = "\033[34mB";
                break;
            default:
                res = "\033[0mNOCOULEUR";
        }
        return res;
    }

    public static Couleur couleurOfValue(int value){
        switch (value){
            case 0:
                return ROUGE;
            case 1:
                return VERT;
            case 2:
                return BLEU;
            default:
                return NOCOULEUR;
        }
    }

    public static int valueOf(Couleur couleur){
        int res;
        switch (couleur){
            case ROUGE:
                res = 0;
                break;
            case VERT:
                res = 1;
                break;
            case BLEU:
                res = 2;
                break;
            default:
                res = -1;
        }
        return res;
    }

    public static String colorCode(Couleur couleur){
        String res;
        switch (couleur){
            case ROUGE:
                res = "\033[31m";
                break;
            case VERT:
                res = "\033[32m";
                break;
            case BLEU:
                res = "\033[34m";
                break;
            default:
                res = "\033[0m";
        }
        return res;
    }

    public static List<Couleur> getCouleur3Coloriage(){
        List<Couleur> res = new ArrayList<>();
        res.add(ROUGE);
        res.add(VERT);
        res.add(BLEU);
        return res;
    }
}
