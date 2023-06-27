package org.vidhyaratha.employeeassetmanagement;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.vidhyaratha.employeeassetmanagement.model.Employee;
import org.vidhyaratha.employeeassetmanagement.repository.EmployeeRepository;
import org.vidhyaratha.employeeassetmanagement.service.EmployeeServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class EmployeeServiceTest {


    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;


    @Test
    public void testFindEmployeeByEmail()
    {
        Employee employee = employeeRepository.findEmployeeByEmail("test123@gmail.com");
        Assert.assertEquals("test123@gmail.com",employee.getEmail());
    }


    @Test
    public void testFindEmployeeByEmpId()
    {
        Employee employee = employeeRepository.findEmployeeByEmpId("EID123");
        Assert.assertEquals("EID123", employee.getEmpId());
    }

    @Test
    public void testSaveEmployee()
    {
        Employee employee = new Employee();
        employee.setEmpId("EIkjashdaskj");
        employee.setEmail("bbb@gmail.com");
        employee.setEmpType("Full-time");
        employee.setPassword("test789pwd");
        Assert.assertNotNull(employeeRepository.save(employee));
    }

}
