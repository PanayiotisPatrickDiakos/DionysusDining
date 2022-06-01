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

public class orderCustomerSelectController {
    /* Defining all the FXML items and the database */
    Database database = new Database();
    MainController mainController = new MainController();

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
    @FXML
    private TableView<TempOrder> tempOrderNumber;
   
    
    
    /* Setting up the database and the table view to link together */
    @FXML
    public void initialize() throws SQLException {
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
    
    @FXML
    public void addCustomerToOrder(ActionEvent event) throws IOException, SQLException {
        /* Create a connection to the database */
        Connection conn = DriverManager.getConnection("jdbc:sqlite:Resturant.db");
        Statement st = conn.createStatement();
        /* Identify what item is selected */
        Customer selectedItem = customerTable.getSelectionModel().getSelectedItem();
        /* Update the record based on what has been altered in the text fields */
        String update = "UPDATE TEMPORDER SET CINEMA = " 
                + cinemaField.getText() 
                + ", ROW = '" 
                + rowField.getText() 
                + "', SEAT = " 
                + seatField.getText() + " ";
        /* Run the above query by calling executeQuery*/
        st.execute(update);
        /* https://www.youtube.com/watch?v=0gDxKHnWtHw */
        System.out.println("Customer added to Order");
        System.out.println("Order Sent and added to History");
        /*Insert Temporary Order to Order History */
        String insert = "INSERT INTO ORDERHISTORY "
                + "SELECT * FROM TEMPORDER;";
        st.execute(insert);
        /* Remove all from Temporary Order */
        String delete = "DELETE FROM TEMPORDER;";
        st.execute(delete);
        /*Go back to Main Screen */
        Stage stage = (Stage) customerSave.getScene().getWindow();
        stage.close();
        /* Close all connections */
        st.close();
        conn.close();
    }
    
    
}
