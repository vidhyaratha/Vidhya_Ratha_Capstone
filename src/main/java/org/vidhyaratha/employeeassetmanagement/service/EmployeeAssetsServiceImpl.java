package org.vidhyaratha.employeeassetmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.vidhyaratha.employeeassetmanagement.Exception.DeviceExceededLimitationException;
import org.vidhyaratha.employeeassetmanagement.dto.AssetDTO;
import org.vidhyaratha.employeeassetmanagement.dto.UserDTO;
import org.vidhyaratha.employeeassetmanagement.model.Asset;
import org.vidhyaratha.employeeassetmanagement.model.User;
import org.vidhyaratha.employeeassetmanagement.model.EmployeeAssets;
import org.vidhyaratha.employeeassetmanagement.repository.AssetRepository;
import org.vidhyaratha.employeeassetmanagement.repository.EmployeeAssetsRepository;
import org.vidhyaratha.employeeassetmanagement.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class EmployeeAssetsServiceImpl implements EmployeeAssetsService {

    private final AssetRepository assetRepository;

    private final UserRepository userRepository;

    private final EmployeeAssetsRepository employeeAssetsRepository;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeAssetsServiceImpl.class);

    @Autowired
    public EmployeeAssetsServiceImpl(AssetRepository assetRepository, UserRepository userRepository, EmployeeAssetsRepository employeeAssetsRepository) {
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
        this.employeeAssetsRepository = employeeAssetsRepository;
    }


    @Override
    public List<AssetDTO> getAssetsByEmployeeId(String empId) {
        User user = userRepository.findUserByEmpId(empId);


        List<EmployeeAssets> employeeAssets = employeeAssetsRepository.findByUser(user);
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
            logger.info("Retrieve asset information by employee id - success ");
            assetDTOList.add(assetDTO);
        }

        return assetDTOList;
    }


    @Override
    public void assignAsset(String empId, String assetId)  {
        User user = userRepository.findUserByEmpId(empId);
        Asset asset = assetRepository.findByAssetId(assetId);

            List<EmployeeAssets> employeeAssetsList = employeeAssetsRepository.findByUser(user);

            if (employeeAssetsList.size() < 5 && assetId != null) {
                EmployeeAssets employeeAssets = new EmployeeAssets();
                employeeAssets.setAssetAssignedDate(LocalDate.now().toString());
                employeeAssets.setApprovedAdminName("Smith_ADMIN");

                employeeAssets.setUser(user);
                employeeAssets.setAsset(asset);
                logger.info("Assign a new device to the employee - success");
                employeeAssetsRepository.save(employeeAssets);
            } else {
                asset.setStatus("Unassigned");
                assetRepository.save(asset);
                logger.info("Trying to exceed the device limitation that is set to 5");
                throw new DeviceExceededLimitationException("Trying to exceed the device limitation that is set to 5. Please contact Admin");
            }
    }




    @Override
    public void deleteByAssetId(String assetId) {

        Asset asset = assetRepository.findByAssetId(assetId);
        EmployeeAssets employeeAsset = employeeAssetsRepository.findByAsset(asset);

        if (employeeAsset != null) {
            employeeAssetsRepository.deleteById(employeeAsset.getId());
            logger.info("Assest deleted sucessfully from employeeassest entity");
        }
    }


}
