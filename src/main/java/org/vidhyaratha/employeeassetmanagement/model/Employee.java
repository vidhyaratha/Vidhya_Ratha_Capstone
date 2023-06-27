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


    @Id
    @Column(name = "empId", unique = true)
    private String empId;


    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "empType")
    private String empType;


}





