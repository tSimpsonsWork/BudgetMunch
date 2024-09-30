package com.example.project2.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Student {

    @Id
    private Long id;

    @Column(name = "Customer Name")
    private String customerName;

    @Column(name= "User Name")
    private String userName;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "Age")
    private Integer age;

}
