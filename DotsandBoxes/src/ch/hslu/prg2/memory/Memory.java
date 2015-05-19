/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.prg2.memory;

import ch.hslu.prg2.controlling.Controller;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jordanklaus
 */
public class Memory implements Serializable {
    
    private Controller controller;
    private static final String DATA_TXT_FILE = "dotsandboxes.txt";
    
    public Memory(Controller controller){
        this.controller = controller;
    }
    
    public void saveGame(){
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(DATA_TXT_FILE))){
            objectOutputStream.writeObject(this.controller);
            objectOutputStream.writeObject(null);
        }catch(IOException exception){
           exception.printStackTrace();
        }
    }
    
    public Controller openGame(){
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(DATA_TXT_FILE))){
            Object object;
            while((object = objectInputStream.readObject())!=null){
                if(object instanceof Controller){
                    this.controller = (Controller) object;
                }
            }
        } catch (IOException | ClassNotFoundException exception) {
           exception.printStackTrace();
        }
        return controller;
    }
    
   public static void main(String [] args){
        Controller c =new Controller();
        c.startGame(0, 2, "Lucius", "Klaus");
        Memory m = new Memory(c);
        m.saveGame();
        Controller A = new Controller();
        A = m.openGame();
        System.out.println(A.getGameField().getBoxOwner(1, 1));
    }
         
}
