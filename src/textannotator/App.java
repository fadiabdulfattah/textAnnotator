/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textannotator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Fadi
 */
public class App extends Application {
    public static Stage stg;
    public static Class<?> c;
    
    @Override
    public void start(Stage primaryStage) {
        stg = primaryStage;
        Parent root = null;
        try {
            c = getClass();
            root = FXMLLoader.load(getClass().getResource("FXMLWelcome.fxml"));
        } catch(Exception e){
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        primaryStage.setTitle("FXML Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //launch(args);
        //TestBagOfWords.main(args);
        TestGloVe.main(args);
    }
    
}
