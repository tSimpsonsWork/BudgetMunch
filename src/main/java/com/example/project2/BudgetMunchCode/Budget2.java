package com.example.project2.BudgetMunchCode;

//packvicinity com.example.project2.Arely2;

import jakarta.persistence.*;

//@Entity signals to the JPA that this class should be treated as a table
//Columns are derived from Entity variables so @Columns is optional
@Entity
@Table //This creates the table for the database
public class Budget2 {
    @Id
//    @SequenceGenerator(
//            name = "budget2_sequence",
//            sequenceName = "budget2_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "budget2_sequence"
//    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //variables = columns: 5
    private Long id;
    private String name;
    private Integer price_level;
    private Double rating;
    //@Transient
    private String vicinity;

    //Empty Constructor
    public Budget2(){

    }
    //Budget2 constructor
    public Budget2(Long id, String name, Integer price_level, double rating, String vicinity) {
        this.id = id;
        this.name = name;
        this.price_level = price_level;
        this.rating = rating;
        this.vicinity = vicinity;

    }
    public Budget2(String name, Integer price_level, double rating, String vicinity) {
        this.name = name;
        this.price_level = price_level;
        this.rating = rating;
        this.vicinity = vicinity;
    }
    //all the getters and setters for the variables
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice_level() {
        return price_level;
    }

    public void setPrice_level(Integer price_level) {
        this.price_level = price_level;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    @Override //toString allows to have actual text instead of numerization
    public String toString() {
        return "Budget2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price_level='" + price_level + '\'' +
                ", rating=" + rating +
                ", vicinity=" + vicinity +
                '}';
    }
}
