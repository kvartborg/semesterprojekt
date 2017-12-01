/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import acq.IGame;
import javafx.scene.control.ListView;

/**
 *
 * @author Rasmus
 */
public abstract class Controller {
    /**
     * Instance of IGame
     */
    protected IGame game;
    
    /**
     * Instance of GUI
     */
    protected GUI gui;
    
    /**
     * Method to inject GUI
     * @param gui 
     */
    public void injectGUI(GUI gui) {
        this.gui = gui;
    }
    
    /**
     * Method to inject IGame
     * @param game 
     */
    public void injectGame(IGame game) {
        this.game = game;
    }
    
    /**
     * This method checks if the inventory and search window is empty and if
     * it is not then it replaces the - with a space.
     * @param listView
     * @return 
     */
    protected String listViewSelection(ListView<String> listView) {
        if (listView.getSelectionModel().getSelectedItem() == null) {
            return "";
        }
        return listView.getSelectionModel().getSelectedItem().replace(" ", "-");
    }
}
