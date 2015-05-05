/*
 * HSLU PRG2 DotsandBoxes.
 */
package ch.hslu.prg2.model;

/**
 * Verbindung zwischen GUI und Model, dieses Informiert das Gui das ein Update, 
 * wegen aktuellen Anderungen im Model, nötig ist.
 * d.h. die Methode update wird vom Model aufgerufen.
 * @author Müller Urs
 */
public interface IModelObserver {
    

    void update();
}
 
