package org.vidhyaratha.employeeassetmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "employeemaster")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String empId;
    private String email;
    private String empName;
    private String designation;
    private String location;
    private String phone;
    private String gender;
    private String supervisor;
    private String department;
    private String startDate;

}
