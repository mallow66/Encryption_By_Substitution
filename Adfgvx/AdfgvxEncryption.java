package EncryptionUtils.Adfgvx;

import EncryptionUtils.Lettre;
import EncryptionUtils.Utils.FileUtils;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by brahim on 3/21/17.
 */
public class AdfgvxEncryption extends FileUtils {

    private static char[][] tabAdfgvx;
    private static char[][] transpositionTab;
    private String key;
    private String nameInputFile;
    private String nameInterFile;
    private String nameOutputFile;
    private int messageLength;
    private int t, t2;  // number of lines to allocate for transposition tab

    private static  String nameEncryptedFile;
    private static String nameDecryptedFile;
    private static String decryptionKey;

    public static char[][] getTranspositionTab() {
        return transpositionTab;
    }

    public static void setTranspositionTab(char[][] transpositionTab) {
        AdfgvxEncryption.transpositionTab = transpositionTab;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNameInterFile() {
        return nameInterFile;
    }

    public void setNameInterFile(String nameInterFile) {
        this.nameInterFile = nameInterFile;
    }

    public String getNameOutputFile() {
        return nameOutputFile;
    }

    public void setNameOutputFile(String nameOutputFile) {
        this.nameOutputFile = nameOutputFile;
    }

    public int getMessageLength() {
        return messageLength;
    }

    public void setMessageLength(int messageLength) {
        this.messageLength = messageLength;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public int getT2() {
        return t2;
    }

    public void setT2(int t2) {
        this.t2 = t2;
    }

    public static String getNameEncryptedFile() {
        return nameEncryptedFile;
    }

    public static void setNameEncryptedFile(String nameEncryptedFile) {
        AdfgvxEncryption.nameEncryptedFile = nameEncryptedFile;
    }

    public static String getNameDecryptedFile() {
        return nameDecryptedFile;
    }

    public static void setNameDecryptedFile(String nameDecryptedFile) {
        AdfgvxEncryption.nameDecryptedFile = nameDecryptedFile;
    }

    public static String getDecryptionKey() {
        return decryptionKey;
    }

    public static void setDecryptionKey(String decryptionKey) {
        AdfgvxEncryption.decryptionKey = decryptionKey;
    }
    // Construction for encryption ::

    public AdfgvxEncryption( String nameInputFile, String nameOutputFile, String key) throws IOException{
        newInstance();
        showTableAdfgvx();
        messageLength = 0; 
        this.nameOutputFile = nameOutputFile;
        this.key = key;
        this.nameInputFile = nameInputFile;
        this.nameInterFile = "interfile.txt";
        this.t = 0;
        handleFile(nameInputFile, nameInterFile);
        handleFile2();

    }

    // Construction for decryption ::


    public AdfgvxEncryption(String nameEncryptedFile, String nameDecryptedFile, String decryptionKey, char[][] tab) throws IOException{
        this.nameEncryptedFile = nameEncryptedFile;
        this.nameDecryptedFile = nameDecryptedFile;
        this.decryptionKey = decryptionKey;
        this.t2 = 0;
        this.nameInterFile = "interfile.txt";
        tabAdfgvx = tab;
        decode1();
        //transformToOriginaleTabble();
        decode2();
    }

    public static char[][] getTabAdfgvx() {
        return tabAdfgvx;
    }

    public static void setTabAdfgvx(char[][] tabAdfgvx) {
        AdfgvxEncryption.tabAdfgvx = tabAdfgvx;
    }

    public AdfgvxEncryption(){
        
    }

    public static void newInstance(){
        tabAdfgvx = new char[7][7];
       // LabsCharacterAdfgvx.fillLabsCharacterAdfgvx();

        //fill the name of the A D F G V X characters in the adfgvx table
        tabAdfgvx[0][0] = ' '; // empty case
        tabAdfgvx[0][1] = 'A'; // the range of A is 0
        tabAdfgvx[0][2] = 'D'; // the range of D is 3
        tabAdfgvx[0][3] = 'F'; // the range of F is 5
        tabAdfgvx[0][4] = 'G'; // the range of G is 6
        tabAdfgvx[0][5] = 'V';// the range of V is 21
        tabAdfgvx[0][6] = 'X'; // the range of X is 23

        tabAdfgvx[1][0] = 'A';
        tabAdfgvx[2][0] = 'D';
        tabAdfgvx[3][0] = 'F';
        tabAdfgvx[4][0] = 'G';
        tabAdfgvx[5][0] = 'V';
        tabAdfgvx[6][0] = 'X';


        for(int i = 1; i<7; i++ ){
            for(int j=1; j<7; j++){
                Collections.shuffle(LabsCharacterAdfgvx.getcharacters());
                tabAdfgvx[i][j] = LabsCharacterAdfgvx.getcharacters().get(0).getLettre();
                LabsCharacterAdfgvx.getcharacters().remove(0);
                LabsCharacterAdfgvx.showCharacters();

            }
            System.out.println("");

        }
        LabsCharacterAdfgvx.fillLabsCharacterAdfgvx();

    }

    public void initializeTranspositionTab() throws IOException{


        int l = key.length();
        if(messageLength % l == 0)
            t = messageLength/l;
        else t = messageLength/l +1;

        transpositionTab = new char[t + 1 ][l];


        key = key.trim();
        File fi = new File(nameInterFile);
        InputStream in = new FileInputStream(fi);
        Reader reader = new InputStreamReader(in, Charset.defaultCharset());

        for(int i= 0; i<key.length() ;i++){
            transpositionTab[0][i] = LabsCharacterAdfgvx.getCharacterFromChar(key.charAt(i)).getLettre();
        }
        int r;
        int i, j;
        //fill the empty cells in the transposition table with the character Space ' '
        for(i = 1; i<t+1; i++){
            for(j=0; j<key.length(); j++)
                transpositionTab[i][j]=' ';
        }
        i=1;
        j=0;

        while((r = reader.read()) != -1){
            char c = (char) r;

            transpositionTab[i][j] = c;


            if( j == key.length() - 1){
                i++;
                j = 0;
            }
            else
                j++;





            //transpositionTab[i][j] = LabsCharacterAdfgvx.getCharacterFromChar()



        }





    }




    public  void permuteColonne(int i, int j){
        for(int k=0; k < t+1;k++ ){
            char buffer;

            buffer = transpositionTab[k][i];
            transpositionTab[k][i] = transpositionTab[k][j];
            transpositionTab[k][j] = buffer;

        }
    }

    public void sortTranspositionTabble(){
        int min;
        int l = key.length();
        for(int i =0; i<l-1; i++){
            min = i ;
            for(int j = i+1; j<l; j++){
                if(LabsCharacterAdfgvx
                        .getCharacterFromChar(transpositionTab[0][j])
                        .getRang()
                        <
                        LabsCharacterAdfgvx
                        .getCharacterFromChar(transpositionTab[0][min])
                        .getRang()){
                    permuteColonne(j, min);
                    min = j;
                }
            }
        }
    }

    public String getNameInputFile() {
        return nameInputFile;
    }

    public void setNameInputFile(String nameInputFile) {
        this.nameInputFile = nameInputFile;
    }

    public String getnameInterFile() {
        return nameInterFile;
    }

    public void setnameInterFile(String nameInterFile) {
        this.nameInterFile = nameInterFile;
    }




    public static CoupleLetrre  encode(char c){
            CoupleLetrre coupleLetrre = search(c);
            if(coupleLetrre.isAbletoBeCode()){
                return coupleLetrre;
            }
            return new CoupleLetrre(new Lettre(c, -1), new Lettre(c, -1));
    }

    public  char search(char c1, char c2){
        int i=0, j=0;
        for(int k=1; k<7; k++ ) {
            if (tabAdfgvx[0][k] == c1) {
                j = k;
                break;
            }
        }

        for( int k=1; k<7; k++ ){
            if(tabAdfgvx[k][0] == c2){
                    i=k;
                    break;
            }
        }
        if(j!=0 && i!=0)
            return tabAdfgvx[i][j];
        else return '#';
        }




    public static CoupleLetrre search(char c){
        Lettre lettre = LabsCharacterAdfgvx.getCharacterFromChar(Character.toUpperCase(c));
        if(lettre.getRang()!=-1){
            for(int i=1; i<7; i++)
                for(int j=1; j<7 ;j++){
                    if (tabAdfgvx[i][j] == lettre.getLettre()){

                        return new CoupleLetrre(LabsCharacterAdfgvx.getCharacterFromRang(tabAdfgvx[0][j]),LabsCharacterAdfgvx.getCharacterFromRang(tabAdfgvx[i][0]) );
                    }
                }
        }
        int l = LabsCharacterAdfgvx.getCharacterFromChar(c).getRang();

        return new CoupleLetrre(new Lettre('&',-1),new Lettre('&',-1) );
    }


    public static void showTableAdfgvx(){
        for(int i=0; i<7; i++){
            for(int j=0; j<7; j++){
                if(i==0 && j==0)
                    System.out.print("   ");
                else
                    System.out.print(LabsCharacterAdfgvx.getCharacterFromRang(tabAdfgvx[i][j])+"  ");

            }
            System.out.println("");
        }
    }

    @Override
    public void handleCharacters(Reader reader, BufferedWriter bufferedWriter) throws IOException {
        int r;
        while((r = reader.read()) != -1){
            char c = (char) r;
            CoupleLetrre out = encode(c);
            if(out.getLettre1().getRang()!=-1){
                if(Character.isLowerCase(c))
                    bufferedWriter.append(out.toString().toLowerCase());
                else
                    bufferedWriter.append(out.toString());


                    messageLength = messageLength + 2;


            }
            else{
                bufferedWriter.append(c);
                messageLength= messageLength+1;

            }

        }
        bufferedWriter.close();
    }


    public void handleFile2() throws IOException{
        File fi = new File(nameInterFile);
        InputStream in = new FileInputStream(fi);
        FileWriter fw = new FileWriter(nameOutputFile);
        BufferedWriter bw = new BufferedWriter(fw);
        handleCharacters2(bw);
    }

    public void showTranspositionTable(){
        for ( int i=0; i<t+1; i++ ){
            for(int j = 0 ;j< key.length(); j++)
                System.out.print(transpositionTab[i][j]+" ");
            System.out.print("\n");
        }
    }

    public void showTranspositionTable2(){
        for ( int i=0; i<t2+1; i++ ){
            for(int j = 0 ;j< decryptionKey.length(); j++)
                System.out.print(transpositionTab[i][j]+" ");
            System.out.print("\n");
        }
    }

   public void handleCharacters2( BufferedWriter bufferedWriter) throws IOException{

        initializeTranspositionTab();
      // showTranspositionTable();
       System.out.println("__________________________");
        sortTranspositionTabble();
      // showTranspositionTable();






        for(int j =0 ; j<key.length() ; j++){
            for( int i=1; i<t+1; i++){
                bufferedWriter.append(transpositionTab[i][j]);
            }
        }
        bufferedWriter.close();

    }



    // take the encrypted file and place it on the transposition table with the decryption key :
    public void decode1 () throws IOException{

        File fi = new File(nameEncryptedFile);
        InputStream in = new FileInputStream(fi);
        Reader reader = new InputStreamReader(in, Charset.defaultCharset());
        t2 = finalFileLength()/decryptionKey.length();

        transpositionTab = null;
        transpositionTab = new char[t2+1][decryptionKey.length()];
       // showTranspositionTable2();
        String sortedKey = sortKey();

        for (int i=0; i<decryptionKey.length(); i++){
            transpositionTab[0][i] = sortedKey.charAt(i);
        }
        int r;
        int i=1, j=0;
        while( (r = reader.read())!= -1){
            char c = (char)r;
            transpositionTab[i][j] = c;

            if( i == t2 ){
                j++;
                i = 1;
            }
            else
                i++;
        }

        System.out.println("Affichage");

       // showTranspositionTable2();

        transformToOriginaleTabble();

        System.out.println("Fill the InterFile ::\n ");
        FileWriter fw = new FileWriter(nameInterFile);
        BufferedWriter bw = new BufferedWriter(fw);

        //erroooooooooooooor
        for( i=1; i<t2+1; i++){
            for(j=0; j<decryptionKey.length(); j++){
               // System.out.print(transpositionTab[i][j]);
                bw.append(transpositionTab[i][j]);
            }
        }

        bw.close();
        System.out.println("\n ");
    }

    //from interfile to the originale file :
    public void decode2() throws IOException{
        int r;
        char c1,c2;
        FileWriter fw = new FileWriter(nameDecryptedFile);
        BufferedWriter bw = new BufferedWriter(fw);
        File fi = new File(nameInterFile);
        InputStream in = new FileInputStream(fi);
        Reader reader = new InputStreamReader(in, Charset.defaultCharset());
        while((r=reader.read())!=-1){
            c1= (char)r;
            if(LabsCharacterAdfgvx.getCharacterFromChar(Character.toUpperCase(c1)).getRang()!=-1){
                if(Character.isLowerCase(c1)){
                    c2 = (char)reader.read();
                    c1 = Character.toUpperCase(c1);
                    c2 = Character.toUpperCase(c2);
                    bw.append(Character.toLowerCase(search(c1, c2)));
                }
                else{
                    c2 = (char)reader.read();
                    bw.append(search(c1, c2));
                }

            }
            else{
                bw.append(c1);
            }

        }

        bw.close();
    }

    public  String sortKey(){
        char[] chars = decryptionKey.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        return sorted;

    }

    public int finalFileLength() throws  IOException{
        File fi = new File(nameEncryptedFile);
        InputStream in = new FileInputStream(fi);
        Reader reader = new InputStreamReader(in, Charset.defaultCharset());
        int r=0;

        while( (reader.read()) != -1){
            r++;
        }
        return r;
    }


    // transform the table to the originale transposition table :
    public void transformToOriginaleTabble(){
        int k;
        char[] tab;
        String sortedKey = sortKey();
        int deplacement=0;
        char[][] buffer = new char[t2+1][decryptionKey.length()];
        for(int i=0; i<decryptionKey.length(); i++){
            k =indiceCharacterKey(sortedKey, decryptionKey.charAt(i));

            tab = getColumnWithIndice(k);

            for (int j= 0; j<t2+1; j++){
                buffer[j][deplacement] = tab[j];
            }
            deplacement++;


        }
        transpositionTab = buffer;
        System.out.println("Transformation to the originale table : ");
     //   showTranspositionTable2();

    }



    private char[] getColumnWithIndice(int j){
        char[] tab = new char[t2+1];
        for(int i=0; i<t2+1; i++){
            tab[i] = transpositionTab[i][j];
        }
        return tab;
    }




    private static int indiceCharacterKey(String key, char k){
        for(int i=0;i<key.length(); i++){
            if(key.charAt(i) == k)
                return i;
        }
        return -1;

    }


}
