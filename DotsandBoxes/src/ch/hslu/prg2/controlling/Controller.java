
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.prg2.controlling;

import ch.hslu.prg2.model.GameField;
import ch.hslu.prg2.model.Player;
import ch.hslu.prg2.view.GUI;
import java.awt.Color;
//test

/**
 *
 * @author Adrian Ruckli
 */
public class Controller {

    private GUI gui;
    private GameField gameField;
    private Player player1, player2;
    private Color color1, color2;
    private GameVariant gameVariant;
    private RandomKI randomKi;

    public Controller() {
        GUI gui = new GUI(gameField, this, player1, player2, gameVariant);
    }

    public boolean move(int row, int colum, Object reference) {
        if (!gameField.isLineDrawed(row, colum)) {
            if (player1.getReference() == reference && player1.getIsActive()) {
                gameField.setLine(row, colum, player1);
                player1.setIsActive(false);
                player2.setIsActive(true);
            } else if (player2.getReference() == reference && player2.getIsActive()) {
                gameField.setLine(row, colum, player2);
                player2.setIsActive(false);
                player1.setIsActive(true);
            } else {
                return false;
            }
            gui.update();
            nextMove();
            return true;
        } else {
            return false;
        }
    }

    

    private void nextMove() {
        if (player1.getScore() + player2.getScore() <= (gameField.getMatrixSize() - 1) / 2) {
            Object activePlayer;
            if(player1.getIsActive()){
                activePlayer = player1;
            } else if (player2.getIsActive()){
                activePlayer = player2;
            }
            if(activePlayer == GUI){
                
            } else if (activePlayer == RandomKI){
                
                nextMove();
            }
        } else {
            //game finished!!
        }
    }

    public void startGame(int gameModi, int size, String player1, String player2) {
        color1 = new Color(255, 0, 0);
        color2 = new Color(0, 0, 255);

        gameField = new GameField(size);
        gameVariant = new GameVariant(gameModi);
        randomKi = new RandomKI();

        this.player1 = new Player(player1, color1, randomKi);
        this.player2 = new Player(player2, color2, gui);

        gui.update();
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
    }
}
