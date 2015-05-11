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
    
    public Player(String name, Color color){
        this.name = name;
        this.color = color;
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
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setColor(Color color){
        this.color = color;
    }
    
    public void setScore(int score){
        this.score = score;
    }
    
    public void incrementScore(){
        this.score++;
    }
    
}
