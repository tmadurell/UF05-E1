import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        String PareNoel = "*<]:-DOo";
        String Rens = ">:o)";
        String Follets = "<]:-D";
        //Llegeix el fitxer de la llista, linia per linia.
        List<String> allLines = Files.readAllLines(Paths.get("santako.txt"));
        System.out.println("\nSense Expressions Regulars:");
        for (String line : allLines) {
            int indexPN, indexR = 0, indexF = 0;
            int contPN=0,contR=0,contF=0;
            do {
                //Comprova si existeix i busca quina es la posició de inici.
                if ((indexPN = line.indexOf(PareNoel)) != -1){
                    contPN++;
                    // La posició de inici de parable i on termina.
                    var endIndex = indexPN+PareNoel.length();
                    // Elimina caracter de posició index a endindex.
                    line= line.substring(0,indexPN) + line.substring(endIndex);
                }else if ((indexR = line.indexOf(Rens)) != -1){
                    contR++;
                    var endIndex = indexR+Rens.length();
                    line= line.substring(0,indexR) + line.substring(endIndex) ;
                }else if ((indexF = line.indexOf(Follets)) != -1){
                    contF++;
                    var endIndex = indexF+Follets.length();
                    line= line.substring(0,indexF) + line.substring(endIndex) ;
                }
            }while (indexR != -1 && indexF != -1 && indexPN != -1);
            if (contPN != 0) System.out.print("Pare Noel ("+ contPN+ ") ");
            if (contR != 0) System.out.print("Ren (" + contR+ ") ");
            if (contF != 0) System.out.print("Follet ("+ contF+ ")\n ");
            if (contF== 0 && contPN == 0 && contR == 0) System.out.print("");
            System.out.println();
        }

        System.out.println("Amb Expressions Regulars:");
        for (String line : allLines) {
            int contPN=0,contR=0,contF=0;
            Pattern pattern = Pattern.compile("\\*<]:-DOo");
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                contPN++;
                line = line.replaceAll("\\*<]:-DOo","");
            }
            pattern = Pattern.compile(">:o\\)");
            matcher = pattern.matcher(line);
            while (matcher.find()) {
                contR++;
                line = line.replaceAll(">:o\\)","");
            }
            pattern = Pattern.compile("<]:-D");
            matcher = pattern.matcher(line);
            while (matcher.find()) {
                contF++;
                line = line.replaceAll("<]:-D","");
            }

            if (contPN != 0) System.out.print("Pare Noel ("+ contPN+ ") ");
            if (contR != 0) System.out.print("Ren (" + contR+ ") ");
            if (contF != 0) System.out.print("Follet ("+ contF+ ")\n ");
            if (contF== 0 && contPN == 0 && contR == 0) System.out.print("");
            System.out.println("");

        }
    }
}
