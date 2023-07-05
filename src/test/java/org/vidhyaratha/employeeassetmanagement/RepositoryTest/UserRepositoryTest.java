package org.vidhyaratha.employeeassetmanagement.RepositoryTest;


import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.vidhyaratha.employeeassetmanagement.DemoApplication;
import org.vidhyaratha.employeeassetmanagement.model.Asset;
import org.vidhyaratha.employeeassetmanagement.model.User;
import org.vidhyaratha.employeeassetmanagement.repository.UserRepository;

@SpringBootTest(classes = DemoApplication.class)
@DisplayName("UserRepository Test Class")
public class UserRepositoryTest
{
    @Autowired
    private UserRepository userRepository;

    //To validate an employee email address
    @Test
    @DisplayName("Test Case : Validate User Email")
    public void findByUserByEmailTest()
    {
        User user = userRepository.findUserByEmail("111@gmail.com");
        Assert.assertEquals("111",user.getEmpId());
    }

    //To validate an employee
    @Test
    @DisplayName("Test Case : Validate User Emp Id")
    public void findByUserByEmpIdTest()
    {
        User user = userRepository.findUserByEmpId("111");
        Assert.assertEquals("111@gmail.com",user.getEmail());
    }
}
