package org.vidhyaratha.employeeassetmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vidhyaratha.employeeassetmanagement.dto.AssetDTO;
import org.vidhyaratha.employeeassetmanagement.model.Asset;
import org.vidhyaratha.employeeassetmanagement.model.Employee;
import org.vidhyaratha.employeeassetmanagement.model.EmployeeAssets;
import org.vidhyaratha.employeeassetmanagement.repository.AssetRepository;
import org.vidhyaratha.employeeassetmanagement.repository.EmployeeAssetsRepository;
import org.vidhyaratha.employeeassetmanagement.repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeAssetsServiceImpl implements EmployeeAssetsService {

    private final AssetRepository assetRepository;

    private final EmployeeRepository employeeRepository;

    private final EmployeeAssetsRepository employeeAssetsRepository;

    @Autowired
    public EmployeeAssetsServiceImpl(AssetRepository assetRepository, EmployeeRepository employeeRepository, EmployeeAssetsRepository employeeAssetsRepository) {
        this.assetRepository = assetRepository;
        this.employeeRepository = employeeRepository;
        this.employeeAssetsRepository = employeeAssetsRepository;
    }


    @Override
    public List<AssetDTO> getAssetsByEmployeeId(String empId) {
        Employee employee = employeeRepository.findEmployeeByEmpId(empId);


        List<EmployeeAssets> employeeAssets = employeeAssetsRepository.findByEmployee(employee);
        List<AssetDTO> assetDTOList = new ArrayList<>();

        for (EmployeeAssets employeeAsset : employeeAssets) {
            Asset asset = employeeAsset.getAsset();
            AssetDTO assetDTO = new AssetDTO();
            assetDTO.setId(asset.getId());
            assetDTO.setAssetId(asset.getAssetId());
            assetDTO.setAssetName(asset.getAssetName());
            assetDTO.setAssetType(asset.getAssetType());
            assetDTO.setAssetCreatedDate(asset.getAssetCreatedDate());
            assetDTO.setStatus(asset.getStatus());

            assetDTOList.add(assetDTO);
        }

        return assetDTOList;
    }


    @Override
    public void assignAsset(String empId, String assetId, String assetName) {
        if (assetId != null) {
            EmployeeAssets employeeAssets = new EmployeeAssets();
            employeeAssets.setAssetAssignedDate(LocalDate.now().toString());
            employeeAssets.setApprovedAdminName("Smith_ADMIN");
            Employee employee = employeeRepository.findEmployeeByEmpId(empId);
            Asset asset = assetRepository.findByAssetId(assetId);

            employeeAssets.setEmployee(employee);
            employeeAssets.setAsset(asset);

            employeeAssetsRepository.save(employeeAssets);
        }

    }

    @Override
    public void deleteByAssetId(String assetId) {

        Asset asset = assetRepository.findByAssetId(assetId);
        EmployeeAssets employeeAsset = employeeAssetsRepository.findByAsset(asset);

        if (employeeAsset != null) {
            employeeAssetsRepository.deleteById(employeeAsset.getId());
        }
    }


}
