/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordrepresentation;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 *
 * @author Fadi
 */
public class WordUtilities {
    public static String UNKOWN_TOKEN = "<unk>";
    /**
     * 
     * @param doc
     * @return 
     */
    public static Map<String, Integer> convertStringToBag(String doc){
        String[] words = doc.split(" ");
        Map<String, Integer> bag = new LinkedHashMap<String, Integer>();
        for(int i = 0;i < words.length;i++)
            words[i] = wordPreprocessing(words[i]);
        for(int i = 0;i < words.length;i++)
            if(!bag.containsKey(words[i]))
                bag.put(words[i], 1);
            else 
                bag.replace(words[i], bag.get(words[i]) + 1);
        return bag;
    }
    
    /**
     * 
     * @param doc
     * @param n
     * @return 
     */
    public static Map<String, Integer> convertStringToN_Gram(String doc, int n){
        String[] words = doc.split(" ");
        Map<String, Integer> bag = new LinkedHashMap<String, Integer>();
        for(int i = 0;i < words.length;i++)
            words[i] = wordPreprocessing(words[i]);
        for(int i = 0;i < words.length - (n - 1);i++) {
            String w = "";
            for(int j = i;j < i + n;j++)
                w += words[j] + " ";
            w = w.substring(0, w.length() - 1);
            if(!bag.containsKey(w))
                bag.put(w, wordN_Gram(words, i, n));
        }
        return bag;
    }
    
    /**
     * 
     * @param words
     * @param startIndex
     * @param n
     * @return 
     */
    private static int wordN_Gram(String[] words, int startIndex, int n){
        int count = 0;
        for(int i = 0;i < words.length - (n - 1);i++) {
            boolean f = true;
            for(int j = 0;j < n && f;j++)
                f = f && (words[i + j].equals(words[startIndex + j]));
            if(f)
                count++;
        }
        return count;
    }
    
    public static String[] punct = new String[] {".", ",", "?", "!", "'", ";", "`"};
    /**
     * 
     * @param word
     * @return 
     */
    public static String wordPreprocessing(String word){
        word = word.trim();
        for(int i = 0;i< punct.length;i++){
            if(word.endsWith(punct[i])){
                word = word.substring(0, word.length() - 1);
            }
            if(word.startsWith(punct[i])){
                word = word.substring(1, word.length());
            }
        }
        return word;
    }
    
    /**
     * 
     * @param bag1
     * @param bag2
     * @return 
     */
    public static Map<String, Integer> bagUnion(Map<String, Integer> bag1, Map<String, Integer> bag2){
        Map<String, Integer> bag3 = new LinkedHashMap<String, Integer>();
        for(Map.Entry<String, Integer> entery : bag1.entrySet()){
            bag3.put(entery.getKey(), entery.getValue());
        }
        for(Map.Entry<String, Integer> entery : bag2.entrySet()){
            if(!bag3.containsKey(entery.getKey()))
                bag3.put(entery.getKey(), entery.getValue());
            else 
                bag3.replace(entery.getKey(), bag3.get(entery.getKey()) + entery.getValue());
        }
        return bag3;
    }
    
    /**
     * 
     * @param dict
     * @param doc
     * @param n
     * @return 
     */
    public static Map<String, Integer> normalizeN_Gram(N_Gram dict, String doc, int n){
        String[] words = doc.split(" ");
        Map<String, Integer> bag = new LinkedHashMap<String, Integer>();
        for(int i = 0;i < words.length;i++) {
            words[i] = wordPreprocessing(words[i]);
        }
        int unkCount = 0;
        for(int i = 0;i < words.length - (n - 1);i++) {
            String w = "";
            for(int j = i;j < i + n;j++)
                w += words[j] + " ";
            if(!dict.getBag().containsKey(w)) {
                w = UNKOWN_TOKEN;
                unkCount++;
            }
            else {
                w = w.substring(0, w.length() - 1);
            }
            if(!bag.containsKey(w))
                bag.put(w, wordN_Gram(words, i, n));
        }
        bag.replace(UNKOWN_TOKEN, unkCount);
        return bag;
    }
}
