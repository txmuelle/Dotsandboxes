/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.prg2.model;

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
    Player p = new Player("P1",Color.BLACK,null);
    PrintOut print = new PrintOut(g.getMatrixSize(),g.getBoxMatrix(),g.getLineMatrix());
    Scanner scan = new Scanner(System.in);
    while(!print.isMatrixFull()){
        try{
         System.out.println("Please enter row number: ");
         int row = scan.nextInt();
         System.out.println("Please enter column number: ");
         int column = scan.nextInt();
         g.setLine(row, column, p);
        }
        catch(NullPointerException ie){
            System.out.println("Ungültige Eingabe.");
        }
            
        print.PrintMatrix();
        
        }
    }
    
    
    
    public static void main(String [] args){
        TextModel t = new TextModel();
        t.startText();
    }
        
} 