package EncryptionUtils;

import EncryptionUtils.Adfgvx.AdfgvxEncryption;
import EncryptionUtils.Adfgvx.CoupleLetrre;
import EncryptionUtils.Affine.AffineCoefficient;
import EncryptionUtils.Cesar.CesarEncryption;
import EncryptionUtils.Vigenere.VigenereEncryption;
import javafx.concurrent.Task;

import java.io.IOException;
import java.math.BigInteger;

/**
 * Created by brahim on 3/21/17.
 */
public class Test {
    public static boolean checkForUnique(String str){
        boolean containsUnique = false;

        for(char c : str.toCharArray()){
            if(str.indexOf(c) == str.lastIndexOf(c)){
                containsUnique = true;
            } else {
                containsUnique = false;
            }
        }

        return containsUnique;
    }

    public static void main(String[] args) throws IOException {

//        AdfgvxEncryption e = new AdfgvxEncryption("v1.txt", "final.txt", "BRAHIM");
  //      e.handleFile(e.getNameInputFile(), e.getnameInterFile());
    //    e.handleFile2();


       /* System.out.println("Sort a String ");
        System.out.println(AdfgvxEncryption.sortKey("BRAHIM"));
        System.out.println("DECODER___________________");

        e.decode1("BRAHIM");
        e.transformToOriginaleTabble("BRAHIM");
        System.out.println("èèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèèè");
        e.showTranspositionTable();*/

       //AdfgvxEncryption e2 = new AdfgvxEncryption("final.txt","v2.txt", "decrypted.txt", "BRAHIM", "BRAHIM");
        //e2.showTranspositionTable();

        /*boolean f = checkForUnique("BRAHI");
        if(f)
            System.out.println("Contient des caracteres uniques");
        else
            System.out.println("Contient des caracteres redondant");

        LabsLettre.fillLabsLettre();
        System.out.println("CESAR ENCRYPTION ---------------");
        CesarEncryption e = new CesarEncryption("C1.txt", "C2.txt", 1);
        e.handleFile(e.getNameInputFile(), e.getNameOutputFile()); */


        //System.out.println(Character.toUpperCase('é'));
       // System.out.println(Integer.MAX_VALUE / 25);
        System.out.println(Integer.MAX_VALUE );
        System.out.println(Integer.MAX_VALUE - 25);


        String str = "brahim 2serghini";
        System.out.println(str.toUpperCase());



    }




}
