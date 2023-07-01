package org.vidhyaratha.employeeassetmanagement.controller;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Slf4j
@Controller
@SessionAttributes("userDTO")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(AssetController.class);

    @ModelAttribute("userDTO")
    public UserDTO setUpUser() {
        return new UserDTO();
    }

    @GetMapping("/requestDevice")
    public String requestDevice(@ModelAttribute("userDTO") UserDTO userDTO, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User existingEmployee = userService.findUserByEmail(username);
        List<String> assetTypeList = assetService.getAllAssetTypes();

        model.addAttribute("assetTypes", assetTypeList);
        model.addAttribute("employee",existingEmployee);
        logger.info("Redirect employee to request a new device");

        return "requestdevice";
    }

    @GetMapping("/addNewAsset")
    public String showAddAsset(Model model)
    {
        List<String> assetTypeList = assetService.getAllAssetTypes();
        AssetDTO assetDTO = new AssetDTO();
        model.addAttribute("assetTypes", assetTypeList);
        model.addAttribute("assetDTO",assetDTO);
        logger.info("Asset types displayed from Asset Entity ");


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
        logger.info("New assest created by admin user");
        return "addnewasset";

    }

}
