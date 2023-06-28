package org.vidhyaratha.employeeassetmanagement;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.vidhyaratha.employeeassetmanagement.model.Asset;
import org.vidhyaratha.employeeassetmanagement.model.Employee;
import org.vidhyaratha.employeeassetmanagement.repository.AssetRepository;

import java.util.List;

@SpringBootTest(classes = DemoApplication.class)
@DisplayName("AssetService Test Class")
public class AssetServiceTest {

    private final AssetRepository assetRepository;

    @Autowired
    public AssetServiceTest(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }


    @Test
    @DisplayName("Test Case : Validate Update Asset Status")
    public void updateAssetStatusTest()
    {
        Asset asset = assetRepository.findByAssetId("AID123");
            asset.setStatus("Unassigned");
            assetRepository.save(asset);
         Assert.assertEquals("Unassigned",asset.getStatus());
    }


    @ParameterizedTest
    @DisplayName("Test Case : Validate Asset Types")
    @ValueSource(strings = {"Keyboard","Laptop"})
    public void getAllAssetTypesTest(String testExpected)
    {
       List<String> assetTypes = assetRepository.findDistinctAssetTypes();
       Assert.assertTrue(assetTypes.contains(testExpected));
    }

}
