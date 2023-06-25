package org.vidhyaratha.employeeassetmanagement.service;

import org.springframework.stereotype.Service;
import org.vidhyaratha.employeeassetmanagement.model.Asset;

import java.util.List;
import java.util.Set;

public interface AssetService {
    //public Asset findAssetByAssetId(String assetId);
    public Asset assignAssetToAssignedStatus(String assetType, String status);
    public void updateAssetStatus(String assetId, String status);
    public List<String> getAllAssetTypes();

}
