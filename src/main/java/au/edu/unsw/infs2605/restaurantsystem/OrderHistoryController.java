/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.infs2605.restaurantsystem;

import java.io.File;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
//import java.awt.image.BufferedImage;


/**
 *
 * @author panay
 */

    /* 
     * This is built to control all interaction with the food item screen.
     * This will allow food items to be interacted with in the system.
     */
public class OrderHistoryController {
    /* Defining all the FXML items and the database */
    Database database = new Database();
    File file;
    @FXML
    private Button foodItemUpdate;
    @FXML
    private Button foodItemDelete;
    @FXML
    private TableView<OrderHistory> orderHistoryTable;
    @FXML
    private TableColumn<OrderHistory, Integer> orderHistoryTableC1;
    @FXML
    private TableColumn<OrderHistory, String> orderHistoryTableC2;
    @FXML
    private TableColumn<OrderHistory, String> orderHistoryTableC3;
    @FXML
    private TableColumn<OrderHistory, String> orderHistoryTableC4;
    @FXML
    private TableColumn<OrderHistory, String> orderHistoryTableC5;
    @FXML
    private TableColumn<OrderHistory, String> orderHistoryTableC6;
    @FXML
    private TableColumn<OrderHistory, Integer> orderHistoryTableC7;
    @FXML
    private TableColumn<OrderHistory, String> orderHistoryTableC8;
    @FXML
    private TableColumn<OrderHistory, Integer> orderHistoryTableC9;
    @FXML
    private TextField idField;
    @FXML
    private TextField orderNumberField;
    @FXML
    private TextField itemNameField;
    @FXML
    private TextField itemPriceField;
    @FXML
    private TextField itemQuantityField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField cinemaField;
    @FXML
    private TextField rowField;
    @FXML
    private TextField seatField;
    @FXML
    private ComboBox categoryComBox;
    @FXML
    private TextField descriptionField;
    @FXML
    private ComboBox availabilityComBox;
    @FXML
    private Button foodItemSave;
    @FXML
    private Button foodItemImageBrowse;
    @FXML
    private Label checkEmptyLabel;
    @FXML
    private AnchorPane foodItemDialogue;
    
    /* Setting up the database and the table view to link together */   
    @FXML
    public void initialize() throws SQLException {
        database = new Database();
        /* Retrieve from the database to get all the food categories */
        ObservableList<OrderHistory> orderHistoryList = FXCollections.observableArrayList();
        orderHistoryList = database.getOrderHistory();
        /* Set list into the table */
        orderHistoryTable.setItems(orderHistoryList);
        /* Set columns based on the attributes */
        orderHistoryTableC1.setCellValueFactory(new PropertyValueFactory<>("id"));
        orderHistoryTableC2.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));   
        orderHistoryTableC3.setCellValueFactory(new PropertyValueFactory<>("itemName"));   
        orderHistoryTableC4.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));   
        orderHistoryTableC5.setCellValueFactory(new PropertyValueFactory<>("itemQuantity"));
        orderHistoryTableC6.setCellValueFactory(new PropertyValueFactory<>("price"));
        orderHistoryTableC7.setCellValueFactory(new PropertyValueFactory<>("cinema"));
        orderHistoryTableC8.setCellValueFactory(new PropertyValueFactory<>("row"));
        orderHistoryTableC9.setCellValueFactory(new PropertyValueFactory<>("seat"));
  
    }

    
    /* 
     * This identifies when a user clicks on the table and will load the 
     * corresponding selection into the text fields . 
     */
    @FXML 
    public void userDidChangeSelection() {
        /* Identify the user selection */
        OrderHistory selectedItem = orderHistoryTable.getSelectionModel().getSelectedItem();
//        idField.setText(selectedItem.getId());
        /* Set what the user has selected to the text field */
        idField.setText(Integer.toString(selectedItem.getId()));
        orderNumberField.setText(selectedItem.getOrderNumber());
        itemNameField.setText(selectedItem.getItemName());
        itemPriceField.setText(selectedItem.getItemPrice());
        itemQuantityField.setText(selectedItem.getItemQuantity());
        priceField.setText(selectedItem.getPrice());
        cinemaField.setText(Integer.toString(selectedItem.getCinema()));
        rowField.setText(selectedItem.getRow());
        seatField.setText(Integer.toString(selectedItem.getSeat()));
        System.out.println("Order History Selected");
    }
    
    /* 
     * This will update the food item based on the changes the user makes 
     * when they click the update button after they have changed the text field.
     */
    @FXML
    public void updateOrderHistory (ActionEvent event) throws IOException, SQLException {
        /* Create a connection to the database */
        Connection conn = DriverManager.getConnection("jdbc:sqlite:Resturant.db");
        Statement st = conn.createStatement();
        /* Identify what item is selected */
        OrderHistory selectedItem = orderHistoryTable.getSelectionModel().getSelectedItem();
        /* Update the record based on what has been altered in the text fields */
        String update = "UPDATE ORDERHISTORY SET ORDER_NUMBER = '" + orderNumberField.getText() + "', "
                + "ITEM_NAME = '" + itemNameField.getText() + "', "
                + "ITEM_PRICE = '" + itemPriceField.getText() + "', "
                + "ITEM_QUANTITY = '" + itemQuantityField.getText() + "', "
                + "PRICE = '" + priceField.getText() + "', "
                + "CINEMA = " + cinemaField.getText() + ", "
                + "ROW = '" + rowField.getText() + "', "
                + "SEAT = " + seatField.getText() + " "
                + "WHERE id = " + selectedItem.getId() + "";
        /* Run the above query by executing the update*/
        st.executeUpdate(update);
        System.out.println("OrderHistory Updated");
        /* Reload the database/tableview */
        initialize();
        /* Close all connections */
        st.close();
        conn.close();
    }
    
    /* 
     * This will delete the food item based on the the selection the user
     * makes. This will run when they click the delete button.
     */
    @FXML
    private void deleteOrderHistory(ActionEvent event) throws SQLException {
        /* Create a connection to the database */
        Connection conn = DriverManager.getConnection("jdbc:sqlite:Resturant.db");
        Statement st = conn.createStatement();
        /* Identify what item is selected */
        OrderHistory selectedItem = orderHistoryTable.getSelectionModel().getSelectedItem();
        /* Delete the record based on what has been selected */
        String delete = "DELETE FROM ORDERHISTORY WHERE id = " + selectedItem.getId() + ";";
        /* Execute the query above by executing the update*/
        st.executeUpdate(delete);
        System.out.println("Order History Line Deleted");
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
        stage = (Stage) foodItemDialogue.getScene().getWindow();
        stage.close();
    }
    
    /* 
     * Runs when the help button is clicked. Opens up the food item help screen.
     */
    @FXML
    private void helpScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FoodItemHelpScreen.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Food Item Help");
        stage.setScene(scene);
        stage.show();
    }

}
