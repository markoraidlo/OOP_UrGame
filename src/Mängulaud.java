import java.util.ArrayList;
import java.util.List;

public class Mängulaud {
    // Nuppude asukohtade jaoks erinvad listid
    private List<Mängunupp> mängijaAlgus = new ArrayList<>();
    private List<Mängunupp> arvutiAlgus = new ArrayList<>();

    private List<Mängunupp> mängijaLõpp = new ArrayList<>();
    private List<Mängunupp> arvutiLõpp = new ArrayList<>();

    private List<Mängunupp> mängijaTee = new ArrayList<>(14);
    private List<Mängunupp> arvutiTee = new ArrayList<>(14);

    // Constructor mis loob nuppud ning panneb nad mängualgusesse
    public Mängulaud() {
        Mängunupp kasutaja1 = new Mängunupp(true, 1);
        Mängunupp kasutaja2 = new Mängunupp(true, 2);
        Mängunupp kasutaja3 = new Mängunupp(true, 3);
        Mängunupp kasutaja4 = new Mängunupp(true, 4);
        Mängunupp kasutaja5 = new Mängunupp(true, 5);
        Mängunupp kasutaja6 = new Mängunupp(true, 6);

        Mängunupp arvuti1 = new Mängunupp(false, 1);
        Mängunupp arvuti2 = new Mängunupp(false, 2);
        Mängunupp arvuti3 = new Mängunupp(false, 3);
        Mängunupp arvuti4 = new Mängunupp(false, 4);
        Mängunupp arvuti5 = new Mängunupp(false, 5);
        Mängunupp arvuti6 = new Mängunupp(false, 6);

        mängijaAlgus.add(kasutaja1);
        mängijaAlgus.add(kasutaja2);
        mängijaAlgus.add(kasutaja3);
        mängijaAlgus.add(kasutaja4);
        mängijaAlgus.add(kasutaja5);
        mängijaAlgus.add(kasutaja6);

        arvutiAlgus.add(arvuti1);
        arvutiAlgus.add(arvuti2);
        arvutiAlgus.add(arvuti3);
        arvutiAlgus.add(arvuti4);
        arvutiAlgus.add(arvuti5);
        arvutiAlgus.add(arvuti6);
    }

    // Mängu seisu väljastamine
    public void väljastaLaud() {
        System.out.println("__________________________");
        System.out.println("Arvuti algus: " + this.arvutiAlgus);
        System.out.println("Arvuti lõpp: " + this.arvutiLõpp);
        // Päris laud on veel puudu.
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Mägnija algus: " + this.mängijaAlgus);
        System.out.println("Mängoja lõpp: " + this.mängijaLõpp);
        System.out.println("__________________________");
    }

    // Kontrollib kas arvuti või mängija on juba võitnud.
    public int võiduKontroll() {
        if (arvutiLõpp.size() == 6) {
            return 1;
        }
        if (mängijaLõpp.size() == 6) {
            return 2;
        }
        return 0;
    }

    //Algne liigutamis meetod, vajab enen kontrolli läbimist. Tõenäoliselt on vaja paremaks teha.
    public void liiguta(Mängunupp mängunupp, int silmadeArv) {
        if (mängunupp.isMängijaOma()) {
            if (mängijaAlgus.contains(mängunupp)) {
                mängijaAlgus.remove(mängunupp);
                mängijaTee.add(silmadeArv-1,mängunupp);
            }
            if (mängijaTee.contains(mängunupp))  {
                int i = mängijaTee.indexOf(mängunupp);
                mängijaTee.remove(mängunupp);
                mängijaTee.add(i + silmadeArv, mängunupp);
            }
        }
        else {
            if (arvutiAlgus.contains(mängunupp)) {
                arvutiAlgus.remove(mängunupp);
                arvutiTee.add(silmadeArv - 1, mängunupp);
            }
            if (arvutiTee.contains(mängunupp))  {
                int i = arvutiTee.indexOf(mängunupp);
                arvutiTee.remove(mängunupp);
                arvutiTee.add(i + silmadeArv, mängunupp);
            }
        }
    }

    // Selleks kui vastas nupp astub selle nuppu peale
    public void liigutaAlgusesse(Mängunupp mängunupp) {
        if (mängunupp.isMängijaOma()) {
            mängijaTee.remove(mängunupp);
            mängijaAlgus.add(mängunupp);
        }
        else {
            arvutiTee.remove(mängunupp);
            arvutiAlgus.add(mängunupp);
        }
    }

    //Liigutab lõppu, enne peab kontrolli läbima.
    public void liigutaLõppu(Mängunupp mängunupp) {
        if (mängunupp.isMängijaOma()) {
            mängijaTee.remove(mängunupp);
            mängijaLõpp.add(mängunupp);
        }
        else {
            arvutiTee.remove(mängunupp);
            arvutiLõpp.add(mängunupp);
        }
    }


    /*
Meetodid ning muutujad mis tegelevad nuppude asukoha järgimise
ning muutmisega, samuti ka mängulaua väljastamisega ekraanile.
Mängulaud teeb ka 6 mängunuppu arvutile ja mängijale.
 */
}
