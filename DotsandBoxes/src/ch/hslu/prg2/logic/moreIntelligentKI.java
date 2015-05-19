/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.prg2.logic;

import ch.hslu.prg2.model.Boxes;
import ch.hslu.prg2.model.GameField;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author jordanklaus
 */
public class moreIntelligentKI {
    
    private GameField gamefield;
    private int matrixSize;
    
    
    public moreIntelligentKI(GameField gamefield){
        
        this.gamefield = gamefield;
        this.matrixSize = gamefield.getMatrixSize();
        
    }
    
    public ArrayList<Integer> kiMove(GameField incomingGameField){
        
        this.gamefield = incomingGameField;
        Boxes box = chooseBox(checkBoxes());
        int boxRow = box.getRow();
        int boxColumn = box.getColumn();
        ArrayList<Integer> coordinates = chooseLines(boxRow,boxColumn);
 
        return coordinates;
    }
    
    private ArrayList<ArrayList> checkBoxes(){
        
        Boxes[][] boxmatrix = gamefield.getBoxMatrix();
        ArrayList<ArrayList> boxlist = new ArrayList<>();
        ArrayList<Boxes> maxPriority = new ArrayList<>();
        ArrayList<Boxes> normalPriority = new ArrayList<>();
        ArrayList<Boxes> minPriority = new ArrayList<>();
        
        for(int rows = 1; rows < matrixSize - 1; rows = rows + 2){
            
                   for(int columns = 1; columns < matrixSize - 1; columns = columns + 2){
                       
                       Boxes box = boxmatrix[rows][columns];
                       int counter = box.getCounter();
                       
                       if(counter==3){
                           
                           maxPriority.add(box);
                           
                       }else if (counter==0 || counter==1){
                  
                           normalPriority.add(box);
                           
                       }else if (counter==2){
                       
                           minPriority.add(box);
                       }
                                 
                   }
                
            }
        
        boxlist.add(maxPriority);
        boxlist.add(normalPriority);
        boxlist.add(minPriority);
        
        return boxlist;
        
    }
    
    private Boxes chooseBox(ArrayList<ArrayList> boxlist){
        
        ArrayList<Boxes> maxPriority = boxlist.get(0);
        ArrayList<Boxes> normalPriority = boxlist.get(1);
        ArrayList<Boxes> minPriority = boxlist.get(2);
        
        // Überprüfen, ob maxPriorityList nicht leer ist.
        if(!maxPriority.isEmpty()){
            
            int i = random(maxPriority.size());
            return maxPriority.get(i);
            
            
        } else
            // Überprüfen, ob normalPriorityList nicht leer ist
        if(!normalPriority.isEmpty()){
               
                int i = random(normalPriority.size());
                return normalPriority.get(i);
                
        } else {
                
                int i = random(minPriority.size());
                return minPriority.get(i);
                
        }
        
        
    }
    
    private int random(int size ){
        
        Random randomChooser = new Random();
        int randomNumber = (randomChooser.nextInt(size));
        
        return randomNumber;
    }
    
    private ArrayList<Integer> chooseLines(int boxRow,int boxColumn){
       
        ArrayList<Integer> coordinates = new ArrayList<>();
        
        if(!gamefield.isLineDrawed(boxRow-1, boxColumn)){
            coordinates.add(boxRow-1);
            coordinates.add(boxColumn);
            
            
        }else if(!gamefield.isLineDrawed(boxRow, boxColumn - 1)){
            
            coordinates.add(boxRow);
            coordinates.add(boxColumn-1);
           
        }else if(!gamefield.isLineDrawed(boxRow, boxColumn + 1)){
            
            coordinates.add(boxRow);
            coordinates.add(boxColumn+1);
            
        }else{
            
            coordinates.add(boxRow+1);
            coordinates.add(boxColumn);
        }
        
        return coordinates;
    }
    
  
}

