package EncryptionUtils;

import java.util.LinkedList;

/**
 * Created by brahim on 3/14/17.
 */
public class  LabsLettre  {

    public static LinkedList<Lettre> lettres;


    public static void fillLabsLettre() {
        lettres = new LinkedList<>();
        lettres.add(new Lettre('A',0));
        lettres.add(new Lettre('B',1));
        lettres.add(new Lettre('C',2));
        lettres.add(new Lettre('D',3));
        lettres.add(new Lettre('E',4));
        lettres.add(new Lettre('F',5));
        lettres.add(new Lettre('G',6));
        lettres.add(new Lettre('H',7));
        lettres.add(new Lettre('I',8));
        lettres.add(new Lettre('J',9));
        lettres.add(new Lettre('K',10));
        lettres.add(new Lettre('L',11));
        lettres.add(new Lettre('M',12));
        lettres.add(new Lettre('N',13));
        lettres.add(new Lettre('O',14));
        lettres.add(new Lettre('P',15));
        lettres.add(new Lettre('Q',16));
        lettres.add(new Lettre('R',17));
        lettres.add(new Lettre('S',18));
        lettres.add(new Lettre('T',19));
        lettres.add(new Lettre('U',20));
        lettres.add(new Lettre('V',21));
        lettres.add(new Lettre('W',22));
        lettres.add(new Lettre('X',23));
        lettres.add(new Lettre('Y',24));
        lettres.add(new Lettre('Z',25));

    }


    public static LinkedList<Lettre> getLettres() {
        return lettres;
    }

    public static void setLettres(LinkedList<Lettre> lettres) {
        LabsLettre.lettres = lettres;
    }

    public static Lettre getLettreFromChar(char c){
        for(Lettre l : lettres){
            if(l.equals(new Lettre(c, -1)))
                return l;
        }
        return new Lettre(c, -1);

    }



    public static Lettre getLettreFromRang(int rang){
        for(Lettre l : lettres)
            if(l.getRang() == rang)
                return l;
        return null;

    }

    public static boolean contains(char c){
        if(getLettreFromChar(c).getRang() != -1)
            return true;
        return false;
    }
}
