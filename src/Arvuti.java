import java.util.List;

public class Arvuti {
    //Arvuti objekt tegelab kõige parema käigu valimisega mängija vastase jaoks.

    public Mängunupp suvalineKäik(List<Mängunupp> lubatud) {
         int nuppuIndeks = (int)Math.round(Math.random()*lubatud.size());
         return lubatud.get(nuppuIndeks);
    }
}
