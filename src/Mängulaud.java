import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/*
TO DO list:
Meetod mis väljastaks normaalse laua
Boonus ruut
Täringu veeretus 0 erikord
Eemaldada asjad kus on mitu korda samat asja kasutatud.
 */

public class Mängulaud {
    // Nuppude asukohtade jaoks erinvad listid
    private List<Mängunupp> mängijaAlgus = new ArrayList<>();
    private List<Mängunupp> arvutiAlgus = new ArrayList<>();

    private List<Mängunupp> mängijaLõpp = new ArrayList<>();
    private List<Mängunupp> arvutiLõpp = new ArrayList<>();

    List<Mängunupp> mängijaTee = Arrays.asList(new Mängunupp[14]);
    List<Mängunupp> arvutiTee = Arrays.asList(new Mängunupp[14]);

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
        Laud();
        System.out.println(arvutiTee);
        System.out.println(mängijaTee);
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
        //Mängija nupp või arvuti oma
        List<Mängunupp> algus;
        List<Mängunupp> tee;
        List<Mängunupp> lõpp;
        List<Mängunupp> vastaseTee;
        if (mängunupp.isMängijaOma()) {
            algus = mängijaAlgus;
            tee = mängijaTee;
            lõpp = mängijaLõpp;
            vastaseTee = arvutiTee;
        }
        else {
            algus = arvutiAlgus;
            tee = arvutiTee;
            lõpp = arvutiLõpp;
            vastaseTee = mängijaTee;
        }

        // Algusest teepeale
        if (algus.contains(mängunupp)) {
            algus.remove(mängunupp);
            tee.set(silmadeArv - 1, mängunupp);
        } else if (tee.contains(mängunupp)) {
            // Teepealt lõppu
            if (tee.indexOf(mängunupp) + silmadeArv == 14) {
                tee.set(tee.indexOf(mängunupp), null);
                lõpp.add(mängunupp);
            }
            // Teepeal edasi
            else {
                int i = tee.indexOf(mängunupp);
                tee.set(i, null);
                tee.set(i + silmadeArv, mängunupp);
                // Vastase nuppu hävitamine
                if (vastaseTee.get(i + silmadeArv)!= null && i + silmadeArv > 3 && i + silmadeArv < 12) {
                    hävita(vastaseTee.get(i + silmadeArv));
                }
            }
        }
    }

    // Nuppu liigutamine mängu algusesse
    public void hävita(Mängunupp mängunupp) {
        int eemalda;
        if (mängunupp.isMängijaOma()) {
            eemalda = mängijaTee.indexOf(mängunupp);
            mängijaTee.set(eemalda, null);
            mängijaAlgus.add(mängunupp);
        } else {
            eemalda = arvutiTee.indexOf(mängunupp);
            arvutiTee.set(eemalda, null);
            arvutiAlgus.add(mängunupp);
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
        } else {
            kontrolli1 = arvutiAlgus;
            kontrolli2 = arvutiTee;
        }

        //Kontrollib nuppud kas on tühi ruut või et ei ole enda oma
        for (Mängunupp mängunupp : kontrolli1) {
            //Kui ruut kuhu saab käia on tühi
            if (kontrolli2.get(silmadeArv - 1) == null)
                lubatudNuppud.add(mängunupp);
        }
        for (Mängunupp mängunupp: kontrolli2) {
            int nuppuAsukoht = kontrolli2.indexOf(mängunupp);
            if (mängunupp != null) {
                if (nuppuAsukoht + silmadeArv == 14) {
                    lubatudNuppud.add(mängunupp);
                }
                else if (nuppuAsukoht + silmadeArv < 14) {
                    if (kontrolli2.get(nuppuAsukoht + silmadeArv) == null)
                        lubatudNuppud.add(mängunupp);
                }
            }
        }
        return lubatudNuppud;
    }

    //Mängu laua vormistus.
    public void Laud() {
        List<String> arvutiRida = new ArrayList<>();
        List<String> ühineRida = new ArrayList<>();
        List<String> mängijaRida = new ArrayList<>();

        for (int i = 0; i <= 6; i++) {
            if (arvutiTee.get(i) == null) {
                if (i == 0 || i == 5) {
                    arvutiRida.add("[#]");
                } else {
                    arvutiRida.add("[]");
                }
            } else {
                if (i == 0 || i == 5) {
                    arvutiRida.add("[#" + arvutiTee.get(i).toString() + "]");
                } else {
                    arvutiRida.add("[" + arvutiTee.get(i).toString() + "]");
                }
            }
        }

        for (int j = 0; j <= 6; j++) { // vaja ainult esimest rida, seepärast kuni 6ni
            if (mängijaTee.get(j) == null) {
                if (j == 0 || j == 5) {
                    mängijaRida.add("[#]");
                } else {
                    mängijaRida.add("[]");
                }
            } else {
                if (j == 0 || j == 5) {
                    mängijaRida.add("[#" + mängijaTee.get(j).toString() + "]");
                } else {
                    mängijaRida.add("[" + mängijaTee.get(j).toString() + "]");
                }
            }

            for (int k = 7; k < mängijaTee.size(); k++) { //et alustaks alates 7ndast liikmest vaatamist kuni listi lõpuni
                if (mängijaTee.get(k) == null && arvutiTee.get(k) == null) {
                    if (k == k + 4) {
                        ühineRida.add("[#]");
                    } else {
                        ühineRida.add("[]");
                    }
                }
                if (mängijaTee.get(k) != null && arvutiTee.get(k) == null) {
                    if (k == k + 4) {
                        ühineRida.add("[#" + mängijaTee.get(k).toString() + "]");
                    } else {
                        ühineRida.add("[" + mängijaTee.get(k).toString() + "]");
                    }
                }
                if (mängijaTee.get(k) == null && arvutiTee.get(k) != null) {
                    if (k == k + 4) {
                        ühineRida.add("[#" + arvutiTee.get(k).toString() + "]");
                    } else {
                        ühineRida.add("[" + arvutiTee.get(k).toString() + "]");
                    }
                }
            }
        }
    }
}