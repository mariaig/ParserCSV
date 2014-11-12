/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser.csv;

/**
 *
 * @author Maria
 */
public class MainClass {
    public static final String INPUTFILE="tranzactii_selectate_pentru_parser.csv";
    public static final String OUTPUTFILE="out.csv";
    
    public static void main(String[] args){
        FilesManager fm =new FilesManager(INPUTFILE,OUTPUTFILE);     
       
        fm.transformFile();
    }
}
