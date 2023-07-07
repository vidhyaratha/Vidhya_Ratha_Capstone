package org.vidhyaratha.employeeassetmanagement.service;


import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vidhyaratha.employeeassetmanagement.dto.UserDTO;
import org.vidhyaratha.employeeassetmanagement.model.Role;
import org.vidhyaratha.employeeassetmanagement.model.User;
import org.vidhyaratha.employeeassetmanagement.repository.UserRepository;
import org.vidhyaratha.employeeassetmanagement.security.UserPrincipal;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;


@Service
@Slf4j
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    private final RoleService roleService;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserRepository userRepository,@Lazy BCryptPasswordEncoder encoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.roleService = roleService;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }


    @Override
    public User findUserByEmpId(String empId) {
        return userRepository.findUserByEmpId(empId);
    }

    //To save employee details in user entity after successful signup
    @Override
    public void saveUser(UserDTO userDTO, String role) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(encoder.encode(user.getPassword()));
        logger.info("Role : "+role);

        if(role.equals("user")) {
            user.setRoles(Arrays.asList(roleService.findRoleByName("ROLE_USER")));
        }
        else if(role.equals("admin"))
        {
            user.setRoles(Arrays.asList(roleService.findRoleByName("ROLE_ADMIN")));
        }

        logger.info("Employee sign up success");
       userRepository.save(user);
    }



    @Override
    public void editUser(UserDTO userDTO) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(encoder.encode(user.getPassword()));

        Role role = roleService.getRolesByUser(user.getEmpId());
        logger.info("Role - edit : "+role.getName());
        if(role.getName().equals("ROLE_USER")) {
            user.setRoles(Arrays.asList(roleService.findRoleByName("ROLE_USER")));
        }
        else if(role.getName().equals("ROLE_ADMIN"))
        {
            user.setRoles(Arrays.asList(roleService.findRoleByName("ROLE_ADMIN")));
        }

        logger.info("Employee sign up success");
        userRepository.save(user);
    }


    //To authenticate employee using their email and password
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username);
        log.debug(username);
        if (user == null) {
            log.warn("Invalid uname or password ", username);

            throw new UsernameNotFoundException("Invalid username or password.");
        }


        logger.info("Employee sign in success");
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));

    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        Collection<? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return mapRoles;
    }



}
