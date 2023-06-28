package org.vidhyaratha.employeeassetmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vidhyaratha.employeeassetmanagement.model.Asset;
import org.vidhyaratha.employeeassetmanagement.repository.AssetRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class AssetServiceImpl implements AssetService {


    private final AssetRepository assetRepository;


    @Autowired
    public AssetServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }


    @Override
    public Asset assignAssetToAssignedStatus(String assetType, String status) {
        List<Asset> asset = assetRepository.findByAssetType(assetType);
        List<Asset> unassignedAssets = new ArrayList<>();
        Asset newAsset = new Asset();
        for (Asset assetList : asset) {
            if (assetList.getStatus().equalsIgnoreCase(status)) {
                unassignedAssets.add(assetList);
            }
        }
        if (!unassignedAssets.isEmpty()) {
            newAsset = unassignedAssets.get(0);
            newAsset.setStatus("Assigned");
            assetRepository.save(newAsset);
        }


        return newAsset;

    }


    @Override
    public List<String> getAllAssetTypes() {
        return assetRepository.findDistinctAssetTypes();
    }


    @Override
    public void updateAssetStatus(String assetId, String status) {
        Asset asset = assetRepository.findByAssetId(assetId);
        asset.setStatus(status);
        assetRepository.save(asset);

    }
}
