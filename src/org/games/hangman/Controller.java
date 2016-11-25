package org.games.hangman;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;


/*
 * @author Lois Mula Fervenza
 * 
 */


public class Controller {

	@FXML protected Button start;
	@FXML protected Button exit;

/*
 * Mit Button start, starte ich das game.fxml 	
 */
	
	@FXML
	protected void start(ActionEvent event) throws Exception{
	/*Stage stagestart = (Stage) ((Node) event.getSource()).getScene().getWindow();
	GUIController t = new GUIController();
	t.start(stagestart);
	Parent rootstart = FXMLLoader.load(getClass().getResource("GUIController.java"));
	Scene scenestart = new Scene(rootstart);
	stagestart.setTitle("Hangman");
	stagestart.centerOnScreen();
	stagestart.setScene(scenestart);
	stagestart.show();*/
		Main.fenster("game");
	}
	

/*
 * Mit Button Exit beende ich das Spiel	
 */
	@FXML
	protected void exit(ActionEvent event) throws Exception{
		System.exit(0);
	}
	
}

