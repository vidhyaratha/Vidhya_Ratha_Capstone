package org.vidhyaratha.employeeassetmanagement.service;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vidhyaratha.employeeassetmanagement.dto.EmployeeDTO;
import org.vidhyaratha.employeeassetmanagement.model.Employee;
import org.vidhyaratha.employeeassetmanagement.repository.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public Employee findEmployeeByEmail(String email) {
        return employeeRepository.findEmployeeByEmail(email);
    }


    @Override
    public Employee findEmployeeByEmpId(String empId) {
        return employeeRepository.findEmployeeByEmpId(empId);
    }


    @Override
    public void saveEmployee(EmployeeDTO employeeDTO) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Employee employee = modelMapper.map(employeeDTO, Employee.class);


        employeeRepository.save(employee);
    }


}
