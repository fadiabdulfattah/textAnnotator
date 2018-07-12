/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordrepresentation;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Fadi
 */
public class N_Gram {
    private int N;
    private boolean outOfVocabulary = false;
    private boolean includePrev = false;
    private Map<String, Integer> bag = new LinkedHashMap<>();

    public N_Gram(){
        this.N = 1;
    }
    
    public N_Gram(String doc, int n, boolean outOfVocabulary, boolean includePrev){
        this.N = n;
        this.outOfVocabulary = outOfVocabulary;
        this.includePrev = includePrev;
        if(this.outOfVocabulary){
            this.bag.put(WordUtilities.UNKOWN_TOKEN, 0);
        }
        if(includePrev) {
            this.union(new N_Gram(WordUtilities.convertStringToN_Gram(doc, 1), n, outOfVocabulary, includePrev), true);
            for(int i = 2;i <= n;i++)
                this.union(new N_Gram(WordUtilities.convertStringToN_Gram(doc, i), n, outOfVocabulary, includePrev), true);
        } else {
            this.union(new N_Gram(WordUtilities.convertStringToN_Gram(doc, n), n, outOfVocabulary, includePrev), true);
        }
    }
    
    public N_Gram(String doc, int n, boolean outOfVocabulary){
        this(doc, n, outOfVocabulary, false);
    }
    
    public N_Gram(String doc, int n){
        this(doc, n, false, false);
    }
    
    private N_Gram(Map<String, Integer> bag, int n, boolean outOfVocabulary, boolean includePrev){
        this.bag = bag;
        this.N = n;
        this.outOfVocabulary = outOfVocabulary;
        this.includePrev = includePrev;
    }
    
    public int getN(){
        return this.N;
    }
    
    public void setN(int n){
        this.N = n;
    }
    
    public Map<String, Integer> getBag(){
        return this.bag;
    }
    
    public void setBag(Map<String, Integer> bag){
        this.bag = bag;
    }
    
    public N_Gram union(N_Gram bag2, boolean inPlace){
        Map<String, Integer> b = WordUtilities.bagUnion(this.bag, bag2.bag);
        N_Gram result = null;
        if(inPlace) {
            this.bag = b;
            result = this;
        }
        else {
            result = new N_Gram(b, this.N, this.outOfVocabulary, this.includePrev);
        }
        return result;
    }
    
    public int[] featureGeneration(N_Gram b) {
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
        return featureGeneration(new N_Gram(WordUtilities.normalizeN_Gram(this, doc, this.N), this.N, this.outOfVocabulary, this.includePrev));
    }
    
    public void write(String filename) {
        try{
            XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));
            encoder.writeObject(this);
            encoder.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static N_Gram read(String filename) {
        try{
            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)));
            N_Gram o = (N_Gram)decoder.readObject();
            decoder.close();
            return o;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
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
