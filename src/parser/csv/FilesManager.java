/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Maria
 */
public class FilesManager {

    private String inputFile = "";
    private String outputFile = "";

    public FilesManager(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        File f=new File(outputFile);
        if(f.exists()){
            f.delete();
        }
    }

    public void transformFile() {
        if (inputFile.equals("")) {
            System.err.println("EMPTY FILENAME");
            return;
        }
        String tranzaction = "";
        BufferedReader br;
        DateManager dm = DateManager.getInstance();
        TranzactionParser tp = TranzactionParser.getInstance();
        boolean isTranzaction = false; //for detecting junk at the begining
        try {
            String line;
            br = new BufferedReader(new FileReader(inputFile));
            while ((line = br.readLine()) != null) {
                if (dm.lineContainsDate(line)) {
                    isTranzaction = true;
                    tranzaction = tp.parseTranzaction(tranzaction);
                    if (!tranzaction.equals("")) {
                        writeToOutputFile(tranzaction);
                    }
                    tranzaction = line;

                } else {
                    if (isTranzaction) {
                        if (!"".equals(line)) {
                            tranzaction += line.substring(1);
                        }
                    }
                }
            }
        } catch (IOException ex) {
        }

    }

    public void writeToOutputFile(String string) {
        if (outputFile.equals("")) {
            System.err.println("EMPTY FILENAME");
            return;
        }
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(outputFile, true));
            bw.write(string);
            bw.newLine();
            bw.close();
        } catch (IOException ex) {
        }
    }
}
