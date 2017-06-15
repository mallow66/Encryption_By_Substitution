package EncryptionUtils.Adfgvx;

import EncryptionUtils.Lettre;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by brahim on 3/21/17.
 */
public class LabsCharacterAdfgvx  {

    private static ArrayList<Lettre> characters;


    public static void fillLabsCharacterAdfgvx(){

        if(characters == null || characters.isEmpty())
            characters = new ArrayList<>();

        characters.add(new Lettre('A',0));
        characters.add(new Lettre('B',1));
        characters.add(new Lettre('C',2));
        characters.add(new Lettre('D',3));
        characters.add(new Lettre('E',4));
        characters.add(new Lettre('F',5));
        characters.add(new Lettre('G',6));
        characters.add(new Lettre('H',7));
        characters.add(new Lettre('I',8));
        characters.add(new Lettre('J',9));
        characters.add(new Lettre('K',10));
        characters.add(new Lettre('L',11));
        characters.add(new Lettre('M',12));
        characters.add(new Lettre('N',13));
        characters.add(new Lettre('O',14));
        characters.add(new Lettre('P',15));
        characters.add(new Lettre('Q',16));
        characters.add(new Lettre('R',17));
        characters.add(new Lettre('S',18));
        characters.add(new Lettre('T',19));
        characters.add(new Lettre('U',20));
        characters.add(new Lettre('V',21));
        characters.add(new Lettre('W',22));
        characters.add(new Lettre('X',23));
        characters.add(new Lettre('Y',24));
        characters.add(new Lettre('Z',25));
        characters.add(new Lettre('0',26));
        characters.add(new Lettre('1',27));
        characters.add(new Lettre('2',28));
        characters.add(new Lettre('3',29));
        characters.add(new Lettre('4',30));
        characters.add(new Lettre('5',31));
        characters.add(new Lettre('6',32));
        characters.add(new Lettre('7',33));
        characters.add(new Lettre('8',34));
        characters.add(new Lettre('9',35));

    }

    public static ArrayList<Lettre> getcharacters() {
        return characters;
    }

    public static Lettre getCharacterFromChar(char c){
        for(Lettre l : characters){
            if(l.equals(new Lettre(c, -1)))
                return l;
        }
        return new Lettre(c, -1);

    }



    public static Lettre getCharacterFromRang(int rang){
        if(rang == -1) return null;

        for(Lettre l : characters)
            if(l.getRang() == rang)
                return l;
        return new Lettre( (char)rang, rang);

    }

    public static void showCharacters(){
        System.out.println("___________________");
        for(Lettre l : characters)
            System.out.print(l);
        System.out.print('\n');
    }

    public static boolean contains(char c){
        if(getCharacterFromChar(c).getRang() != -1)
            return true;
        return false;
    }
}
