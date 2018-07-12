/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pretrained;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Fadi
 */
public class GloVe {
    private GloVeType gloVeType; 
    private Map<String, float[]> words = new LinkedHashMap<String, float[]>();
    public GloVe(){ 
        //this(gloVeType.GloVe50);
    }
    
    public GloVe(GloVeType type){
        this.gloVeType = type;
        this.init();
    }
    
    protected void init(){
        String path = "../glove.6B/glove.6B." + this.gloVeType.getValue() + "d.txt";
        try {     
            FileReader frd = new FileReader(path);
            BufferedReader brd = new BufferedReader(frd);
            String line = null;
            while((line = brd.readLine()) != null) {
                String[] ws = line.split(" ");
                words.put(ws[0], convertToReal(ws));
            } 
            brd.close();
            frd.close();
        } catch(Exception e) { 
            e.printStackTrace(); 
        }
    }
    
    private float[] convertToReal(String[] words){
        float[] v = new float[words.length - 1];
        for(int i = 1;i < words.length;i++){
            v[i - 1] = Float.parseFloat(words[i]);
        }
        return v;
    }
    
    public float[] lookUp(String word){
        if(words.containsKey(word))
            return words.get(word);
        return null;
    }
}
