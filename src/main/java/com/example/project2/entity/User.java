
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
@Table(name = "users")
@Component
public class User {
    //creates the tables for database
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String customerName;

    @Column(name= "username")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;


}
