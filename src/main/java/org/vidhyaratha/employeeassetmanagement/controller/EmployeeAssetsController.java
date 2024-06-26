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
import org.vidhyaratha.employeeassetmanagement.Exception.AssetNotFoundException;
import org.vidhyaratha.employeeassetmanagement.Exception.UserNotFoundException;
import org.vidhyaratha.employeeassetmanagement.dto.AssetDTO;
import org.vidhyaratha.employeeassetmanagement.dto.UserDTO;
import org.vidhyaratha.employeeassetmanagement.model.Asset;
import org.vidhyaratha.employeeassetmanagement.model.User;
import org.vidhyaratha.employeeassetmanagement.service.AssetService;
import org.vidhyaratha.employeeassetmanagement.service.EmployeeAssetsService;
import org.vidhyaratha.employeeassetmanagement.service.UserService;

import java.util.List;

@Slf4j
@Controller
@SessionAttributes("userDTO")
public class EmployeeAssetsController {


    @Autowired
    private EmployeeAssetsService employeeAssetsService;

    @Autowired
    private AssetService assetService;
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeAssetsController.class);

    @ModelAttribute("userDTO")
    public UserDTO setUpEmployee() {
        return new UserDTO();
    }

    //Get request handler method to retrieve the list of assets assigned to the employee
    @RequestMapping("/getEmployeeAssets")
    public String getEmployeeAssets(Model model,
                                    @ModelAttribute("userDTO") UserDTO userDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User existingEmployee = userService.findUserByEmail(username);

        if (existingEmployee == null) {
            throw new UserNotFoundException("Please contact Admin  at admin@admin.org");
        }

        List<AssetDTO> employeeAssets = employeeAssetsService.getAssetsByEmployeeId(existingEmployee.getEmpId());
        model.addAttribute("employeeAssets", employeeAssets);
        model.addAttribute("employee", existingEmployee);
        logger.info("Employee details page Displayed");
        return "userpage";
    }

    // Get request handler method to redirect to the userpage
    @GetMapping("/showDevice")
    public String showAssignedDevice(@ModelAttribute("userDTO") UserDTO userDTO) {
        return "redirect:/getEmployeeAssets";
    }

    // Post request handler method to request for a new device
    @PostMapping("/processRequestDevice")
    public String processRequestDevice(@RequestParam("selectedType") String selectedAssetType,
                                       @ModelAttribute("userDTO") UserDTO userDTO, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User existingEmployee = userService.findUserByEmail(username);

        if (existingEmployee == null) {
            throw new UserNotFoundException("Please contact Admin  at admin@admin.org");
        }

        Asset asset = assetService.assignAssetToAssignedStatus(selectedAssetType, "Unassigned");
        if (asset.getAssetType() == null) {
            logger.info("No assets found for selected type in unassigned status " + selectedAssetType);
            throw new AssetNotFoundException("No assets found for selected type in unassigned status " + selectedAssetType + "Please contact Admin");

        }

        employeeAssetsService.assignAsset(existingEmployee.getEmpId(), asset.getAssetId());
        logger.info("Process request for new device request");
        return "redirect:/getEmployeeAssets";
    }

    // Get request handler method to return the device
    @GetMapping("/returnDevice")
    public String returnDevice(@ModelAttribute("userDTO") UserDTO userDTO, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User existingEmployee = userService.findUserByEmail(username);

        if (existingEmployee == null) {
            throw new UserNotFoundException("Please contact Admin  at admin@admin.org");
        }

        List<AssetDTO> employeeAssets = employeeAssetsService.getAssetsByEmployeeId(existingEmployee.getEmpId());


        model.addAttribute("employeeAssets", employeeAssets);
        model.addAttribute("employee", existingEmployee);

        return "returnDevice";
    }


    // Post request handler method to update the status of the asset
    @PostMapping("/processReturnDevice")
    public String processReturnDevice(@RequestParam("selectedAsset") String selectedAssetId, @RequestParam("issue") boolean issue,
                                      @ModelAttribute("userDTO") UserDTO userDTO) {
        if (issue) {
            assetService.updateAssetStatus(selectedAssetId, "Inactive");
        } else {
            assetService.updateAssetStatus(selectedAssetId, "Unassigned");
        }

        employeeAssetsService.deleteByAssetId(selectedAssetId);

        return "redirect:/getEmployeeAssets";
    }


}