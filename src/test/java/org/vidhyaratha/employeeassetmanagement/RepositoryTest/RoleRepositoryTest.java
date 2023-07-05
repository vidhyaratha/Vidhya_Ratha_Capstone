package org.vidhyaratha.employeeassetmanagement.RepositoryTest;


import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.vidhyaratha.employeeassetmanagement.DemoApplication;
import org.vidhyaratha.employeeassetmanagement.model.Role;
import org.vidhyaratha.employeeassetmanagement.model.User;
import org.vidhyaratha.employeeassetmanagement.repository.RoleRepository;
import org.vidhyaratha.employeeassetmanagement.repository.UserRepository;

import java.util.List;

@SpringBootTest(classes = DemoApplication.class)
@DisplayName("RoleRepository Test Class")
public class RoleRepositoryTest
{
    @Autowired
    private RoleRepository roleRepository;

    //To validate an employee by role name
    @Test
    @DisplayName("Test Case : Validate Role Name")
    public void findRoleByNameTest()
    {
        Role role = roleRepository.findRoleByName("ROLE_USER");
        Assert.assertEquals("ROLE_USER",role.getName());
    }

    //To validate employee role by employee id
    @Test
    @DisplayName("Test Case : Validate Role By User")
    public void findRoleByUserTest()
    {
        Role role = roleRepository.findRoleByUser("111");
        Assert.assertEquals("ROLE_USER",role.getName());

    }
}
