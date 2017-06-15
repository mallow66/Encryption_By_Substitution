package EncryptionUtils.Affine;

import java.util.ArrayList;

/**
 * Created by brahim on 4/4/17.
 */
public class AffineCoefficient {

    private static ArrayList<Integer> coefficients;



    public  static void fillArray(){
        coefficients = new ArrayList<>();
        coefficients.add(1);
        coefficients.add(3);
        coefficients.add(5);
        coefficients.add(7);
        coefficients.add(9);
        coefficients.add(11);
        coefficients.add(15);
        coefficients.add(17);
        coefficients.add(19);
        coefficients.add(21);
        coefficients.add(23);
        coefficients.add(25);


    }


    public static  boolean isValidCoefficient(int coefficient){
        if(coefficients.contains(new Integer(coefficient)))
            return true;
        return false;
    }
}
