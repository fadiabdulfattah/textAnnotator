/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textannotator;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.net.URL;
import java.nio.CharBuffer;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author Fadi
 */
public class MAINController implements Initializable {
    @FXML
    private TextField TextField1;
    
    @FXML
    private TextArea TextArea1;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
//        Alert alert = new Alert(AlertType.WARNING, "Content here", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
//        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
//        alert.show();
        FileChooser fc = new FileChooser();

        // Set extension filter
        ExtensionFilter extFilter = new ExtensionFilter("TEXT files (*.txt)", "*.txt");
        fc.getExtensionFilters().add(extFilter);

        // Show open file dialog
        File f = fc.showOpenDialog(App.stg);
        if(f != null) {
            System.out.println(f.toString());
            TextField1.setText(f.toString());
            String everything = "";
            try {     
                FileReader frd = new FileReader(f);
                BufferedReader brd = new BufferedReader(frd);
                StringBuilder builder = new StringBuilder();
                char[] buffer = new char[1024];
                while(brd.read(buffer) != -1) {
                    builder.append(new String(buffer));
                    TextArea1.appendText(new String(buffer));
                } 
                builder.append(new String(buffer));
                everything = builder.toString();
                brd.close();
                frd.close();
            } catch(Exception e) { e.printStackTrace(); }
            //TextArea1.setText(everything);
            
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
}
