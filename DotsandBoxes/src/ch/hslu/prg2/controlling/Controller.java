
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.prg2.controlling;

import ch.hslu.prg2.logic.RandomKI;
import ch.hslu.prg2.model.GameField;
import ch.hslu.prg2.model.Player;
import ch.hslu.prg2.model.PrintOut;
import ch.hslu.prg2.view.GUI;
import java.awt.Color;
import java.util.ArrayList;
//import java.lang.Math.*;
//test

/**
 *
 * @author Adrian Ruckli, Patrick Rossacher
 */
public class Controller {

    private GUI gui;
    private GameField gameField;
    private Player player1, player2;
    private GameVariant gameVariant;
    private RandomKI randomKi;

    public Controller() {
        //GUI gui = new GUI(gameField, this, player1, player2, gameVariant);
        startGame(0, 10, "A", "B");
    }

    public void move(int row, int colum, Object reference) {
        if (!gameField.isLineDrawed(row, colum)) {
            if (player1.getReference() == reference && player1.getIsActive()) {
                gameField.setLine(row, colum, player1);
                player1.setIsActive(false);
                player2.setIsActive(true);
            } else if (player2.getReference() == reference && player2.getIsActive()) {
                gameField.setLine(row, colum, player2);
                player2.setIsActive(false);
                player1.setIsActive(true);
            } else {}
//            gui.update();
            nextMove();
        } else {}
    }

    /**
     * Führt den nächsten Spielzug aus. Überprüft welcher Spieler an der Reihe
     * ist und führt je nach Klasse des aktuellen Spielers eine andere Aktion
     * aus: Gui -> keine Aktion (Methode Beenden und auf nächste Eingabe warten)
     *      KI  -> Die KI führt einen Zug aus Es wird ausserdem überprüft ob die
     * maximale Punktzahl erreicht wurde.
     *///(gameField.getMatrixSize() - 1) / 2)
    private void nextMove() {
        Object activePlayer = null;
        if (player1.getIsActive()) {
            activePlayer = player1.getReference();
        } else if (player2.getIsActive()) {
            activePlayer = player2.getReference();
        }
        if (player1.getScore() + player2.getScore() < Math.pow((gameField.getMatrixSize() - 1) / 2,2)) {
            if (activePlayer == gui) {

            } else if (activePlayer.equals(randomKi)) {
                ArrayList<Integer> coordinates = randomKi.kiMove(gameField);
                move(coordinates.get(0), coordinates.get(1), activePlayer);
            }
            // Einfügen von weiteren Spieler Klassen. z.B. Network Player
            //else if (activePlayer == networkPlayer) {}
        } else {
            //game finished!!
            System.out.println("Game Over!\nPlayer1 = " + player1.getScore() + "\nPlayer2 = " + player2.getScore());
            player1.setIsActive(false);
            player2.setIsActive(false);
            PrintOut print = new PrintOut(gameField.getMatrixSize(), gameField.getBoxMatrix(), gameField.getLineMatrix());
            print.printMatrix();
        }
    }

    /**
     * Wird vom Gui aufgerufen. Initialisiert alle benötigten Klassen für ein
     * neues Spiel. Noch nicht implementiert: (Je nach gameModi werden die
     * Spieler unterschiedlich initialisiert): 
     *                  player 1    player2 
     * 0(default)   ->  KI          KI 
     * 1            ->  KI          GUI 
     * 2            ->  GUI         KI 
     * 3            ->  GUI         GUI
     * etc.
     *
     * @param gameModi
     * @param size
     * @param player1
     * @param player2
     */
    public void startGame(int gameModi, int size, String player1, String player2) {
        Color color1 = new Color(255, 0, 0);
        Color color2 = new Color(0, 0, 255);

        gameField = new GameField(size);
        gameVariant = new GameVariant(gameModi);
        randomKi = new RandomKI(gameField);

        switch (gameModi) {
            case 1:
                this.player1 = new Player(player1, color1, randomKi, true);
                this.player2 = new Player(player2, color2, gui, false);
                break;

            case 2:
                this.player1 = new Player(player1, color1, gui, true);
                this.player2 = new Player(player2, color2, randomKi, false);
                break;

            case 3:
                this.player1 = new Player(player1, color1, gui, true);
                this.player2 = new Player(player2, color2, gui, false);
                break;
                
            default:
                this.player1 = new Player(player1, color1, randomKi, true);
                this.player2 = new Player(player2, color2, randomKi, false);
                break;
        }
        nextMove();

       // gui.update();
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
    }
}
