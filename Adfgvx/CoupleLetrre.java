package EncryptionUtils.Adfgvx;

import EncryptionUtils.Lettre;

/**
 * Created by brahim on 3/21/17.
 */
public class CoupleLetrre {

    private Lettre lettre1, lettre2;

    public CoupleLetrre(Lettre lettre1, Lettre lettre2) {
        this.lettre1 = lettre1;
        this.lettre2 = lettre2;
    }

    public CoupleLetrre(){
    }

    public Lettre getLettre1() {
        return lettre1;
    }

    public void setLettre1(Lettre lettre1) {
        this.lettre1 = lettre1;
    }

    public Lettre getLettre2() {
        return lettre2;
    }

    public void setLettre2(Lettre lettre2) {
        this.lettre2 = lettre2;
    }

    @Override
    public String toString() {
        return lettre1.toString()+lettre2.toString();
    }

    public boolean isAbletoBeCode(){
        if(lettre1.getRang() ==-1 || lettre2.getRang() == -1)
            return false;
        return true;
    }
}
