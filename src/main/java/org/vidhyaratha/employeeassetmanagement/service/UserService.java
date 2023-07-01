package org.vidhyaratha.employeeassetmanagement.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.vidhyaratha.employeeassetmanagement.dto.UserDTO;
import org.vidhyaratha.employeeassetmanagement.model.User;

import java.util.List;


public interface UserService extends UserDetailsService{

    public User findUserByEmail(String email);  //  Retrieve Employee by Email

    public User findUserByEmpId(String empId);  //  Retrieve Employee by EmpId

    public void saveUser(UserDTO userDTO);        // Insert Employee



    public UserDetails loadUserByUsername(String userName);

    //List<Employee> getAll();

}
