package EncryptionUtils.Cesar;

import EncryptionUtils.LabsLettre;
import EncryptionUtils.Lettre;
import EncryptionUtils.Utils.FileUtils;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by brahim on 4/6/17.
 */
public class CesarEncryption extends FileUtils {

    private String nameInputFile;
    private String nameOutputFile;
    private int key;


    public CesarEncryption(String nameInputFile, String nameOutputFile, int key) {
        this.nameInputFile = nameInputFile;
        this.nameOutputFile = nameOutputFile;
        this.key = key;
    }

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

    public char encode(char c){
        Lettre l = LabsLettre.getLettreFromChar(c);
        if(l.getRang() == -1)
            return c;
        int out = (l.getRang() + key ) % 26;

        return LabsLettre.getLettreFromRang(out).getLettre();

    }

   /* private char decode(char c){
        Lettre l = LabsLettre.getLettreFromChar(c);
        if(l.getRang() == -1)
            return c;
        int out = (l.getRang() - key ) % 26;
        if(out<0)
            out = out + 26;
        System.out.println("------------------------> "+ out);
        return LabsLettre.getLettreFromRang(out).getLettre();
    }*/

   private char decode(char c){
       return encode(c);
   }


    @Override
    public void handleCharacters(Reader reader, BufferedWriter bufferedWriter) throws IOException {
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

    public void handleFile2() throws IOException{
        File fi = new File(nameInputFile);
        InputStream in = new FileInputStream(fi);
        Reader reader = new InputStreamReader(in, Charset.defaultCharset());

        FileWriter fw = new FileWriter(nameOutputFile);
        BufferedWriter bw = new BufferedWriter(fw);
        handleCharacters2(reader, bw);
    }

    public void handleCharacters2(Reader reader, BufferedWriter bufferedWriter) throws IOException{
        int r;
        while((r = reader.read()) != -1){
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
