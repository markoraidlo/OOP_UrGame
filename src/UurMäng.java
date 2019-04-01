import java.util.Scanner;

public class UurMäng {

    public static void main(String[] args) {
        //Objektide loomine.
        Täring täring = new Täring();
        Mängulaud mängulaud = new Mängulaud();
        Arvuti arvuti = new Arvuti();

        // Mängu alguses väljastatav tekst.
        System.out.println("Reeglid");

        // Kasutaja loeb reeglid läbi ning siis alustab.
        Scanner scan = new Scanner(System.in);
        System.out.println("Sisesta midagi, et alustada.");
        String enter = scan.next();
        mängulaud.väljastaLaud();

        // Boostile maandus, üldine kontroll.
        while (true) {
            // Kontrollib kas mäng on läbi.
            if (mängulaud.võiduKontroll() != 0)
                break;

            //Mängija käik.
            System.out.println("Sinu käik.");
            int silmadeArv = täring.veereta();
            System.out.println("Täring veeretas: " + silmadeArv);

            //Testin kontrolli
            System.out.println(mängulaud.kontroll(true, silmadeArv));

            System.out.println("Vali nupp mida liigutada: ");
            // Toimub nuppu liigutamine kui seda saab liigutada, kui ei saa siis tuleb uuesti sisestada.
            int sisend = scan.nextInt();
            mängulaud.liiguta(mängulaud.mängijaNuppud.get(sisend-1), silmadeArv);


            // Uus mängu laua väljastus.
            mängulaud.väljastaLaud();

            //test
            mängulaud.liigutaAlgusesse(mängulaud.mängijaNuppud.get(0));
            //

            System.out.println("Arvuti käik.");
            silmadeArv = täring.veereta();
            System.out.println("Täring veeretas: " + silmadeArv);

            //Testin kontrolli
            System.out.println(mängulaud.kontroll(false, silmadeArv));

            // Arvuti teeb oma asjad.
            mängulaud.liiguta(mängulaud.arvutiNuppud.get(arvuti.suvalineKäik()), silmadeArv);
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



__________________________
Arvuti Algus: X X X X X X
Arvuti Lõpp:

[#]  [  ]  [  ]  [  ]                [#]  [  ]
[  ]  [  ]  [  ]  [#]  [  ]  [  ]  [  ]  [  ]
[#]  [  ]  [  ]  [  ]                [#]  [  ]

Mängija Algus: 1 2 3 4 5 6
Mängija Lõpp:
 */
