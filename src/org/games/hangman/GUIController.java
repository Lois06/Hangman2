package org.games.hangman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.geometry.VPos;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;

import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;


import org.games.controls.LetterTextField;



import java.net.URL;
import java.util.ResourceBundle;

/*
 * @author Lois Mula Fervenza
 * 
 */

public class GUIController implements Initializable {

    private static final Font fontLarge = Font.font("Droid Sans", FontWeight.BOLD, 35);
    private static final Font fontWord = Font.font("Courier New", FontWeight.BOLD, 20);

    @FXML
    protected BorderPane bdPane;
    @FXML
    protected LetterTextField txtInput;
    @FXML
    protected TextField txtEntered;
    @FXML
    protected Canvas canvas;
    @FXML
    protected GraphicsContext gc;
    protected GameController gameController;

    /*
     * Wörter werden gesucht
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameController = new GameController();
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.setLineWidth(5);

        txtInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if ("".equalsIgnoreCase(newValue)) {
                return;
            }

            char ch = newValue.charAt(0);
            if (gameController.addChar(ch)) {
               
                int wrongGuesses = gameController.getWrongGuesses();
                drawHangman(wrongGuesses);

            }

            txtInput.clear();
            updateEnteredChars();
            updateWord();
            checkGameOver();
        });

        updateWord();
    }
    
    /*
     * Buchstaben werden eingefügt
     */

    private void updateWord() {
        gc.setFont(fontWord);
        gc.setFill(Color.GREEN);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText(gameController.getCurrentWord(), 70, canvas.getHeight() - 60);
    }

    /*
     * Feldchen für Buchstaben
     */
    private void drawCorrectWord() {
        gc.setFont(fontWord);
        gc.setFill(Color.GREEN);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText(gameController.getMissingChars(), 70, canvas.getHeight() - 60);
    }
    
    /*
     * Game Over - Won
     */

    private void checkGameOver() {
        if (gameController.isGameWon()) {
           
            disableGame();
            drawWon();

        } else if (gameController.isGameOver()) {
            
            disableGame();
            drawGameOver();
            drawCorrectWord();
        }
    }

    /*
     * Button für neues Spiel
     */
    
    @FXML
    protected void handleNewGame(final ActionEvent event)
    {
        resetGame();
    }

    private void disableGame() {
        txtInput.setDisable(true);
    }

    /*
     * Reset game
     */
    private void resetGame() {
        gameController.reset();
        txtEntered.clear();
        txtInput.setDisable(false);
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        updateWord();
    }

    /*
     * Gewonnen ausgabe
     */
    
    private void drawWon() {
        gc.setFont(fontLarge);
        gc.setFill(Color.BLUE);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText("You Won!", Math.round(canvas.getWidth() / 2), Math.round(canvas.getHeight()) / 2);
    }

    /*
     * Game Over ausgabe
     */
    
    private void drawGameOver() {
        gc.setFont(fontLarge);
        gc.setFill(Color.BLUE);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText("Game Over!", Math.round(canvas.getWidth() / 2), Math.round(canvas.getHeight()) / 2);
    }

 /*
  * Hier wird der Hangman gezeichnet   
  */
    
    private void drawHangman(int wrongGuesses) {
        switch (wrongGuesses) {
            case 1:
                drawVerticalGallows();
                break;
            case 2:
                drawHorizontalGallows();
                break;
            case 3:
                drawHead();
                break;
            case 4:
                drawBody();
                break;
            case 5:
                drawLegs();
                break;
            case 6:
                drawArms();
                break;
            case 7:
                drawFace();
                break;
        }
    }

    /*
     * Gesicht
     */
    private void drawFace() {
        gc.beginPath();
        gc.moveTo(135, 75);
        gc.lineTo(145, 85);
        gc.moveTo(145, 75);
        gc.lineTo(135, 85);
        gc.moveTo(165, 75);
        gc.lineTo(155, 85);
        gc.moveTo(155, 75);
        gc.lineTo(165, 85);
        gc.stroke();
        gc.strokeArc(135, 95, 30, 30, 50, 80, ArcType.OPEN);
    }
	/*
	 * Arme
	 */
    
    private void drawArms() {
        gc.beginPath();
        gc.moveTo(120,140);
        gc.lineTo(180,140);
        gc.stroke();
    }
    
    /*
     * Beine
     */

    private void drawLegs() {
        gc.beginPath();
        gc.moveTo(150,180);
        gc.lineTo(120, 220);
        gc.moveTo(150,180);
        gc.lineTo(180, 220);
        gc.stroke();
    }

    /*
     * Körper
     */
    
    private void drawBody() {
        gc.beginPath();
        gc.moveTo(150,110);
        gc.lineTo(150,180);
        gc.stroke();
    }

    /*
     * Kopf
     */
    
    private void drawHead() {
        gc.strokeOval(125, 60, 50, 50);
    }

    
    /*
     * Galgen Horizontal
     */
    private void drawHorizontalGallows() {
        gc.beginPath();
        gc.moveTo(31, 60);
        gc.lineTo(60, 31);
        gc.moveTo(30, 30);
        gc.lineTo(150, 30);
        gc.lineTo(150, 60);
        gc.stroke();
    }

    /*
     * Galgen Vertical
     */
    
    private void drawVerticalGallows() {
        gc.beginPath();
        gc.moveTo(30,30);
        gc.lineTo(30, 300);
        gc.moveTo(0, 300);
        gc.lineTo(60,300);
        gc.stroke();
    }

    /*
     * Update
     */
    
    private void updateEnteredChars() {
        StringBuilder enteredFormatted = new StringBuilder();
        gameController.getEnteredChars().stream().sorted().forEach(i -> enteredFormatted.append(i).append(","));
        txtEntered.setText(enteredFormatted.toString().substring(0, enteredFormatted.length() - 1));
    }
    

 
}
