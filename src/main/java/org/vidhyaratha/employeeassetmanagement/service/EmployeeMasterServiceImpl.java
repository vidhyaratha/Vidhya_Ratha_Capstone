package org.vidhyaratha.employeeassetmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.vidhyaratha.employeeassetmanagement.model.Employee;
import org.vidhyaratha.employeeassetmanagement.model.EmployeeMaster;
import org.vidhyaratha.employeeassetmanagement.repository.EmployeeMasterRepository;
import org.vidhyaratha.employeeassetmanagement.repository.EmployeeRepository;

@Service
public class EmployeeMasterServiceImpl implements EmployeeMasterService{


    private final EmployeeRepository employeeRepository;
    private final EmployeeMasterRepository employeeMasterRepository;

    @Autowired
    public EmployeeMasterServiceImpl(EmployeeMasterRepository employeeMasterRepository,EmployeeRepository employeeRepository) {
        this.employeeMasterRepository = employeeMasterRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeMaster getEmployeeByEmpId(String empId) {

        return employeeMasterRepository.findEmployeeByempId(empId);
    }


    @Override
    public void saveEmployeeMaster(EmployeeMaster employeeMaster) {

        EmployeeMaster empMaster = new EmployeeMaster();

       EmployeeMaster employeeMasterExisting =  employeeMasterRepository.findEmployeeByempId(empMaster.getEmpId());
       Employee existingEmployee =  employeeRepository.findEmployeeByEmpId(empMaster.getEmpId());
       existingEmployee.setEmail(employeeMaster.getEmail());

       employeeMasterExisting.setEmail(employeeMaster.getEmail());
       employeeMasterExisting.setPhone(employeeMaster.getPhone());
       employeeMasterExisting.setLocation(employeeMaster.getLocation());
//       employeeMaster.setEmail(employeeMasterExisting.getEmail());
//       employeeMaster.setPhone(employeeMasterExisting.getPhone());
//       employeeMaster.setLocation(employeeMasterExisting.getLocation());


//        employeeMaster.setEmpId(employeeMasterExisting.getEmpId());
//        employeeMaster.setEmpName(employeeMasterExisting.getEmpName());
//        employeeMaster.setDepartment(employeeMasterExisting.getDepartment());
//        employeeMaster.setDesignation(employeeMasterExisting.getDesignation());
//        employeeMaster.setGender(employeeMasterExisting.getGender());
//        employeeMaster.setStartDate(employeeMasterExisting.getStartDate());
//        employeeMaster.setSupervisor(employeeMasterExisting.getSupervisor());

        employeeMasterRepository.save(employeeMaster);
    }
}
