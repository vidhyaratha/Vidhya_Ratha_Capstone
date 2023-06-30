package org.vidhyaratha.employeeassetmanagement.service;

import org.vidhyaratha.employeeassetmanagement.model.Asset;

import java.util.List;


public interface AssetService {

    public Asset assignAssetToAssignedStatus(String assetType, String status);

    public void updateAssetStatus(String assetId, String status);

    public List<String> getAllAssetTypes();

    public void addAsset(Asset asset);
}
