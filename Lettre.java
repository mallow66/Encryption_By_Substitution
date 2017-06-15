package EncryptionUtils;

/**
 * Created by brahim on 3/14/17.
 */
public class Lettre  {

    private char lettre;
    private int rang;

    public Lettre(char lettre, int rang) {
        this.lettre = lettre;
        this.rang = rang;
    }

    public char getLettre() {
        return lettre;
    }

    public void setLettre(char lettre) {
        this.lettre = lettre;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lettre lettre1 = (Lettre) o;

        return lettre == lettre1.lettre;

    }

    @Override
    public String toString() {
        if(this == null)
            return "";
        return ""+lettre;
    }
}


