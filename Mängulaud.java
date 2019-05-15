package OOP_UrGame;

import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Mängulaud extends BorderPane {
    // Nuppude asukohtade jaoks erinvad listid
    private List<Mängunupp> mängijaAlgus = new ArrayList<>();
    private List<Mängunupp> arvutiAlgus = new ArrayList<>();

    private List<Mängunupp> mängijaLõpp = new ArrayList<>();
    private List<Mängunupp> arvutiLõpp = new ArrayList<>();

    private List<Mängunupp> mängijaTee = Arrays.asList(new Mängunupp[14]);
    private List<Mängunupp> arvutiTee = Arrays.asList(new Mängunupp[14]);

    private List<Mängunupp> mängijaNuppud = new ArrayList<>();
    private List<Mängunupp> arvutiNuppud = new ArrayList<>();

    public List<Mängunupp> getMängijaAlgus() {
        return mängijaAlgus;
    }

    public List<Mängunupp> getArvutiAlgus() {
        return arvutiAlgus;
    }

    public List<Mängunupp> getMängijaTee() {
        return mängijaTee;
    }

    public List<Mängunupp> getArvutiTee() {
        return arvutiTee;
    }

    public List<Mängunupp> getMängijaNuppud() {
        return mängijaNuppud;
    }

    public List<Mängunupp> getArvutiNuppud() {
        return arvutiNuppud;
    }

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
    public List<Mängunupp> kontroll(int silmadeArv,List<Mängunupp> kontrolli1,List<Mängunupp> kontrolli2,List<Mängunupp> vastane) {
        List<Mängunupp> lubatudNuppud = new ArrayList<>();

        //Kontrollib nuppud kas on tühi ruut või et ei ole enda oma
        for (Mängunupp mängunupp : kontrolli1) {
            //Kui ruut kuhu saab käia on tühi
            if (kontrolli2.get(silmadeArv - 1) == null)
                lubatudNuppud.add(mängunupp);
        }
        for (Mängunupp mängunupp: kontrolli2) {
            int nuppuAsukoht = kontrolli2.indexOf(mängunupp);
            if (mängunupp != null) {
                //Kui boonus ruudul on vastane, siis sinna käia ei saa.
                if ((silmadeArv + nuppuAsukoht) == 7 && vastane.get(7) != null)
                    continue;

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

    // Mängu seisu väljastamine
    public void väljastaLaud() {
        System.out.println("__________________________");
        System.out.println("Arvuti algus: " + this.arvutiAlgus);
        System.out.println("Arvuti lõpp: " + this.arvutiLõpp);
        System.out.println(laud());
        System.out.println("Mängija algus: " + this.mängijaAlgus);
        System.out.println("Mängija lõpp: " + this.mängijaLõpp);
        System.out.println("__________________________");
    }

    public String tagastaLaud() {
        return  this.arvutiAlgus.toString() + this.arvutiLõpp.toString()  +
                this.laud() +
                this.mängijaAlgus.toString() + this.mängijaLõpp.toString();
    }

    // Teeb esimese või kolmanda mängulaua rea
    public List<String> vormistaRida(List<String> rida, List<Mängunupp> tee) {
        for (int i = 0; i < rida.size(); i++) {
            switch (i) {
                case 0:
                    if (tee.get(3) != null)
                        rida.set(i,"[#" + tee.get(3) + "]");
                    else
                        rida.set(i,"[#]");
                    break;
                case 1:
                    if (tee.get(2) != null)
                        rida.set(i,"[" + tee.get(2) + "]");
                    else
                        rida.set(i,"[]");
                    break;
                case 2:
                    if (tee.get(1) != null)
                        rida.set(i,"[" + tee.get(1) + "]");
                    else
                        rida.set(i,"[]");
                    break;
                case 3:
                    if (tee.get(0) != null)
                        rida.set(i,"[" + tee.get(0) + "]");
                    else
                        rida.set(i,"[]");
                    break;
                case 4:
                    rida.set(i, "     ");
                    break;
                case 5:
                    if (tee.get(13) != null)
                        rida.set(i,"[#" + tee.get(13) + "]");
                    else
                        rida.set(i,"[#]");
                    break;
                case 6:
                    if (tee.get(12) != null)
                        rida.set(i,"[" + tee.get(12) + "]");
                    else
                        rida.set(i,"[]");
                    break;
            }
        }
        return rida;
    }

    //Mängu laua vormistus.
    public String laud() {
        List<String> arvutiRida = Arrays.asList(new String[7]);
        List<String> ühineRida = new ArrayList<>();
        List<String> mängijaRida = Arrays.asList(new String[7]);

        arvutiRida = vormistaRida(arvutiRida,arvutiTee);
        mängijaRida = vormistaRida(mängijaRida,mängijaTee);

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

        return arvutiRida.toString() + "\n" + ühineRida.toString() + "\n" + mängijaRida.toString() + "\n";
    }
}