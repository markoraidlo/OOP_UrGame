public class Mängunupp {
    // Mängunuppu iseloomustab see kas ta omn mängija oma ning mitmes nupp see on.
    private boolean mängijaOma;
    private int nuppuNumber;

    public Mängunupp(boolean mängijaOma, int nuppuNumber) {
        this.mängijaOma = mängijaOma;
        this.nuppuNumber = nuppuNumber;
    }

    public boolean isMängijaOma() {
        return mängijaOma;
    }

    // Mängulaual on mängija nuppu numbriga 1-6, vastase omad lihtsalt X
    @Override
    public String toString() {
        if (mängijaOma) {
            return Integer.toString(nuppuNumber);
        }
        else {
            return "X";
        }
    }
}
