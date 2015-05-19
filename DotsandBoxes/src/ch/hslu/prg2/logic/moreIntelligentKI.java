/*

 */
package ch.hslu.prg2.logic;

import ch.hslu.prg2.model.Boxes;
import ch.hslu.prg2.model.GameField;
import java.util.ArrayList;
import java.util.Random;
/**
 * Diese Klasse erstellt ein KI-Objekt, welche eine Linie auswählt.
 * @author Jordan Klaus
 */
public class moreIntelligentKI {
    
    private GameField gamefield;
    private int matrixSize;
    
    /**
     * Konstruktor von moreIntelligentKI
     * @param gamefield Gamefield, aus welche das KI angewandt werden soll. 
     */
    public moreIntelligentKI(GameField gamefield){
        
        this.gamefield = gamefield;
        this.matrixSize = gamefield.getMatrixSize();
        
    }
    /**
     * Methode wählt eine Linie aus und gibt deren Koordinaten zurück.
     * @param incomingGameField Gamefield, auf welches der Spielzug vollzogen werden soll.
     * @return ArrayList mit den Koordinaten der Linie, die gezeichnet werden soll.
     */
    public ArrayList<Integer> kiMove(GameField incomingGameField){
        
        this.gamefield = incomingGameField;
        Boxes box = chooseBox(checkBoxes());
        int boxRow = box.getRow();
        int boxColumn = box.getColumn();
        ArrayList<Integer> coordinates = chooseLines(boxRow,boxColumn);
 
        return coordinates;
    }
    /**Alle Boxen des Spielfelds werden untersucht und nach Prioritäten(max,normal,min) eingeteilt.
     * 
     * @return ArrayList, welche drei Prioritäts-Listen mit allen noch nicht vergebenen Boxen enthält.
     *         Index 0: maximale Priorität
     *         Index 1: normale Priorität
     *         Index 2: minimale Priorität
     */
    private ArrayList<ArrayList> checkBoxes(){
        
        Boxes[][] boxmatrix = gamefield.getBoxMatrix();
        ArrayList<ArrayList> boxlist = new ArrayList<>();
        ArrayList<Boxes> maxPriority = new ArrayList<>();
        ArrayList<Boxes> normalPriority = new ArrayList<>();
        ArrayList<Boxes> minPriority = new ArrayList<>();
        
        //Aufrufen aller Boxen.
        for(int rows = 1; rows < matrixSize - 1; rows = rows + 2){
            
                   for(int columns = 1; columns < matrixSize - 1; columns = columns + 2){
                       
                       Boxes box = boxmatrix[rows][columns];
                       int counter = box.getCounter();
                       
                        //Maximale Priorität, da Box mit zusätzlicher 
                        //Linie geschlossen werden kann.
                       if(counter==3){
                           
                           maxPriority.add(box);//Speichern der Box in ArrayList 
                           
                       }else
                         //Normale Priorität
                           if (counter==0 || counter==1){
                  
                           normalPriority.add(box);//Speichern der Box in ArrayList
                           
                         //Minimale Priorität
                       }else if (counter==2){
                       
                           minPriority.add(box);//Speichern der Box in ArrayList
                       }
                                 
                   }
                
            }
        
        boxlist.add(maxPriority);
        boxlist.add(normalPriority);
        boxlist.add(minPriority);
        
        return boxlist;
        
    }
    /**
     * Methode sucht eine Box der bestmöglichen Priorität aus.
     * @param boxlist Enthält die nach drei Prioritäten eingeteilten Boxen (siehe Methode checkBoxes)
     * @return Ausgewählte Box
     */
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
    /**Methode wählt zufällig eine Zahl zwischen null und dem vorgegebenen Bereich(size-1) aus
     * 
     * @param size Bereich, in dem die Zahl ausgewählt werden soll 
     * @return zufälliger Int-Wert im Bereich zwischen 0 und size-1 aus
     */
    private int random(int size ){
        
        Random randomChooser = new Random();
        int randomNumber = (randomChooser.nextInt(size));
        
        return randomNumber;
    }
    /**
     * 
     * @param boxRow Zeilenkoordinate der Box
     * @param boxColumn Spaltenkoordinate der Box
     * @return ArrayList mit den Koordinaten der ausgewählten Linie.
     *         ListIndex 0 = Zeilenkoordinate
     *         ListIndex 1 = Spaltenkoordinate
     */
    private ArrayList<Integer> chooseLines(int boxRow,int boxColumn){
       
        ArrayList<Integer> coordinates = new ArrayList<>();
        //  Überprüfen der Linie überhalb der Box
        if(!gamefield.isLineDrawed(boxRow-1, boxColumn)){
            coordinates.add(boxRow-1);
            coordinates.add(boxColumn);
            
        // Überprüfen der Linie links der Box    
        }else if(!gamefield.isLineDrawed(boxRow, boxColumn - 1)){
            
            coordinates.add(boxRow);
            coordinates.add(boxColumn-1);
        // Überprüfen der Linie rechts der Box    
        }else if(!gamefield.isLineDrawed(boxRow, boxColumn + 1)){
            
            coordinates.add(boxRow);
            coordinates.add(boxColumn+1);
        // Überprüfen der Linie unter der Box     
        }else{
            
            coordinates.add(boxRow+1);
            coordinates.add(boxColumn);
        }
        
        return coordinates;
    }
    
  
}