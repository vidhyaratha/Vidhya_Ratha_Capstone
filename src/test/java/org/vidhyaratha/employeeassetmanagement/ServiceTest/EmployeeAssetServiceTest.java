package org.vidhyaratha.employeeassetmanagement.ServiceTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.vidhyaratha.employeeassetmanagement.DemoApplication;
import org.vidhyaratha.employeeassetmanagement.model.EmployeeAssets;
import org.vidhyaratha.employeeassetmanagement.model.User;
import org.vidhyaratha.employeeassetmanagement.repository.EmployeeAssetsRepository;
import org.vidhyaratha.employeeassetmanagement.repository.UserRepository;

import java.util.List;


@SpringBootTest(classes = DemoApplication.class)
@DisplayName("EmployeeAssetService Test Class")
public class EmployeeAssetServiceTest {

    private final UserRepository userRepository;

    private final EmployeeAssetsRepository employeeAssetsRepository;


    @Autowired
    public EmployeeAssetServiceTest(UserRepository userRepository, EmployeeAssetsRepository employeeAssetsRepository) {
        this.userRepository = userRepository;
        this.employeeAssetsRepository = employeeAssetsRepository;
    }


    @Test
    @DisplayName("Test Case : Validate Valid Employee Id")
    public void getAssetsByEmployeeIdValidTest() {
        User user = userRepository.findUserByEmpId("111");
        List<EmployeeAssets> employeeAssetList = employeeAssetsRepository.findByUser(user);
        Assert.assertNotNull(employeeAssetList);
    }


    @Test
    @DisplayName("Test Case : Validate Invalid Employee Id")
    public void getAllAssetsByEmployeeIdInvalidTest() {
        User user = userRepository.findUserByEmpId("111");
        List<EmployeeAssets> employeeAssetList = employeeAssetsRepository.findByUser(user);
        Assert.assertNotNull(employeeAssetList);
    }
}
