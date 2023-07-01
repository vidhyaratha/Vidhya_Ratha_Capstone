package org.vidhyaratha.employeeassetmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vidhyaratha.employeeassetmanagement.controller.UserController;
import org.vidhyaratha.employeeassetmanagement.model.Asset;
import org.vidhyaratha.employeeassetmanagement.repository.AssetRepository;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class AssetServiceImpl implements AssetService {


    private final AssetRepository assetRepository;
    private static final Logger logger = LoggerFactory.getLogger(AssetServiceImpl.class);


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
            logger.info("Set new assest status to assigned");
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
        logger.info("Updated assest status succesfully- "+ status);
        assetRepository.save(asset);

    }

    @Override
    public void addAsset(Asset asset) {
        assetRepository.save(asset);
    }
}
