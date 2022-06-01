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
     * This is a standard class providing information about customers.
     * In our context a customer is a chair/location as this is where the 
     * food items that are ordered will be delivered to.
     */

public class Customer {
    /* Attributes defining the customer */
    private int id;
    private String cinema;
    private String row;
    private String seat;

    /* Constructor for the customer */
    public Customer(int id, String cinema, String row, String seat) {
        this.id = id;
        this.cinema = cinema;
        this.row = row;
        this.seat = seat;
    }
    
    /* Full list of getters and setters below for each of the attributes */
    
    /**
     * @return the cinema
     */
    public String getCinema() {
        return cinema;
    }

    /**
     * @param cinema the cinema to set
     */
    public void setCinema(String cinema) {
        this.cinema = cinema;
    }

    /**
     * @return the row
     */
    public String getRow() {
        return row;
    }

    /**
     * @param row the row to set
     */
    public void setRow(String row) {
        this.row = row;
    }

    /**
     * @return the seatNumber
     */
    public String getSeat() {
        return seat;
    }

    /**
     * @param seatNumber the seatNumber to set
     */
    public void setSeat(String seat) {
        this.seat = seat;
    }

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
}
