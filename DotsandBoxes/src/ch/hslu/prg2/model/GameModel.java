/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.prg2.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Müller Urs
 */
public class GameModel implements IGameModelModifier {
    
    private IBoard board;

    @Override
    public IBoard getBoard() {
       
        return this.board;
    }

    @Override
    public void setBoard(IBoard board) {
        
    }

    @Override
    public void update() {
        
    }

    @Override
    public PlayerColor getCurrentPlayer() {
        return PlayerColor.Red;
    }

    @Override
    public PlayerColor getGridPosition(int x, int y) {
        return PlayerColor.Red;
    }

    @Override
    public PlayerColor getWinner() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GameVariant getVariant() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IBoard getBoardCopy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isGameOver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
