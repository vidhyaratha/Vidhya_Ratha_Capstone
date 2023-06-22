package org.vidhyaratha.employeeassetmanagement.service;


import org.springframework.stereotype.Service;
import org.vidhyaratha.employeeassetmanagement.dto.AssetDTO;
import org.vidhyaratha.employeeassetmanagement.dto.EmployeeDTO;
import org.vidhyaratha.employeeassetmanagement.model.Employee;

import java.util.List;


public interface EmployeeService {

       // public List<AssetDTO> getAssetsForEmployee(String employeeId);






       // List<EmployeeDTO> getAllEmployees();      //  Retrieve all Employees from the Table

        public Employee findEmployeeByEmail(String email);  //  Retrieve Employee by Email

        public Employee findEmployeeByEmpId(String empId);  //  Retrieve Employee by EmpId

        public void saveEmployee(EmployeeDTO employeeDTO);        // Insert Employee

        //public void deleteEmployeeById(int empId);              // Delete Employee By employee ID4

       // public void deleteEmployees();                          // Delete all Employees

//        public boolean doesEmployeeExist(int empId);


    //Optional<Employee> getEmployeeById(int empId);
    // public void updateEmployee(Employee employee,int empId);
}
