package org.vidhyaratha.employeeassetmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "employee")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int Id;

    @Id
    @Column(name = "empId")
    private String empId;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "empType")
    private String empType;



      }





