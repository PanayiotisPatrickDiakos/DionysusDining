/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.infs2605.restaurantsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Bradley
 */

/*
 * This is a confirmation screen to determine if you want to shut down the
 * application, back out and go to the previous screen. This will appear when
 * the shutdown button is clicked
 */
public class ExitController {
    @FXML
    private AnchorPane exitDialogue;

    
    /* Will exit from the entire application if yes is clicked */
    @FXML
    public void shutDown() {
        System.exit(0);
    }
    
    /* Will exit dialogue popup if no is clicked and go back to the previous
     * screen
     */
    Stage stage;
    @FXML
    public void exit() {
        stage = (Stage) exitDialogue.getScene().getWindow();
        stage.close();
    }
}
