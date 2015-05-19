/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.prg2.model;

import ch.hslu.prg2.controlling.GameVariant;
import ch.hslu.prg2.memory.GameMemory;
import java.awt.Color;
import java.util.Scanner;

/**
 * Klasse um textbasiertes Spiel zu testen.
 * @author luciusschaerer
 */
public class TextModel {
    
    public TextModel(){
        
    }
    
    
    public void startText(){
    GameField g = new GameField(4);
    Player p = new Player("P1",Color.BLACK,null,true);
    Player p2 = new Player("P1",Color.BLACK,null,true);
    GameVariant var = new GameVariant(0);
    PrintOut print = new PrintOut(g.getMatrixSize(),g.getBoxMatrix(),g.getLineMatrix());
    GameMemory memo = new GameMemory(g,p,p2,1);
    Scanner scan = new Scanner(System.in);
    g.setLine(0, 1, p);
    g.setLine(1,2,p2);
    g.setLine(1,0,p);
    g.setLine(2,1,p2);
    memo.saveGame();
    memo.loadGame();
    print.printMatrix();
    }
    
    
    
    public static void main(String [] args){
        TextModel t = new TextModel();
        t.startText();
    }
        
} 