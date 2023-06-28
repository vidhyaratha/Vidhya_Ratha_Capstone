package org.vidhyaratha.employeeassetmanagement.service;


import org.vidhyaratha.employeeassetmanagement.dto.EmployeeDTO;
import org.vidhyaratha.employeeassetmanagement.model.Employee;


public interface EmployeeService  {

    public Employee findEmployeeByEmail(String email);  //  Retrieve Employee by Email

    public Employee findEmployeeByEmpId(String empId);  //  Retrieve Employee by EmpId

    public void saveEmployee(EmployeeDTO employeeDTO);        // Insert Employee

    //public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
