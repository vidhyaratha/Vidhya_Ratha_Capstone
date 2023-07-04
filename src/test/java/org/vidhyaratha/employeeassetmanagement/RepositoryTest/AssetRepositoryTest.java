package org.vidhyaratha.employeeassetmanagement.RepositoryTest;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.vidhyaratha.employeeassetmanagement.DemoApplication;
import org.vidhyaratha.employeeassetmanagement.model.Asset;
import org.vidhyaratha.employeeassetmanagement.repository.AssetRepository;

import java.util.List;

@SpringBootTest(classes = DemoApplication.class)
@DisplayName("AssetRepository Test Class")
public class AssetRepositoryTest {

    private final AssetRepository assetRepository;

    @Autowired
    public AssetRepositoryTest(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }


    @ParameterizedTest
    @DisplayName("Test Case : Validate Distinct Asset Types")
    @ValueSource(strings = {"Keyboard","Laptop-Mac"})
    public void distinctAssetTypesTest(String testExpected)
    {
        List<String> assetTypes = assetRepository.findDistinctAssetTypes();
        Assert.assertTrue(assetTypes.contains(testExpected));
    }


    @Test
    @DisplayName("Test Case : Validate  Asset Id")
    public void findByAssetIdTest()
    {
        Asset asset = assetRepository.findByAssetId("AID1002");
        asset.setStatus("Unassigned");
        assetRepository.save(asset);
        Assert.assertEquals("Unassigned",asset.getStatus());
    }


    @Test
    @DisplayName("Test Case : Validate  Asset Type")
    public void findByAssetType()
    {
        List<Asset> asset = assetRepository.findByAssetType("Keyboard");
        Assert.assertEquals("Keyboard",asset.get(0).getAssetType());
    }
}
