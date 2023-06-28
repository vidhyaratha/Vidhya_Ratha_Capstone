package org.vidhyaratha.employeeassetmanagement;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.vidhyaratha.employeeassetmanagement.model.Employee;
import org.vidhyaratha.employeeassetmanagement.repository.EmployeeRepository;


@SpringBootTest(classes = DemoApplication.class)
@DisplayName("EmployeeService Test Class")
public class EmployeeServiceTest {

    @Autowired
    private EmployeeRepository employeeRepository;



    @Test
    @DisplayName("Test Case : Validate Employee by Valid Email")
    public void findEmployeeByValidEmailTest()
    {
        Employee employee = employeeRepository.findEmployeeByEmail("test123@gmail.com");
        Assert.assertEquals("test123@gmail.com", employee.getEmail());
    }



    @ParameterizedTest
    @DisplayName("Test Case : Validate Employee by Invalid Email")
    @ValueSource(strings = {"cstartin3@flickr.com","qllorens2@howstuffworks.com"})
    public void findEmployeeByInvalidEmailTest(String testExpected)
    {
        Employee employee = employeeRepository.findEmployeeByEmail(testExpected);
        Assert.assertEquals(null,employee);
    }




    @Test
    @DisplayName("Test Case : Validate Employee by Valid Employee Id")
    public void findEmployeeByEmpIdTest()
    {
        Employee employee = employeeRepository.findEmployeeByEmpId("EID123");
        Assert.assertEquals("EID123", employee.getEmpId());
    }

    @Test
    @DisplayName("Test Case : Validate Sign up ")
    public void saveEmployeeTest()
    {
        Employee employee = new Employee();
        employee.setEmpId("EIDvalidatesignuptest");
        employee.setEmail("validatesignuptest@gmail.com");
        employee.setPassword("testvalidatesignup");
        employee.setLocation("San Antonio");
        employee.setEmpName("David Harris");
        employee.setGender("male");
        Assert.assertNotNull(employeeRepository.save(employee));
    }

}
