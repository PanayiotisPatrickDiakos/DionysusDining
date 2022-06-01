/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.infs2605.restaurantsystem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author bradl
 */
public class Database {
    final private String database = "jdbc:sqlite:Resturant.db";
    
    public void setupDatabase() throws SQLException {
       Connection conn = DriverManager.getConnection(database);
       Statement st = conn.createStatement();
        String createStatement = "CREATE TABLE IF NOT EXISTS USERS "
           + "(ID INTEGER PRIMARY KEY autoincrement, "
           + "FIRSTNAME TEXT NOT NULL, "
           + "LASTNAME TEXT NOT NULL, "
           + "USERNAME TEXT NOT NULL, "
           + "PASSWORD TEXT NOT NULL "
           + ");";
        st.execute(createStatement);
        String insertQuery = "INSERT OR IGNORE INTO Users (ID, FIRSTNAME, LASTNAME, USERNAME, PASSWORD) "
           + "VALUES(999,'Ben', 'Kemp', 'emp1', '1234');";
        st.execute(insertQuery);
        
        String createStatement1 = "CREATE TABLE IF NOT EXISTS CUSTOMER "
           + "(ID INTEGER PRIMARY KEY autoincrement, "
           + "CINEMA INTEGER NOT NULL, "
           + "ROW TEXT NOT NULL, "
           + "SEAT INTEGER NOT NULL "
           + ");";
        st.execute(createStatement1);
        String insertQuery1 = "INSERT OR IGNORE INTO Customer (ID, CINEMA, ROW, SEAT) "
           + "VALUES(999, '1' , 'A', '1');";
        String createStatement2 = "CREATE TABLE IF NOT EXISTS FOODITEMS "
                + "(ID INTEGER PRIMARY KEY autoincrement, "
                + "IMAGE BLOB, "
                + "NAME TEXT NOT NULL, "
                + "PRICE TEXT NOT NULL, "
                + "CATEGORY TEXT NOT NULL, "
                + "DESCRIPTION TEXT NOT NULL, "
                + "AVAILABILITY TEXT NOT NULL "
                + ");";
        st.execute(createStatement2);
        
        String insertQuery2 = "INSERT OR IGNORE INTO FOODITEMS (ID, NAME, PRICE, CATEGORY, DESCRIPTION, AVAILABILITY) "
                + "VALUES(1, 'Coke', '$10', 'Drinks', '750ml', 'In Stock');";
        String insertQuery3 = "INSERT OR IGNORE INTO FOODITEMS (ID, NAME, PRICE, CATEGORY, DESCRIPTION, AVAILABILITY) "
                + "VALUES(2, 'Nachos', '$20', 'Meals', 'Tortilla chips and sauce', 'In Stock');";
        String insertQuery4 = "INSERT OR IGNORE INTO FOODITEMS (ID, NAME, PRICE, CATEGORY, DESCRIPTION, AVAILABILITY) "
                + "VALUES(3, 'Popcorn', '$5', 'Snacks', 'Salted', 'In Stock');";
        st.execute(insertQuery1);
        st.execute(insertQuery2);
        st.execute(insertQuery3);
        st.execute(insertQuery4);
        
        String createStatement3 = "CREATE TABLE IF NOT EXISTS FOODCATEGORY "
                + "(ID INTEGER PRIMARY KEY autoincrement, "
                + "NAME TEXT NOT NULL"
                + ");";
        st.execute(createStatement3);

        String insertQuery5 = "INSERT OR IGNORE INTO FOODCATEGORY (ID, NAME) "
                + "VALUES(1, 'Drinks');";
        String insertQuery6 = "INSERT OR IGNORE INTO FOODCATEGORY (ID, NAME) "
                + "VALUES(2, 'Meals');";
        String insertQuery7 = "INSERT OR IGNORE INTO FOODCATEGORY (ID, NAME) "
                + "VALUES(3, 'Snacks');";
        st.execute(insertQuery5);
        st.execute(insertQuery6);
        st.execute(insertQuery7);
        
        String dropstatement = "DROP TABLE IF EXISTS TEMPORDER;";
        st.execute(dropstatement);
        String createStatement4 = "CREATE TABLE IF NOT EXISTS TEMPORDER "
                + "(ID INTEGER PRIMARY KEY autoincrement, "
                + "ORDER_NUMBER TEXT NOT NULL, "
                + "ITEM_NAME TEXT NOT NULL, "
                + "ITEM_PRICE TEXT NOT NULL, "
                + "ITEM_QUANTITY TEXT NOT NULL, "
                + "PRICE TEXT NOT NULL, "
                + "CINEMA INTEGER, "
                + "ROW TEXT, "
                + "SEAT INTEGER "
                + ");";
        st.execute(createStatement4);
        
        String createStatement5 = "CREATE TABLE IF NOT EXISTS ORDERHISTORY "
                + "(ID INTEGER PRIMARY KEY autoincrement, "
                + "ORDER_NUMBER TEXT NOT NULL, "
                + "ITEM_NAME TEXT NOT NULL, "
                + "ITEM_PRICE TEXT NOT NULL, "
                + "ITEM_QUANTITY TEXT NOT NULL, "
                + "PRICE TEXT NOT NULL, "
                + "CINEMA INTEGER, "
                + "ROW TEXT, "
                + "SEAT INTGER "
                + ");";
        st.execute(createStatement5);
      
        st.close();
        conn.close();
        
    }
    
