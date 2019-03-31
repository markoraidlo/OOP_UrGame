import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mängulaud {
/*
Meetodid ning muutujad mis tegelevad nuppude asukoha järgimise
ning muutmisega, samuti ka mängulaua väljastamisega ekraanile.
Mängulaud teeb ka 6 mängunuppu arvutile ja mängijale.
 */

    public static void Algne_laud() {

        List<Integer> arvuti = new ArrayList<>();
        List<Integer> mängija = new ArrayList<>();

        List<String> Mängulaua_esimene_rida = new ArrayList<>();
        List<String> Mängulaua_teine_rida = new ArrayList<>();
        List<String> Mängulaua_kolmas_rida = new ArrayList<>();
        List<String> terve_laud = new ArrayList<>();

        Mängulaua_esimene_rida.addAll(Arrays.asList("[#]", "[ ]", "[ ]", "[ ]", "  ", "  ", "[#]", "[ ]"));
        Mängulaua_teine_rida.addAll(Arrays.asList("[ ]", "[ ]", "[ ]", "[ ]", "[ ]", "[ ]", "[ ]", "[ ]"));
        Mängulaua_kolmas_rida.addAll(Arrays.asList("[#]", "[ ]", "[ ]", "[ ]", "  ", "  ", "[#]", "[ ]"));

        terve_laud.addAll(Arrays.asList("[#]", "[ ]", "[ ]", "[ ]", "   ", "   ", "[#]", "[ ]", "[ ]", "[ ]", "[ ]", "[ ]", "[ ]", "[ ]", "[ ]", "[ ]", "[#]", "[ ]", "[ ]", "[ ]", "   ", "   ", "[#]", "[ ]"));

        List<String> esimene_rida = terve_laud.subList(0,8);
        for(String element: esimene_rida) {
            System.out.print(element);
        }
        System.out.println();
        List<String> teine_rida = terve_laud.subList(8,16);
        for(String element2: teine_rida) {
            System.out.print(element2);
        }
        System.out.println();
        List<String> kolmas_rida = terve_laud.subList(16,24);
        for(String element3: kolmas_rida) {
            System.out.print(element3);
        }
        System.out.println();
        }

    public void Mäng(List<Integer> mängija, List<Integer> arvuti, List<String> terve_laud) {
        mängija.addAll(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0));
        arvuti.addAll(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0));

        for (int i=0; i < terve_laud.size(); i++ ) {
            if (mängija.get(i).equals(mängija.get(0))) {
                System.out.println(terve_laud);
            }
            if (mängija.contains(1)) {
                mängija.set(i, 1);
                terve_laud.set(19, "[1]");
            }
        }
    }

    public static void main(String[] args) { //praegu tegin selle main'i siia, et näha lauda
        Algne_laud();
    }
}
