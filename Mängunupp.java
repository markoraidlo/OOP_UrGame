package OOP_UrGame;


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Mängunupp extends Circle{
    // Mängunuppu iseloomustab see kas ta omn mängija oma ning mitmes nupp see on.
    private boolean mängijaOma;
    private int nuppuNumber;
    private int i;
    private int j;

    public Mängunupp(boolean mängijaOma, int nuppuNumber) {
        this.mängijaOma = mängijaOma;
        this.nuppuNumber = nuppuNumber;
        this.i = nuppuNumber - 1;
        this.setRadius(50);

        if (mängijaOma) {
            this.setFill(Color.TAN);
            this.j = 5;
        }
        else {
            this.setFill(Color.SADDLEBROWN);
            this.j = 0;
        }
    }

    public boolean isMängijaOma() {
        return mängijaOma;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
}
