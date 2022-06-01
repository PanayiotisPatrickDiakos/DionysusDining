/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.infs2605.restaurantsystem;

import java.sql.Blob;

/**
 *
 * @author panay
 */

    /*
     * This is a standard class providing information about food items.
     * In our context a food item is a consumable by the customer.
     */
public class FoodItems {
    /* Attributes defining the food items */
    private int id;
//    private Blob image;
    private String name;
    private String price;
    private String category;
    private String description;
    private String availability;
    
    /* Constructor for the food items */
    public FoodItems(int id, String name, String price, String category, String description, String availability) {
        this.id = id;
//        this.image = image;
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.availability = availability;
    }
    
    /* Full list of getters and setters below for each of the attributes */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public Blob getImage() {
//        return image;
//    }
//
//    public void setImage(Blob image) {
//        this.image = image;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }  
    
}
