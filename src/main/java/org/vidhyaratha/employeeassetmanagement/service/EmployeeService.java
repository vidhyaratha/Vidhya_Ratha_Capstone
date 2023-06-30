package org.vidhyaratha.employeeassetmanagement.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.vidhyaratha.employeeassetmanagement.dto.EmployeeDTO;
import org.vidhyaratha.employeeassetmanagement.model.Employee;

import java.util.List;


public interface EmployeeService extends UserDetailsService{

    public Employee findEmployeeByEmail(String email);  //  Retrieve Employee by Email

    public Employee findEmployeeByEmpId(String empId);  //  Retrieve Employee by EmpId

    public void saveEmployee(EmployeeDTO employeeDTO);        // Insert Employee



    public UserDetails loadUserByUsername(String userName);

    //List<Employee> getAll();

}
