/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser.csv;

import java.util.ArrayList;

/**
 *
 * @author Maria
 */
public class DateManager {
    private final ArrayList<String> MONTHS=new ArrayList<String>(){
        {
            add("ianuarie");
            add("februarie");
            add("martie");
            add("aprilie");
            add("mai");
            add("iunie");
            add("iulie");
            add("august");
            add("septembrie");
            add("octombrie");
            add("noiembrie");
            add("decembrie");
        }
    };
    private static final DateManager instance=new DateManager();
    
    private DateManager() {
        
    }
    
    public static DateManager getInstance() {
        return instance;
    }
    
    public boolean lineContainsDate(String line){
        for(String month:MONTHS){
            if(line.contains(" "+month+" ")){
                return true;
            }
        }
        return false;
    }
    
    private int convertMonth(String month){
       //the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element
        return (MONTHS.indexOf(month)+1);
    } 

    public boolean isDate(String input){
        //momentan face doar validarea pe luna si o validare minora ca ziua sa aiba 2 caractere si anul 4
        String pattern="^(\\d{2} [a-z]+ \\d{4})$";
        String[] parts=input.split(" ");
        return input.matches(pattern)&&(convertMonth(parts[1])!=0);
    }
    
    public String convertDate(String date){
        String[] parts=date.split(" ");
        int month=convertMonth(parts[1]);
        return month < 10 ? "\""+parts[2]+"-0"+month+"-"+parts[0]+"\""
                : "\""+parts[2]+"-"+month+"-"+parts[0]+"\"";
    }
    
}
