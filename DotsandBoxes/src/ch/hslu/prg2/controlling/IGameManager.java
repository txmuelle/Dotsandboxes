/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.prg2.controlling;
import java.net.InetAddress;

/**
 * Interface zwischen GUI umd GameManager
 * 
 * @author Müller Urs
 */
public interface IGameManager
{
    void loadGame();
    
    void saveGame();

    void newGameEasy();
    
    void newGameHard();
    
    

}
