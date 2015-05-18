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
public class Boxes {
    
    private int counter;
    private Player owner;
    private Color boxColor;
    private int row;
    private int column;
    
    public Boxes(int row, int column){
        this.owner = null;
        this.boxColor = Color.WHITE;
        this.counter = 0;
        this.row = row;
        this.column = column;
    }
   
    public int getCounter(){
        return counter;
    }
    
    public Player getOwner(){
        return owner;
    }
    
    public Color getBoxColor(){
        return boxColor;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setBoxColor(Color boxColor) {
        this.boxColor = boxColor;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    
    @Override
    public String toString(){
     try{
        return (getRow() + " " + getColumn() + " " + getOwner().getName() + " ");
        }
        catch(NullPointerException ne){
        return null; 
        }
    }
}
