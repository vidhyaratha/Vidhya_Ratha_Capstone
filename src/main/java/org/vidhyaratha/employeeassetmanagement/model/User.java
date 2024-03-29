package org.vidhyaratha.employeeassetmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;


@Table(name = "employee")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {


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
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "empId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Collection<Role> roles;

    public User(String empId, String email, String password, String empName, String gender, String location) {
        this.empId = empId;
        this.email = email;
        this.password = password;
        this.empName = empName;
        this.gender = gender;
        this.location = location;
    }


}





