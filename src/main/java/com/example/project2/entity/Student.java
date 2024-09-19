package com.example.project2.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;


@Entity
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
@Component
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Resturant Name")
    private String name;

    @Column(name= "Rating")
    private double rating;

    @Column(name = "Vicinity")
    private String vicinity;

    @Column(name = "Price Level")
    private Integer priceLevel;


}
