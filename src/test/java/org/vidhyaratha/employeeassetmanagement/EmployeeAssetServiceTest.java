package org.vidhyaratha.employeeassetmanagement;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.vidhyaratha.employeeassetmanagement.model.Employee;
import org.vidhyaratha.employeeassetmanagement.model.EmployeeAssets;
import org.vidhyaratha.employeeassetmanagement.repository.EmployeeAssetsRepository;
import org.vidhyaratha.employeeassetmanagement.repository.EmployeeRepository;

import java.util.List;


@SpringBootTest(classes = DemoApplication.class)
@DisplayName("EmployeeAssetService Test Class")
public class EmployeeAssetServiceTest {

    private final EmployeeRepository employeeRepository;

    private final EmployeeAssetsRepository employeeAssetsRepository;


    @Autowired
    public EmployeeAssetServiceTest(EmployeeRepository employeeRepository, EmployeeAssetsRepository employeeAssetsRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeAssetsRepository = employeeAssetsRepository;
    }


    @Test
    @DisplayName("Test Case : Validate Valid Employee Id")
    public void getAssetsByEmployeeIdValidTest() {
        Employee employee = employeeRepository.findEmployeeByEmpId("EID123");
        List<EmployeeAssets> employeeAssetList = employeeAssetsRepository.findByEmployee(employee);
        Assert.assertNotNull(employeeAssetList);
    }


    @Test
    @DisplayName("Test Case : Validate Invalid Employee Id")
    public void getAllAssetsByEmployeeIdInvalidTest() {
        Employee employee = employeeRepository.findEmployeeByEmpId("EIDabc");
        List<EmployeeAssets> employeeAssetList = employeeAssetsRepository.findByEmployee(employee);
        Assert.assertNotNull(employeeAssetList);
    }
}
