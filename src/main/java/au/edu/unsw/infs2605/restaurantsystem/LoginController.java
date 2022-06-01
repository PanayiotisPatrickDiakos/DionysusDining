/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.infs2605.restaurantsystem;

import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author bradl
 */
public class LoginController {
    /* Defining all the FXML items and the database */
    Database database = new Database();
    @FXML
    private TextField usernameField;
    @FXML 
    private PasswordField passwordField;
    @FXML
    private Button loginButton; 
    @FXML
    private Label lblStatus;
    @FXML
    private Label lblStatus1;
    @FXML
    private Label lblStatus2;
    @FXML
    private AnchorPane loginPane;
    /* 
     * This runs when the login button is clicked. This will scan the username
     * and password textfields for what the user has inputted and will attempt
     * to verify the idetity of the user and grant it access to the system if 
     * the credentials are correct. Where the user has been unable to gain
     * access to the system with the credentials provided, updates are sent the
     * user so they can understand where they went wrong.
    */
    @FXML
    private void loginButton(ActionEvent event) throws IOException, SQLException {
        /* 
         *Define the username and password fields and get the input from
         * the text boxes
         */
        String inputUsername = usernameField.getText();
        String inputPassword = passwordField.getText();
        /* Set all the update label to blank */
        lblStatus.setText(" ");
        lblStatus1.setText(" ");
        lblStatus2.setText(" ");
        /* Connect to the database */
        Database database = new Database();
        /* Checking statement - see individual comments */
        if ("".equals(usernameField.getText())) { 
            /* If username field is blank, let them know */
            lblStatus1.setText("*Username Required");
        } else if ("".equals(passwordField.getText())) {
            /* If password field is blank, let them know */
            lblStatus2.setText("*Password Required");
        } else if (database.login(inputUsername, inputPassword) == true) {
            /* If credentials match with the system, grant access */
            lblStatus.setText("Login In Successful!");
            /* Opening the main/landing page */
            landingPage();
        } else {
            /* If credentials match with the system, let them know */
            lblStatus.setText("Incorrect username or password");
        }           
    }
    
    /* 
     * When the user is granted access this will run. The login screen will close
     * and the main screen will open, being the home screen.
     */
    Stage stage;
    @FXML
    private void landingPage() throws IOException {
        //App.setRoot("FoodSelection");
        /* First need to close the current page they are on i.e login screen*/
            stage = (Stage) loginPane.getScene().getWindow();
            stage.close();
        /* Load and open the new screen, being the main screen*/
        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();  
    }
    /* 
     * This will open a new screen to confirm the exiting of the application.
     * This screen will run when the power button is clicked, indicating that
     * the user may want to exit the application
     */
    @FXML
    private void exitConfirmation() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ExitScreen.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Exit Application");
        stage.setScene(scene);
        stage.show();
    }
    
    /* 
     * Runs when the help icon is clicked. Opens up the login screen help 
     * screen.
     */
    @FXML
    private void helpScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LoginHelpScreen.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Login Help");
        stage.setScene(scene);
        stage.show();
    }
    
    /* Runs when the about icon is clicked. Opens up the about screen*/
    @FXML
    private void aboutScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AboutScreen.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("About");
        stage.setScene(scene);
        stage.show();
    }
}
