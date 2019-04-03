import java.util.List;

public class Arvuti {
    //Praegu arvuti valib suvalise nuppu lubatud käikudest.
    //Hiljem saaks juurde teha mingi kindla strateegia.
    public Mängunupp suvalineKäik(List<Mängunupp> lubatud) {
        if (lubatud.size() == 1)
            return lubatud.get(0);
        else {
            int nuppuIndeks = (int) Math.round(Math.random() * (lubatud.size()-1));
            return lubatud.get(nuppuIndeks);
        }
    }
}
