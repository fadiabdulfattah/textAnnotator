/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textannotator;

import datasets.BioText1;
import datasets.Tokenizer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import pretrained.GloVe;
import pretrained.GloVeType;

/**
 *
 * @author Fadi
 */
public class TestGloVe {
    public static void main(String[] args){
        GloVe g = new GloVe(GloVeType.GloVe50);
        float[] vec = g.lookUp("trisomies");
        for(int i = 0;vec != null && i < vec.length;i++){
            System.out.print(vec[i] + ", ");
        }
        System.out.println();
        
        
        BioText1 dataset = new BioText1();
        Map<Integer, String[]> dissesWords = new LinkedHashMap<Integer, String[]>();
        for(int i = 0;i < dataset.size();i++){
            String[] dis = dataset.getDisses(i);
            for(int j = 0;j < dis.length;j++) {
                String[] disWords = Tokenizer.Tokenize(dis[j]);
                dissesWords.put(i,disWords);
            }
        }
        
        int found = 0;
        int count = 0;
        for(Map.Entry<Integer, String[]> entry : dissesWords.entrySet()){
            for(int i = 0;i < entry.getValue().length;i++) {
                if(g.lookUp(entry.getValue()[i]) != null) {
                    found++;
                    System.out.println(count + ": (" + entry.getKey() + " => " + entry.getValue()[i] + "), Found");
                    //try { Thread.sleep(10); } catch(Exception e){ e.printStackTrace(); }
                } else {
                    System.err.println(count + ": (" + entry.getKey() + " => " + entry.getValue()[i] + "), Not Found");
                    //try { Thread.sleep(10); } catch(Exception e){ e.printStackTrace(); }
                }
                count++;
            }
        }
        System.out.println("Found: " + found + " Of " + count + ", precentage: " + ((double)found / count * 100.0) + " %");
        System.out.println(dissesWords.size());
    }
}
