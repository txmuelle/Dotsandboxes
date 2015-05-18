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
    private boolean isActive = false;
    private Object reference;
    
    public Player(String name, Color color, Object reference, boolean isActive){
        this.name = name;
        this.color = color;
        this.reference = reference;
        this.isActive = isActive;
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
        return isActive;
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

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    
    
    public void incrementScore(){
        this.score++;
    }
    
    @Override
    public String toString(){
        return (name + " "+ color + " "+ score+" "+ isActive +" ");
    }
}
