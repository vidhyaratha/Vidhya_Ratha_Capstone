package org.vidhyaratha.employeeassetmanagement.service;


import org.vidhyaratha.employeeassetmanagement.dto.EmployeeMasterDTO;
import org.vidhyaratha.employeeassetmanagement.model.EmployeeMaster;

public interface EmployeeMasterService {

    public EmployeeMaster getEmployeeByEmpId(String empId);
    public void saveEmployeeMaster(EmployeeMaster employeeMaster);
}
