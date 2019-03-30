// Tagastab arvu 0-4, kus kõige tõenäosem väärtus on 2, siis 1 ja 3, ning vähem tõenäoseim 0 ja 4.
public class Täring {
    public int veereta() {
        int silmadeArv = 0;
        for (int i = 0; i < 4; i++) {
            if (Math.random() > 0.5)
                silmadeArv++;
        }
        return silmadeArv;
    }
}
