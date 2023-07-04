package org.vidhyaratha.employeeassetmanagement.ServiceTest;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.vidhyaratha.employeeassetmanagement.DemoApplication;
import org.vidhyaratha.employeeassetmanagement.model.User;
import org.vidhyaratha.employeeassetmanagement.repository.UserRepository;


@SpringBootTest(classes = DemoApplication.class)
@DisplayName("EmployeeService Test Class")
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;



    @Test
    @DisplayName("Test Case : Validate Employee by Valid Email")
    public void findEmployeeByValidEmailTest()
    {
        User employee = userRepository.findUserByEmail("111@gmail.com");
        Assert.assertEquals("111@gmail.com", employee.getEmail());
    }



    @ParameterizedTest
    @DisplayName("Test Case : Validate Employee by Invalid Email")
    @ValueSource(strings = {"cstartin3@flickr.com","qllorens2@howstuffworks.com"})
    public void findEmployeeByInvalidEmailTest(String testExpected)
    {
        User employee = userRepository.findUserByEmail(testExpected);
        Assert.assertEquals(null,employee);
    }




    @Test
    @DisplayName("Test Case : Validate Employee by Valid Employee Id")
    public void findEmployeeByEmpIdTest()
    {
        User employee = userRepository.findUserByEmpId("111");
        Assert.assertEquals("111", employee.getEmpId());
    }

    @Test
    @DisplayName("Test Case : Validate Sign up ")
    public void saveEmployeeTest()
    {
        User employee = new User();
        employee.setEmpId("EIDvalidatesignuptest");
        employee.setEmail("validatesignuptest@gmail.com");
        employee.setPassword("testvalidatesignup");
        employee.setLocation("San Antonio");
        employee.setEmpName("David Harris");
        employee.setGender("male");
        Assert.assertNotNull(userRepository.save(employee));
    }

}
