package org.vidhyaratha.employeeassetmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.vidhyaratha.employeeassetmanagement.model.Asset;
import org.vidhyaratha.employeeassetmanagement.repository.AssetRepository;
import org.vidhyaratha.employeeassetmanagement.repository.EmployeeAssetsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssetServiceImpl implements AssetService{


    private final AssetRepository assetRepository;

    private final EmployeeAssetsRepository employeeAssetsRepository;

    @Autowired
    public AssetServiceImpl(AssetRepository assetRepository, EmployeeAssetsRepository employeeAssetsRepository) {
        this.assetRepository = assetRepository;
        this.employeeAssetsRepository = employeeAssetsRepository;
    }

    //    @Override
//    public Asset findAssetByAssetId(String assetId) {
//        return assetRepository.findAssetByAssetId(assetId);
//    }


    @Override
    public Asset assignAssetToAssignedStatus(String assetType, String status) {
    List<Asset> asset = assetRepository.findByAssetType(assetType);
    System.out.println("Asset List : "+asset);
    List<Asset> unassignedAssets = new ArrayList<>();
        for (Asset assetList : asset)
        {
            if(assetList.getStatus().equalsIgnoreCase(status))
            {
                unassignedAssets.add(assetList);
            }
        }
        Asset newAsset = unassignedAssets.get(0);
        newAsset.setStatus("Assigned");
        assetRepository.save(newAsset);


        return newAsset;

    }







    @Override
    public void updateAssetStatus(String assetId, String status) {
        Asset asset = assetRepository.findByAssetId(assetId);
        //employeeAssetsRepository.deleteByAssetId(assetId);
        asset.setStatus(status);
        assetRepository.save(asset);

    }
}
