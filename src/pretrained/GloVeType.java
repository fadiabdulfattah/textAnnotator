/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pretrained;

/**
 *
 * @author Fadi
 */
public enum GloVeType {
    GloVe50(50),
    GloVe100(100),
    GloVe200(200),
    GloVe300(300);
    
    private final int value;
    private GloVeType(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
