/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.prg2.model;

/**
 *
 * @author Müller Urs
 */
public interface IGameModelInformer
{
    PlayerColor getCurrentPlayer();
    
    PlayerColor getGridPosition(int x, int y);
    
    PlayerColor getWinner();
    
    GameVariant getVariant();
    
    IBoard getBoardCopy();
    
    boolean isGameOver();
}
