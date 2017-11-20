/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import acq.IGame;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import maga.GameFacade;

/**
 *
 * @author Rasmus
 */
public class GUI extends Application {
    private IGame game = new GameFacade();
    GameController gameController;
    
    /**
     * Method to start the game with gui
     */
    @Override
    public void start(Stage stage) throws Exception {
        this.loadGameController(stage);
    }
    
    /**
     * Loads the game controller stage
     * @param stage
     */
    public void loadGameController(Stage stage){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
            Parent root = (Parent) loader.load();

            this.gameController = loader.getController();
            this.gameController.injectGame(this.game);  


            Scene scene = new Scene(root);
            scene.setOnKeyPressed(event -> this.onKeyPressed(event));
            stage.setMinWidth(600);
            stage.setMinHeight(500);
            stage.setScene(scene);
            stage.show(); 
        } catch(Exception e){}
    }
    
    /**
     * Loads the searchcontroller scene
     */
    public void loadSearchController(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchWindow.fxml"));  
            Parent root = (Parent) loader.load();

            SearchWindowController swc = loader.getController();
            swc.injectGame(this.game);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Searching: "+ game.getPlayer().getCurrentRoom().getName());
            stage.setMinWidth(600);
            stage.setMinHeight(500);
            stage.setScene(scene);
            swc.addItemsToViewList();
            stage.show(); 
        } catch(Exception e){}
        
    }
    
    public void loadInventoryController(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InventoryWindow.fxml"));  
            Parent root = (Parent) loader.load();

            InventoryWindowController iwc = loader.getController();
            iwc.injectGame(this.game);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Player inventory: ");
            stage.setMinWidth(600);
            stage.setMinHeight(500);
            stage.setScene(scene);
            iwc.addItemsToViewList();
            stage.show(); 
        } catch(Exception e){}
        
    }
    
    /**
     * Method to make the player move when using the keyboard
     * @param event 
     */
    public void onKeyPressed(KeyEvent event) {
        switch (event.getCode()) {   
            case UP:                 
                game.command("go", "north");
            break;
            
            case LEFT:
                game.command("go", "west");
            break;
           
            case RIGHT:  
                game.command("go", "east");
            break;
               
            case DOWN:
                game.command("go", "south");
            break; 
           
            case S:
                this.loadSearchController();
            break;
           
            case I:
               this.loadInventoryController();
            break;
       }
        this.gameController.updateGameState();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
