/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.infs2605.restaurantsystem;

/**
 *
 * @author Bradley
 */

    /*
     * This is a standard class providing information about the food categories.
     * A food category are like the sections in the menu.
     */
public class FoodCategory {
    /* Attributes defining the food category */
    private int id;
    private String name;
    
    /* Constructor for the food category */
    public FoodCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    /* Full list of getters and setters below for each of the attributes */
            
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
