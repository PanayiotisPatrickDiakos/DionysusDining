/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.infs2605.restaurantsystem;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Bradley
 */

    /* 
     * The master dialogue controller controls all popup boxes that are 
     * informational such as help screens or about pages. 
     * These screens are designed to only contain a back button.
     */
public class MasterDialogueController {
    /* Defining FXML item */
    @FXML
    private AnchorPane dialogue;
    
    /* When the back button is click the current screen will close*/
    Stage stage;
    @FXML
    public void exit() {
        stage = (Stage) dialogue.getScene().getWindow();
        stage.close();
    }
}
