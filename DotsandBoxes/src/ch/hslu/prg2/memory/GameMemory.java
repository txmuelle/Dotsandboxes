/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.prg2.memory;

import ch.hslu.prg2.controlling.GameVariant;
import ch.hslu.prg2.model.Boxes;
import ch.hslu.prg2.model.GameField;
import ch.hslu.prg2.model.Lines;
import ch.hslu.prg2.model.Player;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 *
 * @author luciusschaerer
 */
public class GameMemory {
    
    private GameField gameField;
    private Player p1;
    private Player p2;
    private GameVariant gameModi;
    private static final String DATA_TXT_FILE = "dotsandboxes.txt";
    private File file;
    
    
    public GameMemory(GameField gameField, Player p1, Player p2, GameVariant gameModi){
        this.gameField = gameField;
        this.p1 = p1;
        this.p2 = p2;
        this.gameModi = gameModi;
        file = new File(DATA_TXT_FILE);
    }
    
    public boolean saveGame() {
        boolean saved = false;
        int matrixSize = gameField.getMatrixSize();
        Lines[][] linematrix = gameField.getLineMatrix();
        Boxes[][] boxmatrix = gameField.getBoxMatrix();

        
        try(BufferedWriter buffWriter = new BufferedWriter(new FileWriter(file))){
            PrintWriter printer = new PrintWriter(buffWriter);
            //Schreibt die Grösse und Player des Spiels ins File.
            printer.println("Controller: ");
            //Schreibt die LinesMatrix ins File.
            printer.print("Lines: ");
            for(int rows = 0; rows < matrixSize; rows++){
                if(rows % 2 == 0){
                    int columns = 1;
                    while(columns < matrixSize - 1){
                        if(linematrix[rows][columns].toString()!=null){
                        printer.print(linematrix[rows][columns]);
                        }
                        columns += 2;
                    }
                }
                else{
                    int columns = 0;
                    while(columns < matrixSize){
                        if(linematrix[rows][columns].toString()!=null){
                        printer.print(linematrix[rows][columns]);
                        }
                        columns += 2;
                    }
                    
                }   
            }
            printer.println();
            //Schreibt die BoxMatrix ins File.
            printer.print("Boxes: ");
            for(int rows = 1; rows < matrixSize - 1; rows = rows + 2){
                   for(int columns = 1; columns < matrixSize - 1; columns = columns + 2){
                       if(boxmatrix[rows][columns].toString()!=null){
                       printer.print(boxmatrix [rows][columns]);
                       }
                   }
            }
            buffWriter.flush();
            saved = true;
        }
        catch(IOException ioe){
            System.out.println("Fehler beim speichern.");
        }
        System.out.println(saved);
        return saved;
    }
    
    public void loadGame(){
        if(file.exists()){
            try(BufferedReader buffRead = new BufferedReader(new FileReader(file))){
                for(int i = 0; i < 4;i++){
                  StringTokenizer st = new StringTokenizer(buffRead.readLine());  
                  System.out.println(buffRead.readLine());
                  
                  while(st.hasMoreTokens()){
                      
                  }
                  
                }
            }
            catch(IOException ioe){
                
            }
        }
    }
    
    
}
