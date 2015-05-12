/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.prg2.model;

import java.awt.Color;

/**
 *
 * @author luciusschaerer
 */
public interface IGameField {
    
    public void setLine(int row, int column, Player p);
    
    public int getMatrixSize();
    
    public Color getBoxColor(int row, int column);
    
    public Color getLineColor(int row, int column);
    
    public boolean isLineDrawed(int row, int column);
    
    public boolean isBoxFull(int row,int column);
    
    
}
