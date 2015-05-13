/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.prg2.model;

import java.awt.Color;

/**
 *
 * @author Schaerer Lucius und Klaus "Schnupfer"
 */

public class Player
{
    
    private String name;
    private Color color;
    private int score = 0;
    private boolean IsActive = false;
    private Object reference;
    
    public Player(String name, Color color, Object reference){
        this.name = name;
        this.color = color;
        this.reference=reference;
    }
    
    public Player(){
        
    }
    
    public String getName(){
        return name;
    }
    
    public int getScore(){
        return score;
    }
    
    public Color getColor(){
        return color;
    }
    
    public boolean getIsActive() {
        return IsActive;
    }
   
    public Object getReference(){
        
        return reference;
    }
    public void setName(String name){
        this.name = name;
    }
    
    public void setColor(Color color){
        this.color = color;
    }
    
    public void setScore(int score){
        this.score = score;
    }

    public void setIsActive(boolean IsActive) {
        this.IsActive = IsActive;
    }
    
    
    
    public void incrementScore(){
        this.score++;
    }
    
    
}
