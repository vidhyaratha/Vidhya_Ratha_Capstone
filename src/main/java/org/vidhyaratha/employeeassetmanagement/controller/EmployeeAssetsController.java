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
import org.vidhyaratha.employeeassetmanagement.service.EmployeeAssetsService;
import org.vidhyaratha.employeeassetmanagement.service.EmployeeService;

import java.util.List;

@Controller
@SessionAttributes("employeeDTO")
public class EmployeeAssetsController {
//
    @Autowired
    private EmployeeAssetsService employeeAssetsService;
   // @Autowired
   // private EmployeeMasterService employeeMasterService;
    @Autowired
    private AssetService assetService;
    @Autowired
    private EmployeeService employeeService;
//
//
    @ModelAttribute("employeeDTO")
    public EmployeeDTO setUpEmployee()
    {
        return new EmployeeDTO();
    }
//
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


@GetMapping("/getEmployeeAssets/{employeeId}")
public String getEmployeeAssets(@PathVariable String employeeId, Model model,@ModelAttribute("employee") EmployeeDTO employeeDTO)
        {
        Employee existingEmployee = employeeService.findEmployeeByEmpId(employeeId);
     //   EmployeeMaster masterEmployee = employeeMasterService.getEmployeeByEmpId(employeeId);

        List<AssetDTO> employeeAssets = employeeAssetsService.getAssetsByEmployeeId(employeeId);
        model.addAttribute("employeeAssets",employeeAssets);
        model.addAttribute("employee",existingEmployee);
      //  model.addAttribute("employeeMaster",masterEmployee);

        return "userpage";
        }




    @GetMapping("/showDevice")
    public String showAssignedDevice(@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO)
    {
        return "redirect:/getEmployeeAssets/"+ employeeDTO.getEmpId();
    }




    @PostMapping("/processRequestDevice")
    public String processRequestDevice(@RequestParam("selectedType") String selectedAssetType,
                                       @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO, Model model)
    {
        Asset asset = assetService.assignAssetToAssignedStatus(selectedAssetType, "Unassigned");
        if(asset.getAssetType()==null)
        {
            throw new RuntimeException("Please contact Admin");

        }

        employeeAssetsService.assignAsset(employeeDTO.getEmpId(), asset.getAssetId());
        return "redirect:/getEmployeeAssets/" + employeeDTO.getEmpId();
    }


    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException ex,@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO ,Model model)
    {
        model.addAttribute("errorMessage", ex.getMessage());
        model.addAttribute("employeeDTO0", employeeDTO);
        return "error";
    }






    @GetMapping("/returnDevice")
    public String showReturnDevice(@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO)
    {
        return "redirect:/"+ employeeDTO.getEmpId() +"/returnDevice";
    }







    @GetMapping("/{employeeId}/returnDevice")
    public String returnDevice(@PathVariable String employeeId, Model model)
    {
        Employee existingEmployee = employeeService.findEmployeeByEmpId(employeeId);
        List<AssetDTO> employeeAssets = employeeAssetsService.getAssetsByEmployeeId(employeeId);
      //  EmployeeMaster masterEmployee = employeeMasterService.getEmployeeByEmpId(employeeId);

        model.addAttribute("employeeAssets",employeeAssets);
        model.addAttribute("employee",existingEmployee);
       // model.addAttribute("employeeMaster",masterEmployee);

        return "returnDevice";
    }




    // To update the status of the asset
    @PostMapping("/processReturnDevice")
    public String processReturnDevice(@RequestParam("selectedAsset") String selectedAssetId,
                                      @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO)
    {
        assetService.updateAssetStatus(selectedAssetId,"Unassigned");

        employeeAssetsService.deleteByAssetId(selectedAssetId);

        return "redirect:/getEmployeeAssets/" + employeeDTO.getEmpId();
    }



}