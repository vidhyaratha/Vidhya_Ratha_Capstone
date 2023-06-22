package org.vidhyaratha.employeeassetmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.vidhyaratha.employeeassetmanagement.dto.AssetDTO;
import org.vidhyaratha.employeeassetmanagement.dto.EmployeeAssetsDTO;
import org.vidhyaratha.employeeassetmanagement.dto.EmployeeDTO;
import org.vidhyaratha.employeeassetmanagement.model.Asset;
import org.vidhyaratha.employeeassetmanagement.model.Employee;
import org.vidhyaratha.employeeassetmanagement.model.EmployeeAssets;
import org.vidhyaratha.employeeassetmanagement.repository.EmployeeAssetsRepository;
import org.vidhyaratha.employeeassetmanagement.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeAssetsServiceImpl implements EmployeeAssetsService{


    private final EmployeeRepository employeeRepository;

    private final EmployeeAssetsRepository employeeAssetsRepository;

    @Autowired
    public EmployeeAssetsServiceImpl(EmployeeRepository employeeRepository, EmployeeAssetsRepository employeeAssetsRepository) {
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
            //assetDTO.setId(asset.getId());
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
    public void assignAsset(String empId, Long assetId, String assetName) {
        EmployeeAssets employeeAssets = new EmployeeAssets();

        Employee employee = new Employee();
        employee.setEmpId(empId);
        employeeAssets.setEmployee(employee);

        Asset asset = new Asset();
        asset.setAssetId(assetId);
        asset.setAssetName(assetName);
        employeeAssets.setAsset(asset);

        employeeAssetsRepository.save(employeeAssets);

    }



    //    public void returnAsset(String empId, Long assetId)
//    {
//        EmployeeAssets employeeAsset = employeeAssetsRepository.findByEmployeeIdAndAssetId(empId,assetId);
//        if(employeeAsset != null)
//        {
//            employeeAssetsRepository.delete(employeeAsset);
//        }
//    }


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

//        for(EmployeeAssets listOfEmployeeAssets : employeeAssetsList)
//        {
//            EmployeeAssetsDTO employeeAssetsDTO = new EmployeeAssetsDTO();
//
//            employeeAssetsDTOList.add(employeeAssetsDTO);
//        }
//        return employeeAssetsDTOList;
   // }


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
