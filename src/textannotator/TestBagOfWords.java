package textannotator;


import wordrepresentation.BagOfWord;
import wordrepresentation.N_Gram;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fadi
 */
public class TestBagOfWords {
    public static void main(String[] args){
        String doc1 = "John likes to watch movies. Mary likes movies too.";
        String doc2 = "John also likes to watch football games.";
        String doc3 = "A A B C D D A A A B B B A A A B B B";
        BagOfWord bag1 = new BagOfWord(doc1);
        BagOfWord bag2 = new BagOfWord(doc2);
        System.out.println(bag1);
        System.out.println(bag2);
        BagOfWord bag3 = bag1.union(bag2, false);
        System.out.println(bag3);
        int[] r1 = bag3.featureGeneration(bag1);
        int[] r2 = bag3.featureGeneration(bag2);
        for(int i = 0;i < r1.length;i++){
            System.out.print(r1[i] + ", ");
        }
        System.out.println();
        for(int i = 0;i < r2.length;i++){
            System.out.print(r2[i] + ", ");
        }
        System.out.println();
        N_Gram n_gram1 = new N_Gram(doc1, 2, true);
        N_Gram n_gram2 = new N_Gram(doc2, 2, true);
        System.out.println(n_gram1);
        System.out.println(n_gram2);
        N_Gram n_gram3 = n_gram1.union(n_gram2, false);
        n_gram3.write("n_gram.xml");
        System.out.println(n_gram3);
        int[] r3 = n_gram3.featureGeneration(n_gram1);
        for(int i = 0;i < r3.length;i++){
            System.out.print(r3[i] + ", ");
        }
        System.out.println();
        int[] r4 = n_gram3.featureGeneration(doc3);
        for(int i = 0;i < r4.length;i++){
            System.out.print(r4[i] + ", ");
        }
        System.out.println();
    }
}
