/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.prg2.view;

/**
 * This interface defines a callback that user interface code can use to deliver
 * the parameters for the new game. (dialog box method calls this callback
 * method)
 *
 * Assignment: MP3 Class: CS 340, Fall 2005 TA: Nitin Jindal System: jdk-1.5.0.4
 * and Eclipse 3.1 on Windows XP
 *
 * @author Michael Leonhard (CS account mleonhar)
 * @version 12 Oct 2005
 */
public interface NewGameParametersCallback {

    /**
     * method to accept new game parameters
     *
     * @param gamevariant 
     * @param size number of colums/rows of boxes in new game
     * @param Player1
     * @param Player2
     *
     */
    public void newGameParameters(int gamevariant, int size, String Player1, String Player2);
}
