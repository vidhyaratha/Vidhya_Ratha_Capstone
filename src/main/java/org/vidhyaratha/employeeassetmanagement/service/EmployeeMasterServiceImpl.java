//package org.vidhyaratha.employeeassetmanagement.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import org.vidhyaratha.employeeassetmanagement.model.Employee;
//import org.vidhyaratha.employeeassetmanagement.model.EmployeeMaster;
//import org.vidhyaratha.employeeassetmanagement.repository.EmployeeMasterRepository;
//import org.vidhyaratha.employeeassetmanagement.repository.EmployeeRepository;
//
//@Service
//public class EmployeeMasterServiceImpl implements EmployeeMasterService {
//
//
//    private final EmployeeRepository employeeRepository;
//    private final EmployeeMasterRepository employeeMasterRepository;
//
//    @Autowired
//    public EmployeeMasterServiceImpl(EmployeeMasterRepository employeeMasterRepository, EmployeeRepository employeeRepository) {
//        this.employeeMasterRepository = employeeMasterRepository;
//        this.employeeRepository = employeeRepository;
//    }
//
//    @Override
//    public EmployeeMaster getEmployeeByEmpId(String empId) {
//
//        return employeeMasterRepository.findEmployeeByempId(empId);
//    }


//    @Override
//    public void saveEmployeeMaster(EmployeeMaster employeeMaster) {
//
//        EmployeeMaster empMaster = new EmployeeMaster();
//
//        Employee existingEmployee = employeeRepository.findEmployeeByEmpId(empMaster.getEmpId());
//        existingEmployee.setEmail(employeeMaster.getEmail());
//
//        EmployeeMaster employeeMasterExisting = employeeMasterRepository.findEmployeeByempId(empMaster.getEmpId());
//        employeeMasterExisting.setEmail(employeeMaster.getEmail());
//        employeeMasterExisting.setPhone(employeeMaster.getPhone());
//        employeeMasterExisting.setLocation(employeeMaster.getLocation());
////
//        employeeMasterRepository.save(employeeMaster);
//    }
//}
