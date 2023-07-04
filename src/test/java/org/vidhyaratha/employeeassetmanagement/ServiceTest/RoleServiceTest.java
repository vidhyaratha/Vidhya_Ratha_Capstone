package org.vidhyaratha.employeeassetmanagement.ServiceTest;


import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.vidhyaratha.employeeassetmanagement.DemoApplication;
import org.vidhyaratha.employeeassetmanagement.model.Role;
import org.vidhyaratha.employeeassetmanagement.model.User;
import org.vidhyaratha.employeeassetmanagement.repository.RoleRepository;

@SpringBootTest(classes = DemoApplication.class)
@DisplayName("RoleService Test Class")
public class RoleServiceTest
{

    @Autowired
    RoleRepository roleRepository;

    @Test
    @DisplayName("Test Case : Validate Role by Valid Role name")
    public void findRoleByNameTest()
    {
        Role role = roleRepository.findRoleByName("ROLE_USER");
        Assert.assertEquals("ROLE_USER", role.getName());
    }


    @Test
    @DisplayName("Test Case : Validate Role by Valid Role name - ROLE admin")
    public void findRoleByNameAdminTest()
    {
        Role role = roleRepository.findRoleByName("ROLE_ADMIN");
        Assert.assertEquals("ROLE_ADMIN", role.getName());
    }
}
