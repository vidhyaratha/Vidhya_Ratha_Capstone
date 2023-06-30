//package org.vidhyaratha.employeeassetmanagement.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.vidhyaratha.employeeassetmanagement.model.Employee;
//import org.vidhyaratha.employeeassetmanagement.repository.EmployeeRepository;
//
//import java.util.stream.Collectors;
//
//@Service
//public class CustomEmployeeDetailsService implements UserDetailsService {
//
//   private final EmployeeRepository employeeRepository;
//
//    @Autowired
//    public CustomEmployeeDetailsService(EmployeeRepository employeeRepository) {
//        super();
//        this.employeeRepository = employeeRepository;
//    }
//
//
//    @Override
//    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
//
//        Employee employee = employeeRepository.findEmployeeByEmpId(usernameOrEmail);
//        if(employee != null) {
//            return new org.springframework.security.core.userdetails.User(employee.getEmpId(),
//                    employee.getPassword(),
//                    employee.getRoles().stream()
//                            .map((role) -> new SimpleGrantedAuthority(role.getRole()))
//                            .collect(Collectors.toList()));
//        }else {
//            throw new UsernameNotFoundException("Invalid email or password");
//        }
//    }
//}
//
//
