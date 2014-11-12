/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser.csv;

import com.sun.xml.internal.ws.util.StringUtils;
import java.util.ArrayList;

/**
 *
 * @author Maria
 */
public class TranzactionParser {

    private static final TranzactionParser instance = new TranzactionParser();
    private static final String PERSONPATTERN = "Ordonator:";
    private static final String MONEYREGEX="^(\\d+,?\\d+)$";
    
    
    private TranzactionParser() {
    }

    public static TranzactionParser getInstance() {
        return instance;
    }
  
    public String parseTranzaction(String tranzaction) {
        if (tranzaction.isEmpty()) {
            return "";
        }
        
        String money="";
        String[] searchForSum=tranzaction.split("\"");
        //get money
        for(String sum:searchForSum){
            if(sum.matches(MONEYREGEX)){
                money="\""+sum+"\"";
                break;
            }
            
        }
    
        String[] data=tranzaction.split(",");
        
        DateManager dm=DateManager.getInstance();
        String date=dm.convertDate(data[0]);
        
        String ordonator="";
        String[] details=new String[50];
        int j=0; //index for details 
        
        int i=1;
        while(i<data.length){  
           
            if(data[i].equals("")){
                i++;
                continue;
            }
            if(money.startsWith(data[i])){
                String[] sub=data[++i].split("\"");
                if(money.endsWith(sub[0].substring(0,sub[0].length()-2))){
                    if(sub.length>1){
                        // ex data[1]="123 data[2]=45"something else
                       data[i]=sub[1];
                    }else{
                        //if it's just another part of the sum, continue
                        i++;
                    }
                    continue;
                }
            }
            if(data[i].contains(PERSONPATTERN)){
                 ordonator=data[i].substring(PERSONPATTERN.length());
            }
            details[j++]=data[i++];
        }
        
        String result=date+",\"";

        for(i=0;i<j-1;i++){
            result+=details[i]+" || ";
        }
        
        result+=details[j-1]+"\",";
        
        result+=money+",\""+ordonator+"\"";
              
        return result;
    }


}
