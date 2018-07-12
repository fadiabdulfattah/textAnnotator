/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordrepresentation;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Fadi
 */
public class BagOfWord {
    private Map<String, Integer> bag = new LinkedHashMap<String, Integer>();
    
    public BagOfWord(){
        
    }
    
    public BagOfWord(String doc){
        this.union(new BagOfWord(WordUtilities.convertStringToBag(doc)), true);
    }
    
    private BagOfWord(Map<String, Integer> bag){
        this.bag = bag;
    }
    
    public Map<String, Integer> getBag(){
        return this.bag;
    }
    
    public void setBag(Map<String, Integer> bag){
        this.bag = bag;
    }
    
    public BagOfWord union(BagOfWord bag2, boolean inPlace){
        Map<String, Integer> b = WordUtilities.bagUnion(this.bag, bag2.bag);
        BagOfWord result = null;
        if(inPlace) {
            this.bag = b;
            result = this;
        }
        else {
            result = new BagOfWord(b);
        }
        return result;
    }
    
    public int[] featureGeneration(BagOfWord b) {
        int[] represent = new int[this.bag.size()];
        int i = 0;
        for(Map.Entry<String, Integer> entery : this.bag.entrySet()){
            if(b.bag.containsKey(entery.getKey()))
                represent[i] = b.bag.get(entery.getKey());
            i++;
        }
        return represent;
    }
    
    public int[] featureGeneration(String doc) {
        return featureGeneration(new BagOfWord(doc));
    }
    
    @Override
    public String toString(){
        String s = "{";
        for (Map.Entry<String, Integer> entry : bag.entrySet()) {
            s += entry.getKey() + ":" + entry.getValue() + ", ";
        }
        s = s.substring(0, s.length() - 2) + "}";
        return s;
    }
}
