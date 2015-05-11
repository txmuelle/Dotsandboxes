/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.prg2.controlling;
import ch.hslu.prg2.view.GUI;


/**
 *
 * @author Adrian Ruckli
 */
public class Controller {
    
    public Controller(){
        p
        
    }
    
    public void setLine(int row, int colum, Player player){
        if(modell.isLineDrawed(row, colum)){
        }else{
                gui.drawLine(row,colum,modell.getColor());
                }
            
        if(colum % 2 == 0){
            if(colum-1 > 0){
                if(modell.isBoxFull(row, colum-1)){
                    gui.drawBox(row, colum-1);
                }
            }else if(colum+1 <= modell.getMatrixSize()-1){
                if(modell.isBoxFull(row, colum+1)){
                    gui.drawBox(row, colum+1);
                }
            }
        }else{
            if(row-1 > 0){
                if(modell.isBoxFull(row-1, colum)){
                    gui.drawBox(row-1, colum);
                }
            }else if(row+1 <= modell.getMatrixSize()-1){
                if(modell.isBoxFull(row+1, colum)){
                    gui.drawBox(row+1, colum);
                }
            }
        }
    }
    
    public static void main(String[] args){
        
        gui = new GUI(this.model, this.gameController, this, PlayerColor.Red, this.gameVariant);
    }
}
