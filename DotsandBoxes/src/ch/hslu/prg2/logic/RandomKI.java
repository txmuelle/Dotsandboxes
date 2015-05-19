/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.prg2.logic;

import ch.hslu.prg2.model.GameField;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Jordan Klaus
 */
public class RandomKI implements Serializable{
        
        private GameField gamefield;
        
        public RandomKI(GameField gamefield){
         this.gamefield = gamefield;   
        }
     /**
      * Methode prüft, welche Linien gezeichnet werden können und wählt aus diesen zufällig eine aus.
      * @param matrixsize Matrixgrösse des Spielfeldes
      * @param incomingGameField Referenz auf das aktuelle Spielfeld
      * 
      */   
    public ArrayList<Integer> kiMove(GameField incomingGameField){
        int matrixsize = incomingGameField.getMatrixSize();
        this.gamefield = incomingGameField;
        ArrayList<ArrayList<Integer>> randomlist = new ArrayList<>();
        ArrayList<Integer> coordinates = new ArrayList<>();
        Random randomChooser = new Random();
    
        int listSize=0;
        int randomnumber=0;
        // Spielfeld absuchen nach noch nicht gezeichneten Linien. Falls sie nicht gezeichnet sind,
        // werden sie in der ArrayList randomList mit den Koordinaten abgespeichert.
        for(int row=0;row < matrixsize; row++){
            
            if(row %2==0){
                
                for(int column1 = 1; column1 < matrixsize-1;column1 = column1 + 2 ){
                    
                    if(!checkLine(row,column1)){
                      randomlist.add(saveCoordinate(row,column1));
                      
                    }
                }
                      
            }else{
                        
                for(int column2 = 0 ;column2 < matrixsize ; column2 = column2 + 2){
                        
                    if(!checkLine(row,column2)){
                        randomlist.add(saveCoordinate(row,column2));
                        
                    }
                }
            }
                        
        }
        // Eine noch nicht gezeichnete Linie zufällig wählen;
        listSize = randomlist.size();
        System.out.println("Listenlänge: "+listSize);
        randomnumber = (randomChooser.nextInt(listSize));
        coordinates = randomlist.get(randomnumber);
        int returnRow=coordinates.get(0);
        int returnColumn=coordinates.get(1);
        System.out.println("row: " + returnRow + " colmn: " + returnColumn);
        //Controller.move(returnRow,returnColumn,this);
        return coordinates;//Nur zum Testen, sobald Controller läuft entfernen + Methode auf void setzen
    }
        
    /**
     * Überprüft, ob die Linie schon gezeichnet wurde.
     * @param row Index der Zeile der zu überprüfenden Linie
     * @param column Index der Spalte der zu überprüfenden Linie
     * @return true, falls die Linie schon gezeichnet wurde.
     */
    private boolean checkLine(int row, int column){
        return gamefield.isLineDrawed(row, column);
        }
    /**
     * Speichert die Koordinaten der noch nicht gezeichneten Linie in eine ArrayList
     * mit dem Index 0=row und 1=column
     * 
     * @param row Index der Zeile
     * @param column Index der Spalte
     * @return ArrayList mit den Koordinaten Linie
     */
    private ArrayList<Integer> saveCoordinate(int row, int column){
       ArrayList<Integer> a = new ArrayList<>();
       a.add(row);
       a.add(column);
       return a;
    }   
       
    }


