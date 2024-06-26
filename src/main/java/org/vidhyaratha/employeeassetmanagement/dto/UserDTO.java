package org.vidhyaratha.employeeassetmanagement.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.vidhyaratha.employeeassetmanagement.model.Role;

import java.util.Collection;

public class UserDTO {

    @NotEmpty
    private String empId;

    @NotEmpty
    private String empName;

    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    @NotEmpty(message = "Password should not be empty")
    private String password;

    @NotEmpty
    private String gender;

    @NotEmpty
    private String location;

    private Collection<Role> roles;

    public UserDTO() {
    }


    public UserDTO(String empId, String empName,String email, String password,String gender, String location, Collection<Role> roles) {
        this.empId = empId;
        this.empName = empName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.location = location;
        this.roles = roles;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }



}

