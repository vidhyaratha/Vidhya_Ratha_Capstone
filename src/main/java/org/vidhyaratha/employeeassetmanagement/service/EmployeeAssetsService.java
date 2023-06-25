package org.vidhyaratha.employeeassetmanagement.service;

import org.springframework.stereotype.Service;
import org.vidhyaratha.employeeassetmanagement.dto.AssetDTO;
import org.vidhyaratha.employeeassetmanagement.dto.EmployeeAssetsDTO;
import org.vidhyaratha.employeeassetmanagement.model.Asset;
import org.vidhyaratha.employeeassetmanagement.model.Employee;
import org.vidhyaratha.employeeassetmanagement.model.EmployeeAssets;


import java.util.List;

public interface EmployeeAssetsService {

   // public List<EmployeeAssetsDTO> findAllAssetsByEmpId(String empId);

    public List<AssetDTO> getAssetsByEmployeeId(String empId);

    public void assignAsset(String empId, String assetId, String assetName);

    //public void returnAsset(String empId, String assetId);

    //public Employee findEmployeeByEmail(String email);

    public void deleteByAssetId(String assetId);
}
