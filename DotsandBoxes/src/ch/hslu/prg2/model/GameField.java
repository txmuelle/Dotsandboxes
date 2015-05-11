/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.prg2.model;


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
    }
    
    
    public int getMatrixSize(){
        return matrixSize;
    }
    
    public boolean isLineDrawed(int row, int column){
        return !linematrix[row][column].isEmpty();
    }
    
 
    
    public static void main(String [] args){
        GameField g = new GameField(1);
        Player p = new Player("Lucius", Color.BLACK);
        g.linematrix[1][2].setOwner(p);
        g.linematrix[3][0].setOwner(p);
        System.out.println(g.isLineDrawed(1, 2));
        System.out.println(g.isLineDrawed(3, 0));
    }
    
}