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
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Bradley
 */
public class MainController {
    Database database = new Database();
    @FXML
    private Label lblWelcome;
    @FXML
    private TableView <FoodItems> foodItemTableMain;
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
    private Label totalPrice;
    @FXML
    private TextField textQuantity;
    @FXML
    private TableView<TempOrder> orderTableMain;
    @FXML
    private TableColumn<TempOrder, String> orderTableMainC1;
    @FXML
    private TableColumn<TempOrder, Integer> orderTableMainC2;
    @FXML
    private TableColumn<TempOrder, Integer> orderTableMainC3;
    @FXML
    private TableColumn<TempOrder, Integer> orderTableMainC4;
    @FXML
    private TextField orderFoodName;
    @FXML
    private TextField orderFoodPrice;
    @FXML
    public TextField orderFoodNumber;
    @FXML
    public BorderPane mainPane;
    
    @FXML
    public void welcomeLabel(String firstname) {
        lblWelcome.setText("Welcome" + firstname);
    }
    
    @FXML
    public void initialize() throws SQLException {
        ObservableList<FoodItems> foodItemList = FXCollections.observableArrayList();
        foodItemList = database.getFoodItems();
        foodItemTableMain.setItems(foodItemList);
        foodItemTableC2.setCellValueFactory(new PropertyValueFactory<>("name"));
        foodItemTableC3.setCellValueFactory(new PropertyValueFactory<>("price"));   
        foodItemTableC4.setCellValueFactory(new PropertyValueFactory<>("category"));   
        foodItemTableC5.setCellValueFactory(new PropertyValueFactory<>("description"));   
        foodItemTableC6.setCellValueFactory(new PropertyValueFactory<>("availability"));
        
        fillOrderTable();
        totalPrice.setText(calculateTotalPrice());

    }
    
