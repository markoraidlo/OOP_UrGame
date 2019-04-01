import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mängulaud {
    // Nuppude asukohtade jaoks erinvad listid
    private List<Mängunupp> mängijaAlgus = new ArrayList<>();
    private List<Mängunupp> arvutiAlgus = new ArrayList<>();

    private List<Mängunupp> mängijaLõpp = new ArrayList<>();
    private List<Mängunupp> arvutiLõpp = new ArrayList<>();

    List<Mängunupp> mängijaTee = Arrays.asList(new Mängunupp[14]);
    List<Mängunupp> arvutiTee = Arrays.asList(new Mängunupp[14]);

    //private List<Mängunupp> mängijaTee = new ArrayList<>();
    //private List<Mängunupp> arvutiTee = new ArrayList<>();

    List<Mängunupp> mängijaNuppud = new ArrayList<>();
    List<Mängunupp> arvutiNuppud = new ArrayList<>();

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

        mängijaNuppud.add(kasutaja1);
        mängijaNuppud.add(kasutaja2);
        mängijaNuppud.add(kasutaja3);
        mängijaNuppud.add(kasutaja4);
        mängijaNuppud.add(kasutaja5);
        mängijaNuppud.add(kasutaja6);

        arvutiNuppud.add(arvuti1);
        arvutiNuppud.add(arvuti2);
        arvutiNuppud.add(arvuti3);
        arvutiNuppud.add(arvuti4);
        arvutiNuppud.add(arvuti5);
        arvutiNuppud.add(arvuti6);

        mängijaAlgus.addAll(mängijaNuppud);
        arvutiAlgus.addAll(arvutiNuppud);
    }

    // Mängu seisu väljastamine
    public void väljastaLaud() {
        System.out.println("__________________________");
        System.out.println("Arvuti algus: " + this.arvutiAlgus);
        System.out.println("Arvuti lõpp: " + this.arvutiLõpp);
        // Päris laud on veel puudu.
        System.out.println(arvutiTee);
        System.out.println(mängijaTee);
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
                mängijaTee.set(silmadeArv-1,mängunupp);
            }
            else if (mängijaTee.contains(mängunupp))  {
                int i = mängijaTee.indexOf(mängunupp);
                mängijaTee.remove(mängunupp);
                mängijaTee.set(i + silmadeArv, mängunupp);
            }
        }
        else {
            if (arvutiAlgus.contains(mängunupp)) {
                arvutiAlgus.remove(mängunupp);
                arvutiTee.set(silmadeArv - 1, mängunupp);
            }
            else if (arvutiTee.contains(mängunupp))  {
                int i = arvutiTee.indexOf(mängunupp);
                arvutiTee.remove(mängunupp);
                arvutiTee.set(i + silmadeArv, mängunupp);
            }
        }
    }

    // Selleks kui vastas nupp astub selle nuppu peale
    public void liigutaAlgusesse(Mängunupp mängunupp) {
        int eemalda = mängijaTee.indexOf(mängunupp);
        if (mängunupp.isMängijaOma()) {
            mängijaTee.set(eemalda, null);
            mängijaAlgus.add(mängunupp);
        }
        else {
            arvutiTee.set(eemalda, null);
            arvutiAlgus.add(mängunupp);
        }
    }

    //Liigutab lõppu, enne peab kontrolli läbima.
    public void liigutaLõppu(Mängunupp mängunupp) {
        int eemalda = mängijaTee.indexOf(mängunupp);
        if (mängunupp.isMängijaOma()) {
            mängijaTee.set(eemalda, null);
            mängijaLõpp.add(mängunupp);
        }
        else {
            arvutiTee.set(eemalda, null);
            arvutiLõpp.add(mängunupp);
        }
    }

    // Iga käigu alguses kontrollib kas igat nuppu saab liigutada.
    // Algne variant, vajab veel lõppu kontrolli, boonus ruudu kontrolli.
    public List<Mängunupp> kontroll(boolean mängijaKäik, int silmadeArv) {
        List<Mängunupp> lubatudNuppud = new ArrayList<>();

        // Selleks et samat koodi ei peaks arvuti ja mängija jaoks kirjutama
        List<Mängunupp> kontrolli1;
        List<Mängunupp> kontrolli2;
        if (mängijaKäik) {
            kontrolli1 = mängijaAlgus;
            kontrolli2 = mängijaTee;
        }
        else {
            kontrolli1 = arvutiAlgus;
            kontrolli2 = arvutiTee;
        }

        //Kontrollib nuppud kas on tühi ruut või et ei ole enda oma
        for (Mängunupp mängunupp: kontrolli1) {
            //Kui ruut kuhu saab käia on tühi või seal asub vaenlase nupp
            if (kontrolli2.get(silmadeArv-1) == null || kontrolli2.get(silmadeArv-1).isMängijaOma()!= mängunupp.isMängijaOma())
                lubatudNuppud.add(mängunupp);
        }
        /*for (Mängunupp mängunupp: kontrolli2) {
            if (kontrolli2.get(silmadeArv-1) == null || kontrolli2.get(silmadeArv-1).isMängijaOma() != mängunupp.isMängijaOma())
                lubatudNuppud.add(mängunupp);
        }
        */
        return lubatudNuppud;
    }
}
