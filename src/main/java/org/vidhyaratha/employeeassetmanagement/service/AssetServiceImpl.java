package org.vidhyaratha.employeeassetmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.vidhyaratha.employeeassetmanagement.model.Asset;
import org.vidhyaratha.employeeassetmanagement.repository.AssetRepository;
import org.vidhyaratha.employeeassetmanagement.repository.EmployeeAssetsRepository;

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
//    public Asset findAssetByAssetId(Long assetId) {
//        return assetRepository.findAssetByAssetId(assetId);
//    }


    @Override
    public void updateAssetStatus(Long assetId, String status) {
        Asset asset = assetRepository.findByAssetId(assetId);
        //employeeAssetsRepository.deleteByAssetId(assetId);
        asset.setStatus(status);
        assetRepository.save(asset);

    }
}
