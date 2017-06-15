package EncryptionUtils.Vigenere;



import EncryptionUtils.LabsLettre;
import EncryptionUtils.Lettre;
import EncryptionUtils.Utils.FileUtils;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by brahim on 3/15/17.
 */
public class
VigenereEncryption  extends FileUtils{

    private String nameInputFile;
    private String nameOutputFile;
    private static int[][] vigenereTable;
    private String key;


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

    //constructor for encryption;
    public VigenereEncryption(String nameInputFile, String nameOutputFile, String key) throws IOException {
        this.nameInputFile = nameInputFile;
        this.nameOutputFile = nameOutputFile;
        this.key = key;
        fillVigenereTable();
        handleFile(nameInputFile, nameOutputFile);
    }

    //constructor for decryption
    //the object parametre is just to make the difference between the two constructors
    public VigenereEncryption(String nameInputFile, String nameOutputFile, String decryptionKey, Object n) throws IOException{
        this.nameInputFile = nameInputFile;
        this.nameOutputFile = nameOutputFile;
        this.key = decryptionKey;
        fillVigenereTable();
        handleFile2();

    }

    //constructor for using the plateform
    public VigenereEncryption(String nameInputFile) {
        this.nameInputFile = nameInputFile;
        fillVigenereTable();

    }

    public static void fillVigenereTable(){
        vigenereTable = new int[27][27];
        vigenereTable[0][0] = -1;
            int k=0;
        for (int i=1; i<27; i++){
            vigenereTable[0][i] = k;
            k++;
        }
            k = 0;
        for(int i=1; i<27; i++)
            for (int j=0; j<27; j++){
                vigenereTable[i][j] = k % 26;
                k++;
            }

    }

    public static int[][] getVigenereTable() {
        return vigenereTable;
    }

    public static void setVigenereTable(int[][] vigenereTable) {
        VigenereEncryption.vigenereTable = vigenereTable;
    }


    public Lettre encode(char c, char k){
        int r = LabsLettre.getLettreFromChar(c).getRang();
        if(r==-1)
            return new Lettre(c, -1);

        int r2 = LabsLettre.getLettreFromChar(k).getRang();
        System.out.println("("+(r+1)+","+(r2+1)+")");

        return LabsLettre.getLettreFromRang(vigenereTable[r+1][r2+1]);
    }

    public Lettre decode(char c, char k){
        int r = LabsLettre.getLettreFromChar(c).getRang();
        int r2 = LabsLettre.getLettreFromChar(k).getRang();

        for (int i=1; i<27; i++){
            if(vigenereTable[i][r2+1] == r){
                System.out.println(vigenereTable[i][r2+1]);
                return new Lettre(LabsLettre.getLettreFromRang(vigenereTable[i][0]).getLettre(), vigenereTable[i][0]);
            }

        }

            return new Lettre(c, -1);

    }


   /* public void handleFile() throws IOException {

        File fi = new File(nameInputFile);
        InputStream in = new FileInputStream(fi);
        Reader reader = new InputStreamReader(in, Charset.defaultCharset());

        FileWriter fw = new FileWriter(nameOutputFile);
        BufferedWriter bw = new BufferedWriter(fw);
        handleCharacters(reader, bw);
    }
*/

   @Override
    public void handleCharacters(Reader reader, BufferedWriter bufferedWriter) throws IOException{
        int r;
        int m=0;
        while((r = reader.read()) != -1){
            char c = (char) r;
            Lettre out = encode(Character.toUpperCase(c),key.charAt(m % key.length()));
            if(out.getRang() != -1 ) m++;
            if(Character.isLowerCase(c))
                bufferedWriter.append(Character.toLowerCase(out.getLettre()));

            else
                bufferedWriter.append(out.getLettre());


        }
        bufferedWriter.close();

    }


    private void handleFile2() throws  IOException{
        File fi = new File(nameInputFile);
        InputStream in = new FileInputStream(fi);
        Reader reader = new InputStreamReader(in, Charset.defaultCharset());

        FileWriter fw = new FileWriter(nameOutputFile);
        BufferedWriter bw = new BufferedWriter(fw);
        handleCharacters2(reader, bw);
    }

    private void handleCharacters2(Reader reader, BufferedWriter bufferedWriter) throws IOException{
        int r;
        int m=0;
        while((r = reader.read()) != -1){
            char c = (char) r;
            Lettre out = decode(Character.toUpperCase(c),key.charAt(m % key.length()));
            if(out.getRang() != -1 ) m++;
            if(Character.isLowerCase(c))
                bufferedWriter.append(Character.toLowerCase(out.getLettre()));

            else
                bufferedWriter.append(out.getLettre());


        }
        bufferedWriter.close();

    }
}
