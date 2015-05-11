/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.prg2.controlling;

import ch.hslu.prg2.model.GameField;
import ch.hslu.prg2.model.GameVariant;
import ch.hslu.prg2.model.IBoard;
import ch.hslu.prg2.model.PlayerColor;
import ch.hslu.prg2.controlling.IGameManager;
import ch.hslu.prg2.model.IGameModelInformer;
import ch.hslu.prg2.view.GUI;
import java.net.InetAddress;



/**
 *
 * @author Müller Urs
 */
public class GameManager implements IGameManager {
    
    private IBoard board;
    private GameField model;
    private GameController gameController;
    private GUI gui;
    private GameVariant gameVariant;
    
    public GameManager()
    {

        this.gui = new GUI(this.model, this.gameController, this, PlayerColor.Red, this.gameVariant);
    }
    


    @Override
    public void loadGame() {
        
    }

    @Override
    public void saveGame() {
        
    }

    @Override
    public void newGameEasy() {
        
    }


    @Override
    public void newGameHard() {
       
    }

    
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
            IGameManager manager = new GameManager();
    }
    
}
