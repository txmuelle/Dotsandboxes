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
    
    public Boxes(){
        this.owner = null;
        this.boxColor = null;
        this.counter = 0;
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

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setBoxColor(Color boxColor) {
        this.boxColor = boxColor;
    }
    
}