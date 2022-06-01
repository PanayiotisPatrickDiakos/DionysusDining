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
public class FoodItemsController {
    /* Defining all the FXML items and the database */
    Database database = new Database();
    File file;
    @FXML
    private Button foodItemUpdate;
    @FXML
    private Button foodItemDelete;
    @FXML
    private TableView<FoodItems> foodItemTable;
    @FXML
    private TableColumn<FoodItems, Integer> foodItemTableC1;
    @FXML
    private TableColumn<FoodItems, String> foodItemTableC2;
    @FXML
    private TableColumn<FoodItems, String> foodItemTableC3;
    @FXML
    private TableColumn<FoodItems, String> foodItemTableC4;
    @FXML
    private TableColumn<FoodItems, String> foodItemTableC5;
    @FXML
    private TableColumn<FoodItems, String> foodItemTableC6;
    @FXML
    private ImageView foodItemImage;
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
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
        ObservableList<FoodItems> foodItemList = FXCollections.observableArrayList();
        foodItemList = database.getFoodItems();
        /* Set list into the table */
        foodItemTable.setItems(foodItemList);
        /* Set columns based on the attributes */
        foodItemTableC2.setCellValueFactory(new PropertyValueFactory<>("name"));
        foodItemTableC3.setCellValueFactory(new PropertyValueFactory<>("price"));   
        foodItemTableC4.setCellValueFactory(new PropertyValueFactory<>("category"));   
        foodItemTableC5.setCellValueFactory(new PropertyValueFactory<>("description"));   
        foodItemTableC6.setCellValueFactory(new PropertyValueFactory<>("availability"));
        
        /* Ensuring population of items */
        if(categoryComBox.getItems().isEmpty()) {
            populateCategory();
        }
        
        if(availabilityComBox.getItems().isEmpty()) {
            availabilityComBox.getItems().add("In Stock");
            availabilityComBox.getItems().add("Out of Stock");
        }

    }
    
    /* 
     * This adds a food items to the the database.
     * This will run when the save button is clicked.
     * This will also run initialize at the top to link have the new addition
     * show up in the table view.
     */
    @FXML
    public void addFoodItem(ActionEvent event) throws IOException, SQLException {
        /* Check label to give user additional information about errors */
        if(nameField.getText().isEmpty() || priceField.getText().isEmpty()
                || categoryComBox.getItems().isEmpty()
                || descriptionField.getText().isEmpty()
                || availabilityComBox.getItems().isEmpty()) {
            checkEmptyLabel.setText("Please fill in all fields.");
        } else {
            checkEmptyLabel.setText("");
        /* Standard connection to the database */
        Connection conn = DriverManager.getConnection("jdbc:sqlite:Resturant.db");
        Statement st = conn.createStatement();
        /* SQL query to insert items into the database */
        PreparedStatement pst = conn.prepareStatement(
                "INSERT OR IGNORE INTO FOODITEMS (name, price, category, description, availability) VALUES (?,?,?,?,?)" );
        /* Running statement above based on what is in the text field */
        pst.setString(1, nameField.getText());
        pst.setString(2, priceField.getText());
        pst.setString(3, (String) categoryComBox.getSelectionModel().getSelectedItem());
        pst.setString(4, descriptionField.getText());
        pst.setString(5, (String) availabilityComBox.getSelectionModel().getSelectedItem());
        /* Executing the above statement */
        pst.executeUpdate();
        System.out.println("Food Item Added");
        /* Resetting form */
        nameField.clear();
        priceField.clear();
        categoryComBox.valueProperty().set(null);
        descriptionField.clear();
        availabilityComBox.valueProperty().set(null);
        /* Reload the database/tableview */
        initialize();
        /* Close all connections */
        st.close();
        conn.close();
        
        }
    }
    
    /* 
     * This identifies when a user clicks on the table and will load the 
     * corresponding selection into the text fields . 
     */
    @FXML 
    public void userDidChangeSelection() {
        /* Identify the user selection */
        FoodItems selectedItem = foodItemTable.getSelectionModel().getSelectedItem();
//        idField.setText(selectedItem.getId());
        /* Set what the user has selected to the text field */
        nameField.setText(selectedItem.getName());
        priceField.setText(selectedItem.getPrice());
        categoryComBox.getSelectionModel().select(selectedItem.getCategory());
        descriptionField.setText(selectedItem.getDescription());
        availabilityComBox.getSelectionModel().select(selectedItem.getAvailability());
        System.out.println("Food Item Selected");
    }
    
    /* 
     * This will update the food item based on the changes the user makes 
     * when they click the update button after they have changed the text field.
     */
    @FXML
    public void updateFoodItem (ActionEvent event) throws IOException, SQLException {
        /* Create a connection to the database */
        Connection conn = DriverManager.getConnection("jdbc:sqlite:Resturant.db");
        Statement st = conn.createStatement();
        /* Identify what item is selected */
        FoodItems selectedItem = foodItemTable.getSelectionModel().getSelectedItem();
        /* Update the record based on what has been altered in the text fields */
        String update = "UPDATE FOODITEMS SET name = '" + nameField.getText() + "', "
                + "price = '" + priceField.getText() + "', "
                + "category = '" + categoryComBox.getSelectionModel().getSelectedItem() + "', "
                + "description = '" + descriptionField.getText() + "', "
                + "availability = '" + availabilityComBox.getSelectionModel().getSelectedItem() + "' "
                + "WHERE id = " + selectedItem.getId() + "";
        /* Run the above query by executing the update*/
        st.executeUpdate(update);
        System.out.println("Food Item Updated");
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
    private void deleteFoodItem(ActionEvent event) throws SQLException {
        /* Create a connection to the database */
        Connection conn = DriverManager.getConnection("jdbc:sqlite:Resturant.db");
        Statement st = conn.createStatement();
        /* Identify what item is selected */
        FoodItems selectedItem = foodItemTable.getSelectionModel().getSelectedItem();
        /* Delete the record based on what has been selected */
        String delete = "DELETE FROM FOODITEMS WHERE id = " + selectedItem.getId() + ";";
        /* Execute the query above by executing the update*/
        st.executeUpdate(delete);
        System.out.println("Food Item Deleted");
        /* Reload the database/tableview */
        initialize();
        /* Close all connections */
        st.close();
        conn.close();
    }
    
    /* 
     * Runs when the add category button is clicked. It opens the food category
     * form for the user.
     */
    @FXML
    private void addFoodCategoryWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FoodCategory.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Food Category");
        stage.setScene(scene);
        stage.show();
   }
    
    /* 
     * Populates that categories so the user have something to select when they
     * us the combo box.
     */
    private void populateCategory() throws SQLException {
        /* Create a connection to the database */
        Connection conn = DriverManager.getConnection("jdbc:sqlite:Resturant.db");
        Statement st = conn.createStatement();
        /* Add the food categories */
        String query = "SELECT * FROM FOODCATEGORY";
        ResultSet rs = st.executeQuery(query);
        ObservableList<String> foodCategoryList = FXCollections.observableArrayList();
        /* Add the food categories until all added*/
        while (rs.next()) {
            foodCategoryList.add(rs.getString(2));
        }
        /* Close all connections */
        st.close();
        conn.close();
        /* Set the combo box based on the selection*/
        categoryComBox.setItems(foodCategoryList);
    }
    
