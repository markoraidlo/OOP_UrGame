
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class UurMäng {

    public static void main(String[] args) throws Exception {
        //Objektide loomine.
        Täring täring = new Täring();
        Mängulaud mängulaud = new Mängulaud();
        Arvuti arvuti = new Arvuti();

        List<Mängunupp> lubatud = new ArrayList<>();

        // Mängu alguses väljastatav tekst.
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.println("Mängu reeglid:");
        System.out.println("Mängu eesmärk on viia enda 6 nuppu esimesena mängu lõppu.");
        System.out.println("Käigu alguses visatakse 4 täringut, mille igal on 50/50 võimalus tagastada 1 või 0");
        System.out.println("Mängija valib nuppu mida soovib liigutada ning liigutab seda vastav arvu ruute(0-4)");
        System.out.println("Kui mängija nuppule, mis asub mänguväljakul maandub peale vastase nupp, siis läheb nupp algusesse tagasi.");
        System.out.println("Boonusruudud on tähistatud #-ga. Sellele maandudes saab uuesti täringuid veeretada. Kui su nupp on boonus");
        System.out.println(",siis on see kaitsud vaenlase rünnakute eest.");
        System.out.println("Selleks, et mänguväljalt lahkuda, pead veeretama täpse arvu silmasid, kui veeretasid rohkem, siis käia ei saa");
        System.out.println("----------------------------------------------------------------------------------------------------------------");


        // Kasutaja loeb reeglid läbi ning siis alustab.
        Scanner scan = new Scanner(System.in);
        System.out.println("Sisesta midagi, et alustada.");
        String enter = scan.next();
        mängulaud.väljastaLaud();


        // Boostile maandus, üldine kontroll.
        while (true) {
            //TimeUnit.SECONDS.sleep(5);

            // Kontrollib kas mäng on läbi.
            if (mängulaud.võiduKontroll() != 0)
                break;

            //Mängija käik.
            System.out.println("Sinu käik.");
            int silmadeArv = täring.veereta();
            System.out.println("Täring veeretas: " + silmadeArv);

            //Testin kontrolli
            lubatud = mängulaud.kontroll(true, silmadeArv);
            System.out.println(lubatud);

            System.out.println("Vali nupp mida liigutada: ");
            // Toimub nuppu liigutamine kui seda saab liigutada, kui ei saa siis tuleb uuesti sisestada.
            int sisend = scan.nextInt();
            mängulaud.liiguta(mängulaud.mängijaNuppud.get(sisend - 1), silmadeArv);


            // Uus mängu laua väljastus.
            mängulaud.väljastaLaud();

            //test
            mängulaud.liigutaAlgusesse(mängulaud.mängijaNuppud.get(0));
            //


            System.out.println("Arvuti käik.");
            silmadeArv = täring.veereta();
            System.out.println("Täring veeretas: " + silmadeArv);


            //Testin kontrolli
            lubatud = mängulaud.kontroll(false, silmadeArv);
            System.out.println(lubatud);

            // Arvuti teeb oma asjad.
            mängulaud.liiguta(arvuti.suvalineKäik(lubatud), silmadeArv);
            System.out.println("Arvuti tegi oma käigu");

            mängulaud.väljastaLaud();

            //Siit läheks loop algusesse tagasi
            break;
        }

        //Mängu kõppus väljastatav tekst.
        System.out.println("Mäng on läbi");
        if (mängulaud.võiduKontroll() == 1) {
            System.out.println("Arvuti võitis!");
        }
        if (mängulaud.võiduKontroll() == 2) {
            System.out.println("Mängija võitis!");
        }
    }
}
/*
        for (int i = 0; i < 20; i++)
            System.out.println(täring.veereta());

        //mäng
        System.out.println("---------------------------");
        System.out.println("Arvuti algus: "); // + parasjagu nuppe
        System.out.println("Arvuti lõpp: ");
        Mängulaud.Algne_laud();
        System.out.println("Mängija algus: "); // + parasjagu nuppe
        System.out.println("Mängija lõpp: ");
        System.out.println("---------------------------");

        System.out.println("Vajuta ENTER, et alustada: ");
        Scanner keyboard = new Scanner(System.in);
        keyboard.nextLine();
        System.out.println("Sinu käik.");
        System.out.println("Täring veeretas: " + täring.veereta());
        System.out.println("Vali nupp, mida liigutada: "); //klaviatuurilt lugemine?
        Scanner scanner = new Scanner(System.in);
        int nupp = scanner.nextInt();
        System.out.println("Valisid nupu: " + nupp);

'/
    }
}

/*



__________________________
Arvuti Algus: X X X X X X
Arvuti Lõpp:

[#]  [  ]  [  ]  [  ]                [#]  [  ]
[  ]  [  ]  [  ]  [#]  [  ]  [  ]  [  ]  [  ]
[#]  [  ]  [  ]  [  ]                [#]  [  ]

Mängija Algus: 1 2 3 4 5 6
Mängija Lõpp:
 */
