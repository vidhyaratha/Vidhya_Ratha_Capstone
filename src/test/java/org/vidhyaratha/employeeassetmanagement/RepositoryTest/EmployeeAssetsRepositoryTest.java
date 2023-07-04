package org.vidhyaratha.employeeassetmanagement.RepositoryTest;


import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.vidhyaratha.employeeassetmanagement.DemoApplication;
import org.vidhyaratha.employeeassetmanagement.model.Asset;
import org.vidhyaratha.employeeassetmanagement.model.EmployeeAssets;
import org.vidhyaratha.employeeassetmanagement.model.User;
import org.vidhyaratha.employeeassetmanagement.repository.AssetRepository;
import org.vidhyaratha.employeeassetmanagement.repository.EmployeeAssetsRepository;
import org.vidhyaratha.employeeassetmanagement.repository.UserRepository;

import java.util.List;

@SpringBootTest(classes = DemoApplication.class)
@DisplayName("EmployeeAssetsRepository Test Class")
public class EmployeeAssetsRepositoryTest
{
    @Autowired
    private EmployeeAssetsRepository employeeAssetsRepository;
    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Test Case : Validate Employee Asset")
    public void findByAssetTest()
    {
        Asset asset = assetRepository.findByAssetId("AID1002");
        EmployeeAssets employeeAssets = employeeAssetsRepository.findByAsset(asset);
        Assert.assertEquals("HP 21 inch Monitor",employeeAssets.getAsset().getAssetName());
    }

    @Test
    @DisplayName("Test Case : Validate Employee")
    public void findByUserTest()
    {
        User user = userRepository.findUserByEmpId("Admin1");
        List<EmployeeAssets> employeeAssetsList = employeeAssetsRepository.findByUser(user);
        Assert.assertEquals("AID1005",employeeAssetsList.get(0).getAsset().getAssetId());
    }
}
