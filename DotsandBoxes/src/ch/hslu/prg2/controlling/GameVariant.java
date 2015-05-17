/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.prg2.controlling;

/**
 *
 * @author Patrick Rossacher
 */
public class GameVariant {
    private int gameModi;
    
    public GameVariant(int gameModi){
        this.gameModi = gameModi;
    }
    
    public void setModi(int gameModi){
        this.gameModi = gameModi;
    }
    
    public int getModi(){
        return gameModi;
    }
}
