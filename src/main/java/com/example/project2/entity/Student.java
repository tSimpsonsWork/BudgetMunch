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

    @Column(name = "Student_Name")
    private String name;

    @Column(name= "Email")
    private String email;

    @Column(name = "Age")
    private Integer age;

}
