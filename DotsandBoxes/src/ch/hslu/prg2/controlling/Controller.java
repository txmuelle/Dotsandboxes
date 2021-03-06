
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.prg2.controlling;

import ch.hslu.prg2.logic.moreIntelligentKI;
import ch.hslu.prg2.model.GameField;
import ch.hslu.prg2.model.Player;
import ch.hslu.prg2.model.PrintOut;
import ch.hslu.prg2.view.GUI;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Adrian Ruckli, Patrick Rossacher
 */
public class Controller implements Serializable{

    private transient GUI gui;
    private GameField gameField;
    private Player player1, player2;
    private transient GameVariant gameVariant;
    private moreIntelligentKI Ki;
    private boolean GameOver;

    public Controller() {

        startGame(2, 3, "Spieler 1", "Computer");
    }

    /**
     * Führt einen Spielzug aus. Überprüft wer den Zug machen will und ob dieser
     * an der Reihe ist. Wenn sich der Score des Spielers nach dem Zug nicht
     * verändert wird der aktuelle Player auf inaktiv gesetzt. Wenn sich der
     * Score verändert bleibt er aktiv und kann noch einen Zug machen.
     *
     * @param row
     * @param colum
     * @param reference
     */
    public void move(int row, int colum, Object reference) {
        int score;
        if (!gameField.isLineDrawed(row, colum)) {
            if (player1.getReference() == reference && player1.getIsActive()) {
                score = player1.getScore();
                gameField.setLine(row, colum, player1);
                if (score == player1.getScore()) {
                    player1.setIsActive(false);
                    player2.setIsActive(true);
                } else {
                }

            } else if (player2.getReference() == reference && player2.getIsActive()) {
                score = player2.getScore();
                gameField.setLine(row, colum, player2);
                if (score == player2.getScore()) {
                    player2.setIsActive(false);
                    player1.setIsActive(true);
                } else {
                }

            } else {
            }
            this.gui.update();
            nextMove();
        } else {
        }
    }

    /**
     * Führt den nächsten Spielzug aus. Überprüft welcher Spieler an der Reihe
     * ist und führt je nach Klasse des aktuellen Spielers eine andere Aktion
     * aus: Gui -> keine Aktion (Methode Beenden und auf nächste Eingabe warten)
     * KI -> Die KI führt einen Zug aus Es wird ausserdem überprüft ob die
     * maximale Punktzahl erreicht wurde.
     */
    private void nextMove() {
        Object activePlayer = null;
        if (player1.getIsActive()) {
            activePlayer = player1.getReference();
        } else if (player2.getIsActive()) {
            activePlayer = player2.getReference();
        }
        if (player1.getScore() + player2.getScore() < Math.pow((gameField.getMatrixSize() - 1) / 2, 2)) {
            if (activePlayer.equals(gui)) {

            } else if (activePlayer.equals(Ki)) {
                ArrayList<Integer> coordinates = Ki.kiMove(gameField);
                move(coordinates.get(0), coordinates.get(1), activePlayer);
            }
            // Einfügen von weiteren Spieler Klassen. z.B. Network Player
            //else if (activePlayer == networkPlayer) {}
        } else {
            //game finished!!
            System.out.println("Game Over!\nPlayer1 = " + player1.getScore() + "\nPlayer2 = " + player2.getScore());
            player1.setIsActive(false);
            player2.setIsActive(false);
            GameOver = true;
            PrintOut print = new PrintOut(gameField.getMatrixSize(), gameField.getBoxMatrix(), gameField.getLineMatrix());
            print.printMatrix();
            System.out.println(this.toString());
        }
    }

    /**
     * Wird vom Gui aufgerufen. Initialisiert alle benötigten Klassen für ein
     * neues Spiel. Noch nicht implementiert: (Je nach gameModi werden die
     * Spieler unterschiedlich initialisiert): player 1 player2 0(default) -> KI
     * KI 1 -> KI GUI 2 -> GUI KI 3 -> GUI GUI etc.
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
        Ki = new moreIntelligentKI(gameField);
        GameOver = false;
        gui = new GUI(this.gameField, this, this.player1, this.player2, this.gameVariant);
        switch (gameModi) {
            case 1:
                this.player1 = new Player(player1, color1, Ki, true);
                this.player2 = new Player(player2, color2, gui, false);
                break;

            case 2:
                this.player1 = new Player(player1, color1, gui, true);
                this.player2 = new Player(player2, color2, Ki, false);
                break;

            case 3:
                this.player1 = new Player(player1, color1, gui, true);
                this.player2 = new Player(player2, color2, gui, false);
                break;

            default:
                this.player1 = new Player(player1, color1, Ki, true);
                this.player2 = new Player(player2, color2, Ki, false);
                break;
        }
        this.gui.setPlayer(this.player1, this.player2);
        this.gui.update();
        nextMove();

    }

    @Override
    public String toString() {
        String string;
        string = gameVariant.getModi() + " " + (gameField.getMatrixSize() - 1) / 2 + " " + player1.getName() + " " + player2.getName();
        return string;
    }

    /**
     * Zeigt dem GUI an dass das Spiel beendet ist
     *
     * @return false wenn Spiel noch läuft, true wenn vorbei.
     */
    public boolean isGameOver() {
        return GameOver;
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
    }

    public GUI getGui() {
        return gui;
    }

    public GameField getGameField() {
        return gameField;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public GameVariant getGameVariant() {
        return gameVariant;
    }

    public moreIntelligentKI getKi() {
        return Ki;
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }

    public void setGameField(GameField gameField) {
        this.gameField = gameField;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setGameVariant(GameVariant gameVariant) {
        this.gameVariant = gameVariant;
    }

    public void setRandomKi(moreIntelligentKI Ki) {
        this.Ki = Ki;
    }

    public void setGameOver(boolean GameOver) {
        this.GameOver = GameOver;
    }
    
    
}
