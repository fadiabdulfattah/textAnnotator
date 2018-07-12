/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasets;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fadi
 */
public class Tokenizer {
    public static String[] Tokenize(String s){
        List<String> tokens = new ArrayList<String>();
        s = s.trim();
        String[] words = s.split(" ");
        for(int i = 0;i < words.length;i++){
            tokens.addAll(wordPreprocessing(words[i], true));
        }
        return tokens.toArray(new String[tokens.size()]);
    }
    
    public static String[] punct = new String[] {".", ",", "?", "!", "'", ";", "`"};
    /**
     * 
     * @param word
     * @return 
     */
    public static List<String> wordPreprocessing(String word, boolean toLower){
        word = word.trim();
        List<String> words = new ArrayList<String>();
        String pre = null, suf = null;
        for(int i = 0;i< punct.length;i++){
            if(word.startsWith(punct[i])){
                pre = word.charAt(0) + "";
                word = word.substring(1, word.length());
            }
            if(word.endsWith(punct[i])){
                suf = word.charAt(word.length() - 1) + "";
                word = word.substring(0, word.length() - 1);
            }
        }
        if(pre != null)
            words.add(pre);
        if(toLower)
            words.add(word.toLowerCase());
        else
            words.add(word);
        if(suf != null)
            words.add(suf);
        return words;
    }
}
