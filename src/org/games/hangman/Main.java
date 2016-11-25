package org.games.hangman;  

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/*
 * @author Lois Mula Fervenza
 * 
 */


public class Main extends Application {

	public static Stage stage;
	
/*
 * Beim Starten des Main wird home.fxml gestartet	
 */
	
    @Override
    public void start(Stage Stage) throws Exception {
    	stage = Stage;
        fenster("home");
    }
    
 
 /*
  * Je nach Button wird das bezügliche Fenster geöffnet  
  */
    
    public static void fenster(String fenstername) throws Exception {
		Parent root = FXMLLoader.load(Main.class.getResource(fenstername + ".fxml"));
		stage.setTitle("Hangman");
		stage.setResizable(false);
		stage.setScene(new Scene(root, 500, 450));
		stage.show();
	}
   
  /*
   * Launch befehl
   *    
   */

      public static void main(String[] args) {
           launch(args);
       }
    
    
    
}
