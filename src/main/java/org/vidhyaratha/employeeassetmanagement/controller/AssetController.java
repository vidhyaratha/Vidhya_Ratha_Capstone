package org.vidhyaratha.employeeassetmanagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.vidhyaratha.employeeassetmanagement.dto.EmployeeDTO;
import org.vidhyaratha.employeeassetmanagement.model.EmployeeMaster;
import org.vidhyaratha.employeeassetmanagement.service.AssetService;
import org.vidhyaratha.employeeassetmanagement.service.EmployeeMasterService;

import java.util.List;

@Controller
@SessionAttributes("employeeDTO")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @Autowired
    private EmployeeMasterService employeeMasterService;

    @ModelAttribute("employeeDTO")
    public EmployeeDTO setUpEmployee() {
        return new EmployeeDTO();
    }


    @GetMapping("/requestDevice")
    public String showRequestDevice(@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO) {
        return "redirect:/" + employeeDTO.getEmpId() + "/requestDevice";
    }


    @GetMapping("/{employeeId}/requestDevice")
    public String requestDevice(@PathVariable String employeeId, Model model) {

        List<String> assetTypeList = assetService.getAllAssetTypes();
        EmployeeMaster masterEmployee = employeeMasterService.getEmployeeByEmpId(employeeId);

        model.addAttribute("assetTypes", assetTypeList);
        model.addAttribute("employeeMaster", masterEmployee);

        return "requestdevice";
    }

}
