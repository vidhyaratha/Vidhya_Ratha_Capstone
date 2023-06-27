package org.vidhyaratha.employeeassetmanagement.service;

import org.vidhyaratha.employeeassetmanagement.dto.AssetDTO;

import java.util.List;


public interface EmployeeAssetsService {

    public List<AssetDTO> getAssetsByEmployeeId(String empId);

    public void assignAsset(String empId, String assetId, String assetName);

    public void deleteByAssetId(String assetId);
}