//    @FXML
//    private void browseImage(ActionEvent event) {
//        FileChooser filechoose = new FileChooser();
//        FileChooser.ExtensionFilter jpgext = new FileChooser.ExtensionFilter("JPG files(*.jpg)", "*.JPG");
//        FileChooser.ExtensionFilter pngext = new FileChooser.ExtensionFilter("PNG files(*.png)", "*.PNG");
//        
//        filechoose.getExtensionFilters().addAll(jpgext, pngext);
//        
//        file = filechoose.showOpenDialog(null);
//        BufferedImage buffer = ImageIO.read(file);
//        Image image = SwingFXUtils.toFXImage(buffer, null);
//        foodItemImage.setImage(image);
//    }
    
    /* 
     * Filters the table view based on the categories. Testing on drinks only 
     * here to prepare a move to the main screen, button was removed so this
     * code is now redundent.
     */
    @FXML
    public void filter(ActionEvent event) throws SQLException {
        /* Create a connection to the database */
        Connection conn = DriverManager.getConnection("jdbc:sqlite:Resturant.db");
        Statement st = conn.createStatement();
        /* Choose the fooditems, testing drinks for the main screen conversion */
        String query = "SELECT * FROM FOODITEMS WHERE Category = 'Drinks'";
        /* Run filter */
        st.executeQuery(query);
        /* Update table based on the filter */
        ObservableList<FoodItems> foodItemList = FXCollections.observableArrayList();
        foodItemList = database.getDrinksFoodItems();
        foodItemTable.setItems(foodItemList);
        foodItemTableC2.setCellValueFactory(new PropertyValueFactory<>("name"));
        foodItemTableC3.setCellValueFactory(new PropertyValueFactory<>("price"));   
        foodItemTableC4.setCellValueFactory(new PropertyValueFactory<>("category"));   
        foodItemTableC5.setCellValueFactory(new PropertyValueFactory<>("description"));   
        foodItemTableC6.setCellValueFactory(new PropertyValueFactory<>("availability"));
        /* Close all connections */
        st.close();
        conn.close();
        System.out.println("Items Filtered");   
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
