
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class UurMäng {

    public static void main(String[] args) throws Exception {
        //Objektide loomine.
        Täring täring = new Täring();
        Mängulaud mängulaud = new Mängulaud();
        Arvuti arvuti = new Arvuti();
        List<Mängunupp> lubatud;
        int silmadeArv;
        int liigutatudNuppuAsukoht;

        // Mängu alguses väljastatav tekst.
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.println("Mängureeglid:");
        System.out.println("Mängu eesmärk on viia enda 6 nuppu esimesena mängu lõppu.");
        System.out.println("Iga käigu alguses visatakse 4 täringut, millel kõigil on 50/50 võimalus tagastada 1 või 0");
        System.out.println("Mängija valib nupu, mida soovib liigutada ning liigutab seda vastav arvu ruute(0-4)");
        System.out.println("Kui mängija nupule, mis asub mänguväljakul maandub peale vastase nupp, siis läheb nupp algusesse tagasi.");
        System.out.println("Boonusruudud on tähistatud #-ga. Sellele maandudes saab uuesti täringuid veeretada. Kui su nupp on boonusruudul");
        System.out.println(",siis on see kaitsud vaenlase rünnakute eest.");
        System.out.println("Selleks, et mänguväljalt lahkuda, pead veeretama täpse arvu silmasid, kui veeretasid rohkem, siis käia ei saa");
        //Mängulaua õpetus
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.println("Programmi eeldus: mängija alati valib ühe nuppudest mida võib liigutada.");
        System.out.println("----------------------------------------------------------------------------------------------------------------");

        // Kasutaja loeb reeglid läbi ning siis alustab.
        Scanner scan = new Scanner(System.in);
        System.out.println("Sisesta midagi, et alustada.");
        String suvalineSisend = scan.next();
        mängulaud.väljastaLaud();
        TimeUnit.SECONDS.sleep(1);


        //Peamine mängu loop.
        while (true) {
            // Kontrollib kas mäng on läbi. Enne kasutaja käiku.
            if (mängulaud.võiduKontroll() != 0)
                break;

            //Mängija käik.
            while (true) {
                System.out.println("Sinu käik.");
                silmadeArv = täring.veereta();
                System.out.println("Täring veeretas: " + silmadeArv);

                //Vastase käik kui täring veeretas 0
                if (silmadeArv == 0) {
                    System.out.println("Sa jääd vahele.");
                    TimeUnit.SECONDS.sleep(1);
                    break;
                }

                //Kontroll(Mis nuppudega võib käia)
                lubatud = mängulaud.kontroll(silmadeArv,mängulaud.getMängijaAlgus(),mängulaud.getMängijaTee(),mängulaud.getArvutiTee());
                System.out.println("Mängija võimalikud käigud" + lubatud);
                if (lubatud.size() == 0) {
                    System.out.println("Sinul pole võimalik käia.");
                    TimeUnit.SECONDS.sleep(1);
                    break;
                }

                System.out.println("Vali nupp, mida liigutada: ");
                // Toimub nuppu liigutamine kui seda saab liigutada, kui ei saa siis tuleb uuesti sisestada.
                int sisend = scan.nextInt();
                mängulaud.liiguta(mängulaud.getMängijaNuppud().get(sisend - 1), silmadeArv);

                // Uus mängu laua väljastus.
                TimeUnit.SECONDS.sleep(2);
                mängulaud.väljastaLaud();

                //Boonus ruudu kontroll
                liigutatudNuppuAsukoht = mängulaud.getMängijaTee().indexOf(mängulaud.getMängijaNuppud().get(sisend - 1));
                if (liigutatudNuppuAsukoht == 3 || liigutatudNuppuAsukoht == 7 ||liigutatudNuppuAsukoht == 13) {
                    System.out.println("Astusid boonus ruudule.");
                    continue;
                }
                else
                    break;
            }


            //Kontroll enne arvuti käiku
            if (mängulaud.võiduKontroll() != 0)
                break;

            //Arvuti käik
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Arvuti käik.");

            while (true) {
                silmadeArv = täring.veereta();
                System.out.println("Täring veeretas: " + silmadeArv);
                if (silmadeArv == 0) {
                    System.out.println("Arvuti jääb vahele.");
                    break;
                }

                //Kontroll(Mis nuppudega võib käia)
                lubatud = mängulaud.kontroll(silmadeArv,mängulaud.getArvutiAlgus(),mängulaud.getArvutiTee(),mängulaud.getMängijaTee());
                if (lubatud.size() == 0) {
                    System.out.println("Arvutil pole võimalik käia.");
                    break;
                }

                // Arvuti teeb oma asjad.
                Mängunupp arvutiKäik = arvuti.suvalineKäik(lubatud);
                mängulaud.liiguta(arvutiKäik, silmadeArv);
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Arvuti tegi oma käigu");
                mängulaud.väljastaLaud();

                //Boonus ruudu kontroll
                liigutatudNuppuAsukoht = mängulaud.getArvutiTee().indexOf(arvutiKäik);
                if (liigutatudNuppuAsukoht == 3 || liigutatudNuppuAsukoht == 7 ||liigutatudNuppuAsukoht == 13) {
                    System.out.println("Arvuti astus boonusruudule.");
                    continue;
                }
                else
                    break;
            }

            //Siit läheb loop algusesse tagasi
            //break;
        }

        //Mängu lõppus väljastatav tekst.
        System.out.println("Mäng on läbi");
        TimeUnit.SECONDS.sleep(1);
        if (mängulaud.võiduKontroll() == 1) {
            System.out.println("Arvuti võitis!");
        }
        if (mängulaud.võiduKontroll() == 2) {
            System.out.println("Mängija võitis!");
        }
    }
}


