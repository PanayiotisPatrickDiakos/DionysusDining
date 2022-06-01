/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.infs2605.restaurantsystem;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Bradley
 */

    /* 
     * This is built to control all interaction with the food category screen.
     * This will allow food categories to be interacted with in the system.
     */
public class FoodCategoryController {
    /* Defining all the FXML items and the database */
    Database database = new Database();
    @FXML
    private TextField foodCategoryField;
    @FXML
    private Button foodCategorySave;
    @FXML
    private Button foodCategoryUpdate;
    @FXML
    private Button foodCategoryDelete;
    @FXML
    private TableView<FoodCategory> foodCategoryTable;
    @FXML
    private TableColumn<FoodCategory, String> foodCategoryTableC1;
    @FXML
    private AnchorPane foodCategoryDialogue;
    
    /* Setting up the database and the table view to link together */
    public void initialize() throws SQLException {
        database = new Database();
        /* Retrieve from the database to get all the food categories */
        ObservableList<FoodCategory> foodCategoryList = FXCollections.observableArrayList();
        foodCategoryList = database.getFoodCategory();
        /* Set list into the table */
        foodCategoryTable.setItems(foodCategoryList);
        /* Set column based on the attribute */
        foodCategoryTableC1.setCellValueFactory(new PropertyValueFactory<>("name"));  
    }
    
    /* 
     * This adds a food category to the the database.
     * This will run when the save button is clicked.
     * This will also run initialize at the top to link have the new addition
     * show up in the table view.
     */
    @FXML
    public void addCategory(ActionEvent event) throws IOException, SQLException {
        /* Standard connection to the database */
        Connection conn = DriverManager.getConnection("jdbc:sqlite:Resturant.db");
        Statement st = conn.createStatement();
        /* SQL query to insert items into the database */
        PreparedStatement pst = conn.prepareStatement(
                "INSERT OR IGNORE INTO FOODCATEGORY (NAME) VALUES (?)" );
        /* Running statement above based on what is in the text field */
        pst.setString(1, foodCategoryField.getText());
        /* Executing the above statement */
        pst.executeUpdate();
        System.out.println("Category Added");
        /* Reload the database/tableview */
        initialize();
        /* Close all connections */
        st.close();
        conn.close();
    }
    
    /* 
     * This identifies when a user clicks on the table and will load the 
     * corresponding selection into the text fields . 
     */
    @FXML 
    public void userDidChangeSelection() {
        /* Identify the user selection */
        FoodCategory selectedItem = foodCategoryTable.getSelectionModel().getSelectedItem();
        /* Set what the user has selected to the text field */
        foodCategoryField.setText(selectedItem.getName());
        System.out.println("Category Selected");
    }
    
    /* 
     * This will update the food category based on the changes the user makes 
     * when they click the update button after they have changed the text field.
     */
    @FXML
    public void updateFoodCategory(ActionEvent event) throws IOException, SQLException {
        /* Create a connection to the database */
        Connection conn = DriverManager.getConnection("jdbc:sqlite:Resturant.db");
        Statement st = conn.createStatement();
        /* Identify what item is selected */
        FoodCategory selectedItem = foodCategoryTable.getSelectionModel().getSelectedItem();
        /* Update the record based on what has been altered in the text fields */
        String update = "UPDATE FOODCATEGORY SET NAME = '" + foodCategoryField.getText() + "' WHERE ID = " + selectedItem.getId() + "";
        /* Run the above query by executing the update*/
        st.executeUpdate(update);
        System.out.println("Category Updated");
        /* Reload the database/tableview */
        initialize();
        /* Close all connections */
        st.close();
        conn.close();
    }
    
    /* 
     * This will delete the food category based on the the selection the user
     * makes. This will run when they click the delete button.
     */
    @FXML
    private void deleteFoodCategory(ActionEvent event) throws SQLException {
        /* Create a connection to the database */
        Connection conn = DriverManager.getConnection("jdbc:sqlite:Resturant.db");
        Statement st = conn.createStatement();
        /* Identify what item is selected */
        FoodCategory selectedItem = foodCategoryTable.getSelectionModel().getSelectedItem();
        /* Delete the record based on what has been selected */
        String delete = "DELETE FROM FOODCATEGORY WHERE id = " + selectedItem.getId() + ";";
        /* Execute the query above by executing the update*/
        st.executeUpdate(delete);
        System.out.println("Category Deleted");
        /* Reload the database/tableview */
        initialize();
        /* Close all connections */
        st.close();
        conn.close();
    }
    
    /* 
     * Runs when the exit button is clicked. It closes the anchor pane and
     * hence closes the whole window.
     */
    Stage stage;
    @FXML
    public void exit() {
        stage = (Stage) foodCategoryDialogue.getScene().getWindow();
        stage.close();
    }
    
    /* 
     * Runs when the help button is clicked. Opens up the food category help 
     * screen.
     */
    @FXML
    private void helpScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FoodCategoryHelpScreen.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Food Category Help");
        stage.setScene(scene);
        stage.show();
    }
}
