import java.util.Scanner;

public class UurMäng {

    public static void main(String[] args) {
        Täring täring = new Täring();

        for (int i = 0; i < 20; i++)
            System.out.println(täring.veereta());

        //mäng
        System.out.println("---------------------------");
        System.out.println("Arvuti algus: "); // + parasjagu nuppe
        System.out.println("Arvuti lõpp: ");
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

    }
}
