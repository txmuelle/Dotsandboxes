/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.prg2.controlling;

import ch.hslu.prg2.model.PlayerColor;
import ch.hslu.prg2.model.IBoard;

/**
 * Interface zwischen View und Model
 * @author Müller Urs
 */
public interface IPlayerActionController
{
    void setStripe(int xposition, int yposition, PlayerColor player);
    
    void setResetBoard(IBoard board, PlayerColor player);
}