    public boolean login(String username, String password) throws SQLException {
        Connection conn = DriverManager.getConnection(database);
        PreparedStatement pst = conn.prepareStatement(
            "SELECT count(1) FROM Users WHERE USERNAME = ? AND PASSWORD = ?"
        );
        pst.setString(1, username);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();
        rs.next();
        System.out.println(rs.getInt(1));
        boolean result = rs.getInt(1) == 1;
        System.out.println(result);
        pst.close();
        conn.close();
        return result;      
    }
    
    public ObservableList<Customer> getCustomer() throws SQLException {
        Connection conn = DriverManager.getConnection(database);
        Statement st = conn.createStatement();
        String query = "SELECT id, cinema, row, seat FROM CUSTOMER";
        ResultSet rs = st.executeQuery(query);
        
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        while (rs.next()) {
            customerList.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
        }

        st.close();
        conn.close();
        return customerList;
    }
    
    public ObservableList<FoodItems> getFoodItems() throws SQLException {
        Connection conn = DriverManager.getConnection(database);
        Statement st = conn.createStatement();
        String query = "SELECT id, name, price, category, description, availability FROM FOODITEMS;";
        ResultSet rs = st.executeQuery(query);

        ObservableList<FoodItems> foodItemList = FXCollections.observableArrayList();
        while (rs.next()) {
            foodItemList.add(new FoodItems(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
        }
        

        st.close();
        conn.close();
        return foodItemList;
    }
    
    public ObservableList<FoodItems> getDrinksFoodItems() throws SQLException {
        Connection conn = DriverManager.getConnection(database);
        Statement st = conn.createStatement();
        String query = "SELECT id, name, price, category, description, availability FROM FOODITEMS WHERE category = 'Drinks'";
        ResultSet rs = st.executeQuery(query);

        ObservableList<FoodItems> foodItemList = FXCollections.observableArrayList();
        while (rs.next()) {
            foodItemList.add(new FoodItems(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
        }
        st.close();
        conn.close();
        return foodItemList;
    }
    
    public ObservableList<FoodItems> getMealsFoodItems() throws SQLException {
        Connection conn = DriverManager.getConnection(database);
        Statement st = conn.createStatement();
        String query = "SELECT id, name, price, category, description, availability FROM FOODITEMS WHERE category = 'Meals'";
        ResultSet rs = st.executeQuery(query);

        ObservableList<FoodItems> foodItemList = FXCollections.observableArrayList();
        while (rs.next()) {
            foodItemList.add(new FoodItems(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
        }
        st.close();
        conn.close();
        return foodItemList;
    }
    
    public ObservableList<FoodItems> getSnacksFoodItems() throws SQLException {
        Connection conn = DriverManager.getConnection(database);
        Statement st = conn.createStatement();
        String query = "SELECT id, name, price, category, description, availability FROM FOODITEMS WHERE category = 'Snacks'";
        ResultSet rs = st.executeQuery(query);

        ObservableList<FoodItems> foodItemList = FXCollections.observableArrayList();
        while (rs.next()) {
            foodItemList.add(new FoodItems(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
        }
        st.close();
        conn.close();
        return foodItemList;
    }
      
    public ObservableList<FoodCategory> getFoodCategory() throws SQLException {
        Connection conn = DriverManager.getConnection(database);
        Statement st = conn.createStatement();
        String query = "SELECT id, name FROM FOODCATEGORY;";
        ResultSet rs = st.executeQuery(query);

        ObservableList<FoodCategory> foodCategoryList = FXCollections.observableArrayList();
        while (rs.next()) {
            foodCategoryList.add(new FoodCategory(rs.getInt(1), rs.getString(2)));
        }
        
        st.close();
        conn.close();
        return foodCategoryList;
    }
    
    public ObservableList<TempOrder> getTempOrder() throws SQLException {
        Connection conn = DriverManager.getConnection(database);
        Statement st = conn.createStatement();
        String query = "SELECT ID, ORDER_NUMBER, ITEM_NAME, ITEM_PRICE, ITEM_QUANTITY, "
                + "PRICE FROM TEMPORDER;";
        ResultSet rs = st.executeQuery(query);

        ObservableList<TempOrder> tempOrderList = FXCollections.observableArrayList();
        while (rs.next()) {
            tempOrderList.add(new TempOrder(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6)));
        }
        st.close();
        conn.close();
        return tempOrderList;
    }
    
    public ObservableList<OrderHistory> getOrderHistory() throws SQLException {
        Connection conn = DriverManager.getConnection(database);
        Statement st = conn.createStatement();
        String query = "SELECT * FROM ORDERHISTORY;";
        ResultSet rs = st.executeQuery(query);

        ObservableList<OrderHistory> orderHistoryList = FXCollections.observableArrayList();
        while (rs.next()) {
            orderHistoryList.add(new OrderHistory(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getInt(9)));
        }
        

        st.close();
        conn.close();
        return orderHistoryList;
    }

    

    public Connection getConnection(){
        try{
            Connection connection = DriverManager.getConnection(database);
            return connection;
        }catch(SQLException ex){
        }
        return null;
    }
}