    public void fillOrderTable() throws SQLException {
        ObservableList<TempOrder> tempOrderList = FXCollections.observableArrayList();
        tempOrderList = database.getTempOrder();
        orderTableMain.setItems(tempOrderList);
        orderTableMainC1.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        orderTableMainC2.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        orderTableMainC3.setCellValueFactory(new PropertyValueFactory<>("itemQuantity"));
        orderTableMainC4.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
 
    
    
    
    @FXML
    public void customersWindow(ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(getClass().getResource("Customer.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Customer");
        stage.setScene(scene);
        stage.show();
   }
    
    @FXML
    public void foodItemsWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FoodItems.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Food Items");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void orderHistoryWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("OrderHistory.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Order History");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void foodCategoryWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FoodCategory.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Food Items");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void drinksFilter(ActionEvent event) throws SQLException {
        System.out.println("Button Click1");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:Resturant.db");
        Statement st = conn.createStatement();
        String query = "SELECT * FROM FOODITEMS WHERE Category = 'Drinks'";
        st.executeQuery(query);
        
        ObservableList<FoodItems> foodItemList = FXCollections.observableArrayList();
        foodItemList = database.getDrinksFoodItems();
        foodItemTableMain.setItems(foodItemList);
        foodItemTableC2.setCellValueFactory(new PropertyValueFactory<>("name"));
        foodItemTableC3.setCellValueFactory(new PropertyValueFactory<>("price"));   
        foodItemTableC4.setCellValueFactory(new PropertyValueFactory<>("category"));   
        foodItemTableC5.setCellValueFactory(new PropertyValueFactory<>("description"));   
        foodItemTableC6.setCellValueFactory(new PropertyValueFactory<>("availability"));
        
        st.close();
        conn.close();
        System.out.println("Button Clicked2");   
    }
    @FXML
    public void mealsFilter(ActionEvent event) throws SQLException {
        System.out.println("Button Click1");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:Resturant.db");
        Statement st = conn.createStatement();
        String query = "SELECT * FROM FOODITEMS WHERE Category = 'Meals'";
        st.executeQuery(query);
        
        ObservableList<FoodItems> foodItemList = FXCollections.observableArrayList();
        foodItemList = database.getMealsFoodItems();
        foodItemTableMain.setItems(foodItemList);
        foodItemTableC2.setCellValueFactory(new PropertyValueFactory<>("name"));
        foodItemTableC3.setCellValueFactory(new PropertyValueFactory<>("price"));   
        foodItemTableC4.setCellValueFactory(new PropertyValueFactory<>("category"));   
        foodItemTableC5.setCellValueFactory(new PropertyValueFactory<>("description"));   
        foodItemTableC6.setCellValueFactory(new PropertyValueFactory<>("availability"));
        
        st.close();
        conn.close();
        System.out.println("Button Clicked2");   
    }
    
    @FXML
    public void snacksFilter(ActionEvent event) throws SQLException {
        System.out.println("Button Click1");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:Resturant.db");
        Statement st = conn.createStatement();
        String query = "SELECT * FROM FOODITEMS WHERE Category = 'Snacks'";
        st.executeQuery(query);
        
        ObservableList<FoodItems> foodItemList = FXCollections.observableArrayList();
        foodItemList = database.getSnacksFoodItems();
        foodItemTableMain.setItems(foodItemList);
        foodItemTableC2.setCellValueFactory(new PropertyValueFactory<>("name"));
        foodItemTableC3.setCellValueFactory(new PropertyValueFactory<>("price"));   
        foodItemTableC4.setCellValueFactory(new PropertyValueFactory<>("category"));   
        foodItemTableC5.setCellValueFactory(new PropertyValueFactory<>("description"));   
        foodItemTableC6.setCellValueFactory(new PropertyValueFactory<>("availability"));
        
        st.close();
        conn.close();
        System.out.println("Button Clicked2");   
    }
     
    @FXML
    private void selectionFillText(){
        FoodItems selectedItem = foodItemTableMain.getSelectionModel().getSelectedItem();

        orderFoodName.setText(selectedItem.getName());
        orderFoodPrice.setText(selectedItem.getPrice());
    }

    @FXML
    private void addTextToTempOrder() throws SQLException {
        totalPrice.setText(calculateTotalPrice());

        Connection conn = DriverManager.getConnection("jdbc:sqlite:Resturant.db");
        Statement st = conn.createStatement();
        
        String itemPriceString = orderFoodPrice.getText();        
        int itemPriceInt = Integer.parseInt(itemPriceString.substring(1, itemPriceString.length()));
        System.out.println(itemPriceInt);
        
        String itemQuantityString = textQuantity.getText();        
        int itemQuantityInt = Integer.parseInt(itemQuantityString);
        System.out.println(itemQuantityInt);
        
        int price = itemQuantityInt * itemPriceInt;
        System.out.println(price);  
        
        String insert = "INSERT INTO TEMPORDER (ORDER_NUMBER, ITEM_NAME, ITEM_PRICE, ITEM_QUANTITY, PRICE) "
                + "VALUES(?, ?, ?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(insert);
        pst.setString(1, orderFoodNumber.getText());
        pst.setString(2, orderFoodName.getText());
        pst.setInt(3, itemPriceInt);
        pst.setInt(4, itemQuantityInt);
        pst.setInt(5, price);
        
        pst.executeUpdate();
        
        st.close();
        conn.close();
        System.out.println("it worked");
        
        fillOrderTable();
        String total = calculateTotalPrice();
        totalPrice.setText(total);
        orderFoodName.clear();
        textQuantity.clear();
        orderFoodPrice.clear();
    }
    
    @FXML
    private void deleteOrderItem(ActionEvent event) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:Resturant.db");
        Statement st = conn.createStatement();
        TempOrder selectedItem = orderTableMain.getSelectionModel().getSelectedItem();
        selectedItem.getId();
        String delete = "DELETE FROM TEMPORDER WHERE id = " + selectedItem.getId() + ";";
        st.executeUpdate(delete);
        
        initialize();
        st.close();
        conn.close();
    }
    
    private String calculateTotalPrice() {
        int sum = 0;
        
        for (TempOrder value : orderTableMain.getItems()) {
            sum = sum + value.getPrice();
        }
        
        String total;
        total = Integer.toString(sum);
        return total;
    }
    
    @FXML
    public void submitToOrderHistory(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("OrderCustomerSelect.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Select Customer");
        stage.setScene(scene);
        stage.show();
    }
        
    
//    private void orderCustomerSelectionWindow() throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("OrderCustomerSelect.fxml"));
//        Stage stage = new Stage();
//        Scene scene = new Scene(root);
//        stage.setTitle("Customer");
//        stage.setScene(scene);
//        stage.show();
//    }
    

    @FXML
    private void exitConfirmation() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ExitScreen.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Exit Application");
        stage.setScene(scene);
        stage.show();
    }
Stage stage;
@FXML
    private void logOff() throws IOException {
	stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Exit Application");
        stage.setScene(scene);
        stage.show();
    }

@FXML
    private void aboutScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AboutScreen.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("About");
        stage.setScene(scene);
        stage.show();
    }

@FXML
    private void helpScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainScreenHelpScreen.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("About");
        stage.setScene(scene);
        stage.show();
    }
    
}
