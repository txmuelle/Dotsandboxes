/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.prg2.controlling;
import ch.hslu.prg2.model.GameField;
import ch.hslu.prg2.model.Player;
import ch.hslu.prg2.view.GUI;


/**
 *
 * @author Adrian Ruckli
 */
public class Controller {
    
    private GUI gui;
    private GameField field;
    private Player player;
    
    public Controller(int size){
        field = new GameField(size);
        gui = new GUI();
    }
    
    public void setLine(int row, int colum, Player player){
        if(field.isLineDrawed(row, colum)){
        }else{
                gui.drawLine(row,colum,field.getColor());
                }
            
        if(colum % 2 == 0){
            if(colum-1 > 0){
                if(field.isBoxFull(row, colum-1)){
                    gui.drawBox(row, colum-1, field.getColor());
                }
            }else if(colum+1 <= field.getMatrixSize()-1){
                if(field.isBoxFull(row, colum+1)){
                    gui.drawBox(row, colum+1, field.getColor());
                }
            }
        }else{
            if(row-1 > 0){
                if(field.isBoxFull(row-1, colum)){
                    gui.drawBox(row-1, colum, field.getColor());
                }
            }else if(row+1 <= field.getMatrixSize()-1){
                if(field.isBoxFull(row+1, colum)){
                    gui.drawBox(row+1, colum, field.getColor());
                }
            }
        }
    }
    
    public static void main(String[] args){
        Controller controller = new Controller (5);
    }
}
