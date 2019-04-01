public class Mängunupp {

    private boolean mängijaOma;
    private int nuppuNumber;

    public Mängunupp(boolean mängijaOma, int nuppuNumber) {
        this.mängijaOma = mängijaOma;
        this.nuppuNumber = nuppuNumber;
    }

    public boolean isMängijaOma() {
        return mängijaOma;
    }

    public int getNuppuNumber() {
        return nuppuNumber;
    }

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
