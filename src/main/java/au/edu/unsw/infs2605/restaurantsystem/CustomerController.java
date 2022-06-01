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
 * This is built to control all interaction with the customer screen.
 */

public class CustomerController {
    /* Defining all the FXML items and the database */
    Database database = new Database();
    @FXML
    private TextField cinemaField;
    @FXML
    private TextField rowField;
    @FXML
    private TextField seatField;
    @FXML
    private Button customerDelete;
    @FXML
    private Button customerUpdate;
    @FXML
    private Button customerSave;
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, Integer> customerTableC1;
    @FXML
    private TableColumn<Customer, String> customerTableC2;
    @FXML
    private TableColumn<Customer, Integer> customerTableC3;
    @FXML
    private AnchorPane customerDialogue;
    @FXML
    private AnchorPane deleteDialogue;
    
    /* Setting up the database and the table view to link together */
    @FXML
    public void initialize() throws SQLException {
        database = new Database();
        /* Retrieve from the database to get all the customers */
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        customerList = database.getCustomer();
        /* Set list into the table */
        customerTable.setItems(customerList);
        /* Set columns into the tables columns */
        customerTableC1.setCellValueFactory(new PropertyValueFactory<>("cinema"));
        customerTableC2.setCellValueFactory(new PropertyValueFactory<>("row"));
        customerTableC3.setCellValueFactory(new PropertyValueFactory<>("seat"));
        System.out.println("Loaded Customers");
    }
    
    /* 
     * This adds a customer to the the database.
     * This will run when the save button is clicked.
     * This will also run initialize at the top to link have the new addition
     * show up in the table view.
     */
    @FXML
    public void addCustomer(ActionEvent event) throws IOException, SQLException {
        /* Standard connection to the database */
        Connection conn = DriverManager.getConnection("jdbc:sqlite:Resturant.db");
        Statement st = conn.createStatement();
        /* SQL query to insert items into the database */
        PreparedStatement pst = conn.prepareStatement(
            "INSERT OR IGNORE INTO Customer (cinema, row, seat) VALUES (?,?,?)" );
        /* Running statement above based on what is in the text fields */
        pst.setString(1, cinemaField.getText());
        pst.setString(2, rowField.getText());
        pst.setString(3, seatField.getText());
        /* Executing the statement above */
        pst.executeUpdate();
        System.out.println("Customer added");
        /* Reload the database/tableview */
        initialize();
        /* Closing all connections */
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
        Customer selectedItem = customerTable.getSelectionModel().getSelectedItem();
        /* Set what the user has selected to the text fields */
        cinemaField.setText(selectedItem.getCinema());
        rowField.setText(selectedItem.getRow());
        seatField.setText(selectedItem.getSeat());
        System.out.println("Customer selected");
    }
    
    /* 
     * This will update the customer based on the changes the user makes when
     * they click the update button after they have changed the text fields.
     */
    @FXML
    public void updateCustomer(ActionEvent event) throws IOException, SQLException {
        /* Create a connection to the database */
        Connection conn = DriverManager.getConnection("jdbc:sqlite:Resturant.db");
        Statement st = conn.createStatement();
        /* Identify what item is selected */
        Customer selectedItem = customerTable.getSelectionModel().getSelectedItem();
        /* Update the record based on what has been altered in the text fields */
        String update = "UPDATE Customer SET cinema = '" 
                + cinemaField.getText() 
                + "', row = '" 
                + rowField.getText() 
                + "', seat = " 
                + seatField.getText() 
                + " WHERE id = " 
                + selectedItem.getId() + "";
        /* Run the above query by calling executeQuery*/
        executeQuery(update);
        /* https://www.youtube.com/watch?v=0gDxKHnWtHw */
        System.out.println("Customer updated");
        /* Reload the database/tableview */
        initialize();
        /* Close all connections */
        st.close();
        conn.close();
    }
    
    /* 
     * This will delete the customer based on the the selection the user makes.
     * This will run when they click the delete button.
     */
    @FXML
    private void deleteCustomer(ActionEvent event) throws SQLException {
        /* Create a connection to the database */
        Connection conn = DriverManager.getConnection("jdbc:sqlite:Resturant.db");
        Statement st = conn.createStatement();
        /* Identify what item is selected */
        Customer selectedItem = customerTable.getSelectionModel().getSelectedItem();
        /* Delete the record based on what has been selected */
        String delete = "DELETE FROM Customer WHERE id = " + selectedItem.getId() + ";";
        /* Execute the query above by calling executeQuery */
        executeQuery(delete);
        System.out.println("Customer Deleted");
        /* Reload the database/tableview */
        initialize();
        /* Close all connections */
        st.close();
        conn.close();
        //back();
    }
        
    /* 
     * Runs the query sent from the update/deletion of a customer.
     * Connects and executes the update.
     */
    private void executeQuery(String query) throws SQLException {
        Connection conn = database.getConnection();
        Statement st = conn.createStatement();
        st.executeUpdate(query);
    }
    
    
    /* 
     * Runs when the exit button is clicked. It closes the anchor pane and
     * hence closes the whole window.
     */
    Stage stage;
    @FXML
    public void exit() {
        stage = (Stage) customerDialogue.getScene().getWindow();
        stage.close();
    }
    
    /* 
     * Runs when the help button is clicked. Opens up the customer help screen.
     */
    @FXML
    private void helpScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CustomerHelpScreen.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Customer Help");
        stage.setScene(scene);
        stage.show();
    }
    
//    @FXML
//    private void deleteConfirmation() throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("CustomerDeleteScreen.fxml"));
//        Stage stage = new Stage();
//        Scene scene = new Scene(root);
//        stage.setTitle("Delete Confirmation");
//        stage.setScene(scene);
//        stage.show();
//    }
//    
//    @FXML
//    public void back() {
//        stage = (Stage) deleteDialogue.getScene().getWindow();
//        stage.close();
//    }
}
