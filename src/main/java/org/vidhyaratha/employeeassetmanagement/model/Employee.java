package org.vidhyaratha.employeeassetmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Table(name = "employee")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int Id;

    @Id
@Column(name = "emp_id")
    private String empId;

    private String email;

    private String password;
@Column(name="emp_type")
    private String empType;


       public String getEmpId() {
            return empId;
        }

        public void setEmpId(String empId) {
            this.empId = empId;
        }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }
}





