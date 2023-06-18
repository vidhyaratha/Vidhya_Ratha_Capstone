package org.vidhyaratha.employeeassetmanagement.service;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vidhyaratha.employeeassetmanagement.dto.EmployeeDTO;
import org.vidhyaratha.employeeassetmanagement.model.Employee;
import org.vidhyaratha.employeeassetmanagement.repository.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public List<EmployeeDTO> getAllEmployees() {
          List<Employee> employees = employeeRepository.findAll();
          return employees.stream()
                  .map((employee) -> mapToEmployeeDTO(employee))
                  .collect(Collectors.toList());
    }


    private EmployeeDTO mapToEmployeeDTO(Employee employee)
    {
        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setEmpId(employee.getEmpId());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setEmpType(employee.getEmpType());
        employeeDTO.setPassword(employee.getPassword());
        return employeeDTO;
    }




    @Override
    public Employee findEmployeeByEmail(String email) {
        return employeeRepository.findEmployeeByEmail(email);
    }


    @Override
    public void saveEmployee(EmployeeDTO employeeDTO) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Employee employee= modelMapper.map(employeeDTO, Employee.class);
//        student.setPassword(encoder.encode(student.getPassword()));
//        student.setStudentRoles(Arrays.asList(roleService.findRoleByName("ROLE_BEGINNER")));

         employeeRepository.save(employee);
    }


    //    public Optional<Employee> getEmployeeById(int empId) {
//        return  employeeRepository.findById(empId);
//    }





//    @Override
//    public void updateEmployee(Employee employee, int id) {
//        Optional<Employee> employeeData = employeeRepository.findById(id);
//
//        if (employeeData.isPresent()) {
//            Employee employeeUpdate = employeeData.get();
//            employeeUpdate.setEmailId(employee.getEmailId());
//            employeeUpdate.setPassword(employee.getPassword());
//            employeeUpdate.setEmpType(employee.getEmpType());
//            employeeRepository.save(employee);
//        }
//    }

//    @Override
//    public void deleteEmployeeById(int empId) {
//        employeeRepository.deleteById(empId);
//    }
//
//    @Override
//    public void deleteEmployees() {
//        employeeRepository.deleteAll();
//    }


//    @Override
//    public boolean doesEmployeeExist(int empId) {
//        return employeeRepository.existsById(empId);
//    }
}
