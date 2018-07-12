/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Fadi
 */
public class BioText1 {
    private String filePath = "../data/sentences_with_roles_and_relations.txt";
    private List<String[]> sentences = new ArrayList<String[]>();
    public BioText1(){
        init();
    }
    
    public void init(){
        try {     
            FileReader frd = new FileReader(this.filePath);
            BufferedReader brd = new BufferedReader(frd);
            String line = null;
            while((line = brd.readLine()) != null) {
                String[] ws = line.split("	");
                sentences.add(new String[] {ws[0], ws[1]});
            } 
            brd.close();
            frd.close();
        } catch(Exception e) { 
            e.printStackTrace(); 
        }
    }
    
    public String get(int index){
        return this.sentences.get(index)[0];
    }
    
    public String[] getDisses(int index){
        String str = this.sentences.get(index)[0];
        //System.out.println(str);
        //Pattern pattern = Pattern.compile("<.+?>(.+?)</.+?>");
        Pattern pattern = Pattern.compile("<DISONLY>(.+?)</DISONLY>");
        Matcher matcher = pattern.matcher(str);
        List<String> res = new ArrayList<String>();
        while(matcher.find()) { 
            res.add(matcher.group(1));
        }
//        Pattern pattern1 = Pattern.compile("<TREATONLY>(.+?)</TREATONLY>");
//        Matcher matcher1 = pattern1.matcher(str);
//        while(matcher1.find()) { 
//            res.add(matcher1.group(1));
//        }
        return res.toArray(new String[res.size()]);
    }
    
    public int size(){
        return sentences.size();
    }
}
