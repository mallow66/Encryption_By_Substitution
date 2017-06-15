package EncryptionUtils.Affine;

import java.io.*;
import java.nio.charset.Charset;


import EncryptionUtils.LabsLettre;
import EncryptionUtils.Lettre;
import EncryptionUtils.Utils.FileUtils;

/**
 * Created by brahim on 3/14/17.
 */
public class AffineEncryption extends FileUtils {

    private String nameInputFile;
    private String nameOutputFile;
    private int key1, key2;  // key1 * x + key 2 (mod) 26

    private int decryptKey1, decryptKey2;


    public String getNameInputFile() {
        return nameInputFile;
    }

    public void setNameInputFile(String nameInputFile) {
        this.nameInputFile = nameInputFile;
    }

    public String getNameOutputFile() {
        return nameOutputFile;
    }

    public void setNameOutputFile(String nameOutputFile) {
        this.nameOutputFile = nameOutputFile;
    }

    public AffineEncryption(String nameInputFile, String nameOutputFile, int key1, int key2) {
        this.nameInputFile = nameInputFile;
        this.nameOutputFile = nameOutputFile;
        this.key1 = key1;
        this.key2 = key2;
       LabsLettre.fillLabsLettre();
    }




    public AffineEncryption(){

    }

    public char encode(char c){

        Lettre l1 = LabsLettre.getLettreFromChar(c);
        int a = ((l1.getRang()* key1) % 26) + key2;
        int modulo = a % 26;
        if(l1.getRang()== -1)
            return c;


        return   LabsLettre.getLettres().get(modulo).getLettre();
    }

    public char decode(char c){

        Lettre l = LabsLettre.getLettreFromChar(c);
        if(l.getRang() == -1)
            return c;
        int a = ((decryptKey1* l.getRang()) % 26)+ decryptKey2;
        int modulo = a % 26;
        return LabsLettre.getLettres().get(modulo).getLettre();
    }


    private void findDecyptKey1(){
        decryptKey1 = -1;
        System.out.println("I'm on find decryptKey 1 !!");
        for(int i=1; i<26;i++){
            if((key1*i)%26 == 1){
                decryptKey1 = i;
                System.out.println("I found the decryptKey1 :  "+ i);
            }

        }

    }

    private void findDecryptKey2(){
        System.out.println("I'm on find decryptKey 2");
        if(decryptKey1 !=-1){
            decryptKey2 = (decryptKey1 * (26 - (key2%26))) % 26;

            System.out.println("I found the decryptKey2 : "+ decryptKey2  );
        }

        else{
            System.out.println("I didn't find the decryptKey2");
            decryptKey1 = 1;
            decryptKey2 = 0;
        }
    }





    @Override
    public  void  handleCharacters(Reader reader, BufferedWriter bufferedWriter) throws IOException{
        int r;
        while((r = reader.read()) != -1){
            char c = (char) r;
            char out = encode(Character.toUpperCase(c));
            if(Character.isLowerCase(c))
                bufferedWriter.append(Character.toLowerCase(out));

            else
                bufferedWriter.append(out);


        }
        bufferedWriter.close();


    }


    public void handleFile2() throws IOException {
        findDecyptKey1();
        findDecryptKey2();
        File fi = new File(nameInputFile);
        InputStream in = new FileInputStream(fi);
        Reader reader = new InputStreamReader(in, Charset.defaultCharset());

        FileWriter fw = new FileWriter(nameOutputFile);
        BufferedWriter bw = new BufferedWriter(fw);
        handleCharacters2 (reader, bw);

    }


    public void handleCharacters2( Reader reader, BufferedWriter bufferedWriter) throws IOException{

        int r;
        while( (r = reader.read()) != -1){
            char c = (char) r;
            char out = decode(Character.toUpperCase(c));
            if(Character.isLowerCase(c))
                bufferedWriter.append(Character.toLowerCase(out));

            else
                bufferedWriter.append(out);

        }
        bufferedWriter.close();

    }
}
