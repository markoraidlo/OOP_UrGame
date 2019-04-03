public class Mängunupp {

    private boolean mängijaOma;
    private int nupuNumber;

    public Mängunupp(boolean mängijaOma, int nupuNumber) {
        this.mängijaOma = mängijaOma;
        this.nupuNumber = nupuNumber;
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
