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
public class Lines {
    
    private Player owner;
    private Color color;
    private int row;
    private int column;
    
    public Lines(int row, int column){
        this.owner = null;
        this.color = null;
        this.row = row;
        this.column = column;
    }

    public Player getOwner() {
        return owner;
    }

    public Color getColor() {
        return color;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    
}
