package org.vidhyaratha.employeeassetmanagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.vidhyaratha.employeeassetmanagement.dto.AssetDTO;
import org.vidhyaratha.employeeassetmanagement.dto.UserDTO;
import org.vidhyaratha.employeeassetmanagement.model.Asset;
import org.vidhyaratha.employeeassetmanagement.model.User;
import org.vidhyaratha.employeeassetmanagement.service.AssetService;
import org.vidhyaratha.employeeassetmanagement.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Controller
@SessionAttributes("userDTO")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @Autowired
    private UserService userService;

    @ModelAttribute("userDTO")
    public UserDTO setUpUser() {
        return new UserDTO();
    }


//    @GetMapping("/requestDevice")
//    public String showRequestDevice(@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO) {
//        return "redirect:/" + employeeDTO.getEmpId() + "/requestDevice";
//    }


    @GetMapping("/requestDevice")
    public String requestDevice(@ModelAttribute("userDTO") UserDTO userDTO, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User existingEmployee = userService.findUserByEmail(username);
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
