package org.vidhyaratha.employeeassetmanagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.vidhyaratha.employeeassetmanagement.dto.AssetDTO;
import org.vidhyaratha.employeeassetmanagement.dto.EmployeeDTO;
import org.vidhyaratha.employeeassetmanagement.model.Asset;
import org.vidhyaratha.employeeassetmanagement.model.Employee;
import org.vidhyaratha.employeeassetmanagement.service.AssetService;
import org.vidhyaratha.employeeassetmanagement.service.EmployeeService;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Controller
@SessionAttributes("employeeDTO")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @Autowired
    private EmployeeService employeeService;

    @ModelAttribute("employeeDTO")
    public EmployeeDTO setUpEmployee() {
        return new EmployeeDTO();
    }


//    @GetMapping("/requestDevice")
//    public String showRequestDevice(@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO) {
//        return "redirect:/" + employeeDTO.getEmpId() + "/requestDevice";
//    }


    @GetMapping("/requestDevice")
    public String requestDevice(@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO, Model model) {

        Employee existingEmployee = employeeService.findEmployeeByEmpId(employeeDTO.getEmpId());
        List<String> assetTypeList = assetService.getAllAssetTypes();

        model.addAttribute("assetTypes", assetTypeList);
        model.addAttribute("employee",existingEmployee);

        return "requestdevice";
    }

    @GetMapping("/addNewAsset")
    public String showAddAsset(Model model)
    {
        List<String> assetTypeList = assetService.getAllAssetTypes();
        AssetDTO assetDTO = new AssetDTO();
        model.addAttribute("assetTypes", assetTypeList);
        model.addAttribute("assetDTO",assetDTO);


        return "addnewasset";
    }


    @PostMapping("/processAddNewAsset")
    public String addNewAsset(@RequestParam("selectedType") String selectedType,
                              @ModelAttribute("assetDTO") AssetDTO assetDTO, Model model)
    {
        Random random = new Random();
        Asset asset = new Asset();
        asset.setId(random.nextLong());
        asset.setAssetId("AID"+ random.nextInt());
        asset.setAssetName(assetDTO.getAssetName());
        asset.setAssetType(selectedType);
        asset.setAssetCreatedDate(LocalDate.now().toString());
        asset.setStatus("Unassigned");
        assetService.addAsset(asset);

        assetDTO.setAssetName("");
        List<String> assetTypeList = assetService.getAllAssetTypes();
        model.addAttribute("assetTypes", assetTypeList);

        model.addAttribute("successMessage","New Asset created");
        return "addnewasset";

    }

}
