package org.vidhyaratha.employeeassetmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;


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

    @Column(name = "empName")
    private String empName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "location")
    private String location;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "employees_roles",
            joinColumns = @JoinColumn(name = "empId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Collection<Role> roles;

    public Employee(String empId, String email, String password, String empName, String gender, String location) {
        this.empId = empId;
        this.email = email;
        this.password = password;
        this.empName = empName;
        this.gender = gender;
        this.location = location;
    }
}





