//package org.vidhyaratha.employeeassetmanagement.controller;
//
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import org.vidhyaratha.employeeassetmanagement.dto.EmployeeAssetsDTO;
//import org.vidhyaratha.employeeassetmanagement.dto.EmployeeDTO;
//import org.vidhyaratha.employeeassetmanagement.model.Employee;
//import org.vidhyaratha.employeeassetmanagement.model.EmployeeAssets;
//import org.vidhyaratha.employeeassetmanagement.service.AssetService;
//import org.vidhyaratha.employeeassetmanagement.service.EmployeeAssetsService;
//import org.vidhyaratha.employeeassetmanagement.service.EmployeeService;
//
//import java.util.List;
//
//@Controller
//@SessionAttributes("employeeDTO")
//public class EmployeeAssetsController {
//
//    @Autowired
//    private EmployeeAssetsService employeeAssetsService;
//    @Autowired
//    private EmployeeService employeeService;
//    @Autowired
//    private AssetService assetService;
//
//
//    @ModelAttribute("employeeDTO")
//    public EmployeeAssetsDTO setUpEmployee()
//    {
//        return new EmployeeAssetsDTO();
//    }
//
//    @DeleteMapping("/deleteDevice/{assetId}")
//    public String deleteEmployeeById(@PathVariable("assetId") Long assetId,
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
//}