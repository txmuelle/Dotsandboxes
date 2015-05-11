/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.prg2.model;

import java.awt.Color;


/**
 * Erstellt das Spielfeld mit zwei 2d Array für Lines und Boxes
 * Füllt leere Matrix mit Lines und Boxes Objekten.
 * @author Schaerer Lucius und Klaus "Schnupfer"
 */


public class GameField implements IGameField {
    
    private Lines[][] linematrix;
    private Boxes[][] boxmatrix;
    private int matrixSize;

    
    
    public GameField(int size){
        matrixSize = (size * 2 + 1);
        linematrix = new Lines [matrixSize][matrixSize];
        boxmatrix = new Boxes [matrixSize][matrixSize];
        fillLineMatrix();
        fillBoxMatrix();
    }
    
    
    public void fillLineMatrix(){
    
            for(int rows = 0; rows < matrixSize; rows++){
                if(rows % 2 == 0){
                    int columns = 1;
                    while(columns < matrixSize - 1){
                        linematrix[rows][columns] = new Lines(rows, columns);
                        columns += 2;
                    }
                }
                else{
                    int columns = 0;
                    while(columns < matrixSize){
                        linematrix[rows][columns] = new Lines (rows, columns);
                        columns += 2;
                    }
                    
            }
        }
    }
    
    
    public void fillBoxMatrix(){
            for(int rows = 1; rows < matrixSize - 1; rows = rows + 2){
                   for(int columns = 1; columns < matrixSize - 1; columns = columns + 2){
                       boxmatrix [rows][columns] = new Boxes(rows, columns);
                   }
                
            }  
    }
    
    public void setLine(int row, int column, Player p){
        linematrix[row][column].setOwner(p);
        //Setzen des Counters ,der von der Linie abhänigigen Boxen. 
        if(row %2 == 0){//  Überprüfung, ob eine horizontale Linie ist.
            checkBoxes(row-1,column,p);
            checkBoxes(row+1,column,p);   
        }else{
            checkBoxes(row,column-1,p);
            checkBoxes(row,column+1,p);
        }
    
    }
    
    
    public int getMatrixSize(){
        return matrixSize;
    }
    
    public boolean isLineDrawed(int row, int column){
        return !linematrix[row][column].isEmpty();
    }
    /*  Überprüft, ob die Box überhaupt vorhanden ist und erhöht den Zähler der Box
        um eins. Falls die Box komplett umschlossen ist, wird der Besitzer und die Farbe der Box
        gesetzt.
    
    */
    public void checkBoxes(int row,int column,Player p){
        if(row > 0 && row < matrixSize-1 && column>0 && column<matrixSize-1){
            Boxes box = boxmatrix[row][column];
            int counter = box.getCounter()+1;
            box.setCounter(counter);
            //Überprüfen, ob Quadrat geschlossen ist
            if(counter==4){
               box.setOwner(p);
               box.setBoxColor(p.getColor());
               p.setScore(p.getScore()+1);
            }
        }
    }
 
    public boolean isBoxFull(int row,int column){
        int counter = boxmatrix[row][column].getCounter();
        return (counter == 4);
    }
    public static void main(String [] args){
        GameField g = new GameField(2);
        Player p = new Player("Lucius", Color.BLACK);
        g.linematrix[3][0].setOwner(p);
        System.out.println(g.isLineDrawed(3, 0));
        g.setLine(0, 1, p);
        g.setLine(1, 2, p);
        Boxes b1= g.boxmatrix[1][1];
        Boxes b2= g.boxmatrix[1][3];
        System.out.println(b1.getCounter());
        g.setLine(2,1, p);
        g.setLine(1,0, p);
        System.out.println(b1.getCounter());
        System.out.println(b2.getCounter());
        System.out.println(b1.getOwner());
    }
    
}