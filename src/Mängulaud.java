import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/*
TO DO list:
Lõpeta kontrollimis meetod
Meetod mis väljastaks normaalse laua
Boonus kontroll
Boonus ruudul uus täringu veeretus
Täringu veeretus 0 erikord
Ilustada koodi ning eemaldada asjad kus on mitu korda samat asja kasutatud.
Uus skännerite süsteem
 */

public class Mängulaud {
    // Nuppude asukohtade jaoks erinvad listid
    private List<Mängunupp> mängijaAlgus = new ArrayList<>();
    private List<Mängunupp> arvutiAlgus = new ArrayList<>();

    private List<Mängunupp> mängijaLõpp = new ArrayList<>();
    private List<Mängunupp> arvutiLõpp = new ArrayList<>();

    List<Mängunupp> mängijaTee = Arrays.asList(new Mängunupp[14]);
    List<Mängunupp> arvutiTee = Arrays.asList(new Mängunupp[14]);

    List<String> ühekohaline = new ArrayList<>();

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
        Laud();
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
        //Mängija nupp
        if (mängunupp.isMängijaOma()) {
            // Algusest teepeale
            if (mängijaAlgus.contains(mängunupp)) {
                mängijaAlgus.remove(mängunupp);
                mängijaTee.set(silmadeArv - 1, mängunupp);
            } else if (mängijaTee.contains(mängunupp)) {
                // Teepealt lõppu
                if (mängijaTee.indexOf(mängunupp) + silmadeArv == 14) {
                    mängijaTee.set(mängijaTee.indexOf(mängunupp), null);
                    mängijaLõpp.add(mängunupp);
                }
                // Teepeal edasi
                else {
                    int i = mängijaTee.indexOf(mängunupp);
                    mängijaTee.remove(mängunupp);
                    mängijaTee.set(i + silmadeArv, mängunupp);
                }
            }
        }
        //Arvuti nupp
        else {
            if (arvutiAlgus.contains(mängunupp)) {
                arvutiAlgus.remove(mängunupp);
                arvutiTee.set(silmadeArv - 1, mängunupp);
            } else if (arvutiTee.contains(mängunupp)) {
                // Teepealt lõppu
                if (arvutiTee.indexOf(mängunupp) + silmadeArv == 14) {
                    arvutiTee.set(arvutiTee.indexOf(mängunupp), null);
                    arvutiLõpp.add(mängunupp);
                }
                // Teepeal edasi
                else {
                    int i = arvutiTee.indexOf(mängunupp);
                    arvutiTee.remove(mängunupp);
                    arvutiTee.set(i + silmadeArv, mängunupp);
                }
            }
        }
    }

    // Selleks kui vastas nupp astub selle nuppu peale
    public void liigutaAlgusesse(Mängunupp mängunupp) {
        int eemalda = mängijaTee.indexOf(mängunupp);
        if (mängunupp.isMängijaOma()) {
            mängijaTee.set(eemalda, null);
            mängijaAlgus.add(mängunupp);
        } else {
            arvutiTee.set(eemalda, null);
            arvutiAlgus.add(mängunupp);
        }
    }

    /*Liigutab lõppu, enne peab kontrolli läbima.
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
    */

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
            //Kui ruut kuhu saab käia on tühi või seal asub vaenlase nupp
            if (kontrolli2.get(silmadeArv - 1) == null || kontrolli2.get(silmadeArv - 1).isMängijaOma() != mängunupp.isMängijaOma())
                lubatudNuppud.add(mängunupp);
        }
        /*for (Mängunupp mängunupp: kontrolli2) {
            if (kontrolli2.get(silmadeArv-1) == null || kontrolli2.get(silmadeArv-1).isMängijaOma() != mängunupp.isMängijaOma())
                lubatudNuppud.add(mängunupp);
        }
        */
        return lubatudNuppud;
    }


    public void Laud() {
        List<String> arvutiRida = Arrays.asList(new String[7]);
        List<String> ühineRida = new ArrayList<>();
        List<String> mängijaRida = Arrays.asList(new String[7]);

        for (int i = 0; i < mängijaTee.size(); i++) {
            if (arvutiTee.get(i) == null) {
                if (i == 3) {
                    arvutiRida.set(0, "[#]");
                }
                if (i == 13) {
                    arvutiRida.set(5, "[#]");
                }
                if (i == 14) {
                    arvutiRida.set(4, "    ");
                }
                else {
                    arvutiRida.set(i, "[]");
                }
            } else {
                if (i == 3) {
                    arvutiRida.set(0, "[#" + arvutiTee.get(i).toString() + "]");
                }
                if (i == 13) {
                    arvutiRida.set(5, "[#" + arvutiTee.get(i).toString() + "]");
                } else {
                    if (i == 0) {
                        arvutiRida.set(3, "[" + arvutiTee.get(i).toString() + "]");
                    }
                    if (i == 1) {
                        arvutiRida.set(2, "[" + arvutiTee.get(i).toString() + "]");
                    }
                    if (i == 2) {
                        arvutiRida.set(1, "[" + arvutiTee.get(i).toString() + "]");
                    }
                    if (i == 12) {
                        arvutiRida.set(6, "[" + arvutiTee.get(i).toString() + "]");
                    }
                }
            }
        }

        for (int j = 0; j < arvutiTee.size(); j++) { // vaja ainult esimest rida, seepärast kuni 6ni
            if (mängijaTee.get(j) == null) {
                if (j == 3) {
                    mängijaRida.set(0, "[#]");
                }
                if (j == 13) {
                    mängijaRida.set(5, "[#]");
                }
                if (j == 14) {
                    mängijaRida.set(4, "    ");
                }
                else {
                    mängijaRida.set(j, "[]");
                }
            } else {
                if (j == 3) {
                    mängijaRida.set(0, "[#" + mängijaRida.get(j).toString() + "]");
                }
                if (j == 13) {
                    arvutiRida.set(5, "[#" + arvutiTee.get(i).toString() + "]");
                } else {
                    if (j == 0) {
                        mängijaRida.set(3, "[" + mängijaTee.get(j).toString() + "]");
                    }
                    if (j == 1) {
                        mängijaRida.set(2, "[" + mängijaTee.get(j).toString() + "]");
                    }
                    if (j == 2) {
                        mängijaRida.set(1, "[" + mängijaTee.get(j).toString() + "]");
                    }
                    if (j == 12) {
                        mängijaRida.set(6, "[" + mängijaTee.get(j).toString() + "]");
                    }
                }
            }

            for (int k = 4; k < 12; k++) { //et alustaks alates 7ndast liikmest vaatamist kuni listi lõpuni
                if (mängijaTee.get(k) == null && arvutiTee.get(k) == null) {
                    if (k == 7) {
                        ühineRida.add("[#]");
                    } else {
                        ühineRida.add("[]");
                    }
                }
                if (mängijaTee.get(k) != null && arvutiTee.get(k) == null) {
                    if (k == 7) {
                        ühineRida.add("[#" + mängijaTee.get(k).toString() + "]");
                    } else {
                        ühineRida.add("[" + mängijaTee.get(k).toString() + "]");
                    }
                }
                if (mängijaTee.get(k) == null && arvutiTee.get(k) != null) {
                    if (k == 7) {
                        ühineRida.add("[#" + arvutiTee.get(k).toString() + "]");
                    } else {
                        ühineRida.add("[" + arvutiTee.get(k).toString() + "]");
                    }
                }
            }
        }
    }
}

   /* public static void Algne_laud() {
        List<String> terve_laud = new ArrayList<>();

        terve_laud.addAll(Arrays.asList("[#]", "[ ]", "[ ]", "[ ]", "[#]", "[ ]", "[ ]", "[ ]", "[ ]", "[ ]", "[ ]", "[ ]", "[ ]", "[ ]", "[#]", "[ ]", "[ ]", "[ ]", "[#]", "[ ]"));

        List<String> esimene_rida = terve_laud.subList(0,6);
        for(String element: esimene_rida) {
            System.out.print(element);
        }
        System.out.println();
        List<String> teine_rida = terve_laud.subList(6,14);
        for(String element2: teine_rida) {
            System.out.print(element2);
        }
        System.out.println();
        List<String> kolmas_rida = terve_laud.subList(14,20);
        for(String element3: kolmas_rida) {
            System.out.print(element3);
        }
        System.out.println();*/