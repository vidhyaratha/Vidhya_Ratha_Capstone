package org.vidhyaratha.employeeassetmanagement.service;

import org.springframework.stereotype.Service;
import org.vidhyaratha.employeeassetmanagement.model.Asset;

public interface AssetService {
    //public Asset findAssetByAssetId(Long assetId);
    public void updateAssetStatus(Long assetId, String status);
}
