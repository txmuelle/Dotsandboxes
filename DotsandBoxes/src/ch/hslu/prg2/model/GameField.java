/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.prg2.model;


import java.util.ArrayList;
import java.util.List;


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
    }
    
    
    public void fillLineMatrix(){
       //Füllen der Matrix horizontal
            for(int rows = 0; rows < matrixSize; rows++){
                if(rows % 2 == 0){
                    int columns = 0;
                    while(rows < matrixSize){
                        linematrix[rows][columns] = new Lines(rows, columns);
                    }
                }
                else{
                    
                }
            }

       //Füllen der Matrix vertikal
        

    }
    
    
    public void fillBoxMatrix(){
        
        
    }
    
    
    
   
    
}