/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.prg2.logic;

import ch.hslu.prg2.model.GameField;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Jordan Klaus
 */
public class RandomKI {
        
        private GameField gamefield;
        
        public RandomKI(GameField gamefield){
         this.gamefield = gamefield;   
        }
        
    public void kiMove(int matrixsize, GameField gameField){
        this.gamefield = gamefield;
        ArrayList<ArrayList> randomlist = new ArrayList<>();
        ArrayList<Integer> coordinates = new ArrayList<>();
        Random randomChooser = new Random();
        int place = 0;
        int listSize=0;
        int randomnumber=0;
        // Spielfeld absuchen nach noch nicht gezeichneten Linien. Falls sie nicht gezeichnet sind
        // werden sie in der ArrayList randomList mit den Koordinaten abgespeichert.
        for(int row=0;row < matrixsize; row++){
            
            if(row %2==0){
                
                for(int column1=1; column1<matrixsize-1;column1 = column1 + 2 ){
                    
                    if(checkLine(row,column1)){
                      randomlist.add(place, saveCoordinate(row,column1));
                      place++;
                      
                    }else{
                        
                    for(int column2 =0 ;column2 < matrixsize ; column2 = column2 + 2){
                        
                        if(checkLine(row,column2)){
                        randomlist.add(place,saveCoordinate(row,column2));
                        place++;
                        }
                    }
                }
            }
        }
     }
        // Eine noch nicht gezeichnete Linie zufällig wählen;
        listSize = randomlist.size();
        randomnumber = (int)(randomChooser.nextInt(listSize-1));
        coordinates = randomlist.get(randomnumber);
        int returnRow=coordinates.get(0);
        int returnColumn=coordinates.get(1);
        //Controller.move(returnRow,returnColumn,this);
        System.out.println("Zeile "+ returnRow +" Spalte "+returnColumn);
    }
 
    private boolean checkLine(int row, int column){
        return gamefield.isLineDrawed(row, column);
        }
    
    private ArrayList saveCoordinate(int row, int column){
       ArrayList<Integer> a = new ArrayList<>();
       a.add(row);
       a.add(column);
       return a;
    }   
       
    }
}

