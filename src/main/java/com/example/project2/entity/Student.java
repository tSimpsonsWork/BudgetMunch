
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

    @Column(name = "Customer Name")
    private String customerName;

    @Column(name= "User Name")
    private String userName;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;


}
