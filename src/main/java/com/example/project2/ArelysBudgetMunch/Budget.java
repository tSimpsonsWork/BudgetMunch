package com.example.project2.ArelysBudgetMunch;

//import jakarta.persistence.*;
//import jakarta.persistence.Id;

import jakarta.persistence.*;

//@Entity signals to the JPA that this class should be treated as a table
//Columns are derived from Entity variables so @Columns is optional
@Entity
//@Table name for database
@Table
//-----Step 1: Make Budget Class---------
public class Budget {

    //When you create a new entity you have to:
    //1. @Entity & 2.create id field and annotate with @Id
    @Id
    //SequenceGenerator & Generated Value go together
    //this is used with hibernate implementation of the JPA, which will create a
    //sequence of a budget table information which is shown on our console
    @SequenceGenerator(
            name = "budget_sequence",
            sequenceName = "budget_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "budget_sequence"
    )

    private Long id;
    private Double maxPrice;
    private String location;
    private String restaurantName;
    private String menuItem;
    private Double menuItemPrice;



    public Budget() {
    }

    public Budget(Long id,
                  Double maxPrice,
                  String location,
                  String restaurantName,
                  String menuItem,
                  Double menuItemPrice) {
        this.id = id;
        this.maxPrice = maxPrice;
        this.location = location;
        this.restaurantName = restaurantName;
        this.menuItem = menuItem;
        this.menuItemPrice = menuItemPrice;
    }

    public Budget(Double maxPrice, String location, String restaurantName, String menuItem, Double menuItemPrice) {
        this.maxPrice = maxPrice;
        this.location = location;
        this.restaurantName = restaurantName;
        this.menuItem = menuItem;
        this.menuItemPrice = menuItemPrice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

    public Double getMenuItemPrice() {
        return menuItemPrice;
    }

    public void setMenuItemPrice(Double menuItemPrice) {
        this.menuItemPrice = menuItemPrice;
    }

    @Override //typically used for toString, meant to override a method in the superclass
    public String toString() {
        return "Budget{" +
                "id=" + id +
                ", maxPrice=" + maxPrice +
                ", location='" + location + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                ", menuItem='" + menuItem + '\'' +
                ", menuItemPrice=" + menuItemPrice +
                '}';
    }


    //    @Budget //Budget should be the key
//    @SequenceGenerator(
//            name = "budget_sequence",
//            sequenceName = "budget_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "budget_sequence"
//    )

}
