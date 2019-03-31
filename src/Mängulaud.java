public class Mängulaud {
/*
Meetodid ning muutujad mis tegelevad nuppude asukoha järgimise
ning muutmisega, samuti ka mängulaua väljastamisega ekraanile.
Mängulaud teeb ka 6 mängunuppu arvutile ja mängijale.
 */

    public static void Joonista(int a, String b) { //lisameetod laua tegemiseks
        for (int i=0; i < a; i++) {
            System.out.print(b);
    }
}
    public static void Algne_laud() { //see vist pole tegelt kõige parem idee, kuidas seda lauda teha
        Joonista(1, "[#]");
        Joonista(3, "[ ]");
        Joonista(6, " ");
        Joonista(1,"[#]");
        Joonista(1, "[ ]");
        System.out.println();
        Joonista(3, "[ ]");
        Joonista(1,"[#]");
        Joonista(4, "[ ]");
        System.out.println();
        Joonista(1, "[#]");
        Joonista(3, "[ ]");
        Joonista(6, " ");
        Joonista(1, "[#]");
        Joonista(1, "[ ]");
        System.out.println();
    }

    public static void main(String[] args) { //praegu tegin selle main'i siia, et näha lauda
        Algne_laud();
    }
}
