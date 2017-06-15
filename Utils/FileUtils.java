package EncryptionUtils.Utils;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by brahim on 3/21/17.
 */
public abstract  class FileUtils  {


    public  void handleFile(String nameInputFile, String nameOutputFile) throws IOException {

        File fi = new File(nameInputFile);
        InputStream in = new FileInputStream(fi);
        Reader reader = new InputStreamReader(in, Charset.defaultCharset());

        FileWriter fw = new FileWriter(nameOutputFile);
        BufferedWriter bw = new BufferedWriter(fw);
        handleCharacters(reader, bw);
    }

    public abstract  void handleCharacters(Reader reader, BufferedWriter bufferedWriter) throws IOException;



}
