package org.vidhyaratha.employeeassetmanagement.service;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vidhyaratha.employeeassetmanagement.dto.EmployeeDTO;
import org.vidhyaratha.employeeassetmanagement.model.Employee;
import org.vidhyaratha.employeeassetmanagement.model.Role;
import org.vidhyaratha.employeeassetmanagement.repository.EmployeeRepository;
import org.vidhyaratha.employeeassetmanagement.security.EmployeePrincipal;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;


@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeRepository employeeRepository;

    private final BCryptPasswordEncoder encoder;

    private final RoleService roleService;


    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,@Lazy BCryptPasswordEncoder encoder, RoleService roleService) {
        this.employeeRepository = employeeRepository;
        this.encoder = encoder;
        this.roleService = roleService;
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
        employee.setPassword(encoder.encode(employee.getPassword()));
        employee.setRoles(Arrays.asList(roleService.findRoleByName("ROLE_USER")));

        employeeRepository.save(employee);
    }



    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findEmployeeByEmail(userName);
        log.debug(userName);
        if (employee == null) {
            log.warn("Invalid emp id or password ", userName);

            throw new UsernameNotFoundException("Invalid employee Id or password.");
        }
        return new EmployeePrincipal(employee, roleService.getRolesByEmployee(employee.getEmpId()));

    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        Collection<? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return mapRoles;
    }



}
