/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.prg2.model;

/**
 * Gibt die Matrix von Dots und Boxes auf die Konsole aus.
 * Muss für weiterverwendung mit GUI oder Memory noch angepasst werden.
 * @author luciusschaerer
 */
public class PrintOut {
    
    private int matrixSize;
    private Boxes [][] boxmatrix;
    private Lines [][] linematrix;
    private Player player = new Player();
    private int boxcounter = 0;
    private int boxnumbers;
    private boolean full = false;
    
    public PrintOut(int matrixSize, Boxes[][]boxmatrix, Lines[][]linematrix){
        this.matrixSize = matrixSize;
        this.boxmatrix = boxmatrix;
        this.linematrix = linematrix;
        this.boxnumbers = (((matrixSize-1)*(matrixSize-1))/4);
    }
    
    public int getNumberofFilledBoxes(){
        return boxcounter;
    }
    
    public boolean isMatrixFull(){
        return full;
    }
    
    public void printMatrix(){
        
        for(int row = 0; row < matrixSize; row++){
            for(int column = 0; column < matrixSize; column++){
                try{
                   if((row % 2 == 0) && ((column % 2) != 0)){
                        System.out.print("|" + row + column + this.linematrix[row][column].getOwner().getName() + "|\t" );
                   }
                   else if(((row % 2) != 0) && ((column % 2) == 0)){
                        System.out.print("|" + row + column + this.linematrix[row][column].getOwner().getName() + "|\t" );
                        }
                   else{
                        System.out.print( "|"+ row + column + this.boxmatrix[row][column].getOwner().getName() + "|\t" );
                        this.boxcounter++;
                        }
                }
                   
                   catch(NullPointerException e){
                       
                   if(row % 2 == 0 && column % 2 == 0){
                   System.out.print("   *   ");
                   }
                   else{
                       if(row % 2 == 0){
                      System.out.print("|\t|");
                       }
                       else{
                      System.out.print("|\t|"); 
                       }
                   }   
                 }
               }   
           System.out.println();
        }
        System.out.println("Box filled: " + this.boxcounter + " out of " + this.boxnumbers);
        if(this.boxcounter == this.boxnumbers){
            full = true;
        }
        this.boxcounter = 0;
        System.out.println("---------------------------------------");
    }
        
}
