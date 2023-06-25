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
import java.util.Optional;


@Service
public class EmployeeAssetsServiceImpl implements EmployeeAssetsService{

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
               // .orElseThrow(() -> new NoSuchElementException("Employee not found"));

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
        if(assetId != null) {
            EmployeeAssets employeeAssets = new EmployeeAssets();
            // employeeAssets.setId();
            //Random random = new Random();
            // employeeAssets.setId(random.nextLong());
            employeeAssets.setAssetAssignedDate(LocalDate.now().toString());
            employeeAssets.setApprovedAdminName("John_ADMIN");
            Employee employee = employeeRepository.findEmployeeByEmpId(empId);
            Asset asset = assetRepository.findByAssetId(assetId);

            employeeAssets.setEmployee(employee);
            employeeAssets.setAsset(asset);

//        Employee employee = new Employee();
//        employee.setEmpId(empId);
//        employeeAssets.setEmployee(employee);
//
//        Asset asset = new Asset();
//        asset.setAssetId(assetId);
//        asset.setAssetName(assetName);
//        employeeAssets.setAsset(asset);

            employeeAssetsRepository.save(employeeAssets);
        }

    }

    @Override
    public void deleteByAssetId(String assetId) {
           // EmployeeAssets employeeAsset = employeeAssetsRepository.findByAssetId(assetId);
             Asset asset = assetRepository.findByAssetId(assetId);
            EmployeeAssets employeeAsset = employeeAssetsRepository.findByAsset(asset);

            if(employeeAsset != null)
            {
               employeeAssetsRepository.deleteById(employeeAsset.getId());
            }
    }


//    @Override
//    public Employee findEmployeeByEmail(String email) {
//        return employeeAssetsRepository.findemployeeByEmail(email);
//    }


//    @Override
//    public List<EmployeeAssetsDTO> findAllAssetsByEmpId(String empId) {
//        List<EmployeeAssets> employeeAssetsList =  employeeAssetsRepository.findAllAssetsByEmpId(empId);
//       return employeeAssetsList.stream()
//                .map((assetList) -> mapToEmployeeDTO(assetList))
//                .collect(Collectors.toList());
//
//        for(EmployeeAssets listOfEmployeeAssets : employeeAssetsList)
//        {
//            EmployeeAssetsDTO employeeAssetsDTO = new EmployeeAssetsDTO();
//
//            employeeAssetsDTOList.add(employeeAssetsDTO);
//        }
//        return employeeAssetsDTOList;
//    }


//     private EmployeeAssetsDTO mapToEmployeeDTO(EmployeeAssets employeeAssets)
//    {
//        EmployeeAssetsDTO employeeAssetsDTO = new EmployeeAssetsDTO();
//
//
//        employeeAssetsDTO.setAssetId(employeeAssets.getAsset().getAssetId());
//        employeeAssetsDTO.setEmpId(employeeAssets.getEmployee().getEmpId());
//        //employeeAssetsDTO.setAssetName(employeeAssets.getAssetName());
//       // employeeAssetsDTO.setAssetType(employeeAssets.getAssetType());
//        System.out.println(employeeAssets.getAsset().getAssetId());
//        return employeeAssetsDTO;
//    }


    //Used while creating a request for new asset/device
//    public EmployeeAssets toEntity(EmployeeAssetsDTO employeeAssetsDTO)
//    {
//        EmployeeAssets employeeAssets = new EmployeeAssets();
//        employeeAssets.setId(employeeAssetsDTO.getId());
//
//        Employee employee = new Employee();
//        employee.setEmpId(employeeAssetsDTO.getEmpId());
//        employeeAssets.setEmployee(employee);
//
//        Asset asset = new Asset();
//        asset.setAssetId(employeeAssetsDTO.getAssetId());
//        employeeAssets.setAsset(asset);
//
//        return employeeAssets;
//    }













//    @GetMapping("/userpage")
//    public String abc(Model model)
//    {
//        EmployeeAssetsDTO employeeAssetsDTO = new EmployeeAssetsDTO();
//        model.addAttribute("employeeAssetsDTO" , employeeAssetsDTO);
//        return "userpage";
//    }


}
