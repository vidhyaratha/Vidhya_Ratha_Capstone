package org.vidhyaratha.employeeassetmanagement.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.Path;
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
import org.vidhyaratha.employeeassetmanagement.service.EmployeeAssetsService;
import org.vidhyaratha.employeeassetmanagement.service.UserService;

import java.util.List;

@Controller
@SessionAttributes("userDTO")
public class EmployeeAssetsController {


    @Autowired
    private EmployeeAssetsService employeeAssetsService;

    @Autowired
    private AssetService assetService;
    @Autowired
    private UserService userService;


    @ModelAttribute("userDTO")
    public UserDTO setUpEmployee()
    {
        return new UserDTO();
    }

//    @DeleteMapping("/deleteDevice/{assetId}")
//    public String deleteEmployeeById(@PathVariable("assetId") String assetId,
//                                   @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO)
//    {
//        employeeAssetsService.deleteAssetById(assetId);
//        assetService.updateAssetStatus(assetId,"unassigned");
//        return "userpage";
//    }
//
//
//
//
//
//
//////    @GetMapping("/getEmployeeAssets")
//////    public String getEmployeeAssets(@Valid @ModelAttribute("employeeAssetsDTO") EmployeeAssetsDTO employeeAssetsDTO,
//////                                    String empId, BindingResult result, Model model) {
//////        List<EmployeeAssetsDTO> employeeAssetsDto = employeeAssetsService.findAllAssetsByEmpId("EID123");
//////        model.addAttribute("employeeAssets",employeeAssetsDto);
//////
//////        return "success";
//////    }
////


@RequestMapping("/getEmployeeAssets")
public String getEmployeeAssets(Model model,
                                @ModelAttribute("userDTO") UserDTO userDTO)
        {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();


        //User existingEmployee = userService.findUserByEmpId(userDTO.getEmpId());
            User existingEmployee = userService.findUserByEmail(username);

        List<AssetDTO> employeeAssets = employeeAssetsService.getAssetsByEmployeeId(existingEmployee.getEmpId());
        model.addAttribute("employeeAssets",employeeAssets);
        model.addAttribute("employee",existingEmployee);


        return "userpage";
        }




    @GetMapping("/showDevice")
    public String showAssignedDevice(@ModelAttribute("userDTO") UserDTO userDTO)
    {
        return "redirect:/getEmployeeAssets";
    }




    @PostMapping("/processRequestDevice")
    public String processRequestDevice(@RequestParam("selectedType") String selectedAssetType,
                                       @ModelAttribute("userDTO") UserDTO userDTO, Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User existingEmployee = userService.findUserByEmail(username);

        Asset asset = assetService.assignAssetToAssignedStatus(selectedAssetType, "Unassigned");
        if(asset.getAssetType()==null)
        {
            throw new RuntimeException("Please contact Admin");

        }

        employeeAssetsService.assignAsset(existingEmployee.getEmpId(), asset.getAssetId());
        return "redirect:/getEmployeeAssets" ;
    }


    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException ex,@ModelAttribute("userDTO") UserDTO userDTO ,Model model)
    {
        model.addAttribute("errorMessage", ex.getMessage());
        model.addAttribute("userDTO", userDTO);
        return "error";
    }





//
//    @GetMapping("/returnDevice")
//    public String showReturnDevice(@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO)
//    {
//        return "redirect:/returnDevice";
//    }







    @GetMapping("/returnDevice")
    public String returnDevice(@ModelAttribute("userDTO") UserDTO userDTO, Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User existingEmployee = userService.findUserByEmail(username);
        List<AssetDTO> employeeAssets = employeeAssetsService.getAssetsByEmployeeId(existingEmployee.getEmpId());

        model.addAttribute("employeeAssets",employeeAssets);
        model.addAttribute("employee",existingEmployee);

        return "returnDevice";
    }




    // To update the status of the asset
    @PostMapping("/processReturnDevice")
    public String processReturnDevice(@RequestParam("selectedAsset") String selectedAssetId,
                                      @ModelAttribute("userDTO") UserDTO userDTO)
    {
        assetService.updateAssetStatus(selectedAssetId,"Unassigned");

        employeeAssetsService.deleteByAssetId(selectedAssetId);

        return "redirect:/getEmployeeAssets" ;
    }



}